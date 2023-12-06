package com.security.jdbc.auth.securityconfig;


import java.text.Normalizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		UserDetails adminUser=User.withDefaultPasswordEncoder()
				          .username("naresh")
				          .password("naresh@45")
				          .authorities("ADMIN")
				          .build();
		
		UserDetails normalUser=User.withDefaultPasswordEncoder()
				.username("yelladri")
				.password("yelladri@45")
				.authorities("USER")
				.build();
		
		return new InMemoryUserDetailsManager(adminUser,normalUser);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests((req)->
				req.antMatchers("/contact").permitAll()
				.anyRequest().authenticated()
				).formLogin().and().httpBasic();
		return httpSecurity.build(); 
	}
	
}
