package sisgerim.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sisgerim.backend.services.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        try {
            return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                    authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/corretor").permitAll()
                    .requestMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
                    .requestMatchers(HttpMethod.GET, "/v3/api-docs/swagger-config").permitAll()
                    .requestMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
                    .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        try {
            return configuration.getAuthenticationManager();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
