package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import static play.test.Helpers.*;

import org.junit.Before;
import org.junit.Test;

import play.libs.Yaml;

import com.avaje.ebean.Ebean;

import controllers.Application;

public class PersistenciaTest {
//	Application aplicacao;
	@Before
	public void setUp() {
		/*start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
		Ebean.save((List) Yaml.load("resource/usuarios.yml"));*/
	//	aplicacao = new Application();
	}
	
	@Test
	public void test() {
		
	}
	
	 @Test
	    public void fullTest() {
	        Ebean
	        .save((List<?>) 
	        		Yaml
	        		.load
	        		("usuarios.yml"));

	        // Count things
	        assertEquals(1, User.find.findRowCount());

	        // Try to authenticate as users
	        assertNotNull(User.authenticate("bob@example.com", "fafa="));
	        assertNotNull(User.authenticate("jane@example.com", "12mo"));
	        assertNull(User.authenticate("jeff@example.com", "badpassword"));
	        assertNull(User.authenticate("tom@example.com", "secret"));


	    }

}
