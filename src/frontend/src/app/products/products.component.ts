import { Component, OnInit } from '@angular/core';
import {
  AuthenticationResponse,
  ProductResponseDto,
  ProductService,
  UserDto,
  UserService
} from "../../../libs/openapi/out";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  products: ProductResponseDto[];
  recordIdToDelete?: number;

  constructor(private modalService: NgbModal, private productService: ProductService) {
  }

  ngOnInit(): void {
    // @ts-ignore
    this.productService.configuration.credentials = {'Bearer_Authentication': this.getUserDtoFromLocalStorage()?.token};
    this.loadProducts();
  }

  private loadProducts() {
    this.productService.getAllProducts('body', false, {httpHeaderAccept: 'application/json'}).subscribe(
      (data) => {
        console.log(data)
        this.products = data;
      }, error => {
        console.log(error)
      })
  }

  getUserDtoFromLocalStorage(): AuthenticationResponse | undefined {
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

  openConfirmationModal(content: any, recordId?: number) {
    this.recordIdToDelete = recordId;
    this.modalService.open(content, {centered: true}).result.then(
      (result) => {
        if (result === 'confirm') {
          this.productService.deleteProduct(recordId || 0, 'body', false, {httpHeaderAccept: 'application/json'})
            .subscribe(
              data => {
                this.loadProducts();
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
}
