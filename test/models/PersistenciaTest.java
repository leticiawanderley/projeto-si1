package models;

import static org.junit.Assert.assertNotNull;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.util.ArrayList;
import java.util.List;

import models.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.db.ebean.Model.Finder;

public class PersistenciaTest {
	
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}
	
	@Test
    public void pegarUsuarioDoBD() {
		
		new Usuario("Bob","bob@email.com", "senha").save();
		Assert.assertTrue(!Usuario.find.all().isEmpty());
		Assert.assertEquals("Bob", Usuario.find.byId("bob@email.com").getName());

    }	
	
	@Test
	public void pegarPeriodoEDisciplinasDoBD() {
		
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		
		Finder<String,Disciplina> findDisciplina = new Finder<String,Disciplina>(String.class, Disciplina.class); 
		Finder<String,Periodo> findPeriodo = new Finder<String,Periodo>(String.class, Periodo.class); 
		
		Disciplina grafos = new Disciplina("Teoria dos Grafos", 2, 4);
		grafos.save();
		disciplinas.add(grafos);
		
		Disciplina discreta = new Disciplina("Matemática discreta", 4, 4);
		discreta.save();
		disciplinas.add(discreta);
		
		Assert.assertEquals(2, findDisciplina.all().size());
		
		Periodo periodo = new Periodo(disciplinas, 2);
		periodo.save();
		Assert.assertTrue(!findPeriodo.all().isEmpty());
		
	}
	
	@Test
	public void pegarPlano() {
		
		Finder<String,Plano> findPlano = new Finder<String,Plano>(String.class, Plano.class); 
		
		Usuario usuario = new Usuario("Bob","bob@email.com", "senha");
		usuario.save();
		
		Plano plano = new Plano();
		plano.save();
		
		Assert.assertTrue(!findPlano.all().isEmpty());
		Assert.assertNotNull(plano.getTodasDisciplinas());
		
	}
	
	/*@Test
	public void pegarGrade() {
		Finder<String,Grade> findGrade = new Finder<String,Grade>(String.class, Grade.class);
		Finder<String,Periodo> findPeriodo = new Finder<String,Periodo>(String.class, Periodo.class); 
		
		Grade grade = new GradeComum();
		grade.save();
		Assert.assertTrue(!findGrade.all().isEmpty());
		
		Usuario usuario = new Usuario("Bob","bob@email.com", "senha");
		usuario.save();
		
		Plano plano = new Plano();
		plano.save();
		
		Assert.assertTrue(!findPeriodo.all().isEmpty());
		
	}*/

}
