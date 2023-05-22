/**
 * IBSYS RETAIL MANAGER API
 * This API provides various endpoints to manage retail operations such as creating and updating products, managing inventory, and processing orders.
 *
 * The version of the OpenAPI document: x.x.x
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { CustomerResponseDto } from './customerResponseDto';
import { InvoiceItemResponseDto } from './invoiceItemResponseDto';


/**
 * Response body for an invoice response
 */
export interface InvoiceDto { 
    /**
     * The ID of the invoice
     */
    id?: number;
    /**
     * The date on which the invoice was issued
     */
    issueDate?: string;
    customer?: CustomerResponseDto;
    /**
     * The list of items and their quantities included in the invoice
     */
    items?: Array<InvoiceItemResponseDto>;
}

