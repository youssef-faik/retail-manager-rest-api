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
 * Response body for a customer response
 */
export interface CustomerResponseDto { 
    /**
     * ID of the customer
     */
    id?: number;
    /**
     * Name of the customer
     */
    name: string;
    /**
     * Email address of the customer
     */
    email: string;
    /**
     * Phone number of the customer
     */
    phone: string;
    /**
     * Address of the customer
     */
    address: string;
}

