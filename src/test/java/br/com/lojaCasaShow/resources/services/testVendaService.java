package br.com.lojaCasaShow.resources.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.lojaCasaShow.domain.Casa;
import br.com.lojaCasaShow.domain.Evento;
import br.com.lojaCasaShow.domain.Genero;
import br.com.lojaCasaShow.domain.Roles;
import br.com.lojaCasaShow.domain.Usuario;
import br.com.lojaCasaShow.domain.Venda;
import br.com.lojaCasaShow.exceptions.VendaNaoListado;

@RunWith(Parameterized.class)
public class testVendaService {
	private VendaService service;
	private static Usuario usuariotest=new Usuario((long)1,"NomeTest","senha123",Roles.ROLE_CLIENT);
	private static Casa casatest=new Casa((long)1,"Test1","Test1");
	private static Evento eventotest=new Evento((long)1,"NomeTest",Genero.ROCK,casatest,new Date(),200,new BigDecimal(100.2));
	@Parameter
	public Venda vendatest;
	@Parameter(value=1)
	public String status;
	@Before
	public void setup() {
		service=new VendaService();
	}
	@Parameters(name= "{1}")
	public static Collection<Object[]> getParametro(){
		return Arrays.asList(new Object[][]{
				{new Venda((long)1,usuariotest,new Date(),eventotest),"Test"},
				{new Venda(null,usuariotest,new Date(),eventotest),"Test_IdNull"},
				{new Venda((long)1,null,new Date(),eventotest),"Test_UsuarioNull"},
				{new Venda((long)1,usuariotest,null,eventotest),"Test_DataNull"},
				{new Venda((long)1,usuariotest,new Date(),null),"Test_EventoNull"}
		});
	}
	@Rule
	public ErrorCollector erro=new ErrorCollector();
	@Test
	public void excVendaConsulta() {
		try {
			service.busca(null);
		}catch(VendaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não foi encontramos essa Venda!"));
		}
	}
	@Test
	public void excVendaDB() {
		//salvar
		try {
			service.salva(vendatest);
		}catch(VendaNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não foi encontramos essa Venda!"));
		}
		//deletar
		try {
			service.deleta(null);
		}catch(VendaNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não foi encontramos essa Venda!"));
		}
	}
}
