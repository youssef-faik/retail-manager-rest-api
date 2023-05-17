import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {
  AuthenticationResponse,
  ProductRequestDto,
  ProductResponseDto,
  ProductService
} from "../../../../libs/openapi/out";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.scss']
})
export class EditProductComponent implements OnInit {

  editProductFrom!: FormGroup;
  id!: number;
  taxRates = Object.values(ProductRequestDto.TaxRateEnum);
  productToUpdate: ProductResponseDto;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductService
  ) {
  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.editProductFrom = new FormGroup({
      barCode: new FormControl('', Validators.required),
      name: new FormControl('', Validators.required),
      purchasePrice: new FormControl('', Validators.required),
      sellingPriceExcludingTax: new FormControl('', Validators.required),
      taxRate: new FormControl(this.taxRates[0], Validators.required)
    });

    // @ts-ignore
    this.productService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.productService.getProduct(this.id, 'body', false, {httpHeaderAccept: 'application/json'})
      .subscribe(
        (data) => {
          this.productToUpdate = data;
          this.editProductFrom.controls['barCode'].setValue(this.productToUpdate.barCode);
          this.editProductFrom.controls['name'].setValue(this.productToUpdate.name);
          this.editProductFrom.controls['purchasePrice'].setValue(this.productToUpdate.purchasePrice);
          this.editProductFrom.controls['sellingPriceExcludingTax'].setValue(this.productToUpdate.sellingPriceExcludingTax);
          this.editProductFrom.controls['taxRate'].setValue(this.productToUpdate.taxRate);
          console.log(this.productToUpdate)
        }, error => {
          console.log(error)
        });
  }

  onSubmit() {
    if (!this.editProductFrom.valid) {
      return
    }

    console.log(this.editProductFrom.value);
    let productRequestDto: ProductRequestDto = this.editProductFrom.value;
    // @ts-ignore
    this.productService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.productService.updateProduct(this.id, productRequestDto, 'body', false, {httpHeaderAccept: 'application/json'})
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
