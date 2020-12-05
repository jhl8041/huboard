package com.humuson.huboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		http.csrf().disable().authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/editor/**").hasAnyRole("ADMIN,MEMBER")
			.antMatchers("/board/**").hasAnyRole("ADMIN,MEMBER")
			.antMatchers("/lab/**").hasAnyRole("ADMIN,MEMBER")
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
