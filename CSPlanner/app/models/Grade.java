package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representacao da grade de Ciencia da Computacao da Universidade Federal de Campina Grande 
 *
 */
public class Grade {
	
	private static final int DOIS_CREDITOS = 2;
	private static final int QUATRO_CREDITOS = 4;
	//INFORMATION EXPERT - Contém as informações da Grade: listaDeDisciplinas.
	private List<Disciplina> listaDeDisciplinas;

	/**
	 * Enum representando as disciplinas do DSC
	 *
	 */
	public enum DisciplinasDSC {
		
		CALCULO1(new Disciplina("Cálculo Diferencial e Integral I", QUATRO_CREDITOS)),
		VETORIAL(new Disciplina("Álgebra Vetorial e Geometria Analítica", QUATRO_CREDITOS)),
		LPT(new Disciplina("Leitura e Produção de Textos", QUATRO_CREDITOS)),
		P1(new Disciplina("Programação I", QUATRO_CREDITOS)),
		LP1(new Disciplina("Laboratório de Programação I", QUATRO_CREDITOS)),
		IC(new Disciplina("Introdução à Computação", QUATRO_CREDITOS)),	
		
		CALCULO2(new Disciplina("Cálculo Diferencial e Integral II", QUATRO_CREDITOS, new Disciplina[]{CALCULO1.getDisciplina(), VETORIAL.getDisciplina()})),
		DISCRETA(new Disciplina("Matemática Discreta", QUATRO_CREDITOS)),
		METODOLOGIA(new Disciplina("Metodologia Científica", QUATRO_CREDITOS)),
		GRAFOS(new Disciplina("Teoria dos Grafos", DOIS_CREDITOS, new Disciplina[]{P1.getDisciplina(), LP1.getDisciplina()})),
		CLASSICA(new Disciplina("Fundamentos de Física Clássica", QUATRO_CREDITOS, new Disciplina[]{VETORIAL.getDisciplina(), CALCULO1.getDisciplina()})),
		P2(new Disciplina("Programação II", QUATRO_CREDITOS, new Disciplina[]{IC.getDisciplina(), P1.getDisciplina(), LP1.getDisciplina()})),
		LP2(new Disciplina("Laboratório de Programação II", QUATRO_CREDITOS, new Disciplina[]{IC.getDisciplina(), P1.getDisciplina(), LP1.getDisciplina()})),
	
		ALGEBRALINEAR(new Disciplina("Álgebra Linear", QUATRO_CREDITOS , new Disciplina[]{VETORIAL.getDisciplina()})),
		PROBABILIDADE(new Disciplina("Probabilidade e Estatística", QUATRO_CREDITOS  , new Disciplina[]{CALCULO2.getDisciplina()})),
		EDA(new Disciplina("Estruturas de Dados e Algoritmos", QUATRO_CREDITOS  , new Disciplina[]{P2.getDisciplina(),LP2.getDisciplina(),GRAFOS.getDisciplina()})),
		MODERNA(new Disciplina("Fundamentos de Física Moderna", QUATRO_CREDITOS  , new Disciplina[]{CLASSICA.getDisciplina(),CALCULO2.getDisciplina()})),
		GI(new Disciplina("Gerência da Informação", QUATRO_CREDITOS)),
		LEDA(new Disciplina("Laboratório de Estruturas de Dados e Algoritmos", QUATRO_CREDITOS  , new Disciplina[]{P2.getDisciplina(),LP2.getDisciplina(),GRAFOS.getDisciplina()})),
		TC(new Disciplina("Teoria da Computação", QUATRO_CREDITOS  , new Disciplina[]{DISCRETA.getDisciplina(),IC.getDisciplina(),GRAFOS.getDisciplina()})),
		
		METODOS(new Disciplina("Métodos Estatísticos",QUATRO_CREDITOS, new Disciplina[]{PROBABILIDADE.getDisciplina(), ALGEBRALINEAR.getDisciplina()})),
		OAC(new Disciplina("Organização e Arquitetura de Computadores I", QUATRO_CREDITOS  , new Disciplina[]{EDA.getDisciplina(),LEDA.getDisciplina(),MODERNA.getDisciplina()})),
		PLP(new Disciplina("Paradigmas de Linguagens de Programação", DOIS_CREDITOS  , new Disciplina[]{LEDA.getDisciplina(),EDA.getDisciplina(),TC.getDisciplina()})),
		LOGICA(new Disciplina("Lógica Matemática", QUATRO_CREDITOS   , new Disciplina[]{TC.getDisciplina()})),
		ES(new Disciplina("Engenharia de Software I", QUATRO_CREDITOS,new Disciplina[]{PROBABILIDADE.getDisciplina(), LP2.getDisciplina(),P2.getDisciplina()})),
		SI1(new Disciplina("Sistemas de Informação I", QUATRO_CREDITOS , new Disciplina[]{GI.getDisciplina()})),
		LOAC(new Disciplina("Laboratório de Organização e Arquitetura de Computadores", QUATRO_CREDITOS  , new Disciplina[]{EDA.getDisciplina(),LEDA.getDisciplina(),MODERNA.getDisciplina()})),
		
		INFOSOC(new Disciplina("Informática e Sociedade",DOIS_CREDITOS)),
		ATAL(new Disciplina("Análise e Técnicas de Algoritmos", QUATRO_CREDITOS  , new Disciplina[]{EDA.getDisciplina(),LEDA.getDisciplina(),CALCULO2.getDisciplina(),LOGICA.getDisciplina()})),
		COMPILADORES(new Disciplina("Compiladores", QUATRO_CREDITOS  , new Disciplina[]{PLP.getDisciplina(),OAC.getDisciplina(),LOAC.getDisciplina()})),
		REDES(new Disciplina("Redes de Computadores", QUATRO_CREDITOS , new Disciplina[]{OAC.getDisciplina(),LOAC.getDisciplina()})),
		BD1(new Disciplina("Bancos de Dados I", QUATRO_CREDITOS,new Disciplina[]{SI1.getDisciplina()})),
		SI2(new Disciplina("Sistemas de Informação II", QUATRO_CREDITOS , new Disciplina[]{SI1.getDisciplina()})),
		LES(new Disciplina("Laboratório de Engenharia de Software", DOIS_CREDITOS, new Disciplina[]{ES.getDisciplina()})),
		
		DIREITO(new Disciplina("Direito e Cidadania", QUATRO_CREDITOS )),
		
		BASQUETE(new Disciplina("Basquetebol - Fem", DOIS_CREDITOS )),
		CALC3(new Disciplina("Cálculo Diferencial e Integral 3", QUATRO_CREDITOS)),
		CRIATIVIDADE(new Disciplina("Criatividade", QUATRO_CREDITOS )),
		ETICA(new Disciplina("Etica", QUATRO_CREDITOS )),
		INGLES(new Disciplina("Inglês", QUATRO_CREDITOS )),
		
		DATAMINIG(new Disciplina("Tecc (DataMining)", QUATRO_CREDITOS)),
		COMPPARALELA(new Disciplina("Tecc (Computação Paralela)", DOIS_CREDITOS)),
		GRADESCOMP(new Disciplina("Tecc (Grades Computacionais)", DOIS_CREDITOS )),
		PROG3(new Disciplina("Tecc (Programação 3)", QUATRO_CREDITOS)),
		COMPQUANTICA(new Disciplina("Tecc (Computação Quântica)", QUATRO_CREDITOS )),
		METODOSFORMAIS(new Disciplina("Tecc (Métodos Formais)", QUATRO_CREDITOS  ));
		
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
		listaDeDisciplinas.addAll(getDisciplinasDoPrimeiroPeriodo());

		List<Disciplina> disciplinasDoSegundoPeriodo = Arrays.asList(DisciplinasDSC.CALCULO2.getDisciplina(), 
				DisciplinasDSC.CLASSICA.getDisciplina(), 
				DisciplinasDSC.DISCRETA.getDisciplina(), 
				DisciplinasDSC.GRAFOS.getDisciplina(), 
				DisciplinasDSC.P2.getDisciplina(), 
				DisciplinasDSC.LP2.getDisciplina(),
				DisciplinasDSC.METODOLOGIA.getDisciplina());
		
		List<Disciplina> disciplinasDoTerceiroPeriodo = Arrays.asList(DisciplinasDSC.ALGEBRALINEAR.getDisciplina(),
				DisciplinasDSC.PROBABILIDADE.getDisciplina(),
				DisciplinasDSC.EDA.getDisciplina(),
				DisciplinasDSC.LEDA.getDisciplina(),
				DisciplinasDSC.TC.getDisciplina(),
				DisciplinasDSC.GI.getDisciplina(),
				DisciplinasDSC.MODERNA.getDisciplina());
		
		List<Disciplina> disciplinasDoQuartoPeriodo = Arrays.asList(DisciplinasDSC.LOGICA.getDisciplina(),
				DisciplinasDSC.SI1.getDisciplina(),
				DisciplinasDSC.PLP.getDisciplina(),
				DisciplinasDSC.OAC.getDisciplina(),
				DisciplinasDSC.LOAC.getDisciplina(),
				DisciplinasDSC.METODOS.getDisciplina(),
				DisciplinasDSC.ES.getDisciplina());
		
		List<Disciplina> disciplinasDoQuintoPeriodo = Arrays.asList(DisciplinasDSC.COMPILADORES.getDisciplina(),
				DisciplinasDSC.LES.getDisciplina(),
				DisciplinasDSC.ATAL.getDisciplina(),
				DisciplinasDSC.SI2.getDisciplina(),
				DisciplinasDSC.REDES.getDisciplina(),
				DisciplinasDSC.BD1.getDisciplina(),
				DisciplinasDSC.INFOSOC.getDisciplina());
		
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
				
		listaDeDisciplinas.addAll(disciplinasDoSegundoPeriodo);
		listaDeDisciplinas.addAll(disciplinasDoTerceiroPeriodo);
		listaDeDisciplinas.addAll(disciplinasDoQuartoPeriodo);
		listaDeDisciplinas.addAll(disciplinasDoQuintoPeriodo);
		listaDeDisciplinas.addAll(disciplinasOptativas);
		listaDeDisciplinas.addAll(disciplinasTECC);
		listaDeDisciplinas.add(DisciplinasDSC.DIREITO.getDisciplina());
		
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
	
	/**
	 * 
	 * @return as disciplinas do curso
	 */
	public List<Disciplina> getDisciplinasDoCurso() {
		return listaDeDisciplinas;
	}

}
