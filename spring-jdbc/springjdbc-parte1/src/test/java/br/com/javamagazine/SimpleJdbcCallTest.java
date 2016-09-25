package br.com.javamagazine;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by barbero on 25/09/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class SimpleJdbcCallTest {

    @Autowired
    private SimpleJdbcCall simpleJdbcCall;

   @Test
   public void insertNamedParameterJdbcTemplate(){
       this.simpleJdbcCall.withProcedureName("GET_PRIME_NUMBERS");
       SqlParameterSource sqlParameterSource= new MapSqlParameterSource()
               .addValue("beginRange", 1)
               .addValue("endRange", 10);

      Map out =  simpleJdbcCall.execute(sqlParameterSource);

        Assert.assertEquals(1, 1);
    }

}
