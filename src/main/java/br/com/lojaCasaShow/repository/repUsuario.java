package br.com.lojaCasaShow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojaCasaShow.domain.Usuario;

public interface repUsuario extends JpaRepository<Usuario, Long>{
	Usuario findByUsername(String Username);
	List<Usuario> findByUsernameContaining(String username);
}
