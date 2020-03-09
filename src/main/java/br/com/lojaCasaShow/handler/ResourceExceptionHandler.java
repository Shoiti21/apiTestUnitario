package br.com.lojaCasaShow.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.lojaCasaShow.domain.DetalheErro;
import br.com.lojaCasaShow.exceptions.CasaNaoListado;
import br.com.lojaCasaShow.exceptions.EventoNaoListado;
import br.com.lojaCasaShow.exceptions.UsuarioExiste;
import br.com.lojaCasaShow.exceptions.UsuarioNaoListado;
import br.com.lojaCasaShow.exceptions.VendaNaoListado;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(EventoNaoListado.class)
	public ResponseEntity<DetalheErro> handleEventoNaoListado(EventoNaoListado e, HttpServletRequest request){
		DetalheErro erro=new DetalheErro();
		erro.setTitulo("O Evento não foi encontrado");
		erro.setStatus(404l);
		erro.setMensagem("http://test.com/404");
		erro.setTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	@ExceptionHandler(CasaNaoListado.class)
	public ResponseEntity<DetalheErro> handleCasaNaoListado(CasaNaoListado e, HttpServletRequest request){
		DetalheErro erro=new DetalheErro();
		erro.setTitulo("A Casa de Show não foi encontrado");
		erro.setStatus(404l);
		erro.setMensagem("http://test.com/404");
		erro.setTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	@ExceptionHandler(UsuarioNaoListado.class)
	public ResponseEntity<DetalheErro> handleUsuarioNaoListado(UsuarioNaoListado e, HttpServletRequest request){
		DetalheErro erro=new DetalheErro();
		erro.setTitulo("O Usuário não foi encontrado");
		erro.setStatus(404l);
		erro.setMensagem("http://test.com/404");
		erro.setTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	@ExceptionHandler(UsuarioExiste.class)
	public ResponseEntity<DetalheErro> handleUsuarioExiste(UsuarioExiste e, HttpServletRequest request){
		DetalheErro erro=new DetalheErro();
		erro.setTitulo("O Usuário existe!");
		erro.setStatus(406l);
		erro.setMensagem("http://test.com/404");
		erro.setTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(erro);
	}
	@ExceptionHandler(VendaNaoListado.class)
	public ResponseEntity<DetalheErro> handleVendaNaoListado(VendaNaoListado e, HttpServletRequest request){
		DetalheErro erro=new DetalheErro();
		erro.setTitulo("A Venda não foi encontrado");
		erro.setStatus(404l);
		erro.setMensagem("http://test.com/404");
		erro.setTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
