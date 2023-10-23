package bankApp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static bankApp.Consts.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/test").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/test/**").permitAll()
                .requestMatchers("/account/**").hasRole(ROLE_CLIENT)
                .requestMatchers("/announcement/current/random").permitAll()
                .requestMatchers("/announcement/**").hasRole(ROLE_EMPLOYEE)
                .requestMatchers("/auth").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/loans").hasRole(ROLE_CLIENT)
                .requestMatchers("/loans/**").hasRole(ROLE_CLIENT)
                .requestMatchers("/transaction/**").hasRole(ROLE_CLIENT)
                .requestMatchers("/transaction").hasRole(ROLE_CLIENT)
                .requestMatchers("/user/verify/**").hasRole(ROLE_EMPLOYEE)
                .requestMatchers("/user/delete").hasRole(ROLE_CLIENT)
                .requestMatchers("/user/**").hasRole(ROLE_ADMIN)
                .requestMatchers("/user").hasRole(ROLE_ADMIN)
                .requestMatchers("/user_options/**").hasRole(ROLE_CLIENT)
                .requestMatchers("/user_options").hasRole(ROLE_CLIENT)
                .requestMatchers("/test").permitAll()
                .requestMatchers("/test/logged").authenticated()
                .requestMatchers("/test/client").hasRole(ROLE_CLIENT)
                .requestMatchers("/test/employee").hasRole(ROLE_EMPLOYEE)
                .requestMatchers("/test/isNotAdmin").hasAnyRole(ROLE_CLIENT, ROLE_EMPLOYEE) // ?
                .anyRequest().hasRole(ROLE_ADMIN)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()))
        ;


        return http.build();
    }
}
