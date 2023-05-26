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


/**
 * Corps de réponse pour les détails de l\'utilisateur.
 */
export interface UserDto { 
    /**
     * ID de l\'utilisateur.
     */
    id?: number;
    /**
     * Prénom de l\'utilisateur.
     */
    firstName?: string;
    /**
     * Nom de famille de l\'utilisateur.
     */
    lastName?: string;
    /**
     * Adresse e-mail de l\'utilisateur.
     */
    email?: string;
    /**
     * Énumération des rôles utilisateur
     */
    role?: UserDto.RoleEnum;
}
export namespace UserDto {
    export type RoleEnum = 'ADMIN' | 'MANAGER' | 'CASHIER';
    export const RoleEnum = {
        Admin: 'ADMIN' as RoleEnum,
        Manager: 'MANAGER' as RoleEnum,
        Cashier: 'CASHIER' as RoleEnum
    };
}

