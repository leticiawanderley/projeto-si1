package models;
     
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        TCC2(new Disciplina("Trabalho de Conclusão de Curso 2", QUATRO_CREDITOS, new Disciplina[]{TCC1.getDisciplina()}, DIFICIL)),
        
        ADMBD(new Disciplina("Administração de Sistemas Gerenciadores de Bancos de Dados (OE)", QUATRO_CREDITOS, new Disciplina[]{BD1.getDisciplina()}, MEDIO)),
        AA1(new Disciplina("Algoritmos Avançados I (OE)", QUATRO_CREDITOS, MEDIO)),
        AA2(new Disciplina("Algoritmos Avançados II (OE) ", QUATRO_CREDITOS, MEDIO)),
        AA3(new Disciplina("Algoritmos Avançados III (OE) ", QUATRO_CREDITOS, MEDIO)),
        AA4(new Disciplina("Algoritmos Avançados IV (OE) ", QUATRO_CREDITOS, MEDIO)),
        AQS(new Disciplina("Arquitetura de Software (OE) ", QUATRO_CREDITOS, new Disciplina[]{PROJ_SOFT.getDisciplina()}, DIFICIL)),
        ADSD(new Disciplina("Avaliação de Desempenho de Sistemas Discretos (OE)", QUATRO_CREDITOS, new Disciplina[]{INTRO_PROB.getDisciplina()}, DIFICIL)),
        BD2(new Disciplina("Banco de Dados II (OE) ", QUATRO_CREDITOS, new Disciplina[]{BD1.getDisciplina()}, MEDIO)),
        CG(new Disciplina("Computação Grágica (OE) ", QUATRO_CREDITOS, DIFICIL)),
        CM(new Disciplina("Computação e Música (OE)", QUATRO_CREDITOS, new Disciplina[]{EDA.getDisciplina()}, FACIL)),
        ETI(new Disciplina("Economia de Tecnologia da Informação (OE)", QUATRO_CREDITOS, FACIL)),
        EPS(new Disciplina("Empreendedorismo em Software (OE) ", QUATRO_CREDITOS, FACIL)),
        GR(new Disciplina("Gerência de Redes (OE) ", QUATRO_CREDITOS, new Disciplina[]{REDES.getDisciplina()}, MEDIO)),
        IRD(new Disciplina("Interconexão de Redes de Computadores (OE) ", QUATRO_CREDITOS, new Disciplina[]{REDES.getDisciplina()}, MEDIO)),
        IHM(new Disciplina("Interface Homem-Máquina (OE)", QUATRO_CREDITOS, FACIL)),
        OTM(new Disciplina("Otimização (OE) ", QUATRO_CREDITOS, MEDIO)),
        MTSN(new Disciplina("Métodos de Software Numéricos (OE) ", QUATRO_CREDITOS, DIFICIL)),
        MF(new Disciplina("Métodos Formais (OE)", QUATRO_CREDITOS, MEDIO)),
        PE(new Disciplina("Prática de Ensino em Computação (OE)", DOIS_CREDITOS, FACIL)),
        PE2(new Disciplina("Prática de Ensino em Computação II (OE) ", DOIS_CREDITOS, new Disciplina[]{PE.getDisciplina()}, FACIL)),
        PDW(new Disciplina("Princípios de Desenvolvimento Web (OE) ", QUATRO_CREDITOS, new Disciplina[]{P2.getDisciplina(), LP2.getDisciplina()}, MEDIO)),
        PBD(new Disciplina("Programação em Banco de Dados (OE) ", QUATRO_CREDITOS, new Disciplina[]{BD1.getDisciplina()}, MEDIO)),
        PRC(new Disciplina("Projeto de Redes Computadoras (OE)", QUATRO_CREDITOS, new Disciplina[]{REDES.getDisciplina()}, DIFICIL)),
        REC(new Disciplina("Reconhecimento de Padrões e Redes Neurais (OE) ", QUATRO_CREDITOS, new Disciplina[]{ESTATISTICA.getDisciplina(), ATAL.getDisciplina()}, MEDIO)),
        RPW(new Disciplina("Recuperação da Informação e Busca Web (OE)", QUATRO_CREDITOS, MEDIO)),
        SR(new Disciplina("Segurança de Redes (OE) ", QUATRO_CREDITOS, new Disciplina[]{REDES.getDisciplina()}, MEDIO)),
        SAD(new Disciplina("Sistema de Apoio à Decisão (OE) ", QUATRO_CREDITOS, MEDIO)),
        SIG(new Disciplina("Sistemas de Informação Geográfica (OE) ", QUATRO_CREDITOS, MEDIO)),
        SD(new Disciplina("Sistemas Distribuidos (OE) ", QUATRO_CREDITOS, DIFICIL)),
        TPCC1(new Disciplina("Tópicos em Ciência da Computação I (OE)", QUATRO_CREDITOS, MEDIO)),
        TPCC2(new Disciplina("Tópicos em Ciência da Computação II (OE)", QUATRO_CREDITOS, MEDIO)),
        TPCC3(new Disciplina("Tópicos em Ciência da Computação III (OE)", QUATRO_CREDITOS, MEDIO)),
        TPCC4(new Disciplina("Tópicos em Ciência da Computação IV (OE)", QUATRO_CREDITOS, MEDIO)),
        VVS(new Disciplina("Verificação e Validação de Software (OE)", QUATRO_CREDITOS, new Disciplina[]{ES.getDisciplina()}, MEDIO)),
        VC(new Disciplina("Visão Computacional (OE)", QUATRO_CREDITOS, MEDIO)),
                    
        ADM(new Disciplina("Administraçãoe Empreendedorismo (OG) ", QUATRO_CREDITOS, FACIL)),
        AVGA(new Disciplina("Álgebra Vetorial e Geometria Analítica (OG) ", QUATRO_CREDITOS, DIFICIL)),
        C3(new Disciplina("Cálculo Diferencial e Integral III (OG) ", QUATRO_CREDITOS, new Disciplina[]{CALCULO2.getDisciplina(), DISCRETA2.getDisciplina()}, DIFICIL)),
        DEC(new Disciplina("Direito e Cidadania (OG) ", QUATRO_CREDITOS, FACIL)),
        ECO(new Disciplina("Economia (OG) ", QUATRO_CREDITOS, FACIL)),
        F1(new Disciplina("Física Geral I (OG)", QUATRO_CREDITOS, MEDIO)),
        F2(new Disciplina("Física Geral II (OG)", QUATRO_CREDITOS, new Disciplina[]{F1.getDisciplina(), CALCULO1.getDisciplina(), DISCRETA1.getDisciplina()}, MEDIO)),
        F3(new Disciplina("Física Geral III (OG)", QUATRO_CREDITOS, new Disciplina[]{F2.getDisciplina(), CALCULO2.getDisciplina()}, MEDIO)),
        F4(new Disciplina("Física Geral IV (OG)", QUATRO_CREDITOS, new Disciplina[]{F3.getDisciplina(), C3.getDisciplina()}, MEDIO)),
        INFOSOC(new Disciplina("Informática e Sociedade (OE)", QUATRO_CREDITOS, FACIL)),
        ING(new Disciplina("Inglês (OG)", QUATRO_CREDITOS, MEDIO)),
        LIBRAS(new Disciplina("Libras (OG)", QUATRO_CREDITOS, MEDIO)),
        LP(new Disciplina("Língua Portuguesa (OG)", QUATRO_CREDITOS, FACIL)),
        TPC1(new Disciplina("Tópicos em Humanidades (OG)", QUATRO_CREDITOS, FACIL)),
        TPC(new Disciplina("Tópico de Humanidades (OG)", QUATRO_CREDITOS, FACIL));
		
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
		List<Disciplina> disciplinasOptativas = Arrays.asList(DisciplinasDSC.ADMBD.getDisciplina(),
				DisciplinasDSC.AA1.getDisciplina(),
				DisciplinasDSC.AA2.getDisciplina(),
				DisciplinasDSC.AA3.getDisciplina(),
				DisciplinasDSC.AA4.getDisciplina(),
				DisciplinasDSC.AQS.getDisciplina(),
				DisciplinasDSC.ADSD.getDisciplina(),
				DisciplinasDSC.BD2.getDisciplina(),
				DisciplinasDSC.CG.getDisciplina(),
				DisciplinasDSC.CM.getDisciplina(),
				DisciplinasDSC.ETI.getDisciplina(),
				DisciplinasDSC.EPS.getDisciplina(),
				DisciplinasDSC.GR.getDisciplina(),
				DisciplinasDSC.IRD.getDisciplina(),
				DisciplinasDSC.IHM.getDisciplina(),
				DisciplinasDSC.OTM.getDisciplina(),
				DisciplinasDSC.MTSN.getDisciplina(),
				DisciplinasDSC.MF.getDisciplina(),
				DisciplinasDSC.PE.getDisciplina(),
				DisciplinasDSC.PE2.getDisciplina(),
                DisciplinasDSC.PDW.getDisciplina(),
                DisciplinasDSC.PBD.getDisciplina(),
                DisciplinasDSC.PRC.getDisciplina(),
                DisciplinasDSC.REC.getDisciplina(),
                DisciplinasDSC.RPW.getDisciplina(),
                DisciplinasDSC.SR.getDisciplina(),
                DisciplinasDSC.SAD.getDisciplina(),
                DisciplinasDSC.SIG.getDisciplina(),
                DisciplinasDSC.SD.getDisciplina(),
                DisciplinasDSC.TPCC1.getDisciplina(),
                DisciplinasDSC.TPCC2.getDisciplina(),
                DisciplinasDSC.TPCC3.getDisciplina(),
                DisciplinasDSC.TPCC4.getDisciplina(),
                DisciplinasDSC.VVS.getDisciplina(),
                DisciplinasDSC.VC.getDisciplina(),
                
                DisciplinasDSC.ADM.getDisciplina(),
                DisciplinasDSC.AVGA.getDisciplina(),
                DisciplinasDSC.C3.getDisciplina(),
                DisciplinasDSC.DEC.getDisciplina(),
                DisciplinasDSC.ECO.getDisciplina(),
                DisciplinasDSC.F1.getDisciplina(),
                DisciplinasDSC.F2.getDisciplina(),
                DisciplinasDSC.F3.getDisciplina(),
                DisciplinasDSC.F4.getDisciplina(),
                DisciplinasDSC.INFOSOC.getDisciplina(),
                DisciplinasDSC.ING.getDisciplina(),
                DisciplinasDSC.LIBRAS.getDisciplina(),
                DisciplinasDSC.LP.getDisciplina(),
                DisciplinasDSC.TPC1.getDisciplina(),
                DisciplinasDSC.TPC.getDisciplina());
		
		return disciplinasOptativas;
	}
           
	@Override
	public List<Disciplina> getDisciplinasTECC() {
		List<Disciplina> disciplinasTECC = new ArrayList<Disciplina>();
		return disciplinasTECC;
	}	
}