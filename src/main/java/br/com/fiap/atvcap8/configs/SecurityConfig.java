package br.com.fiap.atvcap8.configs;

import br.com.fiap.atvcap8.libs.PasswordCrypto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private TokenVerification tokenVerification;

    public SecurityConfig(TokenVerification tokenVerification) {
        this.tokenVerification = tokenVerification;
    }

    @Bean
    public SecurityFilterChain filterSecurityChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers("/api/v1/public/**").permitAll()
                        // TRUCK
                        .requestMatchers(HttpMethod.GET, "/api/v1/truck/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/truck").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/truck").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/truck").hasRole("ADMIN")
                        // ROUTE
                        .requestMatchers(HttpMethod.GET, "/api/v1/route/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/route").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/route").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/route").hasRole("ADMIN")
                        // CONTAINER
                        .requestMatchers(HttpMethod.GET, "/api/v1/container/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/container").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/container").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/container").hasRole("ADMIN")
                        // COLLECTION
                        .requestMatchers(HttpMethod.GET, "/api/v1/collection/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/collection").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/collection").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/collection").hasRole("ADMIN")
                        // USER
                        .requestMatchers("/api/v1/user/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(
                        tokenVerification,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordCrypto.getDefaultEncoder();
    }
}
