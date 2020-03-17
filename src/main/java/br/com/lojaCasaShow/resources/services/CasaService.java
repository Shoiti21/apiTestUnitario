package br.com.lojaCasaShow.resources.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lojaCasaShow.domain.Casa;
import br.com.lojaCasaShow.exceptions.CasaNaoListado;
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
		try{
			Casa casa=repCasa.findById(id).orElse(null);
			if(casa==null) {
				throw new CasaNaoListado("Não encontramos essa Casa de Show!");
			}
			return casa; 
		}catch(NullPointerException e) {
			throw new CasaNaoListado("Não encontramos essa Casa de Show!");
		}
	}
	public URI salvar(Casa casa) {
		try{
			casa.setId(null);
			repCasa.save(casa);
			URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(casa.getId()).toUri();
			return uri;
		}catch(NullPointerException e) {
			throw new CasaNaoListado("Não encontramos essa Casa de Show!");
		}
	}
	public void atualiza(Long id,Casa casa) {
		try {
			if(repCasa.findById(id).orElse(null)==null) {
				throw new CasaNaoListado("Não encontramos essa Casa de Show!");
			}
			casa.setId(id);
			repCasa.save(casa);
		}catch(NullPointerException e){
			throw new CasaNaoListado("Não encontramos essa Casa de Show!");
		}
	}
	public void deleta(Long id) {
		try {
			Optional<Casa> casa=repCasa.findById(id);
			repCasa.delete(casa.get());
		}catch (EmptyResultDataAccessException e) {
			throw new CasaNaoListado("Não encontramos essa Casa de Show!");
		}catch(NullPointerException e) {
			throw new CasaNaoListado("Não encontramos essa Casa de Show!");
		}
	}
}
