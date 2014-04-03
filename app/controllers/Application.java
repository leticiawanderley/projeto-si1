package controllers;

import models.Aluno;
import models.Disciplina;
import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import com.avaje.ebean.Ebean;

/**
 * Classe que controla as requisições do sistema web
 *
 */
public class Application extends Controller {
	
	private static GridSystem grid = new GridSystem();
	private static Form<Disciplina> disciplinaForm = Form.form(Disciplina.class);
	
	/**
	 * 
	 * @return pagina inicial de login do sistema
	 */
	public static Result login() {
	    return ok(views.html.login.render(Form.form(Usuario.class)));
	}
	
	/**
	 * 
	 * @return pagina para cadastrar um novo usuario no sistema
	 */
	public static Result cadastrarNovoUsuario() {
		return ok(views.html.cadastrarUsuario.render(Form.form(Usuario.class)));
	}
	
	/**
	 * 
	 * @return resultado de cadastro do novo usuario. Em caso de inconsistencias dos dados, continuara no cadastro.
	 * Caso contrario, sera criado o usuario e redirecionado para pagina inicial da conta
	 */
	public static Result criarUsuario() {
		Form<Usuario> loginForm = Form.form(Usuario.class).bindFromRequest();
		if (loginForm.get().getPassword().equals("")) {
			flash("success", "A senha não pode ser vazia");
			return cadastrarNovoUsuario();
		}
		if (!loginForm.get().getPassword().equals(loginForm.data().get("confirmPassword"))) {
			flash("success", "Senha e confirmação de senha estão incompativeis");
			return cadastrarNovoUsuario();
		}
    	Aluno novoAluno = new Aluno(loginForm.get().getName(), loginForm.get().getEmail(), loginForm.get().getPassword());
    	if (grid.getFinder().byId(novoAluno.getEmail()) != null) {
    		flash("success", "Usuário já existente no sistema, utilize outro e-mail");
			return cadastrarNovoUsuario();
    	}
    	grid.alocandoNovoUsuario(novoAluno);
    	Ebean.save(novoAluno);
    	session().clear();
    	session("email", novoAluno.getEmail());
    	return index();
	}
	
	/**
	 * 
	 * @return resultado da tentativa de autenticacao do usuario no sistema
	 */
	public static Result authenticate() {
	    Form<Usuario> loginForm = Form.form(Usuario.class).bindFromRequest();
	    if (loginForm.hasErrors()) {
	        return badRequest(views.html.login.render(loginForm));
	    } else {
	    	if (grid.getFinder().byId(loginForm.get().getEmail()) == null) {
	    		flash("success", "Usuário não existente");
	    	} else {
	    		if (!grid.getFinder().byId(loginForm.get().getEmail()).getPassword().equals(loginForm.get().getPassword())) {
	    			flash("success", "Senha incorreta, tente novamente");
	    		} else {
	    			session().clear();
    		        session("email", loginForm.get().getEmail());
    		        return redirect(routes.Application.index());
	    		}
	    	}
	    }
	    return badRequest(views.html.login.render(Form.form(Usuario.class)));
	}
	
	/**
	 * Pagina inicial da alocacao das disciplinas
	 * @return um resultado/pagina que serah exibida no navegador
	 */
	@Security.Authenticated(Secured.class)
    public static Result index() {
		if (session("email") == null ) {
			return redirect(routes.Application.login());
		}
/*    	if (grid.getFinder().byId(session("email")) ==  USUARIO_NAO_LOGADO) {
    		System.out.println();
    		return redirect(routes.Application.login());
    	}
*/        return ok(views.html.index.render(grid.getFinder().byId(session("email")), disciplinaForm, grid.getPlanejador()));
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
    	return ok(views.html.disciplinasDoCurso.render(grid.getFinder().byId(session("email")), disciplinaForm,grid.getPlanejador()));
    }

    /**
     * Matricula o usuario na disciplina
     * @return um resultado/pagina que serah exibida no navegador
     */
    public static Result matriculaNaDisciplina(int periodo, String disciplina) {
    	Form<Disciplina> filledForm = disciplinaForm.bindFromRequest();
    	if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(grid.getFinder().byId(session("email")), disciplinaForm, grid.getPlanejador()));
		} else {
			grid.getPlanejador().alteraPeriodoDaDisciplina(grid.getFinder().byId(session("email")), grid.getPlanejador().getDisciplina(disciplina), periodo);
		}
    	return redirect("/");
    }

	/**
     * Remove uma disciplina do usuario
     * @return um resultado/pagina que serah exibida no navegador
     */
	public static Result removeDisciplina(String nomeDaDisciplina) {
		Aluno aluno = grid.getFinder().byId(session("email"));
		grid.getPlanejador().removeDisciplinaESeusPreRequisitos(aluno, grid.getPlanejador().getDisciplina(nomeDaDisciplina));
		aluno.update();
		return redirect("/");
    }
	
	/**
	 * 
	 * @param periodoAtual periodo corrente do aluno
	 * @return novo periodo corrente do aluno
	 */
	public static Result setarPeriodoAtual(int periodoAtual) {
		Aluno aluno = grid.getFinder().byId(session("email"));
		aluno.getPlanoDoAluno().setPeriodoAtual(periodoAtual);
		aluno.update();
		return redirect("/");
	}
	
	/**
	 * 
	 * @return pagina inicial do sistema
	 */
	public static Result logout() {
	    session().clear();
	    session("email", "usuarioLogout");
	    flash("success", "Você saiu do sistema.");
	    return redirect(routes.Application.login());
	}

}
