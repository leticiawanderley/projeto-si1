package controllers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Aluno;
import models.Disciplina;
import models.GradeComum;
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
	
	private Planejador planejador;
	private Finder<String, Aluno> finder = new Finder<String, Aluno>(String.class, Aluno.class);
	
	/**
	 * 
	 * Construtor
	 */
	public GridSystem() {
		this.planejador = new Planejador();
	/*	if (finder.all().isEmpty() || finder.all().size() < 10) {
			adicionaUsuarios();
		}*/
	}
	
	public Finder<String, Aluno> getFinder() {
		return finder;
	}

	public void alocandoNovoUsuario(Aluno aluno) {
		if (aluno.getPlanoDoAluno().semPeriodos()) {
			addPeriodosAoAluno(aluno,"oficial");
		}
	}
	
	public List<Aluno> buscaAlunos() {
		return finder.all();
	}
	
	public List<Aluno> buscaAlunoPorNome(String nome) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		for (Aluno aluno : buscaAlunos()) {
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
	public void addPeriodosAoAluno(Aluno aluno, String tipoDeGrade) {
		
		if(tipoDeGrade.equals("comum")) {
			planejador.setGrade(new GradeComum());
		}
		
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoPrimeiroPeriodo(), PRIMEIRO_PERIODO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoSegundoPeriodo(), SEGUNDO_PERIODO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoTerceiroPeriodo(), TERCEIRO_PERIODO));
		
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoQuartoPeriodo(), QUARTO_PERIODO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoQuintoPeriodo(), QUINTO_PERIODO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoSextoPeriodo(), SEXTO_PERIODO));
		
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoSetimoPeriodo(), SETIMO_PERIODO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(planejador.getGrade().getDisciplinasDoOitavoPeriodo(), OITAVO_PERIDO));
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(new ArrayList<Disciplina>(), NONO_PERIODO));
		
		aluno.getPlanoDoAluno().addPeriodo(new Periodo(new ArrayList<Disciplina>(), DECIMO_PERIODO));
	}
	
	private static void adicionaUsuarios() {
	/*	InputStream is = play.Play.application().resourceAsStream("conf/usuarios.txt");
		Scanner scanner = new Scanner(is);
		while(scanner.hasNextLine()) {
			String[] elementos = scanner.nextLine().split("-");
			Aluno aluno = new Aluno(elementos[0], elementos[1], elementos[2]);
			new GridSystem().addPeriodosAoAluno(aluno,"oficial");
			new Planejador().removeDisciplinaESeusPreRequisitos(aluno, aluno.getPlanoDoAluno().getListaDePeriodos()
					.get(PRIMEIRO_PERIODO)
					.getDisciplinas()
					.get((int) (Math.random()*6)));
			aluno.getPlanoDoAluno().save();
		}
		scanner.close();*/
	}
	
}
