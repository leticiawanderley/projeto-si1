package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import models.validador.ValidacaoCreditos;
import play.db.ebean.Model;

/**
 * Representa um periodo de um aluno
 *
 */
@Entity
public class Periodo extends Model {
	
	@Id
	private Long id;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Disciplina> disciplinas;
	private int numeroDoPeriodo;
	private int dificuldadeDoPeriodo;
	private ValidacaoCreditos validadorDoPeriodo;

	/**
	 * Construtor da classe
	 * @param disciplinas lista de disciplinas matriculadas neste periodo
	 * @param periodo quantificacao do periodo
	 */
	public Periodo(List<Disciplina> disciplinas, int periodo) {
		this.numeroDoPeriodo = periodo;
		this.disciplinas = disciplinas;
		dificuldadeDoPeriodo(disciplinas, periodo);
		this.validadorDoPeriodo = ValidacaoCreditos.MINMAX;
	}
	
	/**
	 * Retorna o valor do periodo
	 * @return
	 */
	public int getNumeroDoPeriodo() {
		return numeroDoPeriodo;
	}
	
	/**
	 * Altera o valor do periodo
	 * @param periodo
	 */
	public void setNumeroDoPeriodoPeriodo(int periodo) {
		this.numeroDoPeriodo = periodo;
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
	
	/**
	 * 
	 * @return se o perido eh valido
	 */
	public boolean ehPeriodoValido() {
		return getValidadorDoPeriodo().permiteNumeroDeCreditos(getNumeroDeCreditosDoPeriodo());
	}
	
	/**
	 * 
	 * @return 
	 */
	public String tipoValidacao() {
		return getValidadorDoPeriodo().getTipoValidacao(getNumeroDeCreditosDoPeriodo());
	}

	/**
	 * 
	 * @return o id da disciplina
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id o id da disciplina
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @param validadorDoPeriodo novo validador do periodo
	 */
	public void setValidadorDoPeriodo(ValidacaoCreditos validadorDoPeriodo) {
		this.validadorDoPeriodo = validadorDoPeriodo;
	}

	/**
	 * 
	 * @return o validator de creditos do periodo
	 */
	public ValidacaoCreditos getValidadorDoPeriodo() {
		return this.validadorDoPeriodo;
	}
	
}
