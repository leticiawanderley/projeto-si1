package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Classe que representa um aluno
 *
 */
@Entity
public class Aluno extends User {

	// INFORMATION EXPERT - contém as informacoes do aluno:nomeDoAluno,listaDePeriodos .
	private static final long serialVersionUID = 7507028957989504099L;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Periodo> listaDePeriodo;
	
	/**
	 * Construtor da classe	
	 * @param nome Nome do aluno
	 */
	public Aluno(String name, String email, String password) {
		super(name, email, password);
		this.listaDePeriodo = new ArrayList<Periodo>();
	}
	
	/**
	 * 
	 * @return a lista dos periodos do aluno
	 */
	public List<Periodo> getListaDePeriodos() {
		return listaDePeriodo;
	}
	
}
