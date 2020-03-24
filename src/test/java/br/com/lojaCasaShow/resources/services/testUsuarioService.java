package br.com.lojaCasaShow.resources.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.lojaCasaShow.domain.Roles;
import br.com.lojaCasaShow.domain.Usuario;
import br.com.lojaCasaShow.exceptions.UsuarioNaoListado;
import br.com.lojaCasaShow.repository.repUsuario;

@RunWith(Parameterized.class)
public class testUsuarioService {
	@Rule
	public ErrorCollector erro=new ErrorCollector();
	@InjectMocks
	private UsuarioService service;
	@Mock
	private repUsuario repUsuario;
	@Parameter
	public Usuario usuariotest;
	@Parameter(value=1)
	public String status;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Parameters(name="{1}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][] {
			{new Usuario(1L,"NomeTest","senha123",Roles.ROLE_CLIENT),"Test"},
			{new Usuario(null,null,"senha123",Roles.ROLE_CLIENT),"Test_IdNull"}
		});
	}
	@Test
	public void excUsuarioConsultaId() {
		//busca por id
		try {
			Mockito.when(repUsuario.findById(1L)).thenReturn(Optional.of(usuariotest));
			Mockito.when(repUsuario.findById(null)).thenReturn(Optional.empty());
			Usuario resultado=service.busca(usuariotest.getId());
			erro.checkThat(resultado, CoreMatchers.is(CoreMatchers.not(nullValue())));
		}catch(UsuarioNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
	}
	@Test
	public void excUsuarioConsultaNome() {
		//buscar por nome
		Mockito.when(repUsuario.findByUsernameContaining("Test1")).thenReturn(Mockito.anyList());
		Mockito.when(repUsuario.findByUsernameContaining(null)).thenReturn(null);
		List<Usuario> resultado=service.buscanome(usuariotest.getUsername());
		erro.checkThat(resultado, CoreMatchers.is(CoreMatchers.not(nullValue())));
	}
	@Test
	public void excUsuarioSalvar() {
		//salvar
		try {
			service.salva(usuariotest);
			Mockito.verify(repUsuario).save(usuariotest);
			erro.checkThat(usuariotest.getId(), CoreMatchers.is(nullValue()));
		}catch(UsuarioNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
		/*
		catch(UsuarioExiste e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
		*/
	}
	@Test
	public void excUsuarioEditar() {
		//atualizar
		try {
			Usuario exusuario=new Usuario(1L,"NomeTest","senha123",Roles.ROLE_CLIENT);
			Mockito.when(repUsuario.findById(1L)).thenReturn(Optional.of(exusuario));
			Mockito.when(repUsuario.findById(null)).thenReturn(Optional.empty());
			service.atualiza(usuariotest.getId(), usuariotest);
			Mockito.verify(repUsuario).save(usuariotest);
		}catch(UsuarioNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
	}
	@Test
	public void excUsuarioDeletar() {
		//deletar
		try {
			Mockito.when(repUsuario.findById(1L)).thenReturn(Optional.of(usuariotest));
			Mockito.when(repUsuario.findById(null)).thenReturn(Optional.empty());
			service.deleta(usuariotest.getId());
			Mockito.verify(repUsuario).delete(usuariotest);
		}catch(UsuarioNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
	}
}
