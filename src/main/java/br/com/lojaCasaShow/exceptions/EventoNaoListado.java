package br.com.lojaCasaShow.exceptions;

public class EventoNaoListado extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7894236976600904082L;
	public EventoNaoListado(String mensagem) {
		super(mensagem);
	}
	public EventoNaoListado(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
