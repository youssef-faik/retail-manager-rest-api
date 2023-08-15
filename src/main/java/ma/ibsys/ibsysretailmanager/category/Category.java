package ma.ibsys.ibsysretailmanager.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Category")
@Table(name = "category")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private int id;

  @NotBlank(message = "Category name is required.")
  @Column(name = "name", nullable = false)
  private String name;

  @Size(max = 500, message = "Category description must not exceed {max} characters.")
  @NotNull(message = "Category description is required.")
  @Column(name = "description", nullable = false)
  private String description;
}
