import {Component, OnInit} from '@angular/core';
import {ClientService, CustomerCreateDto} from "../../../../libs/openapi/out";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.scss']
})
export class AddCustomerComponent implements OnInit {
  addCustomerForm: FormGroup;

  constructor(
    private customerService: ClientService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.addCustomerForm = new FormGroup({
      'ice': new FormControl(null, [Validators.required, Validators.pattern('^\\d{15}$')]),
      'name': new FormControl(null, Validators.required),
      'phone': new FormControl(null, [Validators.required, Validators.minLength(10), Validators.maxLength(10)]),
      'email': new FormControl(null, Validators.email),
      'address': new FormControl(null, [Validators.required, Validators.minLength(10)])
    });
  }

  onSubmit() {
    if (!this.addCustomerForm.valid) {
      return
    }
    let customerRequestDto: CustomerCreateDto = this.addCustomerForm.value;
    this.customerService.createCustomer(customerRequestDto).subscribe(
      data => {
        this.router.navigate(
          ['../'],
          {
            relativeTo: this.route,
            replaceUrl: true,
          });
      }, error => {
        console.log(error)
      })
  }

}
