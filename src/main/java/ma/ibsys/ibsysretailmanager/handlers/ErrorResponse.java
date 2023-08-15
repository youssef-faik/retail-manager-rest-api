package ma.ibsys.ibsysretailmanager.handlers;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
        title = "Error Response Schema",
        description = "Represents the error response returned by the API.")
public class ErrorResponse {
  @Schema(description = "The HTTP status code associated with the error.", example = "400")
  private HttpStatus httpStatus;

  @Schema(description = "The timestamp of the error.", example = "2023-04-26T10:30:00")
  private LocalDateTime timestamp;

  @Schema(description = "The error message.", example = "The requested entity was not found.")
  private String message;
}
