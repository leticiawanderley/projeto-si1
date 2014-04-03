package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.data.validation.Constraints;


/**
 * Classe que representa um aluno
 * 
 */
@Entity
public class Aluno extends Usuario {

	@OneToOne(cascade = CascadeType.ALL)
	@Constraints.Required
	private Plano planoDoAluno;

	/**
	 * 
	 * Construtor da classe
	 * @param nome Nome do aluno
	 */
	public Aluno(String name, String email, String password) {
		super(name, email, password);
		this.planoDoAluno = new Plano();
	}

	/**
	 * 
	 * @return o plano do aluno
	 */
	public Plano getPlanoDoAluno() {
		return planoDoAluno;
	}

	/**
	 * 
	 * @param planoDoAluno novo plano do aluno
	 */
	public void setPlanoDoAluno(Plano planoDoAluno) {
		this.planoDoAluno = planoDoAluno;
	}
	
}
