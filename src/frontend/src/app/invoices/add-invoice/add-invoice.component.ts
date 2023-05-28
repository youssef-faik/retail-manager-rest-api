import {Component, OnInit} from '@angular/core';
import {
  ClientService,
  CustomerResponseDto,
  FactureService,
  InvoiceCreateDto,
  InvoiceDto,
  InvoiceItemDto,
  ProductResponseDto,
  ProduitService
} from "../../../../libs/openapi/out";
import {HttpResponse} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {NgxQrcodeElementTypes, NgxQrcodeErrorCorrectionLevels} from "@techiediaries/ngx-qrcode";
import {CartItemService} from "../../services/cartItem.service";
import {ICartItem} from "../../services/cartItem.model";
import {MovingDirection} from "angular-archwizard";

@Component({
  selector: 'app-add-invoice',
  templateUrl: './add-invoice.component.html',
  styleUrls: ['./add-invoice.component.scss']
})
export class AddInvoiceComponent implements OnInit {
  items: InvoiceItemDto[] = [];
  customers: CustomerResponseDto[] = [];
  products: ProductResponseDto[] = [];
  cart: any[] = [];
  taxPercentage = '-';
  amount: number = 0;
  total: number = 0;
  totalExcludingTaxes: number = 0;
  selectedCustomerIce: string;
  selectedProductId: string;
  selectedCustomer: CustomerResponseDto;
  selectedProduct: ProductResponseDto;
  selectedUnitPrice: number | undefined = 0;
  item: InvoiceItemDto = {productId: 0, quantity: 1, unitPrice: 0};

  savedInvoice: InvoiceDto;

  totalIncluVTA: number = 0;
  totalExcluVTA: number = 0;

  cartId: string = (Math.floor(Math.random() * 9000000) + 1000000).toString();
  displayScannerButton: boolean = false;

  elementType: NgxQrcodeElementTypes = NgxQrcodeElementTypes.URL;
  errorCorrectionLevel: NgxQrcodeErrorCorrectionLevels = NgxQrcodeErrorCorrectionLevels.LOW;

  constructor(
    private customerService: ClientService,
    private productService: ProduitService,
    private invoiceService: FactureService,
    private route: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal,
    private cartItemService: CartItemService
  ) {
  }

  ngOnInit(): void {
    this.loadCustomers();
    this.loadProducts();

    // @ts-ignore
    this.cartItemService.cartId = this.cartId;
    this.cartItemService.updateEvents.subscribe(
      data => {
        console.log('updateEvents')
        console.log(data)
        this.handleAddEvent(data.addedCartItem);
      }
    );
  }

  addItem() {
    // @ts-ignore
    this.total += this.getSellingPriceIncludingTaxes(this.selectedProduct.taxRate, this.item.unitPrice) * this.item.quantity;
    this.totalExcludingTaxes += this.item.unitPrice * this.item.quantity;
    this.items.push(this.item);
    this.cart.push(this.selectedProduct);
    this.products = this.products.filter(item => item.id !== this.selectedProduct.id);

    // @ts-ignore
    this.selectedProductId = undefined;
    this.item = {productId: 0, quantity: 1, unitPrice: 0};
    this.amount = 0;
    this.selectedUnitPrice = 0;
    this.taxPercentage = '-';
  }

  removeItem(item: InvoiceItemDto) {
    const index = this.items.indexOf(item);
    if (index > -1) {
      this.items.splice(index, 1);
    }

    let productToRemove = this.cart.filter(product => {
      return product.id == item.productId;
    })[0];

    const indexInCart = this.cart.indexOf(productToRemove);
    if (indexInCart > -1) {
      this.cart.splice(index, 1);
    }

    this.total -= this.getSellingPriceIncludingTaxes(productToRemove.taxRate, item.unitPrice) * item.quantity;
    this.totalExcludingTaxes -= item.unitPrice * item.quantity;

    this.products = [...this.products, productToRemove];
  }

  onChangeProduct() {
    if (this.selectedProductId) {
      this.selectedProduct = this.products.filter(product => {
        return product.id == +this.selectedProductId
      })[0];

      this.item = {
        // @ts-ignore
        productId: this.selectedProduct.id,
        quantity: 1,
        // @ts-ignore
        unitPrice: this.selectedProduct.sellingPriceExcludingTax
      };

      this.selectedUnitPrice = this.selectedProduct.sellingPriceExcludingTax;
      this.onChangeUnitPriceAndQuantity();
      this.taxPercentage = this.getTaxRateDisplayValue(this.selectedProduct?.taxRate);
    } else {
      this.item = {productId: 0, quantity: 1, unitPrice: 1}
      this.selectedProduct = {};
      this.taxPercentage = '-';
      this.selectedUnitPrice = 0;
      this.amount = 0;
    }
  }

  onChangeCustomer() {
    if (this.selectedCustomerIce) {
      this.selectedCustomer = this.customers.filter(customer => {
        return customer.ice == this.selectedCustomerIce
      })[0];
    }
  }

  customSearchFn(term: string, item: any) {
    term = term.toLowerCase();

    // Creating and array of space separated term and removing the empty values using filter
    let splitTerm = term.split(' ').filter(t => t);

    // @ts-ignore
    let isWordThere: boolean[] = [];

    // Pushing True/False if match is found
    splitTerm.forEach(arr_term => {
      let search = item['search_label'].toLowerCase();
      isWordThere.push(search.indexOf(arr_term) != -1);
    });

    const all_words = (this_word: any) => this_word;
    // Every method will return true if all values are true in isWordThere.
    return isWordThere.every(all_words);
  }

  getProductById(id: number): ProductResponseDto {
    let filterElement1 = this.products.filter(product => {
      return product.id === id
    });

    let filterElement2 = this.cart.filter(product => {
      return product.id === id
    });

    return filterElement1.length > 0 ? filterElement1[0] : filterElement2[0];
  }

  getTaxRateDisplayValue(taxRate: ProductResponseDto.TaxRateEnum | undefined): string {
    switch (taxRate) {
      case ProductResponseDto.TaxRateEnum.Twenty:
        return '20%';
      case ProductResponseDto.TaxRateEnum.Fourteen:
        return '14%';
      case ProductResponseDto.TaxRateEnum.Ten:
        return '10%';
      case ProductResponseDto.TaxRateEnum.Seven:
        return '7%';
      default:
        return '';
    }
  }

  getSellingPriceIncludingTaxes(
    taxRate: ProductResponseDto.TaxRateEnum | undefined,
    sellingPriceExcludingTaxes: number | undefined
  ): number {
    let taxRatePercentage;

    if (taxRate === ProductResponseDto.TaxRateEnum.Twenty) {
      taxRatePercentage = 0.2;
    } else if (taxRate === ProductResponseDto.TaxRateEnum.Fourteen) {
      taxRatePercentage = 0.14;
    } else if (taxRate === ProductResponseDto.TaxRateEnum.Ten) {
      taxRatePercentage = 0.1;
    } else if (taxRate === ProductResponseDto.TaxRateEnum.Seven) {
      taxRatePercentage = 0.07;
    } else {
      taxRatePercentage = 0;
    }

    // @ts-ignore
    return sellingPriceExcludingTaxes * (1 + taxRatePercentage);
  }

  getItemSellingPriceIncludingTaxes(item: InvoiceItemDto): number {
    return this.getSellingPriceIncludingTaxes(this.getProductById(item.productId).taxRate, item.unitPrice) * item.quantity;
  }

  saveOldValue(event: KeyboardEvent) {
    const inputElement = event.target as HTMLInputElement;
    if (!inputElement.value || (parseInt(inputElement.value) <= 100 && parseInt(inputElement.value) >= 1)) {
      inputElement.dataset['old'] = inputElement.value;
    }
  }

  checkValue(event: KeyboardEvent) {
    const inputElement = event.target as HTMLInputElement;
    if (!inputElement.value || (parseInt(inputElement.value) <= 100 && parseInt(inputElement.value) >= 1)) {
      // Value is correct.
      this.onChangeUnitPriceAndQuantity();
    } else {
      // @ts-ignore
      inputElement.value = inputElement.dataset['old'];
    }
  }

  saveOldUnitPriceValue(event: KeyboardEvent) {
    const inputElement = event.target as HTMLInputElement;
    if (!inputElement.value || (parseInt(inputElement.value) <= 100000 && parseInt(inputElement.value) >= 1)) {
      inputElement.dataset['old'] = inputElement.value;
    }
  }

  checkUnitPriceValue(event: KeyboardEvent) {
    const inputElement = event.target as HTMLInputElement;
    if (!inputElement.value || (parseInt(inputElement.value) <= 100000 && parseInt(inputElement.value) >= 1)) {
      // Value is correct.
      this.onChangeUnitPriceAndQuantity();
    } else {
      // @ts-ignore
      inputElement.value = inputElement.dataset['old'];
    }
  }

  onChangeUnitPriceAndQuantity() {
    let taxRate = this.selectedProduct.taxRate;
    let sellingPriceIncludingTaxes = this.getSellingPriceIncludingTaxes(taxRate, this.item.unitPrice);
    this.amount = this.item.quantity * sellingPriceIncludingTaxes;
  }

  onSave() {
    // @ts-ignore
    let invoiceCreateDto: InvoiceCreateDto = {customerICE: this.selectedCustomerIce, items: this.items};

    this.invoiceService.createInvoice(
      // @ts-ignore
      invoiceCreateDto,
      'response',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe((response: HttpResponse<any>) => {
      // Access the Location header value
      const locationHeader: string = response.headers.get('Location') || '/api/v1/invoices/0';
      console.log('Location header value:', locationHeader);

      const originalString = locationHeader;
      const prefix = '/api/v1/invoices/';

      const strippedId = originalString.replace(prefix, '');
      console.log(strippedId);


      this.invoiceService.getInvoice(+strippedId)
        .subscribe(
          data => {
            this.savedInvoice = data;

            // @ts-ignore
            for (let item of this.savedInvoice.items) {
              // @ts-ignore
              this.totalExcluVTA += item.quantity * item.unitPrice;

              // @ts-ignore
              this.totalIncluVTA += item.quantity * this.getSellingPriceIncludingTaxes(item.product?.taxRate, item.unitPrice);
            }
          }
          , error => {
            console.log(error)
          }
        )

    }, error => {
      console.log(error)
    })
  }

  public convertToPDF() {
    html2canvas(document.getElementById('invoice')!).then(canvas => {
      // Few necessary setting options
      const contentDataURL = canvas.toDataURL('image/png')

      // A4 size page of PDF
      // @ts-ignore
      let pdf = new jsPDF('p', 'mm', 'a4');
      var width = pdf.internal.pageSize.getWidth();
      var height = canvas.height * width / canvas.width;
      pdf.addImage(contentDataURL, 'PNG', 0, 0, width, height)

      // Generated PDF
      pdf.save(`invoice-${this.savedInvoice.id}.pdf`);
    });
  }

  getDisplayDate(dateStr: string | undefined) {
    // @ts-ignore
    const date = new Date(dateStr);
    const options: Intl.DateTimeFormatOptions = {
      day: 'numeric',
      month: 'long',
      year: 'numeric'
    };

    return date.toLocaleDateString('en-GB', options);
  }

  loadCustomers() {
    this.customerService.getAllCustomers(
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data: any) => {
        // @ts-ignore
        this.customers = data.map(customer => {
          customer.search_label =
            `${customer.id} ${customer.name} ${customer.email} ${customer.phone} ${customer.address}`
          return customer
        });
      }, error => {
        console.log(error)
      })
  }

  loadProducts() {
    this.productService.getAllProducts(
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data: any) => {
        // @ts-ignore
        this.products = data.map((product) => {
          // @ts-ignore
          let sellingPriceIncludingTaxes = this.getSellingPriceIncludingTaxes(product.taxRate, product.sellingPriceExcludingTax);
          let taxRateDisplayValue = this.getTaxRateDisplayValue(product.taxRate);
          product.search_label = `${product.id} ${product.name} ${product.barCode} ${sellingPriceIncludingTaxes} ${product.sellingPriceExcludingTax} ${taxRateDisplayValue}`
          return product
        });
      }, error => {
        console.log(error)
      }
    )
  }

  openGenerateQRCodeModal(content: any) {
    this.modalService.open(content, {centered: true}).result.then(
      (result) => {
      },
      (reason) => {
        // Handle modal dismissal (e.g., cancel button clicked)
        console.log(`Modal dismissed with reason: ${reason}`);
      }
    );
  }


  handleAddEvent(addedCartItem: ICartItem): void {
    console.log('updateEvents')
    console.log(addedCartItem)
    this.cartItemService.getCartItem(addedCartItem.id).subscribe(
      data => {
        let itemsFilter = this.items.filter(item => {
          return item.productId == data.productId;
        });

        if (itemsFilter.length > 0) {
          itemsFilter[0].quantity += 1;
          return
        }

        if (this.products.length == 0) {
          return;
        }

        this.selectedProductId = data.productId;
        this.onChangeProduct();
        this.addItem();
      }, error => {
        console.log(error)
      }
    );
  }

  enterExitStepToggle($event: MovingDirection) {
    this.displayScannerButton = !this.displayScannerButton;
  }

}
