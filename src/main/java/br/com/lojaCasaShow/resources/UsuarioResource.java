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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lojaCasaShow.domain.Usuario;
import br.com.lojaCasaShow.resources.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags="Usuários")
@RestController
@RequestMapping("/users")
public class UsuarioResource {
	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation("Lista os usuários")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.lista());
	}
	@ApiOperation("Busca um usuário pelo ID")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> busca(@ApiParam(value="ID de um usuário", example="1") @PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.busca(id));
	}	
	@ApiOperation(value="Cadastra um usuário")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@ApiParam(name="corpo", value="username, senha e role") @Valid @RequestBody Usuario usuario) {
		usuarioService.salva(usuario);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri()).build();
	}
	@ApiOperation("Edita um usuário")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@ApiParam(value="ID de um usuário", example="1") @PathVariable("id") Long id,@ApiParam(name="corpo", value="username, senha e role") @Valid @RequestBody Usuario usuario) {
		usuarioService.atualiza(id,usuario);
		return ResponseEntity.noContent().build();
	}
	@ApiOperation("Deleta um usuário")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@ApiParam(value="ID de um usuário", example="1") @PathVariable("id") Long id) {
		usuarioService.deleta(id);
		return ResponseEntity.noContent().build();
	}
	@ApiOperation("Busca os usuários pelo nome")
	@RequestMapping(value="/nome/{username}", method=RequestMethod.GET)
	public ResponseEntity<List<Usuario>> buscarnome(@ApiParam(value="Username de um usuário", example="User") @PathVariable("username") String username) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscanome(username));
	}
}
