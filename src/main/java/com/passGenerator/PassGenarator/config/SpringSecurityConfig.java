package com.passGenerator.PassGenarator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				// .antMatchers(HttpMethod.GET, "/**").hasAnyRole("user", "admin") full access to everyone
				.antMatchers(HttpMethod.GET, "/senha/proximasenha").hasRole("admin")
				.antMatchers(HttpMethod.GET, "/protocolo/all").hasRole("admin")
				.antMatchers(HttpMethod.GET, "/pessoa/all").hasRole("admin")
				.antMatchers(HttpMethod.GET, "/senhas/all").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.POST, "/senhas/gerar").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.POST, "/pessoa/gerar").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.POST, "/protocolo/gerar").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.POST, "/protocolo/imprimir").hasAnyRole("admin")
				.antMatchers(HttpMethod.GET, "/protocolo/tipos").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and().httpBasic().and()
				.exceptionHandling().accessDeniedPage("/error");
	}

	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowUrlEncodedSlash(true);
		firewall.setAllowSemicolon(true);
		firewall.setAllowUrlEncodedDoubleSlash(true);
		return firewall;
	}

}