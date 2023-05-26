package ma.ibsys.ibsysretailmanager.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
    title = "Schéma de réponse des données du graphique",
    description = "Corps de la réponse pour les données du graphique")
public class ChartDataDto {
  private List<String> dates;
  private List<Float> data;
}
