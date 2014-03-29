package models;

import java.util.ArrayList;
import java.util.List;

import models.Grade.DisciplinasDSC;

public class GradeOficial extends Grade {
	
	public GradeOficial() {
		super();
	}

	public List<Disciplina> getDisciplinasDoSegundoPeriodo() {
		List<Disciplina> disciplinasDoSegundoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.CALCULO2.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.CLASSICA.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.DISCRETA.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.GRAFOS.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.P2.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.LP2.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.METODOLOGIA.getDisciplina());
		return disciplinasDoSegundoPeriodo;
	}
	
	public List<Disciplina> getDisciplinasDoQuartoPeriodo() {
		List<Disciplina> disciplinasDoQuartoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.LOGICA.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.SI1.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.PLP.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.OAC.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.LOAC.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.METODOS.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.ES.getDisciplina());
		return disciplinasDoQuartoPeriodo;
	}
	
	public List<Disciplina> getDisciplinasDoQuintoPeriodo() {
		List<Disciplina> disciplinasDoQuintoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.COMPILADORES.getDisciplina());
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.LES.getDisciplina());
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.ATAL.getDisciplina());
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.SI2.getDisciplina());
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.REDES.getDisciplina());
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.BD1.getDisciplina());
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.INFOSOC.getDisciplina());
		return disciplinasDoQuintoPeriodo;
	}
	
	public List<Disciplina> getDisciplinasDoSextoPeriodo() {
		List<Disciplina> disciplinasDoSextoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.SO.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.IRC.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.LIRC.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.IA.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.BD2.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.DIREITO.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.OP1.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.OP2.getDisciplina());
		return disciplinasDoSextoPeriodo;
	}

	public List<Disciplina> getDisciplinasDoSetimoPeriodo() {
		List<Disciplina> disciplinasDoSetimoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.MSN.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.ADSD.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.PROJETO1.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.OP3.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.OP4.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.OP5.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.OP6.getDisciplina());
		return disciplinasDoSetimoPeriodo;
	}
	
	public List<Disciplina> getDisciplinasDoOitavoPeriodo() {
		List<Disciplina> disciplinasDoOitavoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.PROJETO2.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.OP7.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.OP8.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.OP9.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.OP10.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.OP11.getDisciplina());
		return disciplinasDoOitavoPeriodo;
	}
	

}
