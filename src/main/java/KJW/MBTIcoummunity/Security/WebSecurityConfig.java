package KJW.MBTIcoummunity.Security;

import KJW.MBTIcoummunity.Security.ExceptionFilter.JwtAuthenticationEntryPoint;
import KJW.MBTIcoummunity.Security.JWT.JwtAuthenticationFilter;
import KJW.MBTIcoummunity.Security.JWT.JwtTokenProvider;
import jakarta.servlet.DispatcherType;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationFailureHandler failureHandler;
    private final JwtTokenProvider jwtTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    private final JwtAuthenticationEntryPoint JwtAuthenticationEntryPoint;

    //세션 관련
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable(); // 외부 POST 요청을 받아야하니 csrf는 꺼준다.
        http.cors(); // ⭐ CORS를 커스텀하려면 이렇게
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests(request -> request.
                        dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll().
                        requestMatchers(
                                new AntPathRequestMatcher("/auth/**"),
                                new AntPathRequestMatcher("/ws/**"),
                                new AntPathRequestMatcher("/chat/**")).permitAll().
                        anyRequest().authenticated())
                .httpBasic().disable()
                .formLogin().disable()
                /*.formLogin(login -> login.
                loginProcessingUrl("/login").
                usernameParameter("email").
                passwordParameter("password").
                failureHandler(failureHandler).
                defaultSuccessUrl("/login/success", true)
                .permitAll())*/
                //.logout(withDefaults())
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(JwtAuthenticationEntryPoint);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
