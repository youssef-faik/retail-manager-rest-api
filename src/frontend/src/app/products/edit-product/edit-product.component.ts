import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {
  CategorieService,
  Category,
  ProductRequestDto,
  ProductResponseDto,
  ProduitService
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
  categories: Category[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategorieService,
    private productService: ProduitService
  ) {
  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.editProductFrom = new FormGroup({
      barCode: new FormControl('', Validators.required),
      name: new FormControl('', Validators.required),
      purchasePrice: new FormControl('', Validators.required),
      sellingPriceExcludingTax: new FormControl('', Validators.required),
      taxRate: new FormControl(this.taxRates[0], Validators.required),
      category: new FormControl(null, Validators.required)
    });

    this.categoryService.getAllCategories('body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        console.log(data)
        this.categories = data;
      }, error => {
        console.log(error)
      }
    );


    this.productService.getProduct(
      this.id,
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        this.productToUpdate = data;
        this.editProductFrom.controls['barCode'].setValue(this.productToUpdate.barCode);
        this.editProductFrom.controls['name'].setValue(this.productToUpdate.name);
        this.editProductFrom.controls['purchasePrice'].setValue(this.productToUpdate.purchasePrice);
        this.editProductFrom.controls['sellingPriceExcludingTax'].setValue(this.productToUpdate.sellingPriceExcludingTax);
        this.editProductFrom.controls['taxRate'].setValue(this.productToUpdate.taxRate);
        this.editProductFrom.controls['category'].setValue(this.productToUpdate.category);
      }, error => {
        console.log(error)
      });
  }

  onSubmit() {
    if (!this.editProductFrom.valid) {
      return
    }

    let productRequestDto: ProductRequestDto = this.editProductFrom.value;
    this.productService.updateProduct(
      this.id,
      productRequestDto,
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      data => {
        this.router.navigate(
          ['../..'], {
            relativeTo: this.route,
            replaceUrl: true,
          });
      }, error => {
        console.log(error)
      })
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

}
