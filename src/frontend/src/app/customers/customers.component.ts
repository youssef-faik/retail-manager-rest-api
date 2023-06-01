import {Component, OnInit} from '@angular/core';
import {ClientService, CustomerResponseDto} from "../../../libs/openapi/out";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
  customers: CustomerResponseDto[];
  recordIdToDelete?: string;

  constructor(
    private modalService: NgbModal,
    private customerService: ClientService
  ) {
  }

  ngOnInit(): void {
    this.loadCustomers();
  }


  openConfirmationModal(content: any, recordId?: string) {
    this.recordIdToDelete = recordId;
    this.modalService.open(content, {centered: true}).result.then(
      (result) => {
        if (result === 'confirm') {
          this.customerService.deleteCustomer(
            recordId || '0',
            'body',
            false,
            {httpHeaderAccept: 'application/json'}
          ).subscribe(
            data => {
              this.loadCustomers();
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

  private loadCustomers() {
    this.customerService.getAllCustomers(
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        console.log(data)
        this.customers = data;
      }, error => {
        console.log(error)
      })
  }
}
