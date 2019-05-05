package com.adi.springresttasksbmp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//Setup a user with role ROLE_USER
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("username").password("{noop}password")
				.roles("USER");
	}

	//Setup basic authentication and disable CSRF
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().csrf().disable();
	}
}
