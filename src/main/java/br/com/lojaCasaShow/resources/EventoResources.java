package br.com.lojaCasaShow.resources;


import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lojaCasaShow.domain.Evento;
import br.com.lojaCasaShow.resources.services.EventoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags="Eventos")
@RestController
@RequestMapping("/evento")
public class EventoResources {
	@Autowired
	private EventoService eventoService;
	
	@ApiOperation("Lista os eventos")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.lista());
	}
	@ApiOperation("Busca um evento")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@ApiParam("ID de um evento") @PathVariable("id") Long id_evento) {
		CacheControl cache=CacheControl.maxAge(10, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(eventoService.busca(id_evento));
	}
	@ApiOperation("Cadastra um evento")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@ApiParam("nome, ID de uma casa, gênero, preço e capacidade") @Valid @RequestBody Evento evento) {
		eventoService.salvar(evento);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(evento.getId()).toUri()).build();
	}
	@ApiOperation("Edita um evento")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@ApiParam("ID de um evento") @PathVariable("id") Long id_evento,@Valid @RequestBody Evento evento) {
		eventoService.atualiza(id_evento, evento);
		return ResponseEntity.noContent().build();
	}
	@ApiOperation("Deleta um evento")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@ApiParam("ID de um evento") @PathVariable("id") Long id_evento) {
		eventoService.deleta(id_evento);
		return ResponseEntity.noContent().build();
	}
	@ApiOperation("Lista os eventos pelo nome por ordem alfabética (A a Z)")
	@RequestMapping(value="/nome/asc", method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> listarNomeAsc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listaNomeAsc());
	}
	@ApiOperation("Lista os eventos pelo nome por ordem alfabética (Z a A)")
	@RequestMapping(value="/nome/desc", method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> listarNomeDesc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listaNomeDesc());
	}
	@ApiOperation("Lista os eventos por nome")
	@RequestMapping(value="/nome/{nome}", method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> buscarNome(@PathVariable("nome") String nome){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.buscaNome(nome));
	}
	@ApiOperation("Lista os eventos pela data por ordem crescente")
	@RequestMapping(value="/data/asc", method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> listarDataAsc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listaDataAsc());
	}
	@ApiOperation("Lista os eventos pela data por ordem decrescente")
	@RequestMapping(value="/data/desc", method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> listarDataDesc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listaDataDesc());
	}
	@ApiOperation("Lista os eventos pela capacidade por ordem crescente")
	@RequestMapping(value="/capacidade/asc", method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> listarCapacidadeAsc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listaQtdIngressoMaxAsc());
	}
	@ApiOperation("Lista os eventos pela capacidade por ordem decrescente")
	@RequestMapping(value="/capacidade/desc", method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> listarCapacidadeDesc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listaQtdIngressoMaxDesc());
	}
	@ApiOperation("Lista os eventos pelo preço por ordem crescente")
	@RequestMapping(value="/preco/asc", method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> listarPrecoAsc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listaPrecoAsc());
	}
	@ApiOperation("Lista os eventos pelo preço por ordem decrescente")
	@RequestMapping(value="/preco/desc", method=RequestMethod.GET)
	public ResponseEntity<List<Evento>> listarPrecoDesc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listaPrecoDesc());
	}
}
