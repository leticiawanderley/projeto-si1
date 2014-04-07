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

	/**
	 * Construtor da classe Plano
	 */
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
	 * 
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

	/**
	 * 
	 * @return o id do Plano
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            novo id do plano
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Adiciona uma nova disciplina ao periodo
	 * 
	 * @param disciplina
	 *            disciplina que sera matriculada
	 * @param periodo
	 *            periodo em que a disciplina sera matriculada
	 */
	public void addDisciplinaAoPeriodo(Disciplina disciplina, int periodo) {
		this.listaDePeriodo.get(periodo).getDisciplinas().add(disciplina);
	}

	/**
	 * 
	 * @param disciplina
	 *            disciplina analisada do aluno
	 * @param periodo
	 *            periodo em que a disciplina esta alocada
	 * @return se o periodo em que a disciplina eh diferente dos periodos das
	 *         disciplinas requisito
	 */
	public boolean isPeriodoDiferenteDosRequisitos(Disciplina disciplina,
			int periodo) {
		for (Periodo periodoAnalisado : this.listaDePeriodo) {
			for (Disciplina disciplinaAnalisada : periodoAnalisado
					.getDisciplinas()) {
				if (disciplina.getListaDePreRequisitos().contains(
						disciplinaAnalisada)
						&& periodoAnalisado.getNumeroDoPeriodo() >= periodo) {
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
	private boolean verificaPreRequisitosNaoAlocados(Disciplina disciplina) {
		for (Disciplina d : disciplina.getListaDePreRequisitos()) {
			if (!getTodasDisciplinas().contains(d)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param periodo
	 *            periodo analisado do aluno
	 * @return se o periodo tem o minimo de creditos
	 */
	public boolean temMinimo(int periodo) {
		return this.listaDePeriodo.get(periodo).getNumeroDeCreditosDoPeriodo() >= MINIMO_CREDITOS;
	}

	/**
	 * 
	 * @param periodo
	 *            periodo analisado do aluno
	 * @return se o periodo tem o maximo de creditos
	 */
	public boolean temMaximo(int periodo) {
		return this.listaDePeriodo.get(periodo).getNumeroDeCreditosDoPeriodo() > MAXIMO_CREDITOS;
	}

	/**
	 * 
	 * @return as disciplinas do aluno contidas no aluno, isto eh, todas as
	 *         disciplinas em que o aluno esta matriculado
	 */
	public List<Disciplina> getTodasDisciplinas() {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		for (Periodo periodo : this.listaDePeriodo) {
			disciplinas.addAll(periodo.getDisciplinas());
		}
		return disciplinas;
	}

	/**
	 * 
	 * @param disciplina
	 *            disciplina que ira ser removida do plano de curso do aluno
	 */
	public void removeDisciplinaParaAlocacao(Disciplina disciplina) {
		for (int i = 0; i < listaDePeriodo.size(); i++) {
			for (int j = 0; j < listaDePeriodo.get(i).getDisciplinas().size(); j++) {
				if (listaDePeriodo.get(i).getDisciplinas().get(j)
						.equals(disciplina)) {
					listaDePeriodo.get(i).getDisciplinas().remove(disciplina);
				}
			}
		}
	}

	/**
	 * 
	 * @param disciplina
	 * @param periodo
	 * @return
	 */
	public String getNomeDaDisciplinasRequisitos(Disciplina disciplina,
			int periodo) {
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
			if (!getTodasDisciplinas().contains(d)) {
				requisitosNaoPreenchidos += d.getNome() + "\n";
			}
		}
		return requisitosNaoPreenchidos;
	}

	/**
	 * 
	 * @param periodo
	 *            novo periodo alocado ao aluno
	 */
	public void addPeriodo(Periodo periodo) {
		this.listaDePeriodo.add(periodo);
	}

	/**
	 * 
	 * @return se o aluno jah possui a alocacao de periodos no sistema
	 */
	public boolean semPeriodos() {
		return this.listaDePeriodo.isEmpty();
	}

	/**
	 * 
	 * @param periodo
	 *            numero do periodo
	 * @return as disciplinas do periodo
	 */
	public List<Disciplina> getDisciplinasDoPeriodo(int periodo) {
		return listaDePeriodo.get(periodo).getDisciplinas();
	}

	/**
	 * 
	 * @param periodo
	 *            numero do periodo
	 * @return o numero de creditos do periodo
	 */
	public int getNumeroDeCreditosDoPeriodo(int periodo) {
		return this.listaDePeriodo.get(periodo).getNumeroDeCreditosDoPeriodo();
	}

	/**
	 * 
	 * @param periodo
	 *            numero do periodo
	 * @return o n(periodo)-esimo periodo do aluno
	 */
	public Periodo getPeriodo(int periodo) {
		return this.listaDePeriodo.get(periodo);
	}

	/**
	 * Remove a disciplina e relacionadas do aluno
	 * 
	 * @param disciplina
	 *            disciplina que sera removida juntamente com suas disciplinas
	 *            relacionadas
	 */
	public void removeDisciplinaESeusPreRequisitos(Disciplina disciplina) {
		List<Disciplina> disciplinasDependentes = getDisciplinasDependentes(disciplina);
		for (int i = 0; i < this.listaDePeriodo.size(); i++) {
			for (int j = 0; j < this.listaDePeriodo.get(i).getDisciplinas()
					.size(); j++) {
				if (this.listaDePeriodo.get(i).getDisciplinas().get(j)
						.equals(disciplina)) {
					this.listaDePeriodo.get(i).getDisciplinas()
							.remove(disciplina);
				}
			}
		}
		for (int i = 0; i < disciplinasDependentes.size(); i++) {
			removeDisciplinaESeusPreRequisitos(disciplinasDependentes.get(i));
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
	private List<Disciplina> getDisciplinasDependentes(Disciplina disciplina) {
		List<Disciplina> disciplinasDependentes = new ArrayList<Disciplina>();
		for (Disciplina disciplinaDoAluno : getTodasDisciplinas()) {
			if (disciplinaDoAluno.getListaDePreRequisitos()
					.contains(disciplina)) {
				disciplinasDependentes.add(disciplinaDoAluno);
			}
		}
		return disciplinasDependentes;
	}

	/**
	 * 
	 * @return o numero de periodos do plano
	 */
	public int getNumeroDePeriodos() {
		return this.listaDePeriodo.size();
	}

}
