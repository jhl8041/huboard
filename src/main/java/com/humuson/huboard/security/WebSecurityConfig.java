package com.humuson.huboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.java.Log;


@Log
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		log.info("security config.................");
				
		http.csrf().disable().authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/goView").hasRole("MEMBER")
			.antMatchers("/").hasRole("MEMBER")
			.anyRequest().permitAll()
			.and()
		.formLogin()
			.loginPage("/goLogin")
			.loginProcessingUrl("/doLogin")
			.usernameParameter("userId")
			.passwordParameter("password")
			.permitAll();
		
		http
			.logout()
			.logoutUrl("/doLogout")
			.logoutSuccessUrl("/goLogin")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("king").password(passwordEncoder().encode("king")).roles("ADMIN");
//		
//		auth.inMemoryAuthentication()
//		.withUser("person").password(passwordEncoder().encode("person")).roles("USER");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
