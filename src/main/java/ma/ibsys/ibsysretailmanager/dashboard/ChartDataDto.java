package ma.ibsys.ibsysretailmanager.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
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
        title = "Schema for Chart Data Response.",
        description = "Response body for chart data.")
public class ChartDataDto {
  private List<String> dates;
  private List<Float> data;
}
