package br.com.lojaCasaShow.resources.services;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.com.lojaCasaShow.domain.Evento;
import br.com.lojaCasaShow.exceptions.EventoNaoListado;

public class testEventoService {
	@Rule
	public ErrorCollector erro=new ErrorCollector();
	@Test
	public void excEventoConsulta() {
		//buscar
		EventoService service=new EventoService();
		try {
			service.busca(null);
		}catch(EventoNaoListado e){
			Assert.assertThat(e.getMessage(), CoreMatchers.is("Não encontramos esse Evento!"));
		}
	}
	@Test
	public void excEventoDB() {
		//salvar
		EventoService service=new EventoService();
		Evento eventotest=new Evento();
		try {
			service.salvar(eventotest);
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
