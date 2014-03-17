package models;

import static org.junit.Assert.*;

import models.Grade.DisciplinasDSC;

import org.junit.Test;

public class SistemaTest {

	@Test 
	public void UsuarioTeste() {
		Planejador planejador = new Planejador();
		Aluno aluno = new Aluno("Maysa M.", "maysa.macedo95@gmail.com", "fafamaysa25");
		
		planejador.removeDisciplina(aluno, DisciplinasDSC.ALGEBRALINEAR.getDisciplina());
		
		/** Remocao recursiva das disciplinas que eram dependentes de Algebra Linear */
		assertFalse(planejador.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.METODOS.getDisciplina()));
		assertFalse(planejador.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.IA.getDisciplina()));
		assertFalse(planejador.getTodasDisciplinasDoAluno(aluno).contains(DisciplinasDSC.MSN.getDisciplina()));
		
	}

}
