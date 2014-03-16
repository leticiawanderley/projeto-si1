package controllers;

import java.util.ArrayList;

import models.Aluno;
import models.Disciplina;
import models.Periodo;
import models.Planejador;
import play.db.ebean.Model.Finder;

/**
 * Classe que ira conter os objetos para utilizar no sistema pelo usuario
 *
 */
public class GridSystem {

	private static final int PRIMEIRO_PERIODO = 1;
	private static final int SEGUNDO_PERIODO = 2;
	private static final int TERCEIRO_PERIODO = 3;
	
	private static final int QUARTO_PERIODO = 4;
	private static final int QUINTO_PERIODO = 5;
	private static final int SEXTO_PERIODO = 6;
	
	private static final int SETIMO_PERIODO = 7;
	private static final int OITAVO_PERIDO = 8;
	private static final int NONO_PERIODO = 9;
	private static final int DECIMO_PERIODO = 10;
	
	private Aluno aluno;
	private Planejador planejador;
	private Finder<Long, Aluno> finder = new Finder<Long, Aluno>(Long.class, Aluno.class);
	
	/**
	 * 
	 * Construtor
	 */
	public GridSystem() {
		this.planejador = new Planejador();
	}
	
	public Finder<Long, Aluno> getFinder() {
		return finder;
	}

	/**
	 * 
	 * @return Aluno do sistema
	 */
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
		if (this.aluno != null) {
			alocandoNovoUsuario(this.aluno);
		}
	}
	
	public void alocandoNovoUsuario(Aluno aluno) {
		if (aluno.getListaDePeriodos().isEmpty()) {
			addPeriodosAoAluno(aluno);
		}
	}
	
	/**
	 * 
	 * @return Planejador do sistema
	 */
	public Planejador getPlanejador() {
		return planejador;
	}

	/**
	 * Inicia os periodos do aluno
	 * @param aluno 
	 */
	private void addPeriodosAoAluno(Aluno aluno) {
		aluno.getListaDePeriodos().add(new Periodo(planejador.getGrade().getDisciplinasDoPrimeiroPeriodo(), PRIMEIRO_PERIODO));
		aluno.getListaDePeriodos().add(new Periodo(planejador.getGrade().getDisciplinasDoSegundoPeriodo(), SEGUNDO_PERIODO));
		aluno.getListaDePeriodos().add(new Periodo(planejador.getGrade().getDisciplinasDoTerceiroPeriodo(), TERCEIRO_PERIODO));
		
		aluno.getListaDePeriodos().add(new Periodo(planejador.getGrade().getDisciplinasDoQuartoPeriodo(), QUARTO_PERIODO));
		aluno.getListaDePeriodos().add(new Periodo(planejador.getGrade().getDisciplinasDoQuintoPeriodo(), QUINTO_PERIODO));
		aluno.getListaDePeriodos().add(new Periodo(planejador.getGrade().getDisciplinasDoSextoPeriodo(), SEXTO_PERIODO));
		
		aluno.getListaDePeriodos().add(new Periodo(planejador.getGrade().getDisciplinasDoSetimoPeriodo(), SETIMO_PERIODO));
		aluno.getListaDePeriodos().add(new Periodo(planejador.getGrade().getDisciplinasDoOitavoPeriodo(), OITAVO_PERIDO));
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), NONO_PERIODO));
		
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), DECIMO_PERIODO));
	}

}
