package br.com.lojaCasaShow.resources.services;

import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.lojaCasaShow.domain.Venda;
import br.com.lojaCasaShow.exceptions.VendaNaoListado;

@RunWith(Parameterized.class)
public class testVendaService {
	private VendaService service;
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
				{new Venda(),"Test1"},
				{new Venda(),"Test2"}
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
