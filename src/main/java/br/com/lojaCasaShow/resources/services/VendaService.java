package br.com.lojaCasaShow.resources.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojaCasaShow.domain.Venda;
import br.com.lojaCasaShow.exceptions.VendaNaoListado;
import br.com.lojaCasaShow.repository.repVenda;

@Service
public class VendaService {
	@Autowired
	private repVenda repVenda;
	
	public List<Venda> lista(){
		return repVenda.findAll();
	}
	public Venda busca(Long id){
		Venda venda=repVenda.findById(id).orElse(null);
		if(venda==null) {
			throw new VendaNaoListado("Não foi encontramos essa Venda!");
		}
		return venda;	
	}
	public void salva(Venda venda) {
		if(venda.getId()==null) {
			venda.setTicket(UUID.randomUUID());
			repVenda.save(venda);
		}
		else {
			throw new VendaNaoListado("Não foi encontramos essa Venda!");
		}
		
	}
	public void deleta(Long id) {
		Optional<Venda> venda=repVenda.findById(id);
		try {
			repVenda.delete(venda.get());
		} catch (NoSuchElementException e) {
			throw new VendaNaoListado("Não encontramos essa Venda!");
		}
	}
}
