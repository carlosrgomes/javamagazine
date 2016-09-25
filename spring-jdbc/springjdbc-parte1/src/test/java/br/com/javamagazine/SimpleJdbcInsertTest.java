package br.com.javamagazine;

import br.com.javamagazine.bean.Pessoa;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by barbero on 25/09/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class SimpleJdbcInsertTest {

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @Test
    public void testSimpleJdbcInsert(){
        simpleJdbcInsert.withTableName("PESSOA").usingGeneratedKeyColumns("ID");

        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("NOME", "Jose");
        parameters.put("EMAIL", "jose@email.com");
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        Assert.assertEquals(1l, newId.longValue());
    }

    @Test
    public void testSimpleJdbcInsertSqlParameter(){
        simpleJdbcInsert.withTableName("PESSOA").usingGeneratedKeyColumns("ID");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("NOME", "Jose")
                .addValue("EMAIL", "jose@email.com");
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        Assert.assertEquals(1l, newId.longValue());
    }

    @Test
    public void testSimpleJdbcInsertBeanPropertySqlParameterSource(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Jose");
        pessoa.setEmail("jose@email.com");
        simpleJdbcInsert.withTableName("PESSOA").usingGeneratedKeyColumns("ID");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(pessoa);
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        Assert.assertEquals(1l, newId.longValue());
    }

}
