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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// INFORMATION EXPERT - contém as informacoes do
	@OneToOne(cascade = CascadeType.ALL)
	@Constraints.Required
	private Plano planoDoAluno;

	/**
	 * Construtor da classe
	 * 
	 * @param nome
	 *            Nome do aluno
	 */
	public Aluno(String name, String email, String password) {
		super(name, email, password);
		this.planoDoAluno = new Plano();
		this.planoDoAluno.save();
	}

	public Plano getPlanoDoAluno() {
		return planoDoAluno;
	}

	public void setPlanoDoAluno(Plano planoDoAluno) {
		this.planoDoAluno = planoDoAluno;
	}

}
