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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
@SecurityScheme(
    name = "Bearer_Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer",
    in = SecuritySchemeIn.HEADER,
    description =
        "A JWT token is required to access this API.\nJWT token can be obtained by providing email/password to the authentication API.")
@OpenAPIDefinition(
    info =
        @Info(
            title = "IBSYS RETAIL MANAGER API",
            version = "${app.version}",
            description =
                "This API provides various endpoints to manage retail operations such as creating and updating products, managing inventory, and processing orders."))
public class IbsysRetailManagerApplication implements CommandLineRunner {
  private final ProductRepository productRepository;
  private final CustomerRepository customerRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(IbsysRetailManagerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Customer ronaSARl =
        Customer.builder()
            .name("Rona SARl")
            .address("Tit Mellil, Casablanca")
            .phone("0522132345")
            .email("rona@mail.ma")
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

    customerRepository.save(ronaSARl);
    productRepository.save(mouse);
    productRepository.save(laptop);
    userRepository.save(user);
  }
}
