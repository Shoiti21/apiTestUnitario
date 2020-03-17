package br.com.lojaCasaShow.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		.antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
		.antMatchers(HttpMethod.GET,"/casa").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/casa").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/casa").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/evento").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/evento").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/evento").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/users").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/users").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/users").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/users/nome").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/venda").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/venda").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/venda").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.csrf().disable()
		;
	}
}
