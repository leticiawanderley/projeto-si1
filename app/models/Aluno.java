package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

/**
 * Classe que representa um aluno
 *
 */
@Entity
public class Aluno extends Model {

	@Id
	private Long id;
	// INFORMATION EXPERT - contém as informacoes do aluno:nomeDoAluno,listaDePeriodos .
	private static final long serialVersionUID = 7507028957989504099L;
	private List<Periodo> listaDePeriodo;
	
	//private Finder<Long, Aluno> finder = new Finder<Long, Aluno>(Long.class, Aluno.class);
	
	/**
	 * Construtor da classe	
	 * @param nome Nome do aluno
	 */
	public Aluno() {
		this.listaDePeriodo = new ArrayList<Periodo>();
	}
	
	/**
	 * 
	 * @return a lista dos periodos do aluno
	 */
	public List<Periodo> getListaDePeriodos() {
		return listaDePeriodo;
	}

	/*public Finder<Long, Aluno> getFinder() {
		return finder;
	}*/

}
