package br.com.javamagazine;

import br.com.javamagazine.bean.Pessoa;
import br.com.javamagazine.bean.dao.rowmapper.PessoaRowMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
public class TestScriptDatabase  {
	
	private Log log = LogFactory.getLog(TestScriptDatabase.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/***
	 * Método responsável por testar o script de criação do banco de dados.
	 * Script: /src/main/resources/script.sql
	 * Testes de insert, select
	 */
	@Test
	public void testInsertJdbcTemplate(){
		String sqlInsertPessoa = "INSERT INTO PESSOA(NOME, EMAIL) VALUES (?, ?)";
		String sqlInsertTelefone = "INSERT INTO TELEFONE(NUMERO, DDD, ID_PESSOA, TIPO) VALUES(?, ? , ?, ?)";
		String sqlSelect = "SELECT * FROM PESSOA P INNER JOIN TELEFONE T ON P.ID = T.ID_PESSOA";
		

		jdbcTemplate.update(sqlInsertPessoa, new Object[]{"JOSE", "jose@jose.com"});
		jdbcTemplate.update(sqlInsertTelefone, new Object[]{"999999999", "99", 1, "CELULAR"});
		
		List<Pessoa> listaResultado = jdbcTemplate.query(sqlSelect, new PessoaRowMapper());
		log.info(listaResultado);
		
		Assert.assertEquals("Testa o resultado da query",1 , listaResultado.size());
		Assert.assertEquals("Testa se o nome do da pessoa corresponde com o que foi enviado via jdbc", "JOSE", listaResultado.get(0).getNome());
		Assert.assertEquals("Testa o ddd do telefone", "99", listaResultado.get(0).getTelefone().getDdd());

		Assert.assertEquals("Testa o numero do telefone", "999999999", listaResultado.get(0).getTelefone().getNumero());
		
	}
	
}
