import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {
  AuthenticationResponse,
  CustomerRequestDto,
  CustomerResponseDto,
  CustomerService
} from "../../../../libs/openapi/out";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.scss']
})
export class EditCustomerComponent implements OnInit {
  editCustomerFrom!: FormGroup;
  id!: number;
  customerToUpdate: CustomerResponseDto;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private customerService: CustomerService
  ) {
  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.editCustomerFrom = new FormGroup({
      'name': new FormControl(null, Validators.required),
      'phone': new FormControl(null, [Validators.required, Validators.minLength(10), Validators.maxLength(10)]),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'address': new FormControl(null, [Validators.required, Validators.minLength(10)])
    });

    // @ts-ignore
    this.customerService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.customerService.getCustomer(this.id, 'body', false, {httpHeaderAccept: 'application/json'})
      .subscribe(
        (data) => {
          this.customerToUpdate = data;
          this.editCustomerFrom.controls['name'].setValue(this.customerToUpdate.name);
          this.editCustomerFrom.controls['email'].setValue(this.customerToUpdate.email);
          this.editCustomerFrom.controls['phone'].setValue(this.customerToUpdate.phone);
          this.editCustomerFrom.controls['address'].setValue(this.customerToUpdate.address);
          console.log(this.customerToUpdate)
        }, error => {
          console.log(error)
        });
  }

  onSubmit() {
    if (!this.editCustomerFrom.valid) {
      return
    }

    console.log(this.editCustomerFrom.value);
    let customerRequestDto: CustomerRequestDto = this.editCustomerFrom.value;
    // @ts-ignore
    this.customerService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.customerService.updateCustomer(this.id, customerRequestDto, 'body', false, {httpHeaderAccept: 'application/json'})
      .subscribe(
        data => {
          console.log(this.route)

          this.router.navigate(['../..'], {
            relativeTo: this.route,
            replaceUrl: true,
          });
        }
        , error => {
          console.log(error)
        })
  }

  private getUserDtoFromLocalStorage(): AuthenticationResponse | undefined {
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

}
