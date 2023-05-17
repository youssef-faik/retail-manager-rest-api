import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppRoutingModule} from './app-routing.module';

import {LayoutModule} from './views/layout/layout.module';
import {AuthGuard} from './core/guard/auth.guard';

import {AppComponent} from './app.component';
import {ErrorPageComponent} from './views/pages/error-page/error-page.component';

import {HIGHLIGHT_OPTIONS} from 'ngx-highlightjs';
import {HttpClientModule} from "@angular/common/http";
import {UsersComponent} from './users/users.component';
import {AddUserComponent} from './users/add-user/add-user.component';
import {TablesModule} from "./views/pages/tables/tables.module";
import {EditUserComponent} from './users/edit-user/edit-user.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CustomFormsModule} from "ngx-custom-validators";
import { ProductsComponent } from './products/products.component';
import { AddProductComponent } from './products/add-product/add-product.component';
import { EditProductComponent } from './products/edit-product/edit-product.component';


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
    ReactiveFormsModule
  ],
  providers: [
    AuthGuard,
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
