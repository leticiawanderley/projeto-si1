package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

/**
 * Representa um periodo de um aluno
 *
 */
@Entity
public class Periodo extends Model {
	
	@Id
	private Long id;
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
		for (Disciplina disciplina : disciplinas) {
			numeroDeCreditosDoPeriodo += disciplina.getCreditos();
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
	 * Calcula Dificuldade do periodo
	 * @param disciplinas lista de disciplinas
	 * @param periodo numero do periodo
	 */
	private void dificuldadeDoPeriodo(List<Disciplina> disciplinas, int periodo) {
		dificuldadeDoPeriodo = 0;
		for (Disciplina disciplina : disciplinas) {
			dificuldadeDoPeriodo += disciplina.getDificuldade();
		}
	}

}
