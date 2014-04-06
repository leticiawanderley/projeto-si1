package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Aluno;
import models.Disciplina;
import models.GradeComum;
import models.GradeNova;
import models.GradeOficial;
import models.Periodo;
import models.Planejador;
import play.db.ebean.Model.Finder;

/**
 * Classe que ira conter os objetos para utilizar no sistema pelo usuario
 *
 */
public class GridSystem {

	private static final int PRIMEIRO_PERIODO = 0;
	private static final int SEGUNDO_PERIODO = 1;
	private static final int TERCEIRO_PERIODO = 2;
	
	private static final int QUARTO_PERIODO = 3;
	private static final int QUINTO_PERIODO = 4;
	private static final int SEXTO_PERIODO = 5;
	
	private static final int SETIMO_PERIODO = 6;
	private static final int OITAVO_PERIDO = 7;
	private static final int NONO_PERIODO = 8;
	private static final int DECIMO_PERIODO = 9;
	
	private static Planejador planejador;
	private Finder<String, Aluno> finder = new Finder<String, Aluno>(String.class, Aluno.class);
	
	/**
	 * 
	 * Construtor
	 */
	public GridSystem() {
		this.planejador = new Planejador();
		if (finder.all().isEmpty()) {
			adicionaUsuarios();
		}
	}
	
	public Finder<String, Aluno> getFinder() {
		return finder;
	}

	public void alocandoNovoUsuario(Aluno aluno) {
		if (aluno.getPlanoDoAluno().semPeriodos()) {
			addPeriodosAoAluno(aluno, aluno.getTipoFluxograma());
		}
	}
	
	public List<Aluno> buscaAlunoPorNome(String nome) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		for (Aluno aluno : finder.all()) {
			if (aluno.getName().contains(nome)) {
				alunos.add(aluno);
			}
		}
		return alunos;	
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
	public static void addPeriodosAoAluno(Aluno aluno, String tipoDeGrade) {
		
		if (tipoDeGrade.equals("comum")) {
			planejador.setGrade(new GradeComum());
		}
		else if (tipoDeGrade.equals("novo")) {
			planejador.setGrade(new GradeNova());
		}
		else {
			planejador.setGrade(new GradeOficial());
		}
		
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoPrimeiroPeriodo(), PRIMEIRO_PERIODO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoSegundoPeriodo(), SEGUNDO_PERIODO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoTerceiroPeriodo(), TERCEIRO_PERIODO));
		
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoQuartoPeriodo(), QUARTO_PERIODO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoQuintoPeriodo(), QUINTO_PERIODO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoSextoPeriodo(), SEXTO_PERIODO));
		
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoSetimoPeriodo(), SETIMO_PERIODO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoOitavoPeriodo(), OITAVO_PERIDO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoNonoPeriodo(), NONO_PERIODO));
		
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(new ArrayList<Disciplina>(), DECIMO_PERIODO));
	}
	
	private static void adicionaUsuarios() {
		InputStream inputStream = play.Play.application().resourceAsStream("resource/usuarios.txt");
		try {
			if (inputStream == null) {
				inputStream = new FileInputStream(new File("resource/usuarios.txt"));
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Scanner scanner = new Scanner(inputStream);
		while(scanner.hasNextLine()) {
			String[] elementos = scanner.nextLine().split("-");
			Aluno aluno = new Aluno(elementos[0], elementos[1], elementos[2]);
			addPeriodosAoAluno(aluno,"oficial");
			new Planejador().removeDisciplinaESeusPreRequisitos(aluno, aluno.getPlanoDoAluno().getPeriodo(PRIMEIRO_PERIODO)
					.getDisciplinas()
					.get((int) (Math.random()*6)));
			aluno.getPlanoDoAluno().setPeriodoAtual((int) (Math.random()*10));
			aluno.save();
		}
		scanner.close();
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
