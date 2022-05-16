package be.technifutur.ApiGatewayV2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, MyFilter filter) {
        // csrf
        http.csrf()
                .disable();

        // filter
        http.addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION);

        // actuator
        http.authorizeExchange()
                .pathMatchers("/actuator/health").permitAll()
                .anyExchange()
                .permitAll();

        return http.build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(PasswordEncoder encoder) {
        User.UserBuilder builder = User.builder();

        UserDetails user = builder
                .username("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();

        UserDetails admin = builder
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new MapReactiveUserDetailsService(user, admin);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
