package com.example.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.services.UserAuthenticator;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private HttpSecurity security;
	// .requestMatchers("/event/creation").hasAnyRole("coach","admin")

	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain setUpSecurity() throws Exception {
		System.out.println("this is new security filter chain");
		return security.csrf(request -> request.disable())
				.authorizeRequests(request -> request
						.requestMatchers("/event/getCreationpage", "/event/creation", "/event/getAllEvent")
						.hasAnyRole("coach", "admin")
						.requestMatchers("/login", "/css/**", "/image/**", "/user/signup/reg", "/user/signup")
						.permitAll()
						.anyRequest()
						.authenticated())
				// return
				// security.authorizeHttpRequests(request->request.anyRequest().permitAll())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home"))
				.httpBasic(basic -> basic.disable())

				.build();

	}

}
