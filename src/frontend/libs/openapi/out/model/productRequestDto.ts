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
 * Request body for creating/updating a product
 */
export interface ProductRequestDto { 
    /**
     * Barcode of the product
     */
    barCode: string;
    /**
     * Name of the product
     */
    name: string;
    /**
     * Selling Price of the product excluding tax
     */
    sellingPriceExcludingTax: number;
    /**
     * Purchase Price of the product
     */
    purchasePrice: number;
    /**
     * Enumeration of tax rates
     */
    taxRate: ProductRequestDto.TaxRateEnum;
}
export namespace ProductRequestDto {
    export type TaxRateEnum = 'TWENTY' | 'FOURTEEN' | 'TEN' | 'SEVEN';
    export const TaxRateEnum = {
        Twenty: 'TWENTY' as TaxRateEnum,
        Fourteen: 'FOURTEEN' as TaxRateEnum,
        Ten: 'TEN' as TaxRateEnum,
        Seven: 'SEVEN' as TaxRateEnum
    };
}


