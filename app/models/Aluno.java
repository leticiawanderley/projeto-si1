package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import models.validador.ValidacaoCreditos;

/**
 * Classe que representa um aluno
 * 
 */
@Entity
public class Aluno extends Usuario {

	// INFORMATION EXPERT - contém as informacoes do
	// aluno:nomeDoAluno,listaDePeriodos .

	private static final long serialVersionUID = 7507028957989504099L;
	private static final int PRIMEIRO_PERIODO = 0;
	private static final int ULTIMO_PERIODO = 9;
	private static final String CREDITOS_PAGOS = "creditosPagos";
	private static final String CREDITOS_EM_CURSO = "creditosEmCurso";
	private static final String CREDITOS_PLANEJADOS = "creditosPlanejados";
	//@OrderBy("periodo")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Periodo> listaDePeriodo;
	private int periodoAtual;

	/**
	 * Construtor da classe
	 * 
	 * @param nome
	 *            Nome do aluno
	 */
	public Aluno(String name, String email, String password) {
		super(name, email, password);
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

	public Map<String, Integer> estatisticasDoAluno() {
		Map<String, Integer> estatisticas = new HashMap<String, Integer>();
		int quantidadeDeCreditos;
		estatisticas.put(CREDITOS_PAGOS, 0);
		estatisticas.put(CREDITOS_EM_CURSO, 0);
		estatisticas.put(CREDITOS_PLANEJADOS, 0);
		for (int i = 0; i < getListaDePeriodos().size(); i++) {
			if (i < periodoAtual) {
				quantidadeDeCreditos = estatisticas.get(CREDITOS_PAGOS);
				estatisticas.put(CREDITOS_PAGOS, quantidadeDeCreditos
						+ getListaDePeriodos().get(i)
								.getNumeroDeCreditosDoPeriodo());
			} else if (i == periodoAtual) {
				estatisticas.put(CREDITOS_EM_CURSO, getListaDePeriodos().get(i)
						.getNumeroDeCreditosDoPeriodo());
			} else {
				quantidadeDeCreditos = estatisticas.get(CREDITOS_PLANEJADOS);
				estatisticas.put(CREDITOS_PLANEJADOS, quantidadeDeCreditos
						+ getListaDePeriodos().get(i)
								.getNumeroDeCreditosDoPeriodo());
			}
		}
		return estatisticas;
	}
	
	

}
