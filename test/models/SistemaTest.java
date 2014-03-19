package models;

import static org.junit.Assert.*;

import models.Grade.DisciplinasDSC;

import org.junit.Test;

import controllers.GridSystem;

public class SistemaTest {
	
	private static final int MENOR_DO_QUE_MINIMO_DE_CREDITOS = 8;
	private static final int MINIMO_DE_CREDITOS = 12;
	private static final int NUMERO_CREDITOS_NORMAL = 24;
	private static final int MAXIMO_DE_CREDITOS = 28;
	private static final int MAIOR_DO_QUE_MAXIMO_DE_CREDITOS = 40;
	
	private static final int PERIODO_ATUAL = 5;
	private static final int ULTIMO_PERIODO = 9;
	private static final int PERIODO_ALTERADO = 6;
	private static final int OUTRO_PERIODO_ALTERADO = 3;

	@Test 
	public void UsuarioTeste() {
		Planejador planejador = new Planejador();
		GridSystem grid = new GridSystem();
		
		Aluno aluno = new Aluno("Maysa M.", "maysa.macedo95@gmail.com", "fafamaysa");
		grid.alocandoNovoUsuario(aluno);
		
		planejador.removeDisciplinaESeusPreRequisitos(aluno, DisciplinasDSC.ALGEBRALINEAR.getDisciplina());
		
		/** Remocao recursiva das disciplinas que eram dependentes de Algebra Linear */
		assertFalse(planejador.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.ALGEBRALINEAR.getDisciplina()));
		assertFalse(planejador.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.METODOS.getDisciplina()));
		assertFalse(planejador.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.IA.getDisciplina()));
		assertFalse(planejador.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.MSN.getDisciplina()));
		
		// TODO Ainda arrumarei os testes para passarem =P
		aluno.setPeriodoAtual(PERIODO_ATUAL);
		
		for (int i = 0; i < PERIODO_ATUAL; i++) {
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MENOR_DO_QUE_MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(NUMERO_CREDITOS_NORMAL));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAXIMO_DE_CREDITOS));
			assertFalse(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAIOR_DO_QUE_MAXIMO_DE_CREDITOS));
		}
		
		for (int i = PERIODO_ATUAL; i < ULTIMO_PERIODO; i++) {
			assertFalse(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MENOR_DO_QUE_MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(NUMERO_CREDITOS_NORMAL));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAXIMO_DE_CREDITOS));
			assertFalse(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAIOR_DO_QUE_MAXIMO_DE_CREDITOS));
		}
		
		assertFalse(aluno.getListaDePeriodos().get(ULTIMO_PERIODO ).getValidadorDoPeriodo().permiteNumeroDeCreditos(MENOR_DO_QUE_MINIMO_DE_CREDITOS));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(MINIMO_DE_CREDITOS));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(NUMERO_CREDITOS_NORMAL));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAXIMO_DE_CREDITOS));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAIOR_DO_QUE_MAXIMO_DE_CREDITOS));
		
		// TODO
		aluno.setPeriodoAtual(PERIODO_ALTERADO );
		
		for (int i = 0; i < PERIODO_ALTERADO; i++) {
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MENOR_DO_QUE_MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(NUMERO_CREDITOS_NORMAL));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAXIMO_DE_CREDITOS));
			assertFalse(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAIOR_DO_QUE_MAXIMO_DE_CREDITOS));
		}
		
		for (int i = PERIODO_ALTERADO; i < ULTIMO_PERIODO; i++) {
			assertFalse(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MENOR_DO_QUE_MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(NUMERO_CREDITOS_NORMAL));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAXIMO_DE_CREDITOS));
			assertFalse(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAIOR_DO_QUE_MAXIMO_DE_CREDITOS));
		}
		
		assertFalse(aluno.getListaDePeriodos().get(ULTIMO_PERIODO ).getValidadorDoPeriodo().permiteNumeroDeCreditos(MENOR_DO_QUE_MINIMO_DE_CREDITOS));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(MINIMO_DE_CREDITOS));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(NUMERO_CREDITOS_NORMAL));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAXIMO_DE_CREDITOS));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAIOR_DO_QUE_MAXIMO_DE_CREDITOS));
		
		// TODO
		aluno.setPeriodoAtual(OUTRO_PERIODO_ALTERADO);
		
		for (int i = 0; i < OUTRO_PERIODO_ALTERADO; i++) {
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MENOR_DO_QUE_MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(NUMERO_CREDITOS_NORMAL));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAXIMO_DE_CREDITOS));
			assertFalse(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAIOR_DO_QUE_MAXIMO_DE_CREDITOS));
		}
		
		for (int i = OUTRO_PERIODO_ALTERADO; i < ULTIMO_PERIODO; i++) {
			assertFalse(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MENOR_DO_QUE_MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MINIMO_DE_CREDITOS));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(NUMERO_CREDITOS_NORMAL));
			assertTrue(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAXIMO_DE_CREDITOS));
			assertFalse(aluno.getListaDePeriodos().get(i).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAIOR_DO_QUE_MAXIMO_DE_CREDITOS));
		}
		
		assertFalse(aluno.getListaDePeriodos().get(ULTIMO_PERIODO ).getValidadorDoPeriodo().permiteNumeroDeCreditos(MENOR_DO_QUE_MINIMO_DE_CREDITOS));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(MINIMO_DE_CREDITOS));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(NUMERO_CREDITOS_NORMAL));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAXIMO_DE_CREDITOS));
		assertTrue(aluno.getListaDePeriodos().get(ULTIMO_PERIODO).getValidadorDoPeriodo().permiteNumeroDeCreditos(MAIOR_DO_QUE_MAXIMO_DE_CREDITOS));
	}

}
