package br.com.javamagazine;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
public class SpringJdbcTest  {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	/***
	 * Metodo de teste responsável por testar as configuracoes do spring
	 * 
	 */
	@Test
	public void testaConfiguracaoDoSpring(){
		
		Assert.assertNotNull(applicationContext);
		
	
	}
	
}
