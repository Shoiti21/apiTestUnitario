package br.com.lojaCasaShow.resources.services;

import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.CoreMatchers;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.lojaCasaShow.domain.Casa;
import br.com.lojaCasaShow.exceptions.CasaNaoListado;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

@RunWith(Parameterized.class)
public class testCasaService {
	private CasaService service;
	@Parameter
	public Casa casatest;
	@Parameter(value=1)
	public String status;
	@Before
	public void setup() {
		service=new CasaService();
	}
	@Parameters(name="{1}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][] {
			{new Casa((long)1,"Test1","Test1"),"Test"},
			{new Casa((long)1,"Test1","Test1"),"Test_IdNull"},
			{new Casa((long)1,"Test1","Test1"),"Test_LocalNull"},
			{new Casa((long)1,"Test1","Test1"),"Test_NomeNull"}
		});
	}
	@Rule
	public ErrorCollector erro=new ErrorCollector();
	@Test
	public void excCasaConsulta() {
		//buscar
		try {
			service.busca(null);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
	}
	@Test
	public void excCasaDB() {
		//salvar
		try {
			service.salvar(casatest);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
		//atualizar
		try {
			service.atualiza(null, casatest);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
		//deletar
		try {
			service.deleta(null);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
	}
}
