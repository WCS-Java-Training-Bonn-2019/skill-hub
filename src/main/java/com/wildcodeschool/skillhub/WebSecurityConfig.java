package com.wildcodeschool.skillhub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wildcodeschool.skillhub.service.UserDetailsServiceImpl;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	

	private final UserDetailsServiceImpl userDetailsService;
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public WebSecurityConfig (UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/images/**", "/style.css", "/webjars/**").permitAll()
				.antMatchers("/skills").hasAnyRole("ADMIN", "USER")
				.anyRequest().hasRole("ADMIN")
				.and()
			.formLogin()
				.and()
			.httpBasic();
	}
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
				
			// .inMemoryAuthentication()
			//		.withUser("user").password("{noop}password").roles("USER");
			
	}

}
