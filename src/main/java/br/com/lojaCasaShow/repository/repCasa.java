package br.com.lojaCasaShow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojaCasaShow.domain.Casa;

public interface repCasa extends JpaRepository<Casa, Long>{
	List<Casa> findByOrderByNomeAsc();
	List<Casa> findByOrderByNomeDesc();
	List<Casa> findByNomeContaining(String nome);
}
