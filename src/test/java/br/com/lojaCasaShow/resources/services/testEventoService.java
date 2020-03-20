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
import org.mockito.Mockito;

import br.com.lojaCasaShow.domain.Casa;
import br.com.lojaCasaShow.domain.Evento;
import br.com.lojaCasaShow.domain.Genero;
import br.com.lojaCasaShow.exceptions.EventoNaoListado;
import br.com.lojaCasaShow.repository.repEvento;

@RunWith(Parameterized.class)
public class testEventoService {
	private EventoService service;
	private repEvento repEventoTest;
	private static Casa casatest=new Casa((long)1,"Test1","Test1");
	@Parameter
	public Evento eventotest;
	@Parameter(value=1)
	public String status;
	@Before
	public void setup() {
		service=new EventoService();
		repEventoTest=Mockito.mock(repEvento.class);
		service.setRepEvento(repEventoTest);
	}
	@Parameters(name="{1}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][]{
			{new Evento((long)1,"NomeTest",Genero.ROCK,casatest,new Date(),200,new BigDecimal(100.2)),"Test"},
			{new Evento(null,"NomeTest",Genero.ROCK,casatest,new Date(),200,new BigDecimal(100.2)),"Test_IdNull"},
			{new Evento((long)1,null,Genero.ROCK,casatest,new Date(),200,new BigDecimal(100.2)),"Test_NomeNull"},
			{new Evento((long)1,"NomeTest",null,casatest,new Date(),200,new BigDecimal(100.2)),"Test_GeneroNull"},
			{new Evento((long)1,"NomeTest",Genero.ROCK,null,new Date(),200,new BigDecimal(100.2)),"Test_CasaNull"},
			{new Evento((long)1,"NomeTest",Genero.ROCK,casatest,null,200,new BigDecimal(100.2)),"Test_DataNull"},
			{new Evento((long)1,"NomeTest",Genero.ROCK,casatest,new Date(),-1,new BigDecimal(100.2)),"Test_IngNegativo"},
			{new Evento((long)1,"NomeTest",Genero.ROCK,casatest,new Date(),200,null),"Test_PrecoNull"}
		});
	}
	@Rule
	public ErrorCollector erro=new ErrorCollector();
	@Test
	public void excEventoConsulta() {
		//buscar
		try {
			Evento eventotest2=new Evento((long)2,"NomeTest",Genero.ROCK,casatest,new Date(),200,new BigDecimal(100.2));
			Mockito.when(repEventoTest.findById(Mockito.anyLong()).orElse(null)).thenReturn(eventotest2);
			Evento resultado=service.busca(eventotest.getId());
			erro.checkThat(resultado, CoreMatchers.is(CoreMatchers.not(null)));
		}catch(EventoNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
	}
	@Test
	public void excEventoDB() {
		//salvar
		try {
			//Mockito.when(repEventoTest.findByNome(eventotest.getNome())==null).thenReturn(true);
			Mockito.when(repEventoTest.findByNome(eventotest.getNome())).thenReturn(null);
			service.salvar(eventotest);
			Mockito.verify(repEventoTest).save(eventotest);
		}catch(EventoNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
		try {
			service.salvar(null);
		}catch(EventoNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
		//atualizar
		try {
			service.atualiza(null, eventotest);
		}catch(EventoNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
		try {
			service.atualiza((long) 1, null);
		}catch(EventoNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
		try {
			service.atualiza(null, null);
		}catch(EventoNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
		//deletar
		try {
			service.deleta(null);
		}catch(EventoNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
	}
}
