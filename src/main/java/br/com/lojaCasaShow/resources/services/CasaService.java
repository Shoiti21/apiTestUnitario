package br.com.lojaCasaShow.resources.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojaCasaShow.domain.Casa;
import br.com.lojaCasaShow.exceptions.CasaNaoListado;
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
		Optional<Casa> casa=repCasa.findById(id);
		if(casa.isEmpty()) {
			throw new CasaNaoListado("Não encontramos essa Casa de Show!");
		}
		return casa.get(); 
	}
	public void salvar(Casa casa) {
		if(casa.getId()==null) {
			repCasa.save(casa);
		}
		else {
			throw new CasaNaoListado("Não encontramos essa Casa de Show!");
		}
	}
	public void atualiza(Long id,Casa casa) {
		if(Objects.isNull(repCasa.findById(id).orElse(null))) {
			throw new CasaNaoListado("Não encontramos essa Casa de Show!");
		}
		casa.setId(id);
		repCasa.save(casa);
	}
	public void deleta(Long id) {
		Optional<Casa> casa=repCasa.findById(id);
		try {
			repCasa.delete(casa.get());
		}catch (NoSuchElementException e) {
			throw new CasaNaoListado("Não encontramos essa Casa de Show!");
		}	
	}
}
