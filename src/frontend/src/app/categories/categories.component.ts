import {Component, OnInit, ViewChild} from '@angular/core';
import {CategorieService, Category} from "../../../libs/openapi/out";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ColumnMode, DatatableComponent} from "@swimlane/ngx-datatable";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {
  categories: Category[];
  temp: Category[];
  recordIdToDelete?: number;
  loadingIndicator = true;
  reorderable = true;
  ColumnMode = ColumnMode;
  @ViewChild(DatatableComponent) table: DatatableComponent;

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

  updateFilter(event: KeyboardEvent) {
    // @ts-ignore
    const val = event.target.value.toLowerCase();

    // filter our data
    const temp = this.temp.filter(function (category) {
      // @ts-ignore
      return category.name.toLowerCase().indexOf(val) !== -1 || !val;
    });

    // update the rows
    this.categories = temp;
    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }

  private loadCategories() {
    this.categorieService.getAllCategories(
      'body',
      false,
      {httpHeaderAccept: 'application/json'}
    ).subscribe(
      (data) => {
        // cache our list
        this.temp = [...data];

        // push our inital complete list
        this.categories = data;

        setTimeout(() => {
          this.loadingIndicator = false;
        }, 1500);
      }, error => {
        console.log(error)
      })
  }
}
