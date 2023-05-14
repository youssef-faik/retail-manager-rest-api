export * from './authentication.service';
import { AuthenticationService } from './authentication.service';
export * from './customer.service';
import { CustomerService } from './customer.service';
export * from './invoice.service';
import { InvoiceService } from './invoice.service';
export * from './product.service';
import { ProductService } from './product.service';
export * from './user.service';
import { UserService } from './user.service';
export const APIS = [AuthenticationService, CustomerService, InvoiceService, ProductService, UserService];
