package ma.ibsys.ibsysretailmanager.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Chart Data Response Schema", description = "Response body for a Chart Data response")
public class ChartDataDto {
  private List<String> dates;
  private List<Integer> data;
}
