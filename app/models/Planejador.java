package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.AlunoNaoPossuiPreRequisitosException;

/**
 * Representa um controlador que sera responsavel de gerenciar o planejamento da
 * grade do aluno
 * 
 */
public class Planejador {

	// CONTROLLER - Delega para outros objetos o trabalho que precisa ser feito
	// CREATOR - Classe Planejador registra objeto do tipo Grade
	private Grade grade ;

	public Planejador() {
		this.grade = new GradeOficial();
	}

	private static final int MINIMO_CREDITOS = 14;
	private static final int MAXIMO_CREDITOS = 28;

	/**
	 * 
	 * @param nome
	 *            nome da disciplina
	 * @return o numero de creditos da disciplina
	 */
	public int getNumeroDeCreditosDaDisciplina(String nome) {
		return grade.getCreditosDaDisciplina(nome);
	}

	/**
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @param disciplina
	 *            disciplina que o aluno quer saber se tem os pre-requisitos
	 * @return se o aluno pode pagar a disciplina (se ele tiver os
	 *         pre-requisitos)
	 */
	public boolean verificaPreRequisitosPago(Aluno aluno, Disciplina disciplina) {
		return getTodasDisciplinasDoAluno(aluno).containsAll(
				disciplina.getListaDePreRequisitos());
	}

	/**
	 * Adiciona mais uma cadeira ao aluno
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @param disciplina
	 *            nova disciplina do aluno
	 * @throws AlunoNaoPossuiPreRequisitosException
	 *             Excecao lancada se o aluno nao tiver os pre-requisitos da
	 *             disciplina
	 */
	public void addCadeiraAoAluno(Aluno aluno, Disciplina disciplina,
			int periodo) {
		aluno.getPlanoDoAluno().getListaDePeriodos().get(periodo).getDisciplinas()
				.add(disciplina);
	}

	/**
	 * O aluno nao pode alocar uma disciplina no mesmo periodo que os seus
	 * pre-requisitos
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @param disciplina
	 *            disciplina do aluno
	 * @param periodo
	 *            periodo em que a disciplina esta alocada
	 * @return se a disciplina estah em um periodo diferente que os seus
	 *         pre-requisitos
	 */
	public boolean verificaPeriodoDiferenteDosRequisitos(Aluno aluno,
			Disciplina disciplina, int periodo) {
		for (Periodo periodoAnalisado : aluno.getPlanoDoAluno().getListaDePeriodos()) {
			for (Disciplina disciplinaAnalisada : periodoAnalisado
					.getDisciplinas()) {
				if (disciplina.getListaDePreRequisitos().contains(
						disciplinaAnalisada)
						&& periodoAnalisado.getNumeroDoPeriodo() - 1 >= periodo) {
					return true;
				}
			}
		}
		if (verificaPreRequisitosNaoAlocados(aluno, disciplina)) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica se a disciplina que estah sendo realocada tem algum de seus
	 * pre-requisitos nao alocados
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @param disciplina
	 *            disciplina realocada
	 * @return true se algum pre-requisito da disciplina estah fora da grade,
	 *         false caso contrario
	 */
	private boolean verificaPreRequisitosNaoAlocados(Aluno aluno,
			Disciplina disciplina) {
		for (Disciplina d : disciplina.getListaDePreRequisitos()) {
			if (!getTodasDisciplinasDoAluno(aluno).contains(d)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método para interface que retorna uma String contendo todas as
	 * disciplinas que são pré-requisitos da disciplina passada como parâmetro e
	 * não estão alocadas corretamente
	 * 
	 * @param aluno
	 *            aluno que está no sistema
	 * @param disciplina
	 *            disciplina analisada
	 * @param periodo
	 *            período atual da disciplina analisada
	 * @return String com todos as disciplinas pré-requisito não alocadas
	 *         corretamente
	 */
	public String getRequisitosNaoPreenchidos(Aluno aluno,
			Disciplina disciplina, int periodo) {
		String requisitosNaoPreenchidos = "\n";
		for (Periodo periodoAnalisado : aluno.getPlanoDoAluno().getListaDePeriodos()) {
			for (Disciplina disciplinaAnalisada : periodoAnalisado
					.getDisciplinas()) {
				if (disciplina.getListaDePreRequisitos().contains(
						disciplinaAnalisada)
						&& periodoAnalisado.getNumeroDoPeriodo() - 1 >= periodo) {
					requisitosNaoPreenchidos += disciplinaAnalisada.getNome()
							+ "\n";
				}
			}
		}
		for (Disciplina d : disciplina.getListaDePreRequisitos()) {
			if (!getTodasDisciplinasDoAluno(aluno).contains(d)) {
				requisitosNaoPreenchidos += d.getNome() + "\n";
			}
		}
		return requisitosNaoPreenchidos;
	}

	/**
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @return se o aluno tem o minimo de creditos
	 */
	public boolean alunoTemMinimoDeCreditos(Aluno aluno, int periodo) {
		return aluno.getPlanoDoAluno().getListaDePeriodos().get(periodo)
				.getNumeroDeCreditosDoPeriodo() >= MINIMO_CREDITOS;
	}

	/**
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @return se o aluno tem o numero maximo de creditos
	 */
	public boolean alunoTemMaximoDeCreditos(Aluno aluno, int periodo) {
		return aluno.getPlanoDoAluno().getListaDePeriodos().get(periodo)
				.getNumeroDeCreditosDoPeriodo() > MAXIMO_CREDITOS;
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
	 * @param aluno
	 *            aluno que estah no sistema
	 * @return todas as disciplinas alocadas do aluno
	 */
	public List<Disciplina> getTodasDisciplinasDoAluno(Aluno aluno) {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		for (Periodo periodo : aluno.getPlanoDoAluno().getListaDePeriodos()) {
			disciplinas.addAll(periodo.getDisciplinas());
		}
		return disciplinas;
	}

	/**
	 * Remove disciplina juntamente com todas que tem ela com pre-requisito
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @param disciplina
	 *            disciplina que sera removida da grade do aluno, juntamente com
	 *            seus pre-requisitos
	 */
	public void removeDisciplinaESeusPreRequisitos(Aluno aluno,
			Disciplina disciplina) {
		List<Disciplina> disciplinasDependentes = getDisciplinasDependentes(
				aluno, disciplina);
		for (int i = 0; i < aluno.getPlanoDoAluno().getListaDePeriodos().size(); i++) {
			for (int j = 0; j < aluno.getPlanoDoAluno().getListaDePeriodos().get(i)
					.getDisciplinas().size(); j++) {
				if (aluno.getPlanoDoAluno().getListaDePeriodos().get(i).getDisciplinas().get(j)
						.equals(disciplina)) {
					aluno.getPlanoDoAluno().getListaDePeriodos().get(i).getDisciplinas()
							.remove(disciplina);
				}
			}
		}
		for (int i = 0; i < disciplinasDependentes.size(); i++) {
			removeDisciplinaESeusPreRequisitos(aluno,
					disciplinasDependentes.get(i));
		}
	}

	/**
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @param disciplina
	 *            disciplina que o aluno quer as disciplinas que dependem dela
	 * @return As disciplinas que dependem da disciplina do parametro
	 */
	private List<Disciplina> getDisciplinasDependentes(Aluno aluno,
			Disciplina disciplina) {
		List<Disciplina> disciplinasDependentes = new ArrayList<Disciplina>();
		for (Disciplina disciplinaDoAluno : getTodasDisciplinasDoAluno(aluno)) {
			if (disciplinaDoAluno.getListaDePreRequisitos()
					.contains(disciplina)) {
				disciplinasDependentes.add(disciplinaDoAluno);
			}
		}
		return disciplinasDependentes;
	}

	/**
	 * 
	 * @param nomeDaDisciplina
	 *            nome da disciplina
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
	 * @param nomeDaCadeira
	 *            no da cadeira
	 * @return Se o nome dado é de uma cadeira existente no sistema
	 */
	public boolean existeCadeira(String nomeDaCadeira) {
		return getDisciplina(nomeDaCadeira) != null;
	}

	public void alteraPeriodoDaDisciplina(Aluno aluno,
			Disciplina disciplinaRealocada, int periodo) {
		removeDisciplina(aluno, disciplinaRealocada);
		addCadeiraAoAluno(aluno, disciplinaRealocada, periodo - 1);
		aluno.getPlanoDoAluno().update();
	}

	private void removeDisciplina(Aluno aluno, Disciplina disciplina) {
		for (int i = 0; i < aluno.getPlanoDoAluno().getListaDePeriodos().size(); i++) {
			for (int j = 0; j < aluno.getPlanoDoAluno().getListaDePeriodos().get(i)
					.getDisciplinas().size(); j++) {
				if (aluno.getPlanoDoAluno().getListaDePeriodos().get(i).getDisciplinas().get(j)
						.equals(disciplina)) {
					aluno.getPlanoDoAluno().getListaDePeriodos().get(i).getDisciplinas()
							.remove(disciplina);
				}
			}
		}
	}

	/**
	 * Metodo para obter os dados de creditos pagos, creditos em curso e creditos planejador pelo aluno.getPlanoDoAluno().
	 * @param aluno aluno que estah logado no sistema
	 * @return mapa com os dados de acordo com o planejamento do aluno
	 */
	public Map<String, Integer> estatisticasDoAluno(Aluno aluno) {
		String CREDITOS_PAGOS = "creditosPagos";
		String CREDITOS_EM_CURSO = "creditosEmCurso";
		String CREDITOS_PLANEJADOS = "creditosPlanejados";

		Map<String, Integer> estatisticas = new HashMap<String, Integer>();
		
		estatisticas.put(CREDITOS_PAGOS, 0);
		estatisticas.put(CREDITOS_EM_CURSO, 0);
		estatisticas.put(CREDITOS_PLANEJADOS, 0);
		
		for (Periodo periodo : aluno.getPlanoDoAluno().getListaDePeriodos()) {
			if (periodo.getNumeroDoPeriodo() < aluno.getPlanoDoAluno().getPeriodoAtual()) {
				estatisticas.put(CREDITOS_PAGOS, estatisticas.get(CREDITOS_PAGOS) + periodo.getNumeroDeCreditosDoPeriodo());
			} else if (periodo.getNumeroDoPeriodo() == aluno.getPlanoDoAluno().getPeriodoAtual()) {
				estatisticas.put(CREDITOS_EM_CURSO, estatisticas.get(CREDITOS_EM_CURSO) + periodo.getNumeroDeCreditosDoPeriodo());
			} else {
				estatisticas.put(CREDITOS_PLANEJADOS, estatisticas.get(CREDITOS_PLANEJADOS) + periodo.getNumeroDeCreditosDoPeriodo());
			}
		}
		return estatisticas;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	

}
