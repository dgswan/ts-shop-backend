package com.tsystems.tshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


 	@Override
 	public void configure(WebSecurity web) throws Exception {
 		web.ignoring()
 		// Spring Security should completely ignore URLs starting with /resources/
 				.antMatchers("/resources/**", "/*.js", "/*.css");
 	}

 	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
 		.authorizeRequests()
                .antMatchers("/").permitAll()
				.antMatchers("/api/login").permitAll()
 			.antMatchers("/api/**").permitAll()
 			.anyRequest().permitAll()
 			.and()
 				// Possibly more configuration ...
 		.httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint())
				.and()
				.logout().logoutUrl("/api/logout")
				.logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");
 	}

 	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth
 		// enable in memory based authentication with a user named "user" and "admin"
 		.inMemoryAuthentication()
 		.passwordEncoder(NoOpPasswordEncoder.getInstance())
 		.withUser("user").password("password").roles("USER")
 			.and()
 		.withUser("admin").password("password").roles("USER", "ADMIN");
 	}

 	@Bean
 	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}
	
}
