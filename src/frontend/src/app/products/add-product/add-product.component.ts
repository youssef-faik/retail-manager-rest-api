import {Component, OnInit, ViewChild} from '@angular/core';
import {
  CategorieService,
  Category,
  ProductRequestDto,
  ProductResponseDto,
  ProduitService
} from "../../../../libs/openapi/out";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {SwalComponent} from '@sweetalert2/ngx-sweetalert2';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent implements OnInit {
  taxRates = Object.values(ProductRequestDto.TaxRateEnum);
  categories: Category[] = [];
  addProductForm: FormGroup;
  @ViewChild('errorSwal')
  public readonly errorSwal!: SwalComponent;

  constructor(
    private productService: ProduitService,
    private categoryService: CategorieService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.categoryService.getAllCategories('body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        console.log(data)
        this.categories = data;
        this.addProductForm.controls['category'].setValue(this.categories[0].id);
      }, error => {
        console.log(error)
      }
    );

    this.addProductForm = new FormGroup({
      'barCode': new FormControl(null, Validators.required),
      'name': new FormControl(null, Validators.required),
      'purchasePrice': new FormControl(null, Validators.required),
      'sellingPriceExcludingTax': new FormControl(null, Validators.required),
      'taxRate': new FormControl(this.taxRates[0], Validators.required),
      'category': new FormControl(null, Validators.required)
    });
  }

  onSubmit() {
    if (!this.addProductForm.valid) {
      return
    }

    console.log(this.addProductForm.value);
    let productRequestDto: // @ts-ignore
      ProductRequestDto = this.addProductForm.value;
    this.productService.createProduct(productRequestDto).subscribe(
      data => {
        this.router.navigate(
          ['../'], {
            relativeTo: this.route,
            replaceUrl: true,
          });
        this.errorSwal.title = 'Le produit a été ajouté avec succès.';
        this.errorSwal.icon = 'success';

        this.errorSwal.fire();
      }
      , error => {
        this.errorSwal.title = error.message;
        this.errorSwal.fire();
        console.log(error)
      }
    )
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
