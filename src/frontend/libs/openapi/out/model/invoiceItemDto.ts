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


/**
 * Represents an item included in an invoice
 */
export interface InvoiceItemDto { 
    /**
     * The ID of the product being invoiced
     */
    productId: number;
    /**
     * The quantity of the product being invoiced
     */
    quantity: number;
    /**
     * The unit price of the product being invoiced
     */
    unitPrice: number;
}
