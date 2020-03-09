package br.com.lojaCasaShow.exceptions;

public class VendaNaoListado extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6398219583083324626L;
	public VendaNaoListado(String mensagem) {
		super(mensagem);
	}
	public VendaNaoListado(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
