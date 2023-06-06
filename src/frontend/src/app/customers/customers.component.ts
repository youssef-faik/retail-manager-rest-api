import {Component, OnInit, ViewChild} from '@angular/core';
import {ClientService, CustomerResponseDto} from "../../../libs/openapi/out";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ColumnMode, DatatableComponent} from "@swimlane/ngx-datatable";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
  customers: CustomerResponseDto[];
  temp: CustomerResponseDto[];
  recordIdToDelete?: string;
  loadingIndicator = true;
  reorderable = true;
  ColumnMode = ColumnMode;
  @ViewChild(DatatableComponent) table: DatatableComponent;

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

  updateFilter(event: KeyboardEvent) {
    // @ts-ignore
    const val = event.target.value.toLowerCase();

    // filter our data
    const temp = this.temp.filter(function (product) {
        // @ts-ignore
        return product.name.toLowerCase().indexOf(val) !== -1 || !val;
      }
    );

    // update the rows
    this.customers = temp;
    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }

  private loadCustomers() {
    this.customerService.getAllCustomers(
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        // cache our list
        this.temp = [...data];

        // push our inital complete list
        this.customers = data;

        setTimeout(() => {
          this.loadingIndicator = false;
        }, 1500);
      }, error => {
        console.log(error)
      }
    );
  }
}
