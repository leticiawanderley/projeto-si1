package controllers;

import models.Aluno;
import models.Disciplina;
import models.User;
import play.data.Form;
import play.db.ebean.Model.Finder;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Classe que controla as requisições do sistema web
 *
 */
public class Application extends Controller {
	
	private static final String CADEIRA_NAO_EXISTENTE = "O nome da disciplina está incorreta ou não existe";
			
	private static GridSystem grid = new GridSystem();
	private static Form<Disciplina> disciplinaForm = Form.form(Disciplina.class);
	private static Finder<String, Aluno> usuariosLogados = new Finder<String, Aluno>(String.class, Aluno.class);
 	private static Aluno USUARIO_NAO_LOGADO;
	
	/**
	 * Pagina inicial da alocacao das disciplinas
	 * @return um resultado/pagina que serah exibida no navegador
	 */
	@Security.Authenticated(Secured.class)
    public static Result index() {
		if (session("email") == null) {
			return redirect(routes.Application.login());
		}
    	if (usuariosLogados.byId(session("email")) ==  USUARIO_NAO_LOGADO) {
    		return redirect(routes.Application.login());
    	}
    	
        return ok(views.html.index.render(usuariosLogados.byId(session("email")), disciplinaForm, grid.getPlanejador()));
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
    	return ok(views.html.disciplinasDoCurso.render(usuariosLogados.byId(session("email")), disciplinaForm,grid.getPlanejador()));
    }

    /**
     * Matricula o usuario na disciplina
     * @return um resultado/pagina que serah exibida no navegador
     */
    public static Result matriculaNaDisciplina(int periodo, String disciplina) {
    	Form<Disciplina> filledForm = disciplinaForm.bindFromRequest();
    	if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(usuariosLogados.byId(session("email")), disciplinaForm, grid.getPlanejador()));
		} else {
			grid.getPlanejador().alteraPeriodoDaDisciplina(usuariosLogados.byId(session("email")), grid.getPlanejador().getDisciplina(disciplina), periodo);
		}
    	return redirect("/");
    }

	/**
     * Remove uma disciplina do usuario
     * @return um resultado/pagina que serah exibida no navegador
     */
	public static Result removeDisciplina(String nomeDaDisciplina) {
		Disciplina disciplina = grid.getPlanejador().getDisciplina(nomeDaDisciplina);
		grid.getPlanejador().removeDisciplinaESeusPreRequisitos(usuariosLogados.byId(session("email")), disciplina);
		usuariosLogados.byId(session("email")).update();
		return index();
    }
	
	public static Result cadastrarNovoUsuario() {
		return ok(views.html.cadastrarUsuario.render(Form.form(User.class)));
	}
	
	public static Result criarUsuario() {
		Form<User> loginForm = Form.form(User.class).bindFromRequest();
		if (!loginForm.get().getPassword().equals(loginForm.data().get("confirmPassword"))) {
			flash("success", "Senha e confirmação de senha estão incompativeis");
			return cadastrarNovoUsuario();
		}
    	Aluno novoAluno = new Aluno(loginForm.get().getName(), loginForm.get().getEmail(), loginForm.get().getPassword());
    	if (usuariosLogados.byId(novoAluno.getEmail()) != null) {
    		flash("success", "Usuário já existente no sistema, utilize outro e-mail");
			return cadastrarNovoUsuario();
    	}
    	grid.alocandoNovoUsuario(novoAluno);
    	novoAluno.save();
    	session().clear();
    	session("email", novoAluno.getEmail());
    	return index();
	}
	public static Result login() {
	    return ok(views.html.login.render(Form.form(User.class)));
	}
	
	public static Result logout() {
	    session().clear();
	    session("email", "usuarioLogout");
	    flash("success", "You've been logged out");
	    return redirect(routes.Application.login());
	}
	
	public static Result authenticate() {
	    Form<User> loginForm = Form.form(User.class).bindFromRequest();
	    if (loginForm.hasErrors()) {
	        return badRequest(views.html.login.render(loginForm));
	    } else {
	    	if (usuariosLogados.byId(loginForm.get().getEmail()) == null) {
	    		flash("success", "Usuário não existente");
	    	} else {
	    		if (!usuariosLogados.byId(loginForm.get().getEmail()).getPassword().equals(loginForm.get().getPassword())) {
	    			flash("success", "Senha incorreta, tente novamente");
	    		} else {
	    			session().clear();
    		        session("email", loginForm.get().getEmail());
    		        return redirect(routes.Application.index());
	    		}
	    	}
	    }
	    return badRequest(views.html.login.render(Form.form(User.class)));
	}
    
}
