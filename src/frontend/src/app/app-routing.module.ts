import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BaseComponent} from './views/layout/base/base.component';
import {AuthGuard} from './core/guard/auth.guard';
import {ErrorPageComponent} from './views/pages/error-page/error-page.component';
import {UsersComponent} from "./users/users.component";
import {AddUserComponent} from "./users/add-user/add-user.component";
import {EditUserComponent} from "./users/edit-user/edit-user.component";
import {EditProductComponent} from "./products/edit-product/edit-product.component";
import {ProductsComponent} from "./products/products.component";
import {AddProductComponent} from "./products/add-product/add-product.component";
import {CustomersComponent} from "./customers/customers.component";
import {AddCustomerComponent} from "./customers/add-customer/add-customer.component";
import {EditCustomerComponent} from "./customers/edit-customer/edit-customer.component";
import {ChangePasswordComponent} from "./change-password/change-password.component";


const routes: Routes = [
  {path: 'auth', loadChildren: () => import('./views/pages/auth/auth.module').then(m => m.AuthModule)},
  {
    path: '',
    component: BaseComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'dashboard',
        loadChildren: () => import('./views/pages/dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'users',
        component: UsersComponent,
      },
      {
        path: 'users/add-user',
        component: AddUserComponent
      },
      {
        path: 'users/edit-user/:id',
        component: EditUserComponent
      },
      {
        path: 'products',
        component: ProductsComponent,
      },
      {
        path: 'products/add-product',
        component: AddProductComponent
      },
      {
        path: 'products/edit-product/:id',
        component: EditProductComponent
      },
      {
        path: 'customers',
        component: CustomersComponent,
      },
      {
        path: 'customers/add-customer',
        component: AddCustomerComponent
      },
      {
        path: 'customers/edit-customer/:id',
        component: EditCustomerComponent
      },
      {
        path: 'change-password',
        component: ChangePasswordComponent
      },
      {path: '', redirectTo: 'dashboard', pathMatch: 'full'}
    ]
  },
  {
    path: 'error',
    component: ErrorPageComponent,
    data: {
      'type': 404,
      'title': 'Page Not Found',
      'desc': 'Oops!! The page you were looking for doesn\'t exist.'
    }
  },
  {
    path: 'error/:type',
    component: ErrorPageComponent
  },
  {path: '**', redirectTo: 'error', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {scrollPositionRestoration: 'top'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
