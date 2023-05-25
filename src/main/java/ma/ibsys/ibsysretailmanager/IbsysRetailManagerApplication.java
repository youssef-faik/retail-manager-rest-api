package ma.ibsys.ibsysretailmanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.customer.Customer;
import ma.ibsys.ibsysretailmanager.customer.CustomerRepository;
import ma.ibsys.ibsysretailmanager.product.Product;
import ma.ibsys.ibsysretailmanager.product.ProductRepository;
import ma.ibsys.ibsysretailmanager.product.TaxRate;
import ma.ibsys.ibsysretailmanager.user.Role;
import ma.ibsys.ibsysretailmanager.user.User;
import ma.ibsys.ibsysretailmanager.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RequiredArgsConstructor
@SecurityScheme(
    name = "Bearer_Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer",
    in = SecuritySchemeIn.HEADER,
    description =
        "Un jeton JWT est requis pour accéder à cette API.\nLe jeton JWT peut être obtenu en fournissant l'e-mail/le mot de passe à l'API d'authentification.")
@OpenAPIDefinition(
    info =
        @Info(
            title = "IBSYS RETAIL MANAGER API",
            version = "${app.version}",
            description =
                "Cette API propose plusieurs points de terminaison pour gérer les opérations de vente au détail, telles que la création et la mise à jour de produits, la gestion des stocks et le traitement des commandes."))
public class IbsysRetailManagerApplication implements CommandLineRunner {
  private final ProductRepository productRepository;
  private final CustomerRepository customerRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(IbsysRetailManagerApplication.class, args);
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .exposedHeaders("*");
      }
    };
  }

  @Override
  public void run(String... args) throws Exception {
    Customer acmeCorp =
        Customer.builder()
            .ICE("563456789123456")
            .name("Acme Corp")
            .address("123 Main St, City, State, Zip")
            .phone("0522132345")
            .email("acme-corp@mail.ma")
            .build();

    Product mouse =
        Product.builder()
            .barCode("432435")
            .taxRate(TaxRate.FOURTEEN)
            .name("Mouse")
            .sellingPriceExcludingTax(BigDecimal.valueOf(199))
            .purchasePrice(BigDecimal.valueOf(50))
            .build();

    Product laptop =
        Product.builder()
            .barCode("1434")
            .name("Laptop")
            .taxRate(TaxRate.SEVEN)
            .sellingPriceExcludingTax(BigDecimal.valueOf(20_000))
            .purchasePrice(BigDecimal.valueOf(15_000))
            .build();

    User user =
        User.builder()
            .firstName("Youssef")
            .lastName("Faik")
            .email("yusef@mail.com")
            .role(Role.ADMIN)
            .password(passwordEncoder.encode("secret-password"))
            .isEnabled(true)
            .build();

    customerRepository.save(acmeCorp);
    productRepository.save(mouse);
    productRepository.save(laptop);
    userRepository.save(user);
  }
}
