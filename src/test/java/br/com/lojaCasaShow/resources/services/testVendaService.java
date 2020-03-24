package br.com.lojaCasaShow.resources.services;

import static org.hamcrest.CoreMatchers.nullValue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

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

import br.com.lojaCasaShow.domain.Casa;
import br.com.lojaCasaShow.domain.Evento;
import br.com.lojaCasaShow.domain.Genero;
import br.com.lojaCasaShow.domain.Roles;
import br.com.lojaCasaShow.domain.Usuario;
import br.com.lojaCasaShow.domain.Venda;
import br.com.lojaCasaShow.exceptions.VendaNaoListado;
import br.com.lojaCasaShow.repository.repVenda;

@RunWith(Parameterized.class)
public class testVendaService {
	private static Usuario usuariotest=new Usuario((long)1,"NomeTest","senha123",Roles.ROLE_CLIENT);
	private static Casa casatest=new Casa((long)1,"Test1","Test1");
	private static Evento eventotest=new Evento((long)1,"NomeTest",Genero.ROCK,casatest,new Date(),200,new BigDecimal(100.2));
	@Rule
	public ErrorCollector erro=new ErrorCollector();
	@InjectMocks
	private VendaService service;
	@Mock
	private repVenda repVenda;
	@Parameter
	public Venda vendatest;
	@Parameter(value=1)
	public String status;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Parameters(name= "{1}")
	public static Collection<Object[]> getParametro(){
		return Arrays.asList(new Object[][]{
				{new Venda((long)1,usuariotest,new Date(),eventotest),"Test"},
				{new Venda(null,usuariotest,new Date(),eventotest),"Test_IdNull"}
		});
	}
	@Test
	public void excVendaConsulta() {
		try {
			Mockito.when(repVenda.findById(1L)).thenReturn(Optional.of(vendatest));
			Mockito.when(repVenda.findById(null)).thenReturn(Optional.empty());
			Venda resultado=service.busca(vendatest.getId());
			erro.checkThat(resultado, CoreMatchers.is(CoreMatchers.not(nullValue())));
		}catch(VendaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não foi encontramos essa Venda!"));
		}
	}
	@Test
	public void excVendaSalvar() {
		//salvar
		try {
			service.salva(vendatest);
			Mockito.verify(repVenda).save(vendatest);
			erro.checkThat(vendatest.getId(), CoreMatchers.is(nullValue()));
		}catch(VendaNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não foi encontramos essa Venda!"));
		}
	}
	@Test
	public void excVendaDeletar() {
		//deletar
		try {
			Mockito.when(repVenda.findById(1L)).thenReturn(Optional.of(vendatest));
			Mockito.when(repVenda.findById(null)).thenReturn(Optional.empty());
			service.deleta(vendatest.getId());
			Mockito.verify(repVenda).delete(vendatest);
		}catch(VendaNaoListado e) {
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Venda!"));
		}
	}
}
