import {Component, OnInit} from '@angular/core';
import {InvoiceDto, InvoiceItemResponseDto, FactureService, ProductResponseDto} from "../../../libs/openapi/out";

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.scss']
})
export class InvoicesComponent implements OnInit {

  invoices: InvoiceDto[];

  constructor(private invoiceService: FactureService) {
  }

  ngOnInit(): void {
    this.invoiceService.getAllInvoices()
      .subscribe(
        data => {
          this.invoices = data;
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

  getTotalIncluVTA(items: InvoiceItemResponseDto[] | undefined) {
    let totalIncluVTA: number = 0;

    // @ts-ignore
    for (let item of items) {
      // @ts-ignore
      totalIncluVTA += item?.quantity * this.getSellingPriceIncludingTaxes(item.product?.taxRate, item?.unitPrice);
    }

    return totalIncluVTA
  }

  getTotalExcluVTA(items: InvoiceItemResponseDto[] | undefined) {
    let totalExcluVTA: number = 0;

    // @ts-ignore
    for (let item of items) {
      // @ts-ignore
      totalExcluVTA += item?.quantity * item?.unitPrice;
    }

    return totalExcluVTA
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
}
