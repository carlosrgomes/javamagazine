package br.com.javamagazine;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.javamagazine.bean.Pessoa;
import br.com.javamagazine.bean.dao.rowmapper.PessoaRowMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
public class JdbcTemplateTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	Pessoa pessoa = null;
	
	@Before
	public void init(){
		pessoa =  new Pessoa();
		pessoa.setEmail("teste@gmail.com");
		pessoa.setNome("Teste Nomr");
	}
	
	@Test()
	public void testInsert(){
		String sql = "INSERT INTO PESSOA (NOME, EMAIL) VALUES (?, ?)";
		jdbcTemplate.update(sql.toString(), new Object[]{pessoa.getNome(), 
							pessoa.getEmail()});
	}
	
	@Test
	public void testSelect(){
		testInsert();
		String sql = "SELECT * FROM PESSOA";
		List<Pessoa> listaPessoa = jdbcTemplate.query(sql, new PessoaRowMapper());
		Assert.assertEquals(1, listaPessoa.size());
	}
	
	@Test
	public void testQueryForObject(){
		testInsert();
		String sql = "SELECT ID FROM PESSOA WHERE ID = ?";
		Integer queryForObject = jdbcTemplate.queryForObject(sql,  new Object[]{1L} ,Integer.class );
		Assert.assertEquals(Integer.valueOf(1), queryForObject);
	}
	
}
