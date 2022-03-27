package com.ite.paulacasadogarcia.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * He optado por no encriptar las contraseñas. En el caso de hacerlo, usaría
 * el método .encode(usuario.getPassword()) en el Controller
 */


@EnableWebSecurity
@Configuration
public class Security extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private DataSource source;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(source)
				.usersByUsernameQuery("select username,password,enabled from Usuarios where username=?")
				.authoritiesByUsernameQuery("select u.username, p.descripcion from Usuario_Perfiles up"
						+ " inner join Usuarios u on u.username = up.username "
						+ " inner join Perfiles p on p.id_perfil = up.id_Perfil where u.username=?");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/registro", "/login", "/sesionIniciada").permitAll()
				.antMatchers("/admon/**").hasAnyAuthority("ADMON")
				.antMatchers("/cliente/**").hasAnyAuthority("CLIENTE")
				.anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/sesionIniciada").permitAll();
	}
}
