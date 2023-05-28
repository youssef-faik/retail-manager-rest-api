import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FactureService, InvoiceDto, ProductResponseDto} from "../../../../libs/openapi/out";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";

@Component({
  selector: 'app-preview-invoice',
  templateUrl: './preview-invoice.component.html',
  styleUrls: ['./preview-invoice.component.scss']
})
export class PreviewInvoiceComponent implements OnInit {
  id!: number;
  invoice: InvoiceDto | undefined;

  totalIncluVTA: number = 0;
  totalExcluVTA: number = 0;
  title = 'html-to-pdf-angular-application';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private invoiceService: FactureService
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
          console.log(error)
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
      let filename = `invoice${this.invoice != undefined ? '-' + this.invoice.id : ''}.pdf`;
      pdf.save(filename);
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
}
