import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {InvoiceDto, InvoiceService, ProductResponseDto} from "../../../../libs/openapi/out";

@Component({
  selector: 'app-preview-invoice',
  templateUrl: './preview-invoice.component.html',
  styleUrls: ['./preview-invoice.component.scss']
})
export class PreviewInvoiceComponent implements OnInit {
  id!: number;
  invoice: InvoiceDto;

  totalIncluVTA: number = 0;
  totalExcluVTA: number = 0;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private invoiceService: InvoiceService
  ) {
  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    // @ts-ignore
    this.invoiceService.getInvoice(this.id)
      .subscribe(
        data => {
          this.invoice = data;

          // @ts-ignore
          for (let item of this.invoice.items) {
            // @ts-ignore
            this.totalExcluVTA += item.quantity * item.unitPrice;

            // @ts-ignore
            this.totalIncluVTA += item.quantity * this.getSellingPriceIncludingTaxes(item.product?.taxRate, item.unitPrice);
          }
        }
        , error => {
        }
      )
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

}
