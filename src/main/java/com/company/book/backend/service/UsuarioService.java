package com.company.book.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.book.backend.model.Usuario;
import com.company.book.backend.model.dao.IUsuarioDao;

@Service
public class UsuarioService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioDao.findByNombreUsuario(username);
		if(usuario == null) {
		log.error("Error, el usuario existe");
		throw new UsernameNotFoundException("Error, el usraio no existe");
		}
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role->new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> log.info("Role: "+ authority.getAuthority()))//impre todos los autoritis 
				.collect(Collectors.toList());
	return new User(usuario.getNombreUsuario(), usuario.getPassword(), usuario.getHabilitado(), true, true, true, authorities);
	}

}
