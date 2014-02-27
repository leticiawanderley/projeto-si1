package models;

import java.util.ArrayList;
import java.util.List;

import play.db.ebean.Model;

/**
 * Classe que representa um aluno
 *
 */
public class Aluno extends Model {

	// INFORMATION EXPERT - contém as informacoes do aluno:nomeDoAluno,listaDePeriodos .
	private static final long serialVersionUID = 7507028957989504099L;
	private List<Periodo> listaDePeriodo;
	
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

}
