import {Component, Input, OnInit} from '@angular/core';
import {FactureService} from "../../../libs/openapi/out";
import {HttpClient} from "@angular/common/http";


@Component({
  selector: 'app-pdf-viewer-component',
  templateUrl: './pdf-viewer.component.html',
  styleUrls: ['./pdf-viewer.component.scss']
})
export class PdfViewerComponent implements OnInit {
  @Input() invoiceId: number;
  pdfBlobUrl: string;

  constructor(
    private invoiceService: FactureService,
    private http: HttpClient
  ) {
  }

  ngOnInit(): void {
    this.getPdfBlobUrl();
  }

  getPdfBlobUrl(): void {
    const apiUrl = `${this.invoiceService.configuration.basePath}/api/v1/invoices/${this.invoiceId}/pdf`;

    this.http.get(apiUrl, {responseType: 'blob'}).subscribe((blob: Blob) => {
      // Create a blob URL from the blob
      this.pdfBlobUrl = URL.createObjectURL(blob);
    });
  }

}
