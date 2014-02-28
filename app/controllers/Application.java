package controllers;

import models.AlunoNaoPossuiPreRequisitos;
import models.Disciplina;
import play.data.Form;
import play.mvc.*;

/**
 * Classe que controla as requisições do sistema web
 *
 */
public class Application extends Controller {
	
	private static final String CADEIRA_NAO_EXISTENTE = "O nome da disciplina está incorreta ou não existe";
	private static final String CAMPO_VAZIO = "Campo Vazio";
	private static final String VALOR_INVALIDO = "O valor do período precisa ser um número e estar no intervalo [2, 10]";
	private static final String PRE_REQUISITOS = "Você precisa ter adicionado os pré-requisitos da disciplina";
	private static final String JA_MATRICULADO = "Você já está matriculado na disciplina";
	private static final String NUMERO_GRANDE_DE_CREDITOS = "Você tem mais de 28 créditos neste período";
			
	private static GridController grid = new GridController();
	private static Form<Disciplina> disciplinaForm = Form.form(Disciplina.class);
	
	/**
	 * Pagina inicial da alocacao das disciplinas
	 * @return um resultado/pagina que serah exibida no navegador
	 */
    public static Result index() {
        return ok(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador(), grid.getPlanejador().getMensagemDeErro()));
    }
    
    /**
     * Pagina para visualizar a grade do DSC
     * @return um resultado/pagina que serah exibida no navegador
     */
    public static Result verGrade() {
    	return ok(views.html.visualizaGrade.render());
    }
    
    /**
     * Pagina para visualizar as disciplinas disponiveis para matricula
     * @return um resultado/pagina que serah exibida no navegador
     */
    public static Result selecionarDisciplinas() {
    	return ok(views.html.disciplinasDoCurso.render(grid.getAluno(), grid.getPlanejador()));
    }
    
    /**
     * Matricula o usuario na disciplina
     * @return um resultado/pagina que serah exibida no navegador
     */
    public static Result matriculaNaDisciplina() {
    	Form<Disciplina> filledForm = disciplinaForm.bindFromRequest();
    	if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador(), grid.getPlanejador().getMensagemDeErro()));
		} else {
			return tratamentoDeErrosDaInterface(filledForm);
		}
    }

    /**
     * Metodo para tratamento de erros da UI
     * @param filledForm
     * @return um resultado/pagina que serah exibida no navegador
     */
	private static Result tratamentoDeErrosDaInterface(Form<Disciplina> filledForm) {
		try {
			// CAMPOS VAZIOS
			if (filledForm.data().get("nomeDaDisciplina").equals("") || filledForm.data().get("periodoAlocado").equals("")) {
				grid.getPlanejador().setMensagemDeErro(CAMPO_VAZIO);
				return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador(), grid.getPlanejador().getMensagemDeErro()));
			}
			
			String nomeDaDisciplina = filledForm.data().get("nomeDaDisciplina");
			// VALOR DO PERIODO INVALIDO
			if (!isNumber(filledForm.data().get("periodoAlocado"))) {
				grid.getPlanejador().setMensagemDeErro(VALOR_INVALIDO);
				return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador(), grid.getPlanejador().getMensagemDeErro()));
			}
			// PERIODO EM UM INTERVALO INVALIDO
			int periodo = Integer.parseInt(filledForm.data().get("periodoAlocado"));
			if (periodo > 10 || periodo <= 1) {
				grid.getPlanejador().setMensagemDeErro(VALOR_INVALIDO);
				return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador(), grid.getPlanejador().getMensagemDeErro()));
			}
			
			// CADEIRA INEXISTENTE
			if (!grid.getPlanejador().existeCadeira(nomeDaDisciplina)) {
				grid.getPlanejador().setMensagemDeErro(CADEIRA_NAO_EXISTENTE);
				return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador(), grid.getPlanejador().getMensagemDeErro()));
			}
			
			Disciplina disciplina = grid.getPlanejador().getDisciplina(nomeDaDisciplina);
			// EXCEDEU O NUMERO DE CREDITOS
			if (grid.getPlanejador().alunoTemMaximoDeCreditos(grid.getAluno(), periodo -1)) {
				grid.getPlanejador().setMensagemDeErro(NUMERO_GRANDE_DE_CREDITOS);
				return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador(), grid.getPlanejador().getMensagemDeErro()));
			}
			// ALUNO JAH ESTAH MATRICULADO NA DISCIPLINA
			if (grid.getPlanejador().jaEstaMatriculado(grid.getAluno(), disciplina) 
					|| grid.getAluno().getListaDePeriodos().get(periodo-1).getNumeroDeCreditosDoPeriodo() + disciplina.getCreditos() > 28) {
				grid.getPlanejador().setMensagemDeErro(JA_MATRICULADO);
				return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador(), grid.getPlanejador().getMensagemDeErro()));
			}
			
			grid.getPlanejador().addCadeiraAoAluno(grid.getAluno(), disciplina, periodo - 1);
			grid.getAluno().save();
			return index();
		} catch (AlunoNaoPossuiPreRequisitos e) {
			// O ALUNO NAO ALOCOU OS PRE-REQUISITOS
			grid.getPlanejador().setMensagemDeErro(PRE_REQUISITOS);
			return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador(), grid.getPlanejador().getMensagemDeErro()));
		}
	}

    /**
     * Remove uma disciplina do usuario
     * @return um resultado/pagina que serah exibida no navegador
     */
	public static Result removeDisciplina() {
    	Form<Disciplina> filledForm = disciplinaForm.bindFromRequest();
    	String nomeDaDisciplina = filledForm.data().get("nomeDaDisciplina");
		Disciplina tmp = grid.getPlanejador().getDisciplina(nomeDaDisciplina);
		if (!grid.getPlanejador().existeCadeira(nomeDaDisciplina)) {
    		grid.getPlanejador().setMensagemDeErro(CADEIRA_NAO_EXISTENTE);
    		return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador(), grid.getPlanejador().getMensagemDeErro()));
    	}
		grid.getPlanejador().removeDisciplinaESeusPreRequisitos(grid.getAluno(), tmp);
		return index();
    }
    
    /**
     * Verifica se o valor inserido pelo usuário é um número
     * @param input valor inserido
     * @return se o valor eh numerico
     */
    public static boolean isNumber(String input) {
        return input.matches("^-?[0-9]+(\\.[0-9]+)?$");
    }
}
