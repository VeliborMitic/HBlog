package net.henryco.hblog.configurations;

import net.henryco.hblog.mvc.servives.account.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Henry on 14/06/17.
 */
@Configuration
@ComponentScan(basePackageClasses = AuthUserService.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	@Autowired
	public SecurityConfiguration(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/index").permitAll()
				.and().formLogin()
				.loginPage("/acc/login")
				.permitAll()
				.and().logout().logoutSuccessUrl("/").permitAll()
				.and().authorizeRequests()
				.antMatchers("/account").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/account").hasRole("USER");
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(userDetailsService);
	}


}
