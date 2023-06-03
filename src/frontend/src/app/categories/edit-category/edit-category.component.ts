import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CategorieService, Category, CategoryCreateDto} from "../../../../libs/openapi/out";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.scss']
})
export class EditCategoryComponent implements OnInit {

  editCategoryFrom!: FormGroup;
  id!: number;
  categorieToUpdate: Category;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private categorieService: CategorieService
  ) {
  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.editCategoryFrom = new FormGroup({
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });

    this.categorieService.getCategoryById(
      this.id,
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        this.categorieToUpdate = data;
        this.editCategoryFrom.controls['name'].setValue(this.categorieToUpdate.name);
        this.editCategoryFrom.controls['description'].setValue(this.categorieToUpdate.description);
      }, error => {
        console.log(error)
      });
  }

  onSubmit() {
    if (!this.editCategoryFrom.valid) {
      return
    }

    let categoryCreateDto: CategoryCreateDto = this.editCategoryFrom.value;
    this.categorieService.updateCategory(
      this.id,
      categoryCreateDto,
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
      }
    )
  }

}
