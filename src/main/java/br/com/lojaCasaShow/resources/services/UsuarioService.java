package br.com.lojaCasaShow.resources.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import org.springframework.beans.BeanUtils;
import br.com.lojaCasaShow.domain.Usuario;
import br.com.lojaCasaShow.exceptions.UsuarioExiste;
import br.com.lojaCasaShow.exceptions.UsuarioNaoListado;
import br.com.lojaCasaShow.repository.repUsuario;

@Service
public class UsuarioService {
	@Autowired
	private repUsuario repUsuario;
	
	public List<Usuario> lista(){
		return repUsuario.findAll();
	}
	public Usuario busca(Long id){
		Optional<Usuario> usuario=repUsuario.findById(id);
		if(usuario.isEmpty()) {
			throw new UsuarioNaoListado("Não encontramos esse Usuário!");
		}
		return usuario.get();
	}
	public void salva(Usuario usuario) {
		try {
			if(usuario.getId()==null) {
				repUsuario.save(usuario);
			}
			else {
				throw new UsuarioNaoListado("Não encontramos esse Usuário!");
			}
		}catch(DataIntegrityViolationException e) {
			throw new UsuarioExiste("Não encontramos esse Usuário!");
		}
	}
	public void atualiza(Long id, Usuario usuario) {
		Optional<Usuario> usuarioDB=repUsuario.findById(id);
		if(Objects.isNull(repUsuario.findById(id).orElse(null))) {
			throw new UsuarioNaoListado("Não encontramos esse Usuário!");
		}
		usuario.setId(id);
		BeanUtils.copyProperties(usuarioDB.get(),usuario, "role", "senha");
		repUsuario.save(usuario);
	}
	public void deleta(Long id) {
		Optional<Usuario> usuario=repUsuario.findById(id);
		try {
			repUsuario.delete(usuario.get());
		} catch (NoSuchElementException e) {
			throw new UsuarioNaoListado("Não encontramos esse Usuário!");
		}
	}
	public List<Usuario> buscanome(String username){
		return repUsuario.findByUsernameContaining(username);
	}
}
