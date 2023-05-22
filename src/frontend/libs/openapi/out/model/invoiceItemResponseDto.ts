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
import { ProductResponseDto } from './productResponseDto';


/**
 * Represents an item included in an invoice
 */
export interface InvoiceItemResponseDto { 
    product?: ProductResponseDto;
    /**
     * The quantity of the product being invoiced
     */
    quantity?: number;
    /**
     * The unit price of the product being invoiced
     */
    unitPrice?: number;
}

