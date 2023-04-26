package ma.ibsys.ibsysretailmanager.security.token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.user.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Token")
@Table(
    name = "token",
    uniqueConstraints = {@UniqueConstraint(name = "token_token_unique", columnNames = "token")})
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  public Long id;

  @Column(name = "token", updatable = false)
  public String token;

  @Enumerated(EnumType.STRING)
  @Column(name = "token_type", nullable = false)
  public TokenType tokenType = TokenType.BEARER;

  @Column(name = "revoked", nullable = false)
  public boolean revoked;

  @Column(name = "expired", nullable = false)
  public boolean expired;

  @ManyToOne
  @JoinColumn(
      name = "user_id",
      referencedColumnName = "id",
      nullable = false,
      foreignKey = @ForeignKey(name = "token_user_id_fk"))
  public User user;
}
