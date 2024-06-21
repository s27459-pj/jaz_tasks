package pw.karczewski.tasks.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@EnableWebSecurity
@ConfigurationProperties("security.config")
public class SecurityConfig {
    private String frontendRedirectUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/login").permitAll();
                    registry.anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .cors(c -> c.configurationSource(r -> {
                    var cors = new CorsConfiguration();
                    cors.addAllowedOrigin(frontendRedirectUrl);
                    cors.addAllowedMethod("*");
                    cors.addAllowedHeader("*");
                    cors.setAllowCredentials(true);
                    return cors;
                }))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .oauth2Login(l -> l.defaultSuccessUrl(frontendRedirectUrl, true))
                .build();
    }
}
