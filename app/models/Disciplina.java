package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

/**
 * Representa uma disciplina/cadeira
 *
 */
@Entity
public class Disciplina extends Model {
	
	@Id
	private Long id;
	private String nomeDaDisciplina;
	private int numeroDeCreditos;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="listaDePreRequisitos", joinColumns=@JoinColumn(name="disciplina_codigo", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="requisito_codigo", referencedColumnName="id"))
	private List<Disciplina> listaDePreRequisitos;
	private int dificuldadeDaDisciplina;
	
	/**
	 * Construtor da classe
	 * @param nomeDaDisciplina nome da disciplina
	 * @param creditos numero de creditos
	 */
	public Disciplina(String nomeDaDisciplina, int creditos, int dificuldade) {
		this.nomeDaDisciplina = nomeDaDisciplina;
		this.numeroDeCreditos = creditos;
		listaDePreRequisitos = new ArrayList<Disciplina>();
		this.dificuldadeDaDisciplina = dificuldade;
	}
	
	/**
	 * Construtor da classe
	 * @param nomeDaDisciplina nome da disciplina
	 * @param creditos numero de creditos
	 * @param preRequisitos lista de pre-requisitos da disciplina
	 */
	public Disciplina(String nomeDaDisciplina, int creditos, Disciplina[] preRequisitos, int dificuldade) {
		this.nomeDaDisciplina = nomeDaDisciplina;
		this.numeroDeCreditos = creditos;
		listaDePreRequisitos = new ArrayList<Disciplina>();
		listaDePreRequisitos.addAll(Arrays.asList(preRequisitos));
		this.setDificuldade(dificuldade);
	}

	/**
	 * 
	 * @return Nome da disciplina
	 */
	public String getNome() {
		return nomeDaDisciplina;
	}

	/**
	 * Altera o nome da disciplina
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nomeDaDisciplina = nome;
	}

	/**
	 * 
	 * @return Numero de creditos que a disciplina possui
	 */
	public int getCreditos() {
		return numeroDeCreditos;
	}

	/**
	 * Altera o numero de creditos da disciplina
	 * @param creditos
	 */
	public void setCreditos(int creditos) {
		this.numeroDeCreditos = creditos;
	}

	/**
	 * 
	 * @return A lista de disciplinas pre-requisito para cursar esta disciplina
	 */
	public List<Disciplina> getListaDePreRequisitos() {
		return listaDePreRequisitos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Disciplina other = (Disciplina) obj;
		if (other.getNome().equals(nomeDaDisciplina) && other.getCreditos() == numeroDeCreditos) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return dificuldade da disciplina
	 */
	public int getDificuldade() {
		return dificuldadeDaDisciplina;
	}

	/**
	 * Altera a dificuldade
	 * @param dificuldade
	 */
	public void setDificuldade(int dificuldade) {
		this.dificuldadeDaDisciplina = dificuldade;
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
	 * @param id novo id da disciplina
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}
