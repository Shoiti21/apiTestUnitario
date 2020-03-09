package br.com.lojaCasaShow.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojaCasaShow.domain.Venda;
import br.com.lojaCasaShow.resources.services.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags="Vendas")
@RestController
@RequestMapping("/venda")
public class VendasResource {
	@Autowired
	private VendaService vendaService;
	
	@ApiOperation("Lista de vendas")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Venda>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(vendaService.lista());
	}
	@ApiOperation("Busca uma venda por ID")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> busca(@ApiParam(value="ID de uma venda", example="1") @PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(vendaService.busca(id));
	}
	@ApiOperation("Cadastra uma venda")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@ApiParam(name="corpo", value="ID do usuário, ID evento e data") @Valid @RequestBody Venda venda) {
		return ResponseEntity.created(vendaService.salva(venda)).build();
	}
	@ApiOperation("Deleta uma venda")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@ApiParam(value="ID de uma venda", example="1") @PathVariable("id") Long id) {
		vendaService.deleta(id);
		return ResponseEntity.noContent().build();
	}
}
