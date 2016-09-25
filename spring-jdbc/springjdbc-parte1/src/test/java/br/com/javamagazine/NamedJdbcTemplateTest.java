package br.com.javamagazine;

import br.com.javamagazine.bean.Pessoa;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
public class NamedJdbcTemplateTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

   @Test
   public void insertNamedParameterJdbcTemplate(){
        String sql = "INSERT INTO PESSOA (NOME, EMAIL) VALUES (:nome, :email)";
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("nome", "Jose")
                .addValue("email", "email@email.com");
        int update = namedParameterJdbcTemplate.update(sql, parameters);
        Assert.assertEquals(update, 1);
    }

}
