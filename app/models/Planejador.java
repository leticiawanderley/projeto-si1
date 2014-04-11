package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa um controlador que sera responsavel de gerenciar o planejamento da
 * grade do aluno
 * 
 */
public class Planejador {

	private Grade grade ;

	public Planejador() {
		this.grade = new GradeOficial();
	}
	
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
	public void addCadeiraAoAluno(Aluno aluno, Disciplina disciplina, int periodo) {
		aluno.getPlanoDoAluno().addDisciplinaAoPeriodo(disciplina, periodo);
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
	public boolean verificaPeriodoDiferenteDosRequisitos(Aluno aluno, Disciplina disciplina, int periodo) {
		return aluno.getPlanoDoAluno().isPeriodoDiferenteDosRequisitos(disciplina, periodo);
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
	public List<String> getRequisitosNaoPreenchidos(Aluno aluno, Disciplina disciplina, int periodo) {
		return aluno.getPlanoDoAluno().getNomeDaDisciplinasRequisitos(disciplina, periodo);
	}

	/**
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @return se o aluno tem o minimo de creditos
	 */
	public boolean alunoTemMinimoDeCreditos(Aluno aluno, int periodo) {
		return aluno.getPlanoDoAluno().temMinimo(periodo);		
	}

	/**
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @return se o aluno tem o numero maximo de creditos
	 */
	public boolean alunoTemMaximoDeCreditos(Aluno aluno, int periodo) {
		return aluno.getPlanoDoAluno().temMaximo(periodo);
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
		return aluno.getPlanoDoAluno().getTodasDisciplinas();
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
	public void removeDisciplinaESeusPreRequisitos(Aluno aluno, Disciplina disciplina) {
		aluno.getPlanoDoAluno().removeDisciplinaESeusPreRequisitos(disciplina);
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

	public void alteraPeriodoDaDisciplina(Aluno aluno, Disciplina disciplinaRealocada, int periodo) {
		aluno.getPlanoDoAluno().removeDisciplinaParaAlocacao(disciplinaRealocada);
		addCadeiraAoAluno(aluno, disciplinaRealocada, periodo - 1);
		aluno.update();
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

		List<Periodo> periodos = new ArrayList<Periodo>();
		for (int i = 0; i < aluno.getPlanoDoAluno().getNumeroDePeriodos(); i++) {
			periodos.add(aluno.getPlanoDoAluno().getPeriodo(i));
		}
		
		for (Periodo periodo : periodos) {
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
