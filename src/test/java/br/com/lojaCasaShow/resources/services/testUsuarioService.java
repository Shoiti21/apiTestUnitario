package br.com.lojaCasaShow.resources.services;

import java.util.Collection;
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

import br.com.lojaCasaShow.domain.Roles;
import br.com.lojaCasaShow.domain.Usuario;
import br.com.lojaCasaShow.exceptions.UsuarioNaoListado;

@RunWith(Parameterized.class)
public class testUsuarioService {
	private UsuarioService service;
	@Parameter
	public Usuario usuariotest;
	@Parameter(value=1)
	public String status;
	@Before
	public void setup() {
		service=new UsuarioService();
	}
	@Parameters(name="{1}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][] {
			{new Usuario((long)1,"NomeTest","senha123",Roles.ROLE_CLIENT),"Test"},
			{new Usuario(null,"NomeTest","senha123",Roles.ROLE_CLIENT),"Test_IdNull"},
			{new Usuario((long)1,null,"senha123",Roles.ROLE_CLIENT),"Test_UsernameNull"},
			{new Usuario((long)1,"NomeTest",null,Roles.ROLE_CLIENT),"Test_SenhaNull"},
			{new Usuario((long)1,"NomeTest","senha123",null),"Test_RoleNull"}
		});
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
		//salvar
		try {
			service.salva(usuariotest);
		}catch(UsuarioNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Usuário!"));
		}
		//atualizar
		try {
			service.atualiza(null, usuariotest);
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
