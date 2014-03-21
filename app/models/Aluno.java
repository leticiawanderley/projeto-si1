package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import models.validador.ValidacaoCreditos;

/**
 * Classe que representa um aluno
 * 
 */
@Entity
public class Aluno extends User {

	// INFORMATION EXPERT - contém as informacoes do
	// aluno:nomeDoAluno,listaDePeriodos .

	private static final long serialVersionUID = 7507028957989504099L;
	private static final int PRIMEIRO_PERIODO = 0;
	private static final int ULTIMO_PERIODO = 9;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Periodo> listaDePeriodo;
	@Column
	private int periodoAtual;

	/**
	 * Construtor da classe
	 * 
	 * @param nome
	 *            Nome do aluno
	 */
	public Aluno(String name, String email, String password) {
		super(name, email, password);
		this.listaDePeriodo = new ArrayList<Periodo>();
		this.periodoAtual = PRIMEIRO_PERIODO;
	}

	/**
	 * 
	 * @return a lista dos periodos do aluno
	 */
	public List<Periodo> getListaDePeriodos() {
		return listaDePeriodo;
	}

	/**
	 * 
	 * @return o periodo em que o aluno estah
	 */
	public int getPeriodoAtual() {
		return periodoAtual;
	}

	/**
	 * 
<<<<<<< HEAD
	 * @param periodoAtual o periodo em qNoue o aluno estah atualmente
=======
	 * @param periodoAtual
	 *            o periodo em que o aluno estah atualmente
>>>>>>> 84c794bbe1d4f63f3ee6b0416c65097e1b7cf3df
	 */
	public void setPeriodoAtual(int periodoAtual) {
		this.periodoAtual = periodoAtual;
		for (int i = 0; i < periodoAtual; i++) {
			listaDePeriodo.get(i).setValidadorDoPeriodo(ValidacaoCreditos.MAX);
		}
		for (int i = periodoAtual; i < listaDePeriodo.size(); i++) {
			listaDePeriodo.get(i).setValidadorDoPeriodo(
					ValidacaoCreditos.MINMAX);
		}
		listaDePeriodo.get(ULTIMO_PERIODO).setValidadorDoPeriodo(
				ValidacaoCreditos.MIN);
	}

}
