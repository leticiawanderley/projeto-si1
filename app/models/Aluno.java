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
	private TipoDeFluxograma tipoFluxograma;

	/**
	 * 
	 * Construtor da classe
	 * @param nome Nome do aluno
	 */
	public Aluno(String name, String email, String password) {
		super(name, email, password);
		this.planoDoAluno = new Plano();
		this.tipoFluxograma = TipoDeFluxograma.OFICIAL;
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
	
	/**
	 * 
	 * @return o tipo de fluxograma
	 */
	public TipoDeFluxograma getTipoFluxograma() {
		return tipoFluxograma;
	}

	/**
	 * 
	 * @param tipoFluxograma o tipo de fluxo grama
	 */
	public void setTipoFluxograma(TipoDeFluxograma tipoFluxograma) {
		this.tipoFluxograma = tipoFluxograma;
	}
}
