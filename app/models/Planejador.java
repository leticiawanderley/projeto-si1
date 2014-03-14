package models;

import java.util.ArrayList;
import java.util.List;

import exception.AlunoNaoPossuiPreRequisitosException;

/**	
 * Representa um controlador que sera responsavel de gerenciar o planejamento da grade do aluno
 *
 */
public class Planejador {
	
	//CONTROLLER - Delega para outros objetos o trabalho que precisa ser feito
	//CREATOR - Classe Planejador registra objeto do tipo Grade
	private Grade grade = new Grade();

	private static final int MINIMO_CREDITOS = 14;
	private static final int MAXIMO_CREDITOS = 28;
	
	/**
	 * 
	 * @param nome nome da disciplina
	 * @return o numero de creditos da disciplina
	 */
	public int getNumeroDeCreditosDaDisciplina(String nome) {
		return grade.getCreditosDaDisciplina(nome);
	}

	/**
	 * 
	 * @param aluno aluno que estah no sistema
	 * @param disciplina disciplina que o aluno quer saber se tem os pre-requisitos
	 * @return se o aluno pode pagar a disciplina (se ele tiver os pre-requisitos)
	 */
	public boolean verificaPreRequisitosPago(Aluno aluno, Disciplina disciplina) {
		return getTodasDisciplinasDoAluno(aluno).containsAll(disciplina.getListaDePreRequisitos());
	}

	/**
	 * Adiciona mais uma cadeira ao aluno
	 * @param aluno aluno que estah no sistema
	 * @param disciplina nova disciplina do aluno
	 * @throws AlunoNaoPossuiPreRequisitosException Excecao lancada se o aluno nao tiver os pre-requisitos da disciplina
	 */
	public void addCadeiraAoAluno(Aluno aluno, Disciplina disciplina, int periodo)/* throws AlunoNaoPossuiPreRequisitos */{
		/*if (!verificaPreRequisitosPago(aluno, disciplina) || verificaPeriodoDiferenteDosRequisitos(aluno, disciplina, periodo - 1)) {
			throw new AlunoNaoPossuiPreRequisitos();
		}*/
		aluno.getListaDePeriodos().get(periodo).getDisciplinas().add(disciplina);
	}
	
	/**
	 * O aluno nao pode alocar uma disciplina no mesmo periodo que os seus pre-requisitos
	 * @param aluno aluno que estah no sistema
	 * @param disciplina disciplina do aluno
	 * @param periodo periodo em que a disciplina esta alocada
	 * @return se a disciplina estah em um periodo diferente que os seus pre-requisitos
	 */
	public boolean verificaPeriodoDiferenteDosRequisitos(Aluno aluno, Disciplina disciplina, int periodo) {
		for (Periodo periodoAnalisado : aluno.getListaDePeriodos()) {
			for (Disciplina disciplinaAnalisada : periodoAnalisado.getDisciplinas()) {
				if (disciplina.getListaDePreRequisitos().contains(disciplinaAnalisada) 
					&& periodoAnalisado.getPeriodo() - 1 >= periodo) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param aluno aluno que estah no sistema
	 * @return se o aluno tem o minimo de creditos
	 */
	public boolean alunoTemMinimoDeCreditos(Aluno aluno, int periodo) {
		return aluno.getListaDePeriodos().get(periodo).getNumeroDeCreditosDoPeriodo() >= MINIMO_CREDITOS;
	}
	
	/**
	 * 
	 * @param aluno aluno que estah no sistema
	 * @return se o aluno tem o numero maximo de creditos
	 */
	public boolean alunoTemMaximoDeCreditos(Aluno aluno, int periodo) {
		return aluno.getListaDePeriodos().get(periodo).getNumeroDeCreditosDoPeriodo() > MAXIMO_CREDITOS;
	}
	
	/**
	 * 
	 * @return a grade do curso
	 */
	public Grade getGrade() {
		return grade;
	}

	/**
	 * 
	 * @return as disciplinas da grade
	 */
	public List<Disciplina> getDisciplinasDaGrade() {
		return grade.getDisciplinasDoCurso();
	}
	
	/**
	 * 
	 * @param aluno aluno que estah no sistema
	 * @return todas as disciplinas alocadas do aluno
	 */
	public List<Disciplina> getTodasDisciplinasDoAluno(Aluno aluno) {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		for (Periodo periodo : aluno.getListaDePeriodos()) {
			disciplinas.addAll(periodo.getDisciplinas());
		}
		return disciplinas;
	}
	
	/**
	 * Remove disciplina juntamente com todas que tem ela com pre-requisito
	 * @param aluno aluno que estah no sistema
	 * @param disciplina disciplina que sera removida da grade do aluno, juntamente com seus pre-requisitos
	 */
	public void removeDisciplinaESeusPreRequisitos(Aluno aluno, Disciplina disciplina) {
		List<Disciplina> disciplinasDependentes = getDisciplinasDependentes(aluno, disciplina);
		if (!grade.getDisciplinasDoPrimeiroPeriodo().contains(disciplina)) {
			for (int i = 0; i < aluno.getListaDePeriodos().size(); i++) {
				for (int j = 0; j < aluno.getListaDePeriodos().get(i).getDisciplinas().size(); j++) {
					if (aluno.getListaDePeriodos().get(i).getDisciplinas().get(j).equals(disciplina)) {
						aluno.getListaDePeriodos().get(i).getDisciplinas().remove(disciplina);
					}
				}
			}
			for (int i = 0; i < disciplinasDependentes.size(); i++) {
				removeDisciplinaESeusPreRequisitos(aluno, disciplinasDependentes.get(i));
			}
		}
		
	}
	
	public void removeDisciplina(Aluno aluno, Disciplina disciplina) {
		for (int i = 0; i < aluno.getListaDePeriodos().size(); i++) {
			for (int j = 0; j < aluno.getListaDePeriodos().get(i).getDisciplinas().size(); j++) {
				if (aluno.getListaDePeriodos().get(i).getDisciplinas().get(j).equals(disciplina)) {
					aluno.getListaDePeriodos().get(i).getDisciplinas().remove(disciplina);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param aluno aluno que estah no sistema
	 * @param disciplina disciplina que o aluno quer as disciplinas que dependem dela
	 * @return As disciplinas que dependem da disciplina do parametro
	 */
	private List<Disciplina> getDisciplinasDependentes(Aluno aluno, Disciplina disciplina) {
		List<Disciplina> disciplinasDependentes = new ArrayList<Disciplina>();
		for (Disciplina disciplinaDoAluno : getTodasDisciplinasDoAluno(aluno)) {
			if (disciplinaDoAluno.getListaDePreRequisitos().contains(disciplina)) {
				disciplinasDependentes.add(disciplinaDoAluno);
			}
		}
		return disciplinasDependentes;
	}

	/**
	 * 
	 * @param aluno aluno que estah no sistema
 	 * @param periodo periodo que o aluno estah se referindo
	 * @return disciplinas do periodo que o aluno possui alocado
	 */
	public List<Disciplina> getDisciplinasDoPeriodo(Aluno aluno, int periodo) {
		return aluno.getListaDePeriodos().get(periodo).getDisciplinas();
	}
	
	/**
	 * 
	 * @param nomeDaDisciplina nome da disciplina
	 * @return a disciplina com o nome do parametro
	 */
	public Disciplina getDisciplina(String nomeDaDisciplina) {
		for (Disciplina disciplina : grade.getDisciplinasDoCurso()) {
			if (disciplina.getNome().equals(nomeDaDisciplina)) {
				return disciplina;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param aluno aluno que estah no sistema
	 * @param disciplina disciplina que o aluno quer saber se estah matriculado
	 * @return Se o aluno esta matriculado na disciplina ou não
	 */
	public boolean jaEstaMatriculado(Aluno aluno, Disciplina disciplina) {
		return getTodasDisciplinasDoAluno(aluno).contains(disciplina);
	}
	
	/**
	 * 
	 * @param nomeDaCadeira no da cadeira
	 * @return Se o nome dado é de uma cadeira existente no sistema
	 */
	public boolean existeCadeira(String nomeDaCadeira) {
		return getDisciplina(nomeDaCadeira) != null;
	}
	
}
