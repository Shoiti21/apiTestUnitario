package br.com.lojaCasaShow.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.lojaCasaShow.domain.Usuario;
import br.com.lojaCasaShow.repository.repUsuario;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{
	@Autowired
	private repUsuario repUsuario;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario=repUsuario.findByUsername(username);
		if(usuario==null) {
			throw new UsernameNotFoundException("Login não foi encontrado!");
		}
		return new User(usuario.getUsername(),usuario.getPassword(),true,true,true,true,usuario.getAuthorities());
	}
}
