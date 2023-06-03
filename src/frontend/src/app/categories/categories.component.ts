import {Component, OnInit} from '@angular/core';
import {CategorieService, Category, ProductResponseDto} from "../../../libs/openapi/out";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {
  categories: Category[];
  recordIdToDelete?: number;

  constructor(
    private modalService: NgbModal,
    private categorieService: CategorieService
  ) {
  }

  ngOnInit(): void {
    this.loadCategories();
  }

  openConfirmationModal(content: any, recordId?: number) {
    this.recordIdToDelete = recordId;
    this.modalService.open(content, {centered: true}).result.then(
      (result) => {
        if (result === 'confirm') {
          this.categorieService.deleteCategory(
            recordId || 0,
            'body',
            false,
            {httpHeaderAccept: 'application/json'}
          ).subscribe(
            data => {
              this.loadCategories();
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

  private loadCategories() {
    this.categorieService.getAllCategories(
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        console.log(data)
        this.categories = data;
      }, error => {
        console.log(error)
      })
  }
}
