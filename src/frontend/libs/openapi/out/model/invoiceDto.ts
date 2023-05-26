/**
 * IBSYS RETAIL MANAGER API
 * Cette API propose plusieurs points de terminaison pour gérer les opérations de vente au détail, telles que la création et la mise à jour de produits, la gestion des stocks et le traitement des commandes.
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
 * Corps de la réponse pour une facture.
 */
export interface InvoiceDto { 
    /**
     * L\'ID de la facture
     */
    id?: number;
    /**
     * La date à laquelle la facture a été émise.
     */
    issueDate?: string;
    customer?: CustomerResponseDto;
    /**
     * La liste des articles et de leurs quantités inclus dans la facture.
     */
    items?: Array<InvoiceItemResponseDto>;
}
