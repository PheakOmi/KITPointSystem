package com.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	

	private static final String[] SUPER_ADMIN_MATCHERS = {
		"/admin/**",
		"/setting/**",
        "/newUser/**",
        "/batch/**",
        "/createBatch/**",
        "/kitpoint/**",
        "/projectCategory/**",
        "/valuePerHour/**",
        "/project/**",
		"/projectDetail/**",
		"/ProjectNTask",
		"/updateTask**",
		"/taskDetail/**",
		"/task/**",
		"/profile/**",
 
    };
	private static final String[] ADMIN_MATCHERS = {
		"/projectAdminView",
		"/projectDetailAdminView",
		"/taskDetailAdminView",
		"/taskAdminView",
		"/updateTask**",
		"/ProjectNTaskAdmin",
		"/taskDetail",
		
   
    };
	private static final String[] PUBLIC_MATCHERS = {
		"/projectUserView",
		"/projectDetailUserView",
		"/taskDetailUserView",
		"/taskUserView",
		"/updateTask**",
		"/ProjectNTask**",
   
    };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests()
		.antMatchers(SUPER_ADMIN_MATCHERS).access("hasRole('ROLE_ADMIN')").and().formLogin().loginPage("/login").failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout().logoutSuccessUrl("/login?logout")
				.and().csrf()
				.and().exceptionHandling().accessDeniedPage("/403");
	/*access right for admin */
		http.authorizeRequests()
        .antMatchers(ADMIN_MATCHERS).access("hasRole('ROLE_N_ADMIN')").and().formLogin().loginPage("/login").failureUrl("/login?error")
		   .usernameParameter("username")
		   .passwordParameter("password")
		   .and().logout().logoutSuccessUrl("/login?logout")
		   .and().csrf()
		   .and().exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests()
        .antMatchers(PUBLIC_MATCHERS).access("hasRole('ROLE_USER')").and().formLogin().loginPage("/login").failureUrl("/login?error")
		   .usernameParameter("username")
		   .passwordParameter("password")
		   .and().logout().logoutSuccessUrl("/login?logout")
		   .and().csrf()
		   .and().exceptionHandling().accessDeniedPage("/403");
					
	}
		
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}



