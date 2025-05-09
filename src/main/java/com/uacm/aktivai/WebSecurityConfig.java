package com.uacm.aktivai;

import com.uacm.aktivai.service.impl.DetalleUsuarioServiceImpl;
import com.uacm.aktivai.util.RefererRedirectionAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private DetalleUsuarioServiceImpl detalleUsuarioServiceImpl;
	
	@Autowired
	private RefererRedirectionAuthenticationSuccessHandler manejadorDeAutenticacionExitosa;
	
	@Autowired
	private AuthenticationFailureHandler manejadorDeAutenticacionFallida;
	
	@Value("${spring.security.debug:false}")
	boolean securityDebug;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
		PasswordEncoder pswEncoder = new BCryptPasswordEncoder(11);
        return pswEncoder;
    }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     
        http
		.authorizeRequests()
		.antMatchers("/", "/index").permitAll()
		.antMatchers("/index.html").permitAll()
		.and()
        .formLogin()
        	.successHandler(manejadorDeAutenticacionExitosa)
            .loginPage("/index.html")
            .loginProcessingUrl("/j_spring_security_check")
            .defaultSuccessUrl("/home.html")
            .failureHandler(manejadorDeAutenticacionFallida)
            .usernameParameter("j_username")
            .passwordParameter("j_password")
        .permitAll()
        .and()
        .logout()
        .invalidateHttpSession(false)
        .logoutUrl("/j_spring_security_logout")
        .logoutSuccessUrl("/index.html").permitAll()
        .deleteCookies("JSESSIONID")
        .permitAll();
		
 
        http.headers().frameOptions().sameOrigin();
 
        return http.build();
    }
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(detalleUsuarioServiceImpl);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(securityDebug)
        .ignoring()
      	.antMatchers("/resources/**")
		.antMatchers("/css/**")
		.antMatchers("/js/**")
		.antMatchers("/webjars/**");
    }



}
