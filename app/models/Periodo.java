package models;

import java.util.List;

/**
 * Representa um periodo de um aluno
 *
 */
public class Periodo {
	
	//INFORMATION EXPERT - Contém as informações do período: disciplinas, período, dificuldade.
	private List<Disciplina> disciplinas;
	private int periodo;
	private int dificuldadeDoPeriodo;

	/**
	 * Construtor da classe
	 * @param disciplinas lista de disciplinas matriculadas neste periodo
	 * @param periodo quantificacao do periodo
	 */
	public Periodo(List<Disciplina> disciplinas, int periodo) {
		this.periodo = periodo;
		this.disciplinas = disciplinas;
		dificuldadeDoPeriodo(disciplinas, periodo);
	}
	
	/**
	 * Retorna o valor do periodo
	 * @return
	 */
	public int getPeriodo() {
		return periodo;
	}
	
	/**
	 * Altera o valor do periodo
	 * @param periodo
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	/**
	 *
	 * @return retorna as disciplinas deste periodo
	 */
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	/**
	 * 
	 * @return retorna o numero de creditos do periodo
	 */
	public int getNumeroDeCreditosDoPeriodo() {
		int numeroDeCreditosDoPeriodo = 0;
		for (int i = 0; i < disciplinas.size(); i++) {
				numeroDeCreditosDoPeriodo += disciplinas.get(i).getCreditos();	
		}
		return numeroDeCreditosDoPeriodo;
	}

	/**
	 * 
	 * @return dificuldade total do periodo
	 */
	public int getDificuldadeDoPeriodo() {
		dificuldadeDoPeriodo(disciplinas, dificuldadeDoPeriodo);
		return dificuldadeDoPeriodo;
	}

	/**
	 * Altera dificuldade
	 * @param dificuldadeDoPeriodo
	 */
	public void setDificuldadeDoPeriodo(int dificuldadeDoPeriodo) {
		this.dificuldadeDoPeriodo = dificuldadeDoPeriodo;
	}
	
	/**
	 * Calcula Dificuldade do periodo
	 * @param disciplinas lista de disciplinas
	 * @param periodo numero do periodo
	 */
	private void dificuldadeDoPeriodo(List<Disciplina> disciplinas, int periodo) {
		dificuldadeDoPeriodo = 0;
		for (int i = 0; i < disciplinas.size(); i++) {
			dificuldadeDoPeriodo += disciplinas.get(i).getDificuldade();
		}
	}

}
