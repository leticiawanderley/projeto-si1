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
	

	private static final int MINIMO_CREDITOS = 14;
	private static final int MAXIMO_CREDITOS = 28;
	
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

	public void addDisciplinaAoPeriodo(Disciplina disciplina, int periodo) {
		this.listaDePeriodo.get(periodo).getDisciplinas().add(disciplina);
	}

	public boolean isPeriodoDiferenteDosRequisitos(Disciplina disciplina,
			int periodo) {
		for (Periodo periodoAnalisado : this.listaDePeriodo) {
		for (Disciplina disciplinaAnalisada : periodoAnalisado
				.getDisciplinas()) {
			if (disciplina.getListaDePreRequisitos().contains(
					disciplinaAnalisada)
					&& periodoAnalisado.getNumeroDoPeriodo() - 1 >= periodo) {
				return true;
			}
		}
	}
	if (verificaPreRequisitosNaoAlocados(disciplina)) {
		return true;
	}
	return false;
	}
	
	/**
	 * Verifica se a disciplina que estah sendo realocada tem algum de seus
	 * pre-requisitos nao alocados
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @param disciplina
	 *            disciplina realocada
	 * @return true se algum pre-requisito da disciplina estah fora da grade,
	 *         false caso contrario
	 */
	// TODO
	private boolean verificaPreRequisitosNaoAlocados(Disciplina disciplina) {
		for (Disciplina d : disciplina.getListaDePreRequisitos()) {
			if (!getDisciplinas().contains(d)) {
				return true;
			}
		}
		return false;
	}

	public boolean temMinimo(int periodo) {
		return this.listaDePeriodo.get(periodo).getNumeroDeCreditosDoPeriodo() >= MINIMO_CREDITOS;
	}

	public boolean temMaximo(int periodo) {
		return this.listaDePeriodo.get(periodo).getNumeroDeCreditosDoPeriodo() > MAXIMO_CREDITOS;
	}

	public List<Disciplina> getDisciplinas() {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		for (Periodo periodo : this.listaDePeriodo) {
			disciplinas.addAll(periodo.getDisciplinas());
		}
		return disciplinas;
	}
	
	public void removeDisciplina(Disciplina disciplina) {
		removeDisciplinaESeusPreRequisitos(disciplina);
	}
	

	public String getNomeDaDisciplinasRequisitos(Disciplina disciplina,int periodo) {
		String requisitosNaoPreenchidos = "\n";
		for (Periodo periodoAnalisado : listaDePeriodo) {
			for (Disciplina disciplinaAnalisada : periodoAnalisado
					.getDisciplinas()) {
				if (disciplina.getListaDePreRequisitos().contains(
						disciplinaAnalisada)
						&& periodoAnalisado.getNumeroDoPeriodo() - 1 >= periodo) {
					requisitosNaoPreenchidos += disciplinaAnalisada.getNome()
							+ "\n";
				}
			}
		}
		for (Disciplina d : disciplina.getListaDePreRequisitos()) {
			if (!getDisciplinas().contains(d)) {
				requisitosNaoPreenchidos += d.getNome() + "\n";
			}
		}
		return requisitosNaoPreenchidos;
	}
	
	public void addPeriodo(Periodo periodo) {
		this.listaDePeriodo.add(periodo);
	}

	public boolean semPeriodos() {
		return this.listaDePeriodo.isEmpty();
	}

	public List<Disciplina> getDisciplinaDoPeriodo(int periodo) {
		return listaDePeriodo.get(periodo).getDisciplinas();
	}

	public int getNumeroDeCreditosDoPeriodo(int periodo) {
		return this.listaDePeriodo.get(periodo).getNumeroDeCreditosDoPeriodo();
	}

	public Periodo getPeriodo(int periodo) {
		return this.listaDePeriodo.get(periodo);
	}
	
	public void removeDisciplinaESeusPreRequisitos(
			Disciplina disciplina) {
		List<Disciplina> disciplinasDependentes = getDisciplinasDependentes(disciplina);
		for (int i = 0; i < this.listaDePeriodo.size(); i++) {
			for (int j = 0; j < this.listaDePeriodo.get(i)
					.getDisciplinas().size(); j++) {
				if (this.listaDePeriodo.get(i).getDisciplinas().get(j)
						.equals(disciplina)) {
					this.listaDePeriodo.get(i).getDisciplinas()
							.remove(disciplina);
				}
			}
		}
		for (int i = 0; i < disciplinasDependentes.size(); i++) {
			removeDisciplinaESeusPreRequisitos(
					disciplinasDependentes.get(i));
		}
	}
	
	/**
	 * 
	 * @param aluno
	 *            aluno que estah no sistema
	 * @param disciplina
	 *            disciplina que o aluno quer as disciplinas que dependem dela
	 * @return As disciplinas que dependem da disciplina do parametro
	 */
	private List<Disciplina> getDisciplinasDependentes(
			Disciplina disciplina) {
		List<Disciplina> disciplinasDependentes = new ArrayList<Disciplina>();
		for (Disciplina disciplinaDoAluno : getDisciplinas()) {
			if (disciplinaDoAluno.getListaDePreRequisitos()
					.contains(disciplina)) {
				disciplinasDependentes.add(disciplinaDoAluno);
			}
		}
		return disciplinasDependentes;
	}
	
	public int getNumeroDePeriodos() {
		return this.listaDePeriodo.size();
	}
	
}
