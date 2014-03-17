package models;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeGlobal;
import static play.test.Helpers.inMemoryDatabase;

import java.util.List;

import org.junit.Before;

import play.libs.Yaml;
import play.test.WithApplication;

import com.avaje.ebean.Ebean;

public class PersistenciaTest extends WithApplication {
	
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
		Ebean.save((List) Yaml.load("resource/usuarios.yml"));
	}

}
