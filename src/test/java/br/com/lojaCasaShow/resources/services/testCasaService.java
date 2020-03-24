package br.com.lojaCasaShow.resources.services;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
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
import br.com.lojaCasaShow.exceptions.CasaNaoListado;
import br.com.lojaCasaShow.repository.repCasa;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

@RunWith(Parameterized.class)
public class testCasaService {
	@Rule
	public ErrorCollector erro=new ErrorCollector();
	@InjectMocks
	private CasaService service;
	@Mock
	private repCasa repCasa;
	@Parameter
	public Casa casatest;
	@Parameter(value=1)
	public String status;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Parameters(name="{1}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][] {
			{new Casa(1L,"Test1","Test1"),"Test"},
			{new Casa(null,"Test1","Test1"),"Test_IdNull"}
		});
	}
	@Test
	public void excCasaConsulta() {
		//buscar
		try {
			Mockito.when(repCasa.findById(1L)).thenReturn(Optional.of(casatest));
			Mockito.when(repCasa.findById(null)).thenReturn(Optional.empty());
			Casa resultado=service.busca(casatest.getId());
			erro.checkThat(resultado, CoreMatchers.is(CoreMatchers.not(nullValue())));
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
	}
	@Test
	public void excCasaSalvar() {
		//salvar
		try {
			service.salvar(casatest);
			Mockito.verify(repCasa).save(casatest);
			erro.checkThat(casatest.getId(), CoreMatchers.is(nullValue()));
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
	}
	@Test
	public void excCasaEditar() {
		//atualizar
		try {
			Casa excasa=new Casa(1L,"Test1","Test1");
			Mockito.when(repCasa.findById(1L)).thenReturn(Optional.of(excasa));
			Mockito.when(repCasa.findById(null)).thenReturn(Optional.empty());
			service.atualiza(casatest.getId(), casatest);
			Mockito.verify(repCasa).save(casatest);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
	}
	@Test
	public void excCasaDeletar() {
		//deletar
		try {
			Mockito.when(repCasa.findById(1L)).thenReturn(Optional.of(casatest));
			Mockito.when(repCasa.findById(null)).thenReturn(Optional.empty());
			service.deleta(casatest.getId());
			Mockito.verify(repCasa).delete(casatest);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
	}
}
