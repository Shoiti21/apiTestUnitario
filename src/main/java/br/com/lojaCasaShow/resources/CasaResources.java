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

import br.com.lojaCasaShow.domain.Casa;
import br.com.lojaCasaShow.exceptions.CasaNaoListado;
import br.com.lojaCasaShow.resources.services.CasaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags="Casas")
@RestController
@RequestMapping("/casa")
public class CasaResources {
	@Autowired
	private CasaService casaService;
	
	@ApiOperation("Lista as casas")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Casa>> listarCasa() {
		return ResponseEntity.status(HttpStatus.OK).body(casaService.lista());
	}
	@ApiOperation("Busca uma casa por ID")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarCasa(@PathVariable("id") Long id_casa) {
		CacheControl cache=CacheControl.maxAge(10, TimeUnit.SECONDS);
		try {
			return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(casaService.busca(id_casa));
		}catch(NumberFormatException e){
			throw new CasaNaoListado("Não encontramos essa Casa de Show!");
		}
	}
	@ApiOperation("Cadastra uma casa")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvarCasa(@Valid @RequestBody Casa casa) {
		casaService.salvar(casa);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(casa.getId()).toUri()).build();
	}
	@ApiOperation("Edita uma casa")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizarCasa(@PathVariable("id") Long id_casa, @Valid @RequestBody Casa casa) {
		casaService.atualiza(id_casa, casa);
		return ResponseEntity.noContent().build();
	}
	@ApiOperation("Deleta uma casa")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletarCasa(@PathVariable("id") Long id_casa) {
		casaService.deleta(id_casa);
		return ResponseEntity.noContent().build();
	}
	@ApiOperation("Lista as casas em ordem alfabética (A a Z)")
	@RequestMapping(value="/asc", method=RequestMethod.GET)
	public ResponseEntity<List<Casa>> listarAscCasa(){
		return ResponseEntity.status(HttpStatus.OK).body(casaService.listaAsc());
	}
	@ApiOperation("Lista as casas em ordem alfabética (Z a A)")
	@RequestMapping(value="/desc", method=RequestMethod.GET)
	public ResponseEntity<List<Casa>> listarDescCasa(){
		return ResponseEntity.status(HttpStatus.OK).body(casaService.listaDesc());
	}
	@ApiOperation("Lista as casas por nome")
	@RequestMapping(value="/nome/{nome}", method=RequestMethod.GET)
	public ResponseEntity<List<Casa>> buscarNomeCasa(@PathVariable("nome") String nome){
		return ResponseEntity.status(HttpStatus.OK).body(casaService.buscaNome(nome));
	}

}
