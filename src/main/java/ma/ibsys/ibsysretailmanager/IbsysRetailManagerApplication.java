package ma.ibsys.ibsysretailmanager;

import com.github.javafaker.Faker;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    Category techCategory =
        Category.builder().name("Informatique").description("Categorie Informatique ").build();
    categoryRepository.save(category);

    Category tech = categoryRepository.save(techCategory);
    Category autre = categoryRepository.save(category1);
    Category cuisine = categoryRepository.save(category2);
    Category cosmetique = categoryRepository.save(category3);

    List<String> frenchProductNames =
        List.of(
            "Ordinateur portable",
            "Smartphone",
            "Tablette",
            "Imprimante",
            "Disque dur externe",
            "Casque audio",
            "Enceinte Bluetooth",
            "Appareil photo",
            "Routeur WiFi",
            "Clavier sans fil",
            "Souris gamer",
            "Écran LCD",
            "Carte graphique",
            "Processeur",
            "Batterie externe",
            "Câble USB",
            "Webcam",
            "Haut-parleurs",
            "Lecteur DVD",
            "Laptop",
            "Microphone",
            "Écouteurs sans fil",
            "Smart TV",
            "Console de jeux",
            "Lunettes de réalité virtuelle",
            "Disque SSD",
            "Scanner",
            "Moniteur",
            "Baffle Bluetooth",
            "Accessoires pour ordinateur",
            "Écouteurs intra-auriculaires",
            "Enregistreur vocal",
            "Montre connectée",
            "Clé USB",
            "Adaptateur HDMI",
            "Étui pour smartphone",
            "Étui pour tablette",
            "Enceinte intelligente",
            "Chargeur sans fil",
            "Éclairage LED pour PC",
            "Projecteur",
            "Imprimante 3D",
            "Support pour ordinateur portable",
            "Stylo 3D",
            "Disque dur NAS",
            "Raspberry Pi",
            "Switch réseau",
            "Carte mère",
            "Ventilateur de refroidissement",
            "Antenne WiFi",
            "Imprimante laser",
            "Micro-ondes",
            "Réfrigérateur intelligent",
            "Caméra de surveillance",
            "Routeur 4G",
            "Enregistreur vidéo",
            "Lecteur de carte mémoire",
            "Batterie de secours",
            "Station d'accueil pour ordinateur portable",
            "Télécommande universelle",
            "Barre de son",
            "Support mural pour TV",
            "Souris sans fil",
            "Adaptateur Bluetooth",
            "Câble Ethernet",
            "Haut-parleurs portables",
            "Chargeur secteur",
            "Clavier Bluetooth",
            "Étui de protection pour ordinateur portable",
            "Support pour tablette",
            "Écran de projection",
            "Tapis de souris",
            "Webcam HD",
            "Microphone USB",
            "Imprimante photo",
            "Clé USB OTG",
            "Hub USB",
            "Mini PC",
            "Carte réseau",
            "Stabilisateur d'image",
            "Station météo",
            "Casque de réalité virtuelle",
            "Disque dur externe SSD",
            "Multiprise intelligente",
            "Lecteur de code-barres",
            "Adaptateur secteur",
            "Enceinte étanche",
            "Carte son externe",
            "Répéteur WiFi",
            "Support pour casque audio",
            "Stylo pour tablette graphique",
            "Câble audio",
            "Chargeur de voiture",
            "Protection d'écran pour smartphone",
            "Enceinte USB",
            "Routeur Mesh",
            "Clavier gamer",
            "Étui de protection pour smartphone",
            "Support pour moniteur",
            "Microphone à condensateur",
            "Imprimante laser couleur",
            "Écran tactile",
            "Casque antibruit",
            "Station de charge sans fil",
            "Souris gamer sans fil",
            "Caméra de sport",
            "Disque dur externe 2 To",
            "Point d'accès WiFi",
            "Télécommande universelle pour smartphone",
            "Clé USB 3.0",
            "Haut-parleur Bluetooth portable",
            "Câble HDMI",
            "Webcam Full HD",
            "Souris ergonomique",
            "Lecteur de DVD externe",
            "Adaptateur USB-C",
            "Support pour smartphone",
            "Caméra de surveillance IP",
            "Disque dur externe 1 To",
            "Réveil intelligent",
            "Chargeur solaire",
            "Enceinte WiFi");

    List<Product> products = new ArrayList<>();

    Faker faker = new Faker(Locale.FRENCH);

    for (int i = 1; i <= frenchProductNames.size(); i++) {
      String barCode = faker.number().digits(13);
      String name = frenchProductNames.get(faker.random().nextInt(frenchProductNames.size()));
      TaxRate taxRate = TaxRate.TWENTY;
      BigDecimal purchasePrice = BigDecimal.valueOf((int) faker.number().randomDouble(2, 10, 1000));
      BigDecimal sellingPriceExcludingTax = purchasePrice.multiply(BigDecimal.valueOf(2));

      Product product =
          Product.builder()
              .barCode(barCode)
              .name(name)
              .taxRate(taxRate)
              .purchasePrice(purchasePrice)
              .sellingPriceExcludingTax(sellingPriceExcludingTax)
              .category(tech)
              .build();

      products.add(product);
    }

    Product waterBottle =
        Product.builder()
            .barCode("6111035001673")
            .taxRate(TaxRate.TWENTY)
            .name("Bouteille d'eau")
            .purchasePrice(BigDecimal.valueOf(23))
            .sellingPriceExcludingTax(BigDecimal.valueOf(3.5))
            .category(autre)
            .build();

    Product oilBottle =
        Product.builder()
            .barCode("6111024001585")
            .taxRate(TaxRate.TWENTY)
            .name("Bouteille d'huile")
            .purchasePrice(BigDecimal.valueOf(80))
            .sellingPriceExcludingTax(BigDecimal.valueOf(90))
            .category(autre)
            .build();

    Product deodorantGel =
        Product.builder()
            .barCode("4005900640253")
            .name("Gel déodorant")
            .taxRate(TaxRate.TWENTY)
            .purchasePrice(BigDecimal.valueOf(30))
            .sellingPriceExcludingTax(BigDecimal.valueOf(35))
            .category(cosmetique)
            .build();

    Product handCream =
        Product.builder()
            .barCode("6111251490619")
            .name("Crème pour les mains")
            .taxRate(TaxRate.TWENTY)
            .purchasePrice(BigDecimal.valueOf(20))
            .sellingPriceExcludingTax(BigDecimal.valueOf(25))
            .category(cosmetique)
            .build();

    Product cheese =
        Product.builder()
            .barCode("3073781154481")
            .name("Fromage")
            .taxRate(TaxRate.TWENTY)
            .purchasePrice(BigDecimal.valueOf(12))
            .sellingPriceExcludingTax(BigDecimal.valueOf(24))
            .category(cuisine)
            .build();

    products.add(waterBottle);
    products.add(oilBottle);
    products.add(deodorantGel);
    products.add(cheese);
    products.add(handCream);

    productRepository.saveAll(products);

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
