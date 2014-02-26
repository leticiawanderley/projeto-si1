package controllers;

import java.util.ArrayList;

import models.Aluno;
import models.Disciplina;
import models.Periodo;
import models.Planejador;

/**
 * Classe que ira conter os objetos para utilizar no sistema pelo usuario
 *
 */
public class GridController {

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
	
	/**
	 * 
	 * Construtor
	 */
	public GridController() {
		this.aluno = new Aluno();
		this.planejador = new Planejador();
		addPeriodosAoAluno();
	}

	/**
	 * 
	 * @return Aluno do sistema
	 */
	public Aluno getAluno() {
		return aluno;
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
	 */
	private void addPeriodosAoAluno() {
		aluno.getListaDePeriodos().add(new Periodo(planejador.getGrade().getDisciplinasDoPrimeiroPeriodo(), PRIMEIRO_PERIODO));
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), SEGUNDO_PERIODO));
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), TERCEIRO_PERIODO));
		
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), QUARTO_PERIODO));
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), QUINTO_PERIODO));
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), SEXTO_PERIODO));
		
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), SETIMO_PERIODO));
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), OITAVO_PERIDO));
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), NONO_PERIODO));
		
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), DECIMO_PERIODO));
	}

}
