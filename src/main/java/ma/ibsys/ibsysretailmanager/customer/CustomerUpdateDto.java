package ma.ibsys.ibsysretailmanager.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
    title = "Schéma de requête de mis à jour du client",
    description = "Corps de requête pour mettre à jour un client")
public class CustomerUpdateDto {
  @Schema(description = "Nom du client", example = "Acme Corp", required = true)
  @Size(min = 2, max = 100, message = "Le nom doit comporter entre {min} et {max} caractères")
  @NotBlank(message = "Le nom est obligatoire")
  private String name;

  @Schema(
      description = "Adresse e-mail du client",
      example = "acme-corp@example.com",
      required = true)
  @Email(message = "L'e-mail doit être valide")
  @Column(name = "email", nullable = false)
  private String email;

  @Schema(description = "Numéro de téléphone du client", example = "0522567890", required = true)
  @NotNull(message = "Le numéro de téléphone est obligatoire")
  @Pattern(
      regexp = "^0\\d{9}$",
      message =
          "Le numéro de téléphone doit contenir exactement 10 chiffres et ne contenir que des chiffres")
  private String phone;

  @Schema(
      description = "Adresse du client",
      example = "123 Rue Principale, Ville, État, Code postal",
      required = true)
  @Size(min = 10, max = 500, message = "L'adresse doit comporter entre {min} et {max} caractères")
  @NotBlank(message = "L'adresse est obligatoire")
  private String address;
}
