package br.com.lojaCasaShow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojaCasaShow.domain.Evento;

public interface repEvento extends JpaRepository<Evento, Long>{
	Evento findByNome(String nome);
	List<Evento> findByOrderByNomeAsc();
	List<Evento> findByOrderByNomeDesc();
	List<Evento> findByNomeContaining(String nome);
	List<Evento> findByOrderByQtdIngressoDispAsc();
	List<Evento> findByOrderByQtdIngressoDispDesc();
	List<Evento> findByOrderByDataAsc();
	List<Evento> findByOrderByDataDesc();
	List<Evento> findByOrderByPrecoAsc();
	List<Evento> findByOrderByPrecoDesc();
}
