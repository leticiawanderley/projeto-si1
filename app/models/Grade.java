
package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representacao da grade de Ciencia da Computacao da Universidade Federal de Campina Grande 
 *
 */
public abstract class Grade {
	
	private static final int DOIS_CREDITOS = 2;
	private static final int QUATRO_CREDITOS = 4;
	private static final int SEIS_CREDITOS = 6;
	private static final int MUITO_FACIL = 1;
	private static final int FACIL = 2;
	private static final int MEDIO = 3;
	private static final int DIFICIL = 4;
	private static final int MUITO_DIFICIL = 5;
	
	//INFORMATION EXPERT - Contém as informações da Grade: listaDeDisciplinas.
	private List<Disciplina> listaDeDisciplinas;

	/**
	 * Enum representando as disciplinas do DSC
	 *
	 */
	public enum DisciplinasDSC {
		
		CALCULO1(new Disciplina("Cálculo Diferencial e Integral I", QUATRO_CREDITOS, MEDIO)),
		VETORIAL(new Disciplina("Álgebra Vetorial e Geometria Analítica", QUATRO_CREDITOS, FACIL)),
		LPT(new Disciplina("Leitura e Produção de Textos", QUATRO_CREDITOS, MUITO_FACIL)),
		P1(new Disciplina("Programação I", QUATRO_CREDITOS, MEDIO)),
		LP1(new Disciplina("Laboratório de Programação I", QUATRO_CREDITOS, MEDIO)),
		IC(new Disciplina("Introdução à Computação", QUATRO_CREDITOS, FACIL)),	
		
		CALCULO2(new Disciplina("Cálculo Diferencial e Integral II", QUATRO_CREDITOS, new Disciplina[]{CALCULO1.getDisciplina(), VETORIAL.getDisciplina()}, DIFICIL )),
		DISCRETA(new Disciplina("Matemática Discreta", QUATRO_CREDITOS, MEDIO)),
		METODOLOGIA(new Disciplina("Metodologia Científica", QUATRO_CREDITOS, FACIL)),
		GRAFOS(new Disciplina("Teoria dos Grafos", DOIS_CREDITOS, new Disciplina[]{P1.getDisciplina(), LP1.getDisciplina()}, MEDIO)),
		CLASSICA(new Disciplina("Fundamentos de Física Clássica", QUATRO_CREDITOS, new Disciplina[]{VETORIAL.getDisciplina(), CALCULO1.getDisciplina()}, DIFICIL)),
		P2(new Disciplina("Programação II", QUATRO_CREDITOS, new Disciplina[]{IC.getDisciplina(), P1.getDisciplina(), LP1.getDisciplina()}, MEDIO)),
		LP2(new Disciplina("Laboratório de Programação II", QUATRO_CREDITOS, new Disciplina[]{IC.getDisciplina(), P1.getDisciplina(), LP1.getDisciplina()}, MEDIO)),
	
		ALGEBRALINEAR(new Disciplina("Álgebra Linear", QUATRO_CREDITOS , new Disciplina[]{VETORIAL.getDisciplina()}, DIFICIL)),
		PROBABILIDADE(new Disciplina("Probabilidade e Estatística", QUATRO_CREDITOS  , new Disciplina[]{CALCULO2.getDisciplina()}, DIFICIL)),
		EDA(new Disciplina("Estruturas de Dados e Algoritmos", QUATRO_CREDITOS  , new Disciplina[]{P2.getDisciplina(),LP2.getDisciplina(),GRAFOS.getDisciplina()}, MEDIO)),
		MODERNA(new Disciplina("Fundamentos de Física Moderna", QUATRO_CREDITOS  , new Disciplina[]{CLASSICA.getDisciplina(),CALCULO2.getDisciplina()}, DIFICIL)),
		GI(new Disciplina("Gerência da Informação", QUATRO_CREDITOS, FACIL)),
		LEDA(new Disciplina("Laboratório de Estruturas de Dados e Algoritmos", QUATRO_CREDITOS  , new Disciplina[]{P2.getDisciplina(),LP2.getDisciplina(),GRAFOS.getDisciplina()}, MEDIO)),
		TC(new Disciplina("Teoria da Computação", QUATRO_CREDITOS  , new Disciplina[]{DISCRETA.getDisciplina(),IC.getDisciplina(),GRAFOS.getDisciplina()}, MEDIO)),
		
		METODOS(new Disciplina("Métodos Estatísticos",QUATRO_CREDITOS, new Disciplina[]{PROBABILIDADE.getDisciplina(), ALGEBRALINEAR.getDisciplina()}, MEDIO)),
		OAC(new Disciplina("Organização e Arquitetura de Computadores I", QUATRO_CREDITOS  , new Disciplina[]{EDA.getDisciplina(),LEDA.getDisciplina(),MODERNA.getDisciplina()}, MEDIO)),
		PLP(new Disciplina("Paradigmas de Linguagens de Programação", DOIS_CREDITOS  , new Disciplina[]{LEDA.getDisciplina(),EDA.getDisciplina(),TC.getDisciplina()}, MEDIO)),
		LOGICA(new Disciplina("Lógica Matemática", QUATRO_CREDITOS   , new Disciplina[]{TC.getDisciplina()}, MEDIO)),
		ES(new Disciplina("Engenharia de Software I", QUATRO_CREDITOS,new Disciplina[]{PROBABILIDADE.getDisciplina(), LP2.getDisciplina(),P2.getDisciplina()}, MEDIO)),
		SI1(new Disciplina("Sistemas de Informação I", QUATRO_CREDITOS , new Disciplina[]{GI.getDisciplina()}, MEDIO)),
		LOAC(new Disciplina("Laboratório de Organização e Arquitetura de Computadores", QUATRO_CREDITOS  , new Disciplina[]{EDA.getDisciplina(),LEDA.getDisciplina(),MODERNA.getDisciplina()}, DIFICIL)),
		
		INFOSOC(new Disciplina("Informática e Sociedade",DOIS_CREDITOS, MUITO_FACIL)),
		ATAL(new Disciplina("Análise e Técnicas de Algoritmos", QUATRO_CREDITOS  , new Disciplina[]{EDA.getDisciplina(),LEDA.getDisciplina(),CALCULO2.getDisciplina(),LOGICA.getDisciplina()}, MUITO_DIFICIL)),
		COMPILADORES(new Disciplina("Compiladores", QUATRO_CREDITOS  , new Disciplina[]{PLP.getDisciplina(),OAC.getDisciplina(),LOAC.getDisciplina()}, MUITO_DIFICIL)),
		REDES(new Disciplina("Redes de Computadores", QUATRO_CREDITOS , new Disciplina[]{OAC.getDisciplina(),LOAC.getDisciplina()}, MEDIO)),
		BD1(new Disciplina("Bancos de Dados I", QUATRO_CREDITOS,new Disciplina[]{SI1.getDisciplina()}, MEDIO)),
		SI2(new Disciplina("Sistemas de Informação II", QUATRO_CREDITOS , new Disciplina[]{SI1.getDisciplina()}, MEDIO)),
		LES(new Disciplina("Laboratório de Engenharia de Software", DOIS_CREDITOS, new Disciplina[]{ES.getDisciplina()}, DIFICIL)),
		
		SO(new Disciplina("Sistemas Operacionais", QUATRO_CREDITOS, new Disciplina[]{OAC.getDisciplina(), LOAC.getDisciplina()}, DIFICIL)),
		IRC(new Disciplina("Interconexão de Redes de Computadores", DOIS_CREDITOS, new Disciplina[]{REDES.getDisciplina()}, MEDIO)),
		LIRC(new Disciplina("Laboratório de Interconexão de Redes de Computadores", DOIS_CREDITOS, new Disciplina[]{REDES.getDisciplina()}, MEDIO)),
		IA(new Disciplina("Integência Artificial I", QUATRO_CREDITOS, new Disciplina[]{ATAL.getDisciplina(), PLP.getDisciplina(), METODOS.getDisciplina()}, DIFICIL)),
		BD2(new Disciplina("Banco de Dados II", QUATRO_CREDITOS, new Disciplina[]{BD1.getDisciplina(), SI2.getDisciplina()}, MEDIO)),
		DIREITO(new Disciplina("Direito e Cidadania", QUATRO_CREDITOS, MUITO_FACIL)),
		OP1(new Disciplina("Optativa 1", QUATRO_CREDITOS, MEDIO)),
		OP2(new Disciplina("Optativa 2", DOIS_CREDITOS, FACIL)),
		
		MSN(new Disciplina("Métodos e Softwares Numéricos", QUATRO_CREDITOS, new Disciplina[]{ATAL.getDisciplina(), ALGEBRALINEAR.getDisciplina()}, FACIL)),
		ADSD(new Disciplina("Avaliação de Desempenho de Sistemas Discretos", QUATRO_CREDITOS, new Disciplina[]{PROBABILIDADE.getDisciplina()}, DIFICIL)),
		PROJETO1(new Disciplina("Projeto em Computação 1", QUATRO_CREDITOS, new Disciplina[]{METODOLOGIA.getDisciplina(), LES.getDisciplina()}, DIFICIL)),
		OP3(new Disciplina("Optativa 3", QUATRO_CREDITOS, DIFICIL)),
		OP4(new Disciplina("Optativa 4", QUATRO_CREDITOS, MEDIO)),
		OP5(new Disciplina("Optativa 5", QUATRO_CREDITOS, FACIL)),
		OP6(new Disciplina("Optativa 6", QUATRO_CREDITOS, FACIL)),
		
		PROJETO2(new Disciplina("Projeto em Computação 2", SEIS_CREDITOS, new Disciplina[]{PROJETO1.getDisciplina()}, DIFICIL)),
		OP7(new Disciplina("Optativa 7", QUATRO_CREDITOS, MEDIO)),
		OP8(new Disciplina("Optativa 8", QUATRO_CREDITOS, DIFICIL)),
		OP9(new Disciplina("Optativa 9", QUATRO_CREDITOS, FACIL)),
		OP10(new Disciplina("Optativa 10",	QUATRO_CREDITOS, FACIL)),
		OP11(new Disciplina("Optativa 11", QUATRO_CREDITOS, MEDIO)),
		
		
		BASQUETE(new Disciplina("Basquetebol - Fem", DOIS_CREDITOS, MUITO_FACIL)),
		CALC3(new Disciplina("Cálculo Diferencial e Integral 3", QUATRO_CREDITOS, MUITO_DIFICIL)),
		CRIATIVIDADE(new Disciplina("Criatividade", QUATRO_CREDITOS, FACIL)),
		ETICA(new Disciplina("Etica", QUATRO_CREDITOS, MUITO_FACIL)),
		INGLES(new Disciplina("Inglês", QUATRO_CREDITOS, FACIL)),
		
		DATAMINIG(new Disciplina("Tecc (DataMining)", QUATRO_CREDITOS, MEDIO)),
		COMPPARALELA(new Disciplina("Tecc (Computação Paralela)", DOIS_CREDITOS, DIFICIL)),
		GRADESCOMP(new Disciplina("Tecc (Grades Computacionais)", DOIS_CREDITOS, MEDIO)),
		PROG3(new Disciplina("Tecc (Programação 3)", QUATRO_CREDITOS, MEDIO)),
		COMPQUANTICA(new Disciplina("Tecc (Computação Quântica)", QUATRO_CREDITOS, MUITO_DIFICIL)),
		METODOSFORMAIS(new Disciplina("Tecc (Métodos Formais)", QUATRO_CREDITOS, MEDIO));
		
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
	
	/**
	 * Construtor da Classe
	 * 
	 */
	public Grade() {
		listaDeDisciplinas = new ArrayList<Disciplina>();
		populaGrade();
	}

	/**
	 * Adiciona disciplinas a grade
	 * 
	 */
	private void populaGrade() {		
		List<Disciplina> disciplinasOptativas = Arrays.asList(DisciplinasDSC.CALC3.getDisciplina(),
				DisciplinasDSC.BASQUETE.getDisciplina(),
				DisciplinasDSC.CRIATIVIDADE.getDisciplina(),
				DisciplinasDSC.ETICA.getDisciplina(),
				DisciplinasDSC.INGLES.getDisciplina());
		
		List<Disciplina> disciplinasTECC = Arrays.asList(DisciplinasDSC.COMPPARALELA.getDisciplina(),
				DisciplinasDSC.COMPQUANTICA.getDisciplina(),
				DisciplinasDSC.DATAMINIG.getDisciplina(),
				DisciplinasDSC.GRADESCOMP.getDisciplina(),
				DisciplinasDSC.METODOSFORMAIS.getDisciplina(),
				DisciplinasDSC.PROG3.getDisciplina());
		
		listaDeDisciplinas.addAll(getDisciplinasDoPrimeiroPeriodo());
		listaDeDisciplinas.addAll(getDisciplinasDoSegundoPeriodo());
		listaDeDisciplinas.addAll(getDisciplinasDoTerceiroPeriodo());
		listaDeDisciplinas.addAll(getDisciplinasDoQuartoPeriodo());
		listaDeDisciplinas.addAll(getDisciplinasDoQuintoPeriodo());
		listaDeDisciplinas.addAll(getDisciplinasDoSextoPeriodo());
		listaDeDisciplinas.addAll(getDisciplinasDoSetimoPeriodo());
		listaDeDisciplinas.addAll(getDisciplinasDoOitavoPeriodo());
		listaDeDisciplinas.addAll(disciplinasOptativas);
		listaDeDisciplinas.addAll(disciplinasTECC);		
	}

	/**
	 * 
	 * @param nome da disciplina
	 * @return o numero de creditos da cadeira especificada
	 */
	public int getCreditosDaDisciplina(String nome) {
		for (int i = 0; i < listaDeDisciplinas.size(); i++) {
			Disciplina disciplina = listaDeDisciplinas.get(i);
			if (disciplina.getNome().equals(nome)) {
				return disciplina.getCreditos();
			}
		}
		return -1;
	}
	
	/**
	 * 
	 * @return as disciplinas do primeiro periodo
	 */
	
	public List<Disciplina> getDisciplinasDoPrimeiroPeriodo() {
		List<Disciplina> disciplinasDoPrimeiroPeriodo = new ArrayList<Disciplina>();
		disciplinasDoPrimeiroPeriodo.add(DisciplinasDSC.CALCULO1.getDisciplina());
		disciplinasDoPrimeiroPeriodo.add(DisciplinasDSC.VETORIAL.getDisciplina());
		disciplinasDoPrimeiroPeriodo.add(DisciplinasDSC.LPT.getDisciplina());
		disciplinasDoPrimeiroPeriodo.add(DisciplinasDSC.P1.getDisciplina());
		disciplinasDoPrimeiroPeriodo.add(DisciplinasDSC.LP1.getDisciplina());
		disciplinasDoPrimeiroPeriodo.add(DisciplinasDSC.IC.getDisciplina());
		return disciplinasDoPrimeiroPeriodo;
	}
	
	public abstract List<Disciplina> getDisciplinasDoSegundoPeriodo();
	
	
	public List<Disciplina> getDisciplinasDoTerceiroPeriodo() {
		List<Disciplina> disciplinasDoTerceiroPeriodo = new ArrayList<Disciplina>();
		disciplinasDoTerceiroPeriodo.add(DisciplinasDSC.ALGEBRALINEAR.getDisciplina());
		disciplinasDoTerceiroPeriodo.add(DisciplinasDSC.PROBABILIDADE.getDisciplina());
		disciplinasDoTerceiroPeriodo.add(DisciplinasDSC.EDA.getDisciplina());
		disciplinasDoTerceiroPeriodo.add(DisciplinasDSC.LEDA.getDisciplina());
		disciplinasDoTerceiroPeriodo.add(DisciplinasDSC.TC.getDisciplina());
		disciplinasDoTerceiroPeriodo.add(DisciplinasDSC.GI.getDisciplina());
		disciplinasDoTerceiroPeriodo.add(DisciplinasDSC.MODERNA.getDisciplina());
		return disciplinasDoTerceiroPeriodo;
	}
	
	public abstract List<Disciplina> getDisciplinasDoQuartoPeriodo();
		
	public abstract List<Disciplina> getDisciplinasDoQuintoPeriodo();
	
	public abstract List<Disciplina> getDisciplinasDoSextoPeriodo();
		
	public abstract List<Disciplina> getDisciplinasDoSetimoPeriodo();
		
	public abstract List<Disciplina> getDisciplinasDoOitavoPeriodo();
		
	
	/**
	 * 
	 * @return as disciplinas do curso
	 */
	public List<Disciplina> getDisciplinasDoCurso() {
		return listaDeDisciplinas;
	}

}
