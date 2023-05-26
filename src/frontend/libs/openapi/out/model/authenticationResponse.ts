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
 * Corps de la réponse d\'authentification de l\'utilisateur
 */
export interface AuthenticationResponse { 
    /**
     * Jeton JWT
     */
    token?: string;
    /**
     * ID de l\'utilisateur
     */
    id?: number;
    /**
     * Prénom
     */
    firstName?: string;
    /**
     * Nom
     */
    lastName?: string;
    /**
     * Email
     */
    email?: string;
    /**
     * Rôle de l\'utilisateur
     */
    role?: string;
}
