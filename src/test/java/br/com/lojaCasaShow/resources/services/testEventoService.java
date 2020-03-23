package br.com.lojaCasaShow.resources.services;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsEqual;
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
import org.springframework.boot.test.context.SpringBootTest;

import br.com.lojaCasaShow.domain.Casa;
import br.com.lojaCasaShow.domain.Evento;
import br.com.lojaCasaShow.domain.Genero;
import br.com.lojaCasaShow.domain.Usuario;
import br.com.lojaCasaShow.exceptions.EventoNaoListado;
import br.com.lojaCasaShow.repository.repEvento;

@SpringBootTest
@RunWith(Parameterized.class)
public class testEventoService {
	private static Casa casatest=new Casa((long)1,"Test1","Test1");
	@Rule
	public ErrorCollector erro=new ErrorCollector();
	@InjectMocks
	private EventoService service;
	@Mock
	private repEvento repEvento;
	@Parameter
	public Evento eventotest;
	@Parameter(value=1)
	public String status;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Parameters(name="{1}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][]{
			{new Evento(1L,"NomeTest",Genero.ROCK,casatest,new Date(),200,new BigDecimal(100.2)),"Test"},
			{new Evento(null,"NomeTest",Genero.ROCK,casatest,new Date(),200,new BigDecimal(100.2)),"Test_IdNull"}
		});
	}
	public void excEventoConsulta() {
		//buscarSem
		try {
			Mockito.when(repEvento.findById(1L)).thenReturn(Optional.of(eventotest));
			Mockito.when(repEvento.findById(null)).thenReturn(Optional.empty());
			System.out.println(eventotest.getId());
			Evento resultado=service.busca(eventotest.getId());
			
			erro.checkThat(resultado, CoreMatchers.is(CoreMatchers.not(nullValue())));
			//resultado=service.busca(Mockito.anyLong());
		}catch(EventoNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
	}
	@Test
	public void excEventoSalvar() {
		//salvar
		service.salvar(eventotest);
		Mockito.verify(repEvento).save(eventotest);
		erro.checkThat(eventotest.getId(), CoreMatchers.is(nullValue()));
	}
	@Test
	public void excEventoEditar() {
		//atualizar
		try {
			Evento exevento=new Evento(1L,"NomeTest",Genero.ROCK,casatest,new Date(),200,new BigDecimal(100.2));
			Mockito.when(repEvento.findById(1L)).thenReturn(Optional.of(exevento));
			service.atualiza(1L, eventotest);
			Mockito.verify(repEvento).save(eventotest);
			Mockito.when(repEvento.findById(2L)).thenThrow(new EventoNaoListado("Não encontramos esse Evento!"));
			service.atualiza(2L, eventotest);
		}catch(EventoNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
	}
	@Test
	public void excEventoDelete() {
		//deletar
		try {
			Mockito.when(repEvento.findById(1L)).thenReturn(Optional.of(eventotest));
			Mockito.when(repEvento.findById(null)).thenReturn(Optional.empty());
			System.out.println(eventotest.getId());
			service.deleta(eventotest.getId());
			Mockito.verify(repEvento).delete(eventotest);
		}catch(EventoNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
	}
}
