package br.com.salao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SalaoWebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("cacofut").password(encoder.encode("123456")).roles("EMPLOYEE", "MANAGER");
		auth.inMemoryAuthentication().withUser("joao").password(encoder.encode("123456")).roles("MANAGER");
		auth.inMemoryAuthentication().withUser("leonardo").password(encoder.encode("123456")).roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll() // permit public access to home page
				.antMatchers("/employees" ).hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
				.antMatchers("/leaders/**").hasRole("MANAGER")
				.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/show-login-page")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
		 	.and()
		 		.logout().logoutSuccessUrl("/")
		 		.permitAll()
		 	.and()
		 		.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
	
	

	
}
  