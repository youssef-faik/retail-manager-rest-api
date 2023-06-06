import {Component, OnInit, ViewChild} from '@angular/core';
import {ProductResponseDto, ProduitService} from "../../../libs/openapi/out";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ColumnMode, DatatableComponent} from "@swimlane/ngx-datatable";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  products: ProductResponseDto[];
  temp: ProductResponseDto[] = [];
  recordIdToDelete?: number;
  loadingIndicator = true;
  reorderable = true;
  ColumnMode = ColumnMode;
  @ViewChild(DatatableComponent) table: DatatableComponent;

  constructor(
    private modalService: NgbModal,
    private productService: ProduitService
  ) {
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  openConfirmationModal(content: any, recordId?: number) {
    this.recordIdToDelete = recordId;
    this.modalService.open(content, {centered: true}).result.then(
      (result) => {
        if (result === 'confirm') {
          this.productService.deleteProduct(
            recordId || 0,
            'body',
            false,
            {httpHeaderAccept: 'application/json'}
          ).subscribe(
            data => {
              this.loadProducts();
            },
            error => {
              console.log(error);
            }
          )
        }
      },
      (reason) => {
        // Handle modal dismissal (e.g., cancel button clicked)
        console.log(`Modal dismissed with reason: ${reason}`);
      }
    );
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

  updateFilter(event: KeyboardEvent) {
    // @ts-ignore
    const val = event.target.value.toLowerCase();

    // filter our data
    const temp = this.temp.filter(function (product) {
      // @ts-ignore
      return product.name.toLowerCase().indexOf(val) !== -1 || !val;
    });

    // update the rows
    this.products = temp;
    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }

  private loadProducts() {
    this.productService.getAllProducts(
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        // cache our list
        this.temp = [...data];

        // push our inital complete list
        this.products = data;

        setTimeout(() => {
          this.loadingIndicator = false;
        }, 1500);
      }, error => {
        console.log(error)
      }
    );
  }
}
