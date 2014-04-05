package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.data.validation.Constraints;


/**
 * Classe que representa um aluno
 * 
 */
/**
 * @author leticia
 *
 */
@Entity
public class Aluno extends Usuario {

	@OneToOne(cascade = CascadeType.ALL)
	@Constraints.Required
	private Plano planoDoAluno;
	private String tipoFluxograma;

	/**
	 * 
	 * Construtor da classe
	 * @param nome Nome do aluno
	 */
	public Aluno(String name, String email, String password) {
		super(name, email, password);
		this.planoDoAluno = new Plano();
		this.tipoFluxograma = "oficial";
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
	
	public String getTipoFluxograma() {
		return tipoFluxograma;
	}

	public void setTipoFluxograma(String tipoFluxograma) {
		this.tipoFluxograma = tipoFluxograma;
	}
}
