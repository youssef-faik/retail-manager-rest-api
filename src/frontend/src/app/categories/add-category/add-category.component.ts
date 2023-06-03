import {Component, OnInit, ViewChild} from '@angular/core';
import {
  CategorieService,
  Category,
  CategoryCreateDto,
  ProductRequestDto,
  ProduitService
} from "../../../../libs/openapi/out";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SwalComponent} from "@sweetalert2/ngx-sweetalert2";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.scss']
})
export class AddCategoryComponent implements OnInit {
  addCategorieForm: FormGroup;
  @ViewChild('errorSwal')
  public readonly errorSwal!: SwalComponent;

  constructor(
    private categoryService: CategorieService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.addCategorieForm = new FormGroup({
      'name': new FormControl(null, Validators.required),
      'description': new FormControl(null, Validators.required)
    });
  }

  onSubmit() {
    if (!this.addCategorieForm.valid) {
      return
    }

    console.log(this.addCategorieForm.value);
    let categoryCreateDto: // @ts-ignore
      CategoryCreateDto = this.addCategorieForm.value;
    this.categoryService.createCategory(categoryCreateDto).subscribe(
      data => {
        this.router.navigate(
          ['../'], {
            relativeTo: this.route,
            replaceUrl: true,
          });
        this.errorSwal.title = 'La categorie a été ajoutée avec succès.';
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

}
