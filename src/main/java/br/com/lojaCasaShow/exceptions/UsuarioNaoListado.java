package br.com.lojaCasaShow.exceptions;

public class UsuarioNaoListado extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5565422115671919049L;
	public UsuarioNaoListado(String mensagem) {
		super(mensagem);
	}
	public UsuarioNaoListado(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
