import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ClientService, CustomerResponseDto, CustomerUpdateDto,} from "../../../../libs/openapi/out";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.scss']
})
export class EditCustomerComponent implements OnInit {
  editCustomerFrom!: FormGroup;
  id!: string | null;
  customerToUpdate: CustomerResponseDto;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private customerService: ClientService
  ) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.editCustomerFrom = new FormGroup({
      'name': new FormControl(null, Validators.required),
      'phone': new FormControl(null, [Validators.required, Validators.minLength(10), Validators.maxLength(10)]),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'address': new FormControl(null, [Validators.required, Validators.minLength(10)])
    });

    this.customerService.getCustomer(
      // @ts-ignore
      this.id,
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        this.customerToUpdate = data;
        this.editCustomerFrom.controls['name'].setValue(this.customerToUpdate.name);
        this.editCustomerFrom.controls['email'].setValue(this.customerToUpdate.email);
        this.editCustomerFrom.controls['phone'].setValue(this.customerToUpdate.phone);
        this.editCustomerFrom.controls['address'].setValue(this.customerToUpdate.address);
      }, error => {
        console.log(error)
      });
  }

  onSubmit() {
    if (!this.editCustomerFrom.valid) {
      return
    }

    let customerRequestDto: CustomerUpdateDto = this.editCustomerFrom.value;
    this.customerService.updateCustomer(
      // @ts-ignore
      this.id, customerRequestDto,
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      data => {
        this.router.navigate(
          ['../..'],
          {
            relativeTo: this.route,
            replaceUrl: true,
          });
      }, error => {
        console.log(error)
      })
  }

}