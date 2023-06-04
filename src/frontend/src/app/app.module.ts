import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppRoutingModule} from './app-routing.module';

import {LayoutModule} from './views/layout/layout.module';
import {AuthGuard} from './core/guard/auth.guard';

import {AppComponent} from './app.component';
import {ErrorPageComponent} from './views/pages/error-page/error-page.component';

import {HIGHLIGHT_OPTIONS} from 'ngx-highlightjs';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {UsersComponent} from './users/users.component';
import {AddUserComponent} from './users/add-user/add-user.component';
import {TablesModule} from "./views/pages/tables/tables.module";
import {EditUserComponent} from './users/edit-user/edit-user.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CustomFormsModule} from "ngx-custom-validators";
import {ProductsComponent} from './products/products.component';
import {AddProductComponent} from './products/add-product/add-product.component';
import {EditProductComponent} from './products/edit-product/edit-product.component';
import {CustomersComponent} from './customers/customers.component';
import {EditCustomerComponent} from './customers/edit-customer/edit-customer.component';
import {AddCustomerComponent} from './customers/add-customer/add-customer.component';
import {ChangePasswordComponent} from './change-password/change-password.component';
import {HttpInterceptorService} from "./services/http-interceptor.service";
import {InvoicesComponent} from './invoices/invoices.component';
import {AddInvoiceComponent} from './invoices/add-invoice/add-invoice.component';
import {NgSelectModule} from "@ng-select/ng-select";
import {PreviewInvoiceComponent} from './invoices/preview-invoice/preview-invoice.component';
import {ArchwizardModule} from "angular-archwizard";
import {NgxQRCodeModule} from '@techiediaries/ngx-qrcode';
import {SweetAlert2Module} from "@sweetalert2/ngx-sweetalert2";
import {CategoriesComponent} from './categories/categories.component';
import {AddCategoryComponent} from './categories/add-category/add-category.component';
import {EditCategoryComponent} from './categories/edit-category/edit-category.component';
import { ConfigurationComponent } from './configuration/configuration.component';


@NgModule({
  declarations: [
    AppComponent,
    ErrorPageComponent,
    UsersComponent,
    AddUserComponent,
    EditUserComponent,
    ProductsComponent,
    AddProductComponent,
    EditProductComponent,
    CustomersComponent,
    EditCustomerComponent,
    AddCustomerComponent,
    ChangePasswordComponent,
    InvoicesComponent,
    AddInvoiceComponent,
    PreviewInvoiceComponent,
    CategoriesComponent,
    AddCategoryComponent,
    EditCategoryComponent,
    ConfigurationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    HttpClientModule,
    TablesModule,
    FormsModule,
    CustomFormsModule,
    ReactiveFormsModule,
    NgSelectModule,
    ArchwizardModule,
    NgxQRCodeModule,
    SweetAlert2Module,
    SweetAlert2Module.forRoot(),
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    },
    {
      provide: HIGHLIGHT_OPTIONS, // https://www.npmjs.com/package/ngx-highlightjs
      useValue: {
        coreLibraryLoader: () => import('highlight.js/lib/core'),
        languages: {
          xml: () => import('highlight.js/lib/languages/xml'),
          typescript: () => import('highlight.js/lib/languages/typescript'),
          scss: () => import('highlight.js/lib/languages/scss'),
        }
      }
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
