import {Component, OnInit, ViewChild} from '@angular/core';
import {FactureService, InvoiceDto, InvoiceItemResponseDto, ProductResponseDto} from "../../../libs/openapi/out";
import {ColumnMode, DatatableComponent} from "@swimlane/ngx-datatable";
import Dinero from 'dinero.js';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.scss']
})
export class InvoicesComponent implements OnInit {

  invoices: InvoiceDto[];
  temp: InvoiceDto[] = [];
  loadingIndicator = true;
  reorderable = true;
  ColumnMode = ColumnMode;
  @ViewChild(DatatableComponent) table: DatatableComponent;

  constructor(private invoiceService: FactureService) {
  }

  ngOnInit(): void {
    this.invoiceService.getAllInvoices().subscribe(
      data => {
        // cache our list
        this.temp = [...data];

        // push our inital complete list
        this.invoices = data;

        setTimeout(() => {
          this.loadingIndicator = false;
        }, 1500);
      },
      error => {
        console.log(error)
      }
    );
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


  getTotalExcluVTA(items: InvoiceItemResponseDto[] | undefined) {
    let price = Dinero({amount: 0, currency: 'MAD'})

    // @ts-ignore
    for (let item of items) {
      // @ts-ignore
      let unitPrice = Dinero({amount: Math.trunc(item?.unitPrice * 100), currency: 'MAD'});
      price = price.add(unitPrice.multiply(<number>item?.quantity));
    }

    console.log("getTotalExcluVTA: " + price.getAmount())

    return price;
  }

  getTotalIncluVTA(items: InvoiceItemResponseDto[] | undefined) {
    let price = Dinero({amount: 0, currency: 'MAD'})

    // @ts-ignore
    for (let item of items) {
      // @ts-ignore

      let sellingPriceInclu = this.getSellingPriceIncludingTaxesDinero(item.product?.taxRate, item?.unitPrice);
      let dinero = sellingPriceInclu.multiply(<number>item?.quantity);
      price = price.add(dinero)
    }
    console.log("getTotalIncluVTA : " + price.getAmount());

    return price;
  }

  getSellingPriceIncludingTaxesDinero(
    taxRate: ProductResponseDto.TaxRateEnum | undefined,
    sellingPriceExcludingTaxes: number | undefined
  ): Dinero.Dinero {
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

    let dinero = Dinero({
    // @ts-ignore
      amount: Math.trunc(100 * sellingPriceExcludingTaxes),
      currency: 'MAD'
    }).multiply(1 + taxRatePercentage);
    return dinero;
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

  updateFilter(event: KeyboardEvent) {
    // @ts-ignore
    const val = event.target.value.toLowerCase();

    // filter our data
    const temp = this.temp.filter(function (invoice) {
      // @ts-ignore
      return invoice.customer?.name.toLowerCase().indexOf(val) !== -1 || !val;
    });

    // update the rows
    this.invoices = temp;
    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }
}
