package br.com.javamagazine;

import br.com.javamagazine.bean.Pessoa;
import br.com.javamagazine.bean.dao.rowmapper.PessoaRowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class JdbcTemplateTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private  Pessoa pessoa = null;
	private List<Pessoa> listaInsertBatch =  new ArrayList<Pessoa>();
	

	@Before
	public void init() {
		pessoa = new Pessoa();
		pessoa.setEmail("teste@gmail.com");
		pessoa.setNome("Teste Nome");
		
		for (int i = 0; i <= 500; i++) {
			Pessoa pessoaInsert =  new Pessoa();
			pessoaInsert.setEmail("email"+i);
			pessoaInsert.setNome("Teste nome Insert"+i);
			listaInsertBatch.add(pessoaInsert);
		}
		
	}

	@Test()
	public void testInsert() {
		String sql = "INSERT INTO PESSOA (NOME, EMAIL) VALUES (?, ?)";
		jdbcTemplate.update(sql.toString(), new Object[] { pessoa.getNome(), pessoa.getEmail() });
	}

	@Test
	public void testSelect() {
		testInsert();
		String sql = "SELECT * FROM PESSOA";
		List<Pessoa> listaPessoa = jdbcTemplate.query(sql, new PessoaRowMapper());
		Assert.assertEquals(1, listaPessoa.size());
	}

	@Test
	public void testQueryForObject() {
		testInsert();
		String sql = "SELECT ID FROM PESSOA WHERE ID = ?";
		Integer queryForObject = jdbcTemplate.queryForObject(sql, new Object[] { 1L }, Integer.class);
		Assert.assertEquals(Integer.valueOf(1), queryForObject);
	}

	@Test
	public void testUpdate() {
		testInsert();
		String sql = "UPDATE PESSOA SET NOME = ? WHERE ID = ?";
		int[] types = { Types.VARCHAR, Types.BIGINT };
		jdbcTemplate.update(sql.toString(), new Object[] { "Nome Novo", 1 }, types);
	}

	@Test
	public void testDelete() {
		testInsert();
		String sql = "DELETE FROM PESSOA WHERE ID = ?";
		jdbcTemplate.update(sql.toString(), new Object[] { 1 });
		String sqlSelect = "SELECT * FROM PESSOA";
		List<Pessoa> listaPessoa = jdbcTemplate.query(sqlSelect, new PessoaRowMapper());
		Assert.assertEquals(0, listaPessoa.size());
	}
	
	@Test
	public void testInsertGetKey(){
		final String sql = "INSERT INTO PESSOA (NOME, EMAIL) VALUES (?, ?)";
	    
		 PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
 	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
 	            PreparedStatement pst =  con.prepareStatement(sql, new String[] {"ID"});
 	            pst.setString(1, pessoa.getNome());
 	            pst.setString(2, pessoa.getEmail());
 	            return pst;
 	        }
 	    };
		
		KeyHolder key = new GeneratedKeyHolder();
		jdbcTemplate.update(preparedStatementCreator, key);
		
		long longValue = key.getKey().longValue();
		Assert.assertEquals(1, longValue);
		
	}

	@Test
	public void testSupportBatchUpdate() throws SQLException {
		boolean isSupportBatchUpdate =
				jdbcTemplate.getDataSource()
						.getConnection()
						.getMetaData().supportsBatchUpdates();
		Assert.assertTrue(isSupportBatchUpdate);
	}
	
	@Test
	public void insertBatch(){
		final String sql = "INSERT INTO PESSOA (NOME, EMAIL) VALUES (?, ?)";
		
		BatchPreparedStatementSetter batchPreparedStatementSetter =  new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Pessoa pessoaBatchInsert = listaInsertBatch.get(i);
				 ps.setString(1, pessoaBatchInsert.getNome());
	 	         ps.setString(2, pessoaBatchInsert.getEmail());
			}
			
			public int getBatchSize() {
				return listaInsertBatch.size();
			}
		};
		
		jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetter);

		String sqlSelect = "SELECT * FROM PESSOA";
		List<Pessoa> listaPessoa = jdbcTemplate.query(sqlSelect, new PessoaRowMapper());
		Assert.assertEquals(501, listaPessoa.size());
	}



}
