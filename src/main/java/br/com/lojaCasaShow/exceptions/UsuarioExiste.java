package br.com.lojaCasaShow.exceptions;

public class UsuarioExiste extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7232884160121952285L;
	public UsuarioExiste(String mensagem) {
		super(mensagem);
	}
	public UsuarioExiste(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
