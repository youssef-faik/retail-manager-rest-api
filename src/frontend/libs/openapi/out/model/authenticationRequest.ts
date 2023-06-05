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
 * Corps de la demande d\'authentification de l\'utilisateur.
 */
export interface AuthenticationRequest { 
    /**
     * Email de l\'utilisateur.
     */
    email: string;
    /**
     * Mot de passe de l\'utilisateur.
     */
    password: string;
}
