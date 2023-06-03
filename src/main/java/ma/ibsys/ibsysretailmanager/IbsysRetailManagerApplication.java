package ma.ibsys.ibsysretailmanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.category.Category;
import ma.ibsys.ibsysretailmanager.category.CategoryRepository;
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
  private final CategoryRepository categoryRepository;
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

    Customer myCorp =
        Customer.builder()
            .ICE("857356789123456")
            .name("My Corp")
            .address("123 Main St, City, State, Zip")
            .phone("0522132345")
            .email("my-corp@mail.ma")
            .build();

    Category category =
        Category.builder().name("Électronique").description("Électronique categorie").build();
    Category category1 = Category.builder().name("Autre").description("Autre categorie").build();
    Category category2 =
        Category.builder().name("Cuisine").description("Cuisine categorie").build();
    Category category3 =
        Category.builder().name("Cosmetique").description("Cosmetique categorie").build();
    categoryRepository.save(category);

    Category autre = categoryRepository.save(category1);
    Category cuisine = categoryRepository.save(category2);
    Category cosmetique = categoryRepository.save(category3);

    Product watterBottle =
        Product.builder()
            .barCode("6111035001673")
            .taxRate(TaxRate.TWENTY)
            .name("Watter Bottle")
            .purchasePrice(BigDecimal.valueOf(23))
            .sellingPriceExcludingTax(BigDecimal.valueOf(3.5))
            .category(autre)
            .build();

    Product oilBottle =
        Product.builder()
            .barCode("6111024001585")
            .taxRate(TaxRate.TWENTY)
            .name("Oil Bottle")
            .purchasePrice(BigDecimal.valueOf(80))
            .sellingPriceExcludingTax(BigDecimal.valueOf(90))
            .category(autre)
            .build();

    Product deodorantGel =
        Product.builder()
            .barCode("4005900640253")
            .name("Deodorant gel")
            .taxRate(TaxRate.TWENTY)
            .purchasePrice(BigDecimal.valueOf(30))
            .sellingPriceExcludingTax(BigDecimal.valueOf(35))
            .category(cosmetique)
            .build();

    Product handCream =
        Product.builder()
            .barCode("6111251490619")
            .name("Hand Cream")
            .taxRate(TaxRate.TWENTY)
            .purchasePrice(BigDecimal.valueOf(20))
            .sellingPriceExcludingTax(BigDecimal.valueOf(25))
            .category(cosmetique)
            .build();

    Product cheese =
        Product.builder()
            .barCode("3073781154481")
            .name("cheese")
            .taxRate(TaxRate.TWENTY)
            .purchasePrice(BigDecimal.valueOf(12))
            .sellingPriceExcludingTax(BigDecimal.valueOf(24))
            .category(cuisine)
            .build();

    productRepository.save(watterBottle);
    productRepository.save(oilBottle);
    productRepository.save(deodorantGel);
    productRepository.save(cheese);
    productRepository.save(handCream);

    User user =
        User.builder()
            .firstName("Youssef")
            .lastName("Faik")
            .email("yusef@mail.com")
            .role(Role.ADMIN)
            .password(passwordEncoder.encode("secret-password"))
            .isEnabled(true)
            .build();

    User appUser =
        User.builder()
            .firstName("user")
            .lastName("user")
            .email("user@mail.com")
            .role(Role.ADMIN)
            .password(passwordEncoder.encode("secret-password"))
            .isEnabled(true)
            .build();

    User admin =
        User.builder()
            .firstName("admin")
            .lastName("admin")
            .email("admin@mail.com")
            .role(Role.ADMIN)
            .password(passwordEncoder.encode("secret-password"))
            .isEnabled(true)
            .build();

    customerRepository.save(acmeCorp);
    customerRepository.save(myCorp);

    userRepository.save(user);
    userRepository.save(appUser);
    userRepository.save(admin);
  }
}
