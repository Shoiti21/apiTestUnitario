package br.com.lojaCasaShow.exceptions;

public class CasaNaoListado extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7191880697359674720L;
	public CasaNaoListado(String mensagem) {
		super(mensagem);
	}
	public CasaNaoListado(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
