package com.humuson.huboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.java.Log;


@Log
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().and().authorizeRequests()
//		.antMatchers("/gologin").permitAll()
//		.and().logout().permitAll()
//		.and().formLogin()
//		.loginPage("/gologin")
//		.loginProcessingUrl("/proceed_login")
//		.defaultSuccessUrl("/");
		
		log.info("security config.................");
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/gojoin").permitAll();
		http.authorizeRequests().antMatchers("/addressDo").permitAll();
		http.authorizeRequests().antMatchers("/idcheck").permitAll();
		
		
		http.httpBasic()
			.and()
        .authorizeRequests()
            .antMatchers("/resources/**").permitAll()
            .and()
        .authorizeRequests()
            .antMatchers("/addressPop").hasAnyRole("ADMIN")
            .anyRequest().authenticated();
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("king").password(passwordEncoder().encode("king")).roles("ADMIN");
		
		auth.inMemoryAuthentication()
		.withUser("person").password(passwordEncoder().encode("person")).roles("USER");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
