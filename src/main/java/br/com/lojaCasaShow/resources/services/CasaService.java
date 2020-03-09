package br.com.lojaCasaShow.resources.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lojaCasaShow.domain.Casa;
import br.com.lojaCasaShow.domain.Evento;
import br.com.lojaCasaShow.exceptions.EventoNaoListado;
import br.com.lojaCasaShow.repository.repCasa;

@Service
public class CasaService {
	@Autowired
	private repCasa repCasa;
	
	public List<Casa> lista(){
		return repCasa.findAll();
	}
	public List<Casa> listaAsc(){
		return repCasa.findByOrderByNomeAsc();
	}
	public List<Casa> listaDesc(){
		return repCasa.findByOrderByNomeDesc();
	}
	public List<Casa> buscaNome(String nome){
		return repCasa.findByNomeContaining(nome);
	}
	public Casa busca(Long id){
		Casa casa=repCasa.findById(id).orElse(null);
		if(casa==null) {
			throw new EventoNaoListado("Não encontramos essa Casa de Show!");
		}
		return casa; 
	}
	public URI salvar(Casa casa) {
		casa.setId(null);
		repCasa.save(casa);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(casa.getId()).toUri();
		return uri;
	}
	public void atualiza(Long id,Casa casa) {
		if(repCasa.findById(id).orElse(null)==null) {
			throw new EventoNaoListado("Não encontramos essa Casa de Show!");
		}
		casa.setId(id);
		repCasa.save(casa);
	}
	public void deleta(Long id) {
		Optional<Casa> casa=repCasa.findById(id);
		try {
		repCasa.delete(casa.get());
		} catch (EmptyResultDataAccessException e) {
			throw new EventoNaoListado("Não encontramos essa Casa de Show!");
		}
	}
}
