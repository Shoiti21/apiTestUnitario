package br.com.lojaCasaShow.resources.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.lojaCasaShow.resources.services.testCasaService;
import br.com.lojaCasaShow.resources.services.testEventoService;
import br.com.lojaCasaShow.resources.services.testUsuarioService;
import br.com.lojaCasaShow.resources.services.testVendaService;

@RunWith(Suite.class)
@SuiteClasses({
	testCasaService.class,
	testEventoService.class,
	testUsuarioService.class,
	testVendaService.class
})
public class SuiteExe {
	//Rodar todos os testes
}
