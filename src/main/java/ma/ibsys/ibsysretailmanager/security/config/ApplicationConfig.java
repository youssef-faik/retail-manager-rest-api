package ma.ibsys.ibsysretailmanager.security.config;

import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 
 This class represents the configuration for the application.
 It defines beans for user details service, authentication provider, password encoder, and authentication manager.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UserRepository repository;
  
  /**
   
   Creates a bean for user details service that fetches the user details from the UserRepository
   based on the email address.
   @return UserDetailsService - the user details service
   @throws UsernameNotFoundException if user not found with provided username
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return username ->
        repository
            .findByEmail(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User not found with username " + username));
  }
  
  /**
   
   Creates a bean for authentication provider that uses the user details service and password
   encoder.
   @return AuthenticationProvider - the authentication provider
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }
  
  /**
   Creates a bean for password encoder that uses the BCrypt algorithm.
   @return PasswordEncoder - the password encoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  /**
   Creates a bean for authentication manager that gets the authentication manager from the
   AuthenticationConfiguration.
   @param config - the authentication configuration
   @return AuthenticationManager - the authentication manager
   @throws Exception if there is an error getting the authentication manager
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }
}
