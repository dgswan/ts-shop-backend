package com.tsystems.tshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


 	@Override
 	public void configure(WebSecurity web) throws Exception {
 		web.ignoring()
 		// Spring Security should completely ignore URLs starting with /resources/
 				.antMatchers("/resources/**");
 	}

 	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http
 		.authorizeRequests()
 			.antMatchers("/api/**").hasRole("USER")
 			.anyRequest().authenticated()
 			.and()
 				// Possibly more configuration ...
 		.httpBasic();
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
	
}
