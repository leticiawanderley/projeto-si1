package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Grade Comum do curso de Ciencia da Computacao da Universidade Federal de Campina Grande
 *
 */
public class GradeComum extends Grade {
	
	public GradeComum() {
		super();
	}
	
	@Override
	public List<Disciplina> getDisciplinasDoSegundoPeriodo() {
		List<Disciplina> disciplinasDoSegundoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.CALCULO2.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.CLASSICA.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.DISCRETA.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.GRAFOS.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.P2.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.LP2.getDisciplina());
		disciplinasDoSegundoPeriodo.add(DisciplinasDSC.INFOSOC.getDisciplina());
		return disciplinasDoSegundoPeriodo;
	}
	
	@Override
	public List<Disciplina> getDisciplinasDoQuartoPeriodo() {
		List<Disciplina> disciplinasDoQuartoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.LOGICA.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.SI1.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.PLP.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.OAC.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.LOAC.getDisciplina());
		disciplinasDoQuartoPeriodo.add(DisciplinasDSC.DIREITO.getDisciplina());
		return disciplinasDoQuartoPeriodo;
	}
	
	@Override
	public List<Disciplina> getDisciplinasDoQuintoPeriodo() {
		List<Disciplina> disciplinasDoQuintoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.METODOS.getDisciplina());
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.ES.getDisciplina());
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.ATAL.getDisciplina());
		disciplinasDoQuintoPeriodo.add(DisciplinasDSC.BD1.getDisciplina());
		return disciplinasDoQuintoPeriodo;
	}
	
	@Override
	public List<Disciplina> getDisciplinasDoSextoPeriodo() {
		List<Disciplina> disciplinasDoSextoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.METODOLOGIA.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.LES.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.REDES.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.SI2.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.OP1.getDisciplina());
		disciplinasDoSextoPeriodo.add(DisciplinasDSC.OP2.getDisciplina());
		return disciplinasDoSextoPeriodo;
	}

	@Override
	public List<Disciplina> getDisciplinasDoSetimoPeriodo() {
		List<Disciplina> disciplinasDoSetimoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.LIRC.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.IRC.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.SO.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.BD2.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.OP3.getDisciplina());
		disciplinasDoSetimoPeriodo.add(DisciplinasDSC.OP4.getDisciplina());
		return disciplinasDoSetimoPeriodo;
	}
	
	@Override
	public List<Disciplina> getDisciplinasDoOitavoPeriodo() {
		List<Disciplina> disciplinasDoOitavoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.PROJETO1.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.COMPILADORES.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.IA.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.MSN.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.ADSD.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.OP5.getDisciplina());
		disciplinasDoOitavoPeriodo.add(DisciplinasDSC.OP6.getDisciplina());
		return disciplinasDoOitavoPeriodo;
	}
	
	public List<Disciplina> getDisciplinasDoNonoPeriodo() {
		List<Disciplina> disciplinasDoNonoPeriodo = new ArrayList<Disciplina>();
		disciplinasDoNonoPeriodo.add(DisciplinasDSC.PROJETO2.getDisciplina());
		disciplinasDoNonoPeriodo.add(DisciplinasDSC.OP7.getDisciplina());
		disciplinasDoNonoPeriodo.add(DisciplinasDSC.OP8.getDisciplina());
		disciplinasDoNonoPeriodo.add(DisciplinasDSC.OP9.getDisciplina());
		disciplinasDoNonoPeriodo.add(DisciplinasDSC.OP10.getDisciplina());
		disciplinasDoNonoPeriodo.add(DisciplinasDSC.OP11.getDisciplina());
		return disciplinasDoNonoPeriodo;
	}

}
