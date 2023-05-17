import {Component, OnInit} from '@angular/core';
import {AuthenticationResponse, CustomerResponseDto, CustomerService} from "../../../libs/openapi/out";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
  customers: CustomerResponseDto[];
  recordIdToDelete?: number;

  constructor(private modalService: NgbModal, private customerService: CustomerService) {
  }

  ngOnInit(): void {
    // @ts-ignore
    this.customerService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.loadCustomers();
  }

  getUserDtoFromLocalStorage(): AuthenticationResponse | undefined {
    try {
      const lsValue = localStorage.getItem('authenticationResponse');
      if (!lsValue) {
        return undefined;
      }

      return JSON.parse(lsValue);
    } catch (error) {
      console.error(error);
      return undefined;
    }
  }

  openConfirmationModal(content: any, recordId?: number) {
    this.recordIdToDelete = recordId;
    this.modalService.open(content, {centered: true}).result.then(
      (result) => {
        if (result === 'confirm') {
          this.customerService.deleteCustomer(recordId || 0, 'body', false, {httpHeaderAccept: 'application/json'})
            .subscribe(
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
    this.customerService.getAllCustomers('body', false, {httpHeaderAccept: 'application/json'}).subscribe(
      (data) => {
        console.log(data)
        this.customers = data;
      }, error => {
        console.log(error)
      })
  }
}
