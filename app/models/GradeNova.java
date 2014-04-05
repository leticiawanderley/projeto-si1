package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Grade.DisciplinasDSC;

public class GradeNova extends Grade {
	
	public enum DisciplinasDSC {
		P1(new Disciplina("Programação I", QUATRO_CREDITOS, MEDIO)),
		LP1(new Disciplina("Laboratório de Programação I", QUATRO_CREDITOS, MEDIO)),
		IC(new Disciplina("Introdução à Computação", QUATRO_CREDITOS, FACIL)),	
		DISCRETA1(new Disciplina("Matemática Discreta 1", QUATRO_CREDITOS, MEDIO)),
		OP_GERAL1(new Disciplina("Optativa Geral 1", QUATRO_CREDITOS, FACIL)),
		
		DISCRETA2(new Disciplina("Matemática Discreta 2", QUATRO_CREDITOS, new Disciplina[]{DISCRETA1.getDisciplina()}, MEDIO)),
		CALCULO1(new Disciplina("Cálculo Diferencial e Integral I", QUATRO_CREDITOS, MEDIO)),
		P2(new Disciplina("Programação II", QUATRO_CREDITOS, new Disciplina[]{P1.getDisciplina(), LP1.getDisciplina()}, MEDIO)),
		LP2(new Disciplina("Laboratório de Programação II", QUATRO_CREDITOS, new Disciplina[]{P1.getDisciplina(), LP1.getDisciplina()}, MEDIO)),
		OP_GERAL2(new Disciplina("Optativa Geral 2", QUATRO_CREDITOS, FACIL)),
		
		ALGEBRALINEAR(new Disciplina("Álgebra Linear", QUATRO_CREDITOS , new Disciplina[]{DISCRETA1.getDisciplina()}, DIFICIL)),
		GRAFOS(new Disciplina("Teoria dos Grafos", QUATRO_CREDITOS, MEDIO)),
		CALCULO2(new Disciplina("Cálculo Diferencial e Integral II", QUATRO_CREDITOS, new Disciplina[]{CALCULO1.getDisciplina()}, DIFICIL)),
		EDA(new Disciplina("Estruturas de Dados e Algoritmos", QUATRO_CREDITOS  , new Disciplina[]{P2.getDisciplina(),LP2.getDisciplina()}, MEDIO)),
		LEDA(new Disciplina("Laboratório de Estruturas de Dados e Algoritmos", QUATRO_CREDITOS  , new Disciplina[]{P2.getDisciplina(),LP2.getDisciplina()}, MEDIO)),
		LOGICA_COMP(new Disciplina("Lógica para Computação", QUATRO_CREDITOS, new Disciplina[]{DISCRETA1.getDisciplina()}, MEDIO)),
		
		INTRO_PROB(new Disciplina("Introdução à Probabilidade", QUATRO_CREDITOS  , new Disciplina[]{CALCULO1.getDisciplina(), DISCRETA2.getDisciplina()}, DIFICIL)),
		PROJ_SOFT(new Disciplina("Projeto de Software", QUATRO_CREDITOS, MEDIO)),
		PLP(new Disciplina("Paradigmas de Linguagens de Programação", QUATRO_CREDITOS, MEDIO)),
		BD1(new Disciplina("Bancos de Dados I", QUATRO_CREDITOS,new Disciplina[]{EDA.getDisciplina()}, MEDIO)),
		OAC(new Disciplina("Organização e Arquitetura de Computadores I", QUATRO_CREDITOS, MEDIO)),
		LOAC(new Disciplina("Laboratório de Organização e Arquitetura de Computadores", QUATRO_CREDITOS, DIFICIL)),
		
		ESTATISTICA(new Disciplina("Estatística Aplicada", QUATRO_CREDITOS, new Disciplina[]{INTRO_PROB.getDisciplina()}, MEDIO)),
		ANALISE(new Disciplina("Análise de Sistemas", QUATRO_CREDITOS, DIFICIL)),
		ES(new Disciplina("Engenharia de Software I", QUATRO_CREDITOS, MEDIO)),
		REDES(new Disciplina("Redes de Computadores", QUATRO_CREDITOS, MEDIO)),
		SO(new Disciplina("Sistemas Operacionais", QUATRO_CREDITOS, DIFICIL)),
		TC(new Disciplina("Teoria da Computação", QUATRO_CREDITOS  , new Disciplina[]{PLP.getDisciplina()}, MEDIO)),
		
		METODOLOGIA(new Disciplina("Metodologia Científica", QUATRO_CREDITOS, FACIL)),
		CONCORRENTE(new Disciplina("Programação Concorrente", QUATRO_CREDITOS, new Disciplina[]{SO.getDisciplina()}, DIFICIL)),
		IA(new Disciplina("Integência Artificial I", QUATRO_CREDITOS, new Disciplina[]{TC.getDisciplina()}, DIFICIL)),
		OP1(new Disciplina("Optativa Específica 1", QUATRO_CREDITOS, MEDIO)),
		OP2(new Disciplina("Optativa Específica 2", QUATRO_CREDITOS, FACIL)),
		
		ATAL(new Disciplina("Análise e Técnicas de Algoritmos", QUATRO_CREDITOS, MUITO_DIFICIL)),
		COMPILADORES(new Disciplina("Compiladores", QUATRO_CREDITOS, MUITO_DIFICIL)),
		OP3(new Disciplina("Optativa Específica 3", QUATRO_CREDITOS, MEDIO)),
		OP4(new Disciplina("Optativa Específica 4", QUATRO_CREDITOS, DIFICIL)),
		OP_GERAL3(new Disciplina("Optativa Geral 3", QUATRO_CREDITOS, FACIL)),
		
		PROJETO1(new Disciplina("Projeto em Computação 1", QUATRO_CREDITOS, new Disciplina[]{ES.getDisciplina()}, DIFICIL)),
		OP5(new Disciplina("Optativa Específica 5", QUATRO_CREDITOS, MEDIO)),
		OP6(new Disciplina("Optativa Específica 6", QUATRO_CREDITOS, FACIL)),
		OP_GERAL4(new Disciplina("Optativa Geral 4", QUATRO_CREDITOS, MEDIO)),
		TCC1(new Disciplina("Trabalho de Conclusão de Curso 1", QUATRO_CREDITOS, DIFICIL)),
		
		
		PROJETO2(new Disciplina("Projeto em Computação 2", QUATRO_CREDITOS, new Disciplina[]{PROJETO1.getDisciplina()}, DIFICIL)),
		OP7(new Disciplina("Optativa Específica 7", QUATRO_CREDITOS, MEDIO)),
		OP8(new Disciplina("Optativa Específica 8", QUATRO_CREDITOS, FACIL)),
		OP9(new Disciplina("Optativa Específica 9", QUATRO_CREDITOS, DIFICIL)),
		OP10(new Disciplina("Optativa Específica 10", QUATRO_CREDITOS, MEDIO)),
		TCC2(new Disciplina("Trabalho de Conclusão de Curso 2", QUATRO_CREDITOS, new Disciplina[]{TCC1.getDisciplina()}, DIFICIL));		
		
		private Disciplina disciplina;

		/**
		 * Construturo do Enum
		 * @param disciplina disciplina que o enum contem
		 */
		DisciplinasDSC(Disciplina disciplina) {
			this.disciplina = disciplina;
		}
		
		/**
		 * 
		 * @return a disciplina do enum
		 */
		public Disciplina getDisciplina() {
			return this.disciplina;
		}
	}
	
	@Override
	public List<Disciplina> getDisciplinasDoPrimeiroPeriodo() {
		List<Disciplina> disciplinasDoPrimeiroPeriodo = Arrays.asList(DisciplinasDSC.DISCRETA1.getDisciplina(),
				DisciplinasDSC.P1.getDisciplina(),
				DisciplinasDSC.LP1.getDisciplina(),
				DisciplinasDSC.IC.getDisciplina(),
				DisciplinasDSC.OP_GERAL1.getDisciplina());
		return disciplinasDoPrimeiroPeriodo;
	}

	@Override
	public List<Disciplina> getDisciplinasDoSegundoPeriodo() {
		List<Disciplina> disciplinasDoSegundoPeriodo = Arrays.asList(DisciplinasDSC.DISCRETA2.getDisciplina(),
				DisciplinasDSC.CALCULO1.getDisciplina(),
				DisciplinasDSC.P2.getDisciplina(),
				DisciplinasDSC.LP2.getDisciplina(),
				DisciplinasDSC.OP_GERAL2.getDisciplina());
		return disciplinasDoSegundoPeriodo;
	}
	
	@Override
	public List<Disciplina> getDisciplinasDoTerceiroPeriodo() {
		List<Disciplina> disciplinasDoTerceiroPeriodo = Arrays.asList(DisciplinasDSC.ALGEBRALINEAR.getDisciplina(),
				DisciplinasDSC.GRAFOS.getDisciplina(),
				DisciplinasDSC.CALCULO2.getDisciplina(),
				DisciplinasDSC.EDA.getDisciplina(),
				DisciplinasDSC.LEDA.getDisciplina(),
				DisciplinasDSC.LOGICA_COMP.getDisciplina());
		return disciplinasDoTerceiroPeriodo;
	}

	@Override
	public List<Disciplina> getDisciplinasDoQuartoPeriodo() {
		List<Disciplina> disciplinasDoQuartoPeriodo = Arrays.asList(DisciplinasDSC.INTRO_PROB.getDisciplina(),
				DisciplinasDSC.PROJ_SOFT.getDisciplina(),
				DisciplinasDSC.PLP.getDisciplina(),
				DisciplinasDSC.BD1.getDisciplina(),
				DisciplinasDSC.OAC.getDisciplina(),
				DisciplinasDSC.LOAC.getDisciplina());
		return disciplinasDoQuartoPeriodo;
	}

	@Override
	public List<Disciplina> getDisciplinasDoQuintoPeriodo() {
		List<Disciplina> disciplinasDoQuintoPeriodo = Arrays.asList(DisciplinasDSC.ESTATISTICA.getDisciplina(),
				DisciplinasDSC.ANALISE.getDisciplina(),
				DisciplinasDSC.ES.getDisciplina(),
				DisciplinasDSC.REDES.getDisciplina(),
				DisciplinasDSC.SO.getDisciplina(),
				DisciplinasDSC.TC.getDisciplina());
		return disciplinasDoQuintoPeriodo;
	}

	@Override
	public List<Disciplina> getDisciplinasDoSextoPeriodo() {
		List<Disciplina> disciplinasDoSextoPeriodo = Arrays.asList(DisciplinasDSC.METODOLOGIA.getDisciplina(),
				DisciplinasDSC.CONCORRENTE.getDisciplina(),
				DisciplinasDSC.IA.getDisciplina(),
				DisciplinasDSC.OP1.getDisciplina(),
				DisciplinasDSC.OP2.getDisciplina());
		return disciplinasDoSextoPeriodo;
	}

	@Override
	public List<Disciplina> getDisciplinasDoSetimoPeriodo() {
		List<Disciplina> disciplinasDoSetimoPeriodo = Arrays.asList(DisciplinasDSC.ATAL.getDisciplina(),
				DisciplinasDSC.COMPILADORES.getDisciplina(),
				DisciplinasDSC.OP3.getDisciplina(),
				DisciplinasDSC.OP4.getDisciplina(),
				DisciplinasDSC.OP_GERAL3.getDisciplina());
		return disciplinasDoSetimoPeriodo;
	}

	@Override
	public List<Disciplina> getDisciplinasDoOitavoPeriodo() {
		List<Disciplina> disciplinasDoOitavoPeriodo = Arrays.asList(DisciplinasDSC.PROJETO1.getDisciplina(),
				DisciplinasDSC.OP5.getDisciplina(),
				DisciplinasDSC.OP6.getDisciplina(),
				DisciplinasDSC.TCC1.getDisciplina(),
				DisciplinasDSC.OP_GERAL4.getDisciplina());
		return disciplinasDoOitavoPeriodo;
	}

	@Override
	public List<Disciplina> getDisciplinasDoNonoPeriodo() {
		List<Disciplina> disciplinasDoNonoPeriodo = Arrays.asList(DisciplinasDSC.PROJETO2.getDisciplina(),
				DisciplinasDSC.OP7.getDisciplina(),
				DisciplinasDSC.OP8.getDisciplina(),
				DisciplinasDSC.OP9.getDisciplina(),
				DisciplinasDSC.OP10.getDisciplina(),
				DisciplinasDSC.TCC2.getDisciplina());
		return disciplinasDoNonoPeriodo;
	}
	
	@Override
	public List<Disciplina> getDisciplinasOptativas() {
		List<Disciplina> disciplinasOptativas = new ArrayList<Disciplina>();
		return disciplinasOptativas;
	}
	
	@Override
	public List<Disciplina> getDisciplinasTECC() {
		List<Disciplina> disciplinasTECC = new ArrayList<Disciplina>();
		return disciplinasTECC;
	}

}
