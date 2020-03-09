package br.com.lojaCasaShow.resources.services;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lojaCasaShow.domain.Evento;
import br.com.lojaCasaShow.exceptions.EventoNaoListado;
import br.com.lojaCasaShow.repository.repEvento;

@Service
public class EventoService {
	@Autowired
	private repEvento repEvento;
	
	public List<Evento> lista(){
		return repEvento.findAll();
	}
	public List<Evento> listaNomeAsc(){
		return repEvento.findByOrderByNomeAsc();
	}
	public List<Evento> listaNomeDesc(){
		return repEvento.findByOrderByNomeDesc();
	}
	public List<Evento> buscaNome(String nome){
		return repEvento.findByNomeContaining(nome);
	}
	public List<Evento> listaDataAsc(){
		return repEvento.findByOrderByDataAsc();
	}
	public List<Evento> listaDataDesc(){
		return repEvento.findByOrderByDataDesc();
	}
	public List<Evento> listaQtdIngressoMaxAsc(){
		return repEvento.findByOrderByQtdIngressoDispAsc();
	}
	public List<Evento> listaQtdIngressoMaxDesc(){
		return repEvento.findByOrderByQtdIngressoDispDesc();
	}
	public List<Evento> listaPrecoAsc(){
		return repEvento.findByOrderByPrecoAsc();
	}
	public List<Evento> listaPrecoDesc(){
		return repEvento.findByOrderByPrecoDesc();
	}
	public Optional<Evento> busca(Long id){
		Optional<Evento> evento=repEvento.findById(id);
		if(evento==null) {
			throw new EventoNaoListado("Não encontramos esse Evento!");
		}
		return evento; 
	}
	public URI salvar(Evento evento) {
		evento.setId(null);
		if(repEvento.findByNome(evento.getNome())==null) {
			repEvento.save(evento);
		}
		
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(evento.getId()).toUri();
		return uri;
	}
	public void atualiza(Long id,Evento evento) {
		Optional<Evento> evento2=repEvento.findById(id);
		if(evento2==null) {
			throw new EventoNaoListado("Não encontramos esse Evento!");
		}
		evento.setId(id);
		repEvento.save(evento);
	}
	public void deleta(Long id) {
		Optional<Evento> evento=repEvento.findById(id);
		try {
			repEvento.delete(evento.get());
		} catch (NoSuchElementException e) {
			throw new EventoNaoListado("Não encontramos esse Evento!");
		}
	}
}
