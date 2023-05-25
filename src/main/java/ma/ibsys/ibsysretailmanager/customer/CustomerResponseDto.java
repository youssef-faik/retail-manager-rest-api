package ma.ibsys.ibsysretailmanager.customer;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "Schéma de réponse du client.", description = "Corps de réponse pour un client.")
public class CustomerResponseDto {
  @Schema(description = "ICE du client.", example = "563456789123456", required = true)
  private String ice;

  @Schema(description = "Nom du client.", example = "Acme Corp", required = true)
  private String name;

  @Schema(
      description = "Adresse e-mail du client.",
      example = "acme-corp@example.com",
      required = true)
  private String email;

  @Schema(description = "Numéro de téléphone du client.", example = "0522567890", required = true)
  private String phone;

  @Schema(
      description = "Adresse du client.",
      example = "123 Rue Principale, Ville, État, Code postal.",
      required = true)
  private String address;
}
