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
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Henry on 14/06/17.
 */
@Configuration
@ComponentScan(basePackageClasses = AuthUserService.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final AccessDeniedHandler accessDeniedHandler;

	@Autowired
	public SecurityConfiguration(UserDetailsService userDetailsService,
								 AccessDeniedHandler accessDeniedHandler) {
		this.userDetailsService = userDetailsService;
		this.accessDeniedHandler = accessDeniedHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/index").permitAll()
				.and().formLogin().loginPage("/access/login").defaultSuccessUrl("/account").permitAll()
				.and().rememberMe().tokenValiditySeconds(2419200).key("escapyKey")
				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
				.and().authorizeRequests()
				.antMatchers("/account/**", "/rel/res/private/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/account/**", "/rel/res/private/**").hasRole("USER")
				.antMatchers("/account/admin/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/account/admin/**").hasRole("ADMIN");
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(userDetailsService);
	}


}
