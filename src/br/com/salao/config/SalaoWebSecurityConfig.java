package br.com.salao.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.salao.service.UserService;

@Configuration
@EnableWebSecurity
public class SalaoWebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource securityDataSource;
	
	@Autowired
	private UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		//auth.inMemoryAuthentication().withUser("cacofut").password(encoder.encode("123456")).roles("EMPLOYEE", "MANAGER");
		//auth.inMemoryAuthentication().withUser("joao").password(encoder.encode("123456")).roles("MANAGER");
		//auth.inMemoryAuthentication().withUser("leonardo").password(encoder.encode("123456")).roles("ADMIN");
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
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
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	
}
  