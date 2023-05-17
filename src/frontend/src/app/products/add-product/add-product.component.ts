import {Component, OnInit} from '@angular/core';
import {AuthenticationResponse, ProductRequestDto, ProductService} from "../../../../libs/openapi/out";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent implements OnInit {
  taxRates = Object.values(ProductRequestDto.TaxRateEnum);
  addProductForm: FormGroup;

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.addProductForm = new FormGroup({
      'barCode': new FormControl(null, Validators.required),
      'name': new FormControl(null, Validators.required),
      'purchasePrice': new FormControl(null, Validators.required),
      'sellingPriceExcludingTax': new FormControl(null, Validators.required),
      'taxRate': new FormControl(this.taxRates[0], Validators.required)
    });
  }

  onSubmit() {
    if (!this.addProductForm.valid) {
      return
    }

    console.log(this.addProductForm.value);
    let productRequestDto: // @ts-ignore
      ProductRequestDto = this.addProductForm.value;
    // @ts-ignore
    this.productService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.productService.createProduct(productRequestDto)
      .subscribe(
        data => {
          this.router.navigate(['../'], {
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
