package KJW.MBTIcoummunity.Security;

import jakarta.servlet.DispatcherType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationFailureHandler failureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().
                authorizeRequests(request -> request.
                        dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll().
                        requestMatchers(
                                new AntPathRequestMatcher("/signup"),
                                new AntPathRequestMatcher("/api/*"),
                                new AntPathRequestMatcher("/login/**")).permitAll().
                        anyRequest().authenticated()).
                formLogin(login -> login.
                        loginProcessingUrl("/login").
                        usernameParameter("email").
                        passwordParameter("password").
                        failureHandler(failureHandler).
                        defaultSuccessUrl("/login/success", true)
                        .permitAll())
                .logout(withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
