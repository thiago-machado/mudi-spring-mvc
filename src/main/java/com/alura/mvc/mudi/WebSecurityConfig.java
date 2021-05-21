package com.alura.mvc.mudi;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	/**
	 * Para exemplo de configuração, acessar: https://spring.io/guides/gs/securing-web/
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Todo request precisa de autenticação
		http
		.authorizeRequests()
			.anyRequest().authenticated()
		.and()
		// Toda página que precisar de autenticação, cairá nessa linha e chamará "/login". Quando o login é feito com sucesso, a pessoa acessará "/home"
		.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll()) 
		.logout(logout -> logout.logoutUrl("/logout")); // Todo logout cairá nessa requisição e deslogará o usuário
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		// Cadastrando um usuário na base de dados automaticamente
		// Descomentar as linhas abaixo para cadastrar outro usuário
		/*UserDetails user =
				 User.builder()
					.username("thiago")
					.password(encoder.encode("123"))
					.roles("ADM")
					.build();*/
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(encoder);
			//.withUser(user); // Essa linha é necessária quando desejamos criar um usuário como nas linhas acima
	}

}
