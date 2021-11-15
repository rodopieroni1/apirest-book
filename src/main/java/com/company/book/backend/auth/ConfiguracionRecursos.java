package com.company.book.backend.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ConfiguracionRecursos extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

http.authorizeRequests().antMatchers(HttpMethod.GET, "/v1/categorias", "/v1/categorias/{id}", "/v1/libros", "/v1/libros/{id}", "/v1/alumnos", "/v1/alumnos/{id}")
	.permitAll().antMatchers(HttpMethod.PUT, "/v1/alumnos","/v1/alumnos/{id}")
	.permitAll().antMatchers(HttpMethod.DELETE,"/v1/alumnos/{id}")
	.permitAll().antMatchers(HttpMethod.POST, "/v1/libros", "/v1/categorias", "/v1/alumnos", "/v1/alumnos/{id}").permitAll().anyRequest().authenticated();
	}
}
