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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
			.antMatchers("/editor/**").hasRole("MEMBER")
			.antMatchers("/board/**").hasRole("MEMBER")
			.antMatchers("/lab/**").hasRole("MEMBER")
			.anyRequest().permitAll()
			.and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.usernameParameter("userId")
			.passwordParameter("password")
			.failureHandler(failureHandler)
			.successHandler(successHandler)
			.permitAll();
		http
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private LoginFailureHandler failureHandler;
	
	@Autowired
	private LoginSuccessHandler successHandler; 
	
}
