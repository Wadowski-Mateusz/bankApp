package bankApp.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_EMPLOYEE = "employee";
    public static final String ROLE_CLIENT = "client";

//    @Autowired
    private JwtAuthEntryPoint jwtAuthenticationEntryPoint;
//    private bankApp.services.UserDetailsService bankUserService

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers("/account").hasRole(ROLE_CLIENT)
//                                .requestMatchers("/account/**").hasRole(ROLE_CLIENT)
//                                .requestMatchers("/announcement/current/random").permitAll()
//                                .requestMatchers("/announcement/**").hasRole(ROLE_EMPLOYEE)
//                                .requestMatchers("/loans/**").hasRole(ROLE_CLIENT)
//                                .requestMatchers("/auth/login").permitAll()
//                                .requestMatchers("/auth/**").permitAll()
//                                .requestMatchers("/auth/login").permitAll()
//                                .requestMatchers("/user/verify/**").hasRole(ROLE_EMPLOYEE)
//                                .requestMatchers("/user/delete").hasRole(ROLE_CLIENT)
////                                .requestMatchers("/user_details/").hasRole()
//                                .requestMatchers("/user_options").hasRole(ROLE_CLIENT)
//                                .requestMatchers("/user_options/**").hasRole(ROLE_CLIENT)
//                                .requestMatchers("/transaction").hasRole(ROLE_CLIENT)
//                                .requestMatchers("/transaction/**").hasRole(ROLE_CLIENT)
//                                .anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
//                .httpBasic();
//                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .anyRequest().permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

}























