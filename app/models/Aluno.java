package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import models.validador.ValidadorMinDeCreditos;

/**
 * Classe que representa um aluno
 *
 */
@Entity
public class Aluno extends User {

	// INFORMATION EXPERT - contém as informacoes do aluno:nomeDoAluno,listaDePeriodos .
	private static final long serialVersionUID = 7507028957989504099L;
	private static final int PRIMEIRO_PERIODO = 0;
	private static final int ULTIMO_PERIODO = 0;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Periodo> listaDePeriodo;
	private int periodoAtual;
	
	/**
	 * Construtor da classe	
	 * @param nome Nome do aluno
	 */
	public Aluno(String name, String email, String password) {
		super(name, email, password);
		this.listaDePeriodo = new ArrayList<Periodo>();
		this.setPeriodoAtual(PRIMEIRO_PERIODO);
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
	 * @param periodoAtual o periodo em que o aluno estah atualmente
	 */
	public void setPeriodoAtual(int periodoAtual) {
		this.periodoAtual = periodoAtual;
		// os anteriores terao maximo
		for (int i = 0; i < periodoAtual; i++) {
			
		}
		// o atual e posteriores terao maximo e minimo
		for (int i = periodoAtual; i < listaDePeriodo.size(); i++) {
			
		}
		listaDePeriodo.get(ULTIMO_PERIODO).setValidadorDoPeriodo(new ValidadorMinDeCreditos(numeroDeCreditos));
		// o ultimo nao terah maximo
	}
	
}
