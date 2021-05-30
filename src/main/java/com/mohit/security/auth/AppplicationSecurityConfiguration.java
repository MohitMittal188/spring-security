package com.mohit.security.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.mohit.security.auth.AuthUserDetailsService;
import com.mohit.security.auth.filter.CustomAuthenticationFilter;
import com.mohit.security.auth.filter.TokenAuthenticationFilter;
import com.mohit.security.auth.providers.CustomDaoAuthenticationProvider;
import com.mohit.security.auth.providers.OtpAuthenticationProvider;
import com.mohit.security.auth.providers.UsernamePasswordAuthenticationProvider;
import com.mohit.security.auth.providers.TokenAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class AppplicationSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomDaoAuthenticationProvider customDaoAuthenticationProvider;
	
	@Autowired
	UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
	
	@Autowired
	OtpAuthenticationProvider otpAuthenticationProvider;
	
	@Autowired
	TokenAuthenticationProvider tokenAuthenticationProvider;
	
	@Autowired
	CustomAuthenticationFilter customAuthenticationFilter;  
	
	@Autowired
	TokenAuthenticationFilter tokenAuthenticationFilter;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.cors(c->{
//			CorsConfigurationSource cs = r ->{
//				CorsConfiguration cc = new CorsConfiguration();
//				cc.setAllowedOrigins(Arrays.asList("*"));
//				cc.setAllowedMethods(Arrays.asList("GET","POST"));
//				return cc;
//			};
//			
//			c.configurationSource(cs);
//		});
		
		http.cors();
		
		http
		.csrf().disable()
		.addFilterAt(customAuthenticationFilter, BasicAuthenticationFilter.class)
		.addFilterAfter(tokenAuthenticationFilter, CustomAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/send/otp","/check/otp","/register","/login/otp").permitAll()
		.anyRequest().authenticated()
		;
//		.and()
//		.httpBasic();
		
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(usernamePasswordAuthenticationProvider)
			.authenticationProvider(otpAuthenticationProvider)
			.authenticationProvider(tokenAuthenticationProvider);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
