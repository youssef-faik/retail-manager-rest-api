package ma.ibsys.ibsysretailmanager.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.user.enums.Role;

@Entity(name = "User")
@Table(
    name = "_user",
    uniqueConstraints = {@UniqueConstraint(name = "user_email_unique", columnNames = "email")})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private int id;

  @Size(min = 2, max = 50, message = "firstName must be between {min} and {max} characters long")
  @NotBlank(message = "firstName is mandatory")
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Size(min = 2, max = 50, message = "lastName must be between {min} and {max} characters long")
  @NotBlank(message = "lastName is mandatory")
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Email(message = "email should be valid")
  @NotBlank(message = "email is mandatory")
  @Column(name = "email", nullable = false)
  private String email;

  @Size(min = 8, message = "password must be between {min} and {max} characters long")
  @NotBlank(message = "password is mandatory")
  @Column(name = "password", nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @NotBlank(message = "role is mandatory")
  @Column(name = "role", nullable = false)
  private Role role;
}
