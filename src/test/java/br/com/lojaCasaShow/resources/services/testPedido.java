package br.com.lojaCasaShow.resources.services;

import org.junit.Before;
import org.junit.Test;

public class testPedido {
	private Pedido pedido;
	@Before
	public void setup() {
		pedido=new Pedido();
	}
	@Test
	public void AdicionarUmItemNoPedido() throws Exception{
		pedido.adicionarItem("Lápis", 1.0, 5);
	}
}
