package br.com.lojaCasaShow.resources.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;

import br.com.lojaCasaShow.domain.Casa;
import br.com.lojaCasaShow.exceptions.CasaNaoListado;
import br.com.lojaCasaShow.exceptions.EventoNaoListado;
import org.junit.Assert;
import org.junit.Rule;

public class testCasaService {
	@Rule
	public ErrorCollector erro=new ErrorCollector();
	@Test
	public void excCasaConsulta() {
		//buscar
		CasaService service=new CasaService();
		try {
			service.busca(null);
		}catch(CasaNaoListado e){
			Assert.assertThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
	}
	@Test
	public void excCasaDB() {
		//salvar
		CasaService service=new CasaService();
		Casa casatest=new Casa();
		try {
			casatest.setId((long) 1);
			casatest.setLocal("Local 1");
			casatest.setNome("Casa 1");
			service.salvar(casatest);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
		try {
			casatest.setId(null);
			casatest.setLocal("Local 1");
			casatest.setNome("Casa 1");
			service.salvar(casatest);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
		try {
			casatest.setId((long) 1);
			casatest.setLocal(null);
			casatest.setNome("Casa 1");
			service.salvar(casatest);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
		try {
			casatest.setId((long) 1);
			casatest.setLocal("Local 1");
			casatest.setNome(null);
			service.salvar(casatest);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
		//atualizar
		try {
			service.atualiza(null, casatest);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
		try {
			service.atualiza((long) 1, null);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
		try {
			service.atualiza(null, null);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
		//deletar
		try {
			service.deleta(null);
		}catch(CasaNaoListado e){
			erro.checkThat(e.getMessage(), CoreMatchers.is("Não encontramos essa Casa de Show!"));
		}
	}
}
