package models;


import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.plaf.MenuItemUI;

import models.Grade.DisciplinasDSC;

import org.junit.Before;
import org.junit.Test;

import exception.AlunoNaoPossuiPreRequisitosException;

/**
 * Testes para a classe Aluno
 *
 */
public class AlunoTest {
	
	private static final int DOIS_CREDITOS = 2;
	private static final int QUATRO_CREDITOS = 4;
	private static final int PRIMEIRO_PERIODO = 0;
	private static final int SEGUNDO_PERIODO = 1;
	private static final int TERCEIRO_PERIODO = 2;
	private static final int QUARTO_PERIODO = 3;
	private static final int MUITO_FACIL = 1;
	private static final int FACIL = 2;
	private static final int MEDIO = 3;
	private static final int DIFICIL = 4;
	private static final int MUITO_DIFICIL = 5;
	
	private static final int NUMERO_DE_CREDITOS_1_PERIODO = QUATRO_CREDITOS * 6;
	
	private Aluno aluno;
	private Planejador sistema;
	
	@Before
	public void setup() {
		aluno = new Aluno("Severino", "severino@gmail.com", "oxe");
		sistema = new Planejador();
	}
	
	@Test
	public void testAlunoDoPrimeiroPeriodo() throws Exception {
		Periodo primeiroPeriodo = new Periodo(sistema.getGrade().getDisciplinasDoPrimeiroPeriodo(), PRIMEIRO_PERIODO);
		aluno.getListaDePeriodos().add(primeiroPeriodo);
		assertTrue(aluno.getListaDePeriodos().get(PRIMEIRO_PERIODO).getDisciplinas().contains(new Disciplina("Cálculo Diferencial e Integral I", QUATRO_CREDITOS, MEDIO)));
		assertTrue(aluno.getListaDePeriodos().get(PRIMEIRO_PERIODO).getDisciplinas().contains(new Disciplina("Álgebra Vetorial e Geometria Analítica", QUATRO_CREDITOS, FACIL)));
		assertTrue(aluno.getListaDePeriodos().get(PRIMEIRO_PERIODO).getDisciplinas().contains(new Disciplina("Leitura e Produção de Textos", QUATRO_CREDITOS, MUITO_FACIL)));
		
		assertTrue(aluno.getListaDePeriodos().get(PRIMEIRO_PERIODO).getDisciplinas().contains(new Disciplina("Programação I", QUATRO_CREDITOS, MEDIO)));
		assertTrue(aluno.getListaDePeriodos().get(PRIMEIRO_PERIODO).getDisciplinas().contains(new Disciplina("Introdução à Computação", QUATRO_CREDITOS, FACIL)));
		assertTrue(aluno.getListaDePeriodos().get(PRIMEIRO_PERIODO).getDisciplinas().contains(new Disciplina("Laboratório de Programação I", QUATRO_CREDITOS, MEDIO)));
		
		assertEquals(aluno.getListaDePeriodos().get(PRIMEIRO_PERIODO).getNumeroDeCreditosDoPeriodo(), NUMERO_DE_CREDITOS_1_PERIODO);
		
		// o sistema nos fornece as informacoes, ele tem os dados
		
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Cálculo Diferencial e Integral I"), QUATRO_CREDITOS);
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Álgebra Vetorial e Geometria Analítica"), QUATRO_CREDITOS);
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Leitura e Produção de Textos"), QUATRO_CREDITOS);
		
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Programação I"), QUATRO_CREDITOS);
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Introdução à Computação"), QUATRO_CREDITOS);
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Laboratório de Programação I"), QUATRO_CREDITOS);
	}
	
	@Test
	public void userStoriesTest() throws AlunoNaoPossuiPreRequisitosException {
		Periodo primeiroPeriodo = new Periodo(sistema.getGrade().getDisciplinasDoPrimeiroPeriodo(), PRIMEIRO_PERIODO);
		aluno.getListaDePeriodos().add(primeiroPeriodo);
		//Verifica se o pré-requisito da cadeira já foi pago
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), 2));
		aluno.getListaDePeriodos().add(new Periodo(new ArrayList<Disciplina>(), TERCEIRO_PERIODO));

		//Adiciona a cadeira
		assertTrue(sistema.verificaPreRequisitosPago(aluno, DisciplinasDSC.CALCULO2.getDisciplina()));
		sistema.addCadeiraAoAluno(aluno, DisciplinasDSC.CALCULO2.getDisciplina(), SEGUNDO_PERIODO);
		//Verifica se o mínimo e máximo de créditos foram atingidos
		assertFalse(sistema.alunoTemMinimoDeCreditos(aluno, SEGUNDO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno, SEGUNDO_PERIODO));
	
		sistema.verificaPreRequisitosPago(aluno, new Disciplina("Matemática Discreta", QUATRO_CREDITOS, MEDIO));
		sistema.addCadeiraAoAluno(aluno, DisciplinasDSC.DISCRETA.getDisciplina(), SEGUNDO_PERIODO);
		assertFalse(sistema.alunoTemMinimoDeCreditos(aluno, SEGUNDO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno, SEGUNDO_PERIODO));
		
		sistema.verificaPreRequisitosPago(aluno, new Disciplina("Metodologia Científica", 2, FACIL));
		sistema.addCadeiraAoAluno(aluno, new Disciplina("Metodologia Científica", QUATRO_CREDITOS, FACIL), SEGUNDO_PERIODO);
		assertFalse(sistema.alunoTemMinimoDeCreditos(aluno, SEGUNDO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno, SEGUNDO_PERIODO));
		
		sistema.verificaPreRequisitosPago(aluno, new Disciplina("Programação II", QUATRO_CREDITOS, MEDIO));
		sistema.addCadeiraAoAluno(aluno, new Disciplina("Programação II", QUATRO_CREDITOS,MEDIO), SEGUNDO_PERIODO);
		assertTrue(sistema.alunoTemMinimoDeCreditos(aluno, SEGUNDO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno, SEGUNDO_PERIODO));
		
		sistema.verificaPreRequisitosPago(aluno, new Disciplina("Teoria dos Grafos", DOIS_CREDITOS, MEDIO));
		sistema.addCadeiraAoAluno(aluno, new Disciplina("Teoria dos Grafos", DOIS_CREDITOS, MEDIO), SEGUNDO_PERIODO);
		assertTrue(sistema.alunoTemMinimoDeCreditos(aluno, SEGUNDO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno, SEGUNDO_PERIODO));
		
		sistema.verificaPreRequisitosPago(aluno, new Disciplina("Fundamentos de Física Clássica", QUATRO_CREDITOS, DIFICIL));
		sistema.addCadeiraAoAluno(aluno, new Disciplina("Fundamentos de Física Clássica", QUATRO_CREDITOS, DIFICIL), SEGUNDO_PERIODO);
		assertTrue(sistema.alunoTemMinimoDeCreditos(aluno, SEGUNDO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno, SEGUNDO_PERIODO));
		
		sistema.verificaPreRequisitosPago(aluno, new Disciplina("Laboratório de Programação II", QUATRO_CREDITOS, MEDIO));
		sistema.addCadeiraAoAluno(aluno, new Disciplina("Laboratório de Programação II", QUATRO_CREDITOS, MEDIO), SEGUNDO_PERIODO);
		assertTrue(sistema.alunoTemMinimoDeCreditos(aluno, SEGUNDO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno, SEGUNDO_PERIODO));
		
		//Verifica se as disciplinas atuais contém as que foram matriculadas
		assertTrue(aluno.getListaDePeriodos().get(SEGUNDO_PERIODO).getDisciplinas().contains(new Disciplina("Cálculo Diferencial e Integral II", QUATRO_CREDITOS, DIFICIL)));
		assertTrue(aluno.getListaDePeriodos().get(SEGUNDO_PERIODO).getDisciplinas().contains(new Disciplina("Matemática Discreta", QUATRO_CREDITOS, MEDIO)));
		assertTrue(aluno.getListaDePeriodos().get(SEGUNDO_PERIODO).getDisciplinas().contains(new Disciplina("Metodologia Científica", QUATRO_CREDITOS, FACIL)));
		
		assertTrue(aluno.getListaDePeriodos().get(SEGUNDO_PERIODO).getDisciplinas().contains(new Disciplina("Programação II", QUATRO_CREDITOS, MEDIO)));
		assertTrue(aluno.getListaDePeriodos().get(SEGUNDO_PERIODO).getDisciplinas().contains(new Disciplina("Teoria dos Grafos", 2, MEDIO)));
		assertTrue(aluno.getListaDePeriodos().get(SEGUNDO_PERIODO).getDisciplinas().contains(new Disciplina("Fundamentos de Física Clássica", QUATRO_CREDITOS, DIFICIL)));
		assertTrue(aluno.getListaDePeriodos().get(SEGUNDO_PERIODO).getDisciplinas().contains(new Disciplina("Laboratório de Programação II", QUATRO_CREDITOS, MEDIO)));
		
		assertEquals(aluno.getListaDePeriodos().get(SEGUNDO_PERIODO).getNumeroDeCreditosDoPeriodo(), 26);
		//verifica o número de créditos correspondente a disciplina
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Cálculo Diferencial e Integral II"), QUATRO_CREDITOS);
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Matemática Discreta"), QUATRO_CREDITOS);
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Metodologia Científica"), QUATRO_CREDITOS);
		
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Programação II"), QUATRO_CREDITOS);
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Teoria dos Grafos"), DOIS_CREDITOS);
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Fundamentos de Física Clássica"), QUATRO_CREDITOS);
		assertEquals(sistema.getNumeroDeCreditosDaDisciplina("Laboratório de Programação II"), QUATRO_CREDITOS);
		
		//Tenta adicionar a discilpina e seus pre-requisitos no mesmo periodo
		try {
			sistema.addCadeiraAoAluno(aluno, DisciplinasDSC.EDA.getDisciplina(), SEGUNDO_PERIODO);
		}catch(Exception e) {
			assertTrue(e.getMessage().equals("O aluno nao possui os pre-requisitos para esta disciplina"));
		}
		
		//TESTE DO TERCEIRO PERÍODO
		assertTrue(sistema.verificaPreRequisitosPago(aluno,DisciplinasDSC.PROBABILIDADE.getDisciplina()));
		sistema.addCadeiraAoAluno(aluno, DisciplinasDSC.PROBABILIDADE.getDisciplina(), TERCEIRO_PERIODO);
		assertFalse(sistema.alunoTemMinimoDeCreditos(aluno,TERCEIRO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno,TERCEIRO_PERIODO));
		
		assertTrue(sistema.verificaPreRequisitosPago(aluno,DisciplinasDSC.EDA.getDisciplina()));
		sistema.addCadeiraAoAluno(aluno, DisciplinasDSC.EDA.getDisciplina(),TERCEIRO_PERIODO);
		assertFalse(sistema.alunoTemMinimoDeCreditos(aluno,TERCEIRO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno,TERCEIRO_PERIODO));
		
		assertTrue(sistema.verificaPreRequisitosPago(aluno,DisciplinasDSC.LEDA.getDisciplina()));
		sistema.addCadeiraAoAluno(aluno, DisciplinasDSC.LEDA.getDisciplina(),TERCEIRO_PERIODO);
		assertFalse(sistema.alunoTemMinimoDeCreditos(aluno,TERCEIRO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno,TERCEIRO_PERIODO));
		
		assertTrue(sistema.verificaPreRequisitosPago(aluno,DisciplinasDSC.TC.getDisciplina()));
		sistema.addCadeiraAoAluno(aluno, DisciplinasDSC.TC.getDisciplina(),TERCEIRO_PERIODO);
		assertTrue(sistema.alunoTemMinimoDeCreditos(aluno,TERCEIRO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno,TERCEIRO_PERIODO));
		
		assertFalse(sistema.verificaPreRequisitosPago(aluno, DisciplinasDSC.BD1.getDisciplina()));
		try{
			sistema.addCadeiraAoAluno(aluno, DisciplinasDSC.BD1.getDisciplina(),TERCEIRO_PERIODO);
		}catch(Exception e) {
			e.getMessage().equals("O aluno nao possui os pre-requisitos para esta disciplina");
		}
		assertFalse(aluno.getListaDePeriodos().get(PRIMEIRO_PERIODO).getDisciplinas().contains(DisciplinasDSC.BD1.getDisciplina()));
	
		// TESTE REFERENTE A US4
		sistema.removeDisciplinaESeusPreRequisitos(aluno, DisciplinasDSC.LEDA.getDisciplina());
		
		assertTrue(sistema.verificaPreRequisitosPago(aluno,DisciplinasDSC.DIREITO.getDisciplina()));
		sistema.addCadeiraAoAluno(aluno, DisciplinasDSC.DIREITO.getDisciplina(),TERCEIRO_PERIODO);
		assertTrue(sistema.alunoTemMinimoDeCreditos(aluno,TERCEIRO_PERIODO));
		assertFalse(sistema.alunoTemMaximoDeCreditos(aluno,TERCEIRO_PERIODO));
		
		assertFalse(sistema.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.OAC.getDisciplina()));
		assertFalse(sistema.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.LOAC.getDisciplina()));
		try{
			sistema.addCadeiraAoAluno(aluno, DisciplinasDSC.OAC.getDisciplina(), QUARTO_PERIODO);
			fail();
		} catch(Exception e) {
			//assertTrue(e.getMessage().equals("O aluno nao possui os pre-requisitos para esta disciplina"));
		}
		
		sistema.removeDisciplinaESeusPreRequisitos(aluno, DisciplinasDSC.GI.getDisciplina());
		assertFalse(sistema.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.SI1.getDisciplina()));
		//assertFalse(sistema.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.BD1.getDisciplina()));
		assertFalse(sistema.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.SI2.getDisciplina()));
		
		sistema.removeDisciplinaESeusPreRequisitos(aluno, DisciplinasDSC.CALCULO1.getDisciplina());
		assertTrue(sistema.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.CALCULO1.getDisciplina()));
	}
	
	
	@Test 
	public void verificaDisciplinasExistentesNaGradeTest() {
		//disciplinas do primeiro período
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Cálculo Diferencial e Integral I", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Álgebra Vetorial e Geometria Analítica", QUATRO_CREDITOS, FACIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Leitura e Produção de Textos", QUATRO_CREDITOS, MUITO_FACIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Programação I", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Introdução à Computação", QUATRO_CREDITOS, FACIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Laboratório de Programação I", QUATRO_CREDITOS, MEDIO)));
		
		//disciplinas do segundo período
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Cálculo Diferencial e Integral II", QUATRO_CREDITOS, DIFICIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Matemática Discreta", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Metodologia Científica", QUATRO_CREDITOS, FACIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Programação II", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Teoria dos Grafos", DOIS_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Fundamentos de Física Clássica", QUATRO_CREDITOS, DIFICIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Laboratório de Programação II", QUATRO_CREDITOS, MEDIO)));
		
		//discplinas do terceiro período
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Álgebra Linear", QUATRO_CREDITOS, DIFICIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Probabilidade e Estatística", QUATRO_CREDITOS, DIFICIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Estruturas de Dados e Algoritmos", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Fundamentos de Física Moderna", QUATRO_CREDITOS, DIFICIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Gerência da Informação", QUATRO_CREDITOS, FACIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Laboratório de Estruturas de Dados e Algoritmos", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Teoria da Computação", QUATRO_CREDITOS, MEDIO)));
		
		//discplinas do quarto período
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Métodos Estatísticos",QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Paradigmas de Linguagens de Programação", DOIS_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Lógica Matemática", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Organização e Arquitetura de Computadores I", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Engenharia de Software I", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Sistemas de Informação I", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Laboratório de Organização e Arquitetura de Computadores", QUATRO_CREDITOS, DIFICIL)));
		
		//disciplinas do quinto
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Informática e Sociedade",DOIS_CREDITOS, MUITO_FACIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Análise e Técnicas de Algoritmos", QUATRO_CREDITOS, MUITO_DIFICIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Compiladores", QUATRO_CREDITOS, MUITO_DIFICIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Redes de Computadores", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Bancos de Dados I", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Sistemas de Informação II", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Laboratório de Engenharia de Software", DOIS_CREDITOS, DIFICIL)));
		
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Direito e Cidadania", QUATRO_CREDITOS, MUITO_FACIL)));
		
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Basquetebol - Fem", DOIS_CREDITOS, MUITO_FACIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Cálculo Diferencial e Integral 3", QUATRO_CREDITOS, MUITO_DIFICIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Criatividade", QUATRO_CREDITOS, FACIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Etica", QUATRO_CREDITOS, MUITO_FACIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Inglês", QUATRO_CREDITOS, FACIL)));
		
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Tecc (DataMining)", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Tecc (Computação Paralela)", DOIS_CREDITOS, DIFICIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Tecc (Programação 3)", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Tecc (Computação Quântica)", QUATRO_CREDITOS, MUITO_DIFICIL)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Tecc (Métodos Formais)", QUATRO_CREDITOS, MEDIO)));
		assertTrue(sistema.getDisciplinasDaGrade().contains(new Disciplina("Tecc (Grades Computacionais)", DOIS_CREDITOS, MEDIO)));
	}
	
}
