package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import models.validador.ValidacaoCreditos;

import play.db.ebean.Model;

@Entity
public class Plano extends Model {

	@Id
	private Long id;	
	private static final int PRIMEIRO_PERIODO = 0;
	private static final int ULTIMO_PERIODO = 9;
	@OrderBy("numeroDoPeriodo")
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Periodo> listaDePeriodo;
	private int periodoAtual;
	
	public Plano() {
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
	 * @param periodoAtual
	 *            o periodo em que o aluno estah atualmente
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
