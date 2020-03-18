package br.com.lojaCasaShow.resources.services;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.com.lojaCasaShow.domain.Usuario;
import br.com.lojaCasaShow.exceptions.UsuarioNaoListado;

public class testUsuarioService {
	private UsuarioService service;
	@Before
	public void setup() {
		service=new UsuarioService();
	}
	@Rule
	public ErrorCollector erro=new ErrorCollector();
	@Test
	public void excUsuarioConsulta() {
		//busca por id
		try {
			service.busca(null);
		}catch(UsuarioNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
		//buscar por nome
		try {
			service.buscanome(null);
		}catch(UsuarioNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
	}
	@Test
	public void excUsuarioDB() {
		Usuario testusuario=new Usuario();
		//salvar
		try {
			service.salva(null);
		}catch(UsuarioNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
		//atualizar
		try {
			service.atualiza(null, testusuario);
		}catch(UsuarioNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
		//deletar
		try {
			service.deleta(null);;
		}catch(UsuarioNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
	}
}
