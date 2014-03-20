package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import models.Aluno;
import models.Disciplina;
import models.User;
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
	
	private static final String CADEIRA_NAO_EXISTENTE = "O nome da disciplina está incorreta ou não existe";
			
	private static GridSystem grid = new GridSystem();
	private static Form<Disciplina> disciplinaForm = Form.form(Disciplina.class);
	private static Aluno USUARIO_NAO_LOGADO;
	
	/**
	 * Pagina inicial da alocacao das disciplinas
	 * @return um resultado/pagina que serah exibida no navegador
	 */
	@Security.Authenticated(Secured.class)
    public static Result index() {
    	if (grid.getAluno() ==  USUARIO_NAO_LOGADO) {
    		return redirect(routes.Application.login());
    	}
        return ok(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador()));
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
    	return ok(views.html.disciplinasDoCurso.render(grid.getAluno(), disciplinaForm,grid.getPlanejador()));
    }

    /**
     * Matricula o usuario na disciplina
     * @return um resultado/pagina que serah exibida no navegador
     */
    public static Result matriculaNaDisciplina(int periodo, String disciplina) {
    	Form<Disciplina> filledForm = disciplinaForm.bindFromRequest();
    	if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador()));
		} else {
			grid.getPlanejador().alteraPeriodoDaDisciplina(grid.getAluno(), grid.getPlanejador().getDisciplina(disciplina), periodo);
		}
    	return redirect("/");
    }

	/**
     * Remove uma disciplina do usuario
     * @return um resultado/pagina que serah exibida no navegador
     */
	public static Result removeDisciplina(String nomeDaDisciplina) {
		Disciplina disciplina = grid.getPlanejador().getDisciplina(nomeDaDisciplina);
		if (!grid.getPlanejador().existeCadeira(nomeDaDisciplina)) {
    		flash("sucess", CADEIRA_NAO_EXISTENTE);
    		return badRequest(views.html.index.render(grid.getAluno(), disciplinaForm, grid.getPlanejador()));
    	}
		grid.getPlanejador().removeDisciplinaESeusPreRequisitos(grid.getAluno(), disciplina);
		grid.getAluno().update();
		return index();
    }
	
	public static Result cadastrarNovoUsuario() {
		return ok(views.html.cadastrarUsuario.render(Form.form(User.class)));
	}
	
	// TODO verificar se o usuario jah existe (procurar qual deve ser a melhor solucao)
	public static Result criarUsuario() {
		Form<User> loginForm = Form.form(User.class).bindFromRequest();
		if (!loginForm.get().getPassword().equals(loginForm.data().get("confirmPassword"))) {
			flash("success", "Senha e confirmação de senha estão incompativeis");
			return cadastrarNovoUsuario();
		}
    	Aluno novoAluno = new Aluno(loginForm.get().getName(), loginForm.get().getEmail(), loginForm.get().getPassword());
    	grid.alocandoNovoUsuario(novoAluno);
    	novoAluno.save();
    	return login();
	}
	
	public static Result populaUsuarios() throws IOException {
		URL url = new URL("http://csplanner.herokuapp.com/assets/alunos.txt");
		Scanner scanner = new Scanner(url.openStream());
		while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(":");
            Aluno novoAluno = new Aluno(line[0], line[1], line[2]);
        	grid.alocandoNovoUsuario(novoAluno);
        	Ebean.save(novoAluno);
        }
    	return ok();
	}
	
	public static Result login() {
	    return ok(views.html.login.render(Form.form(User.class)));
	}
	
	// TODO certo caminhos ainda conseguem voltar para a conta do usuario (nao estah seguro)
	public static Result logout() {
	    session().clear();
	    flash("success", "You've been logged out");
	    grid.setAluno(USUARIO_NAO_LOGADO);
	    return redirect(routes.Application.login());
	}
	
	public static Result authenticate() {
	    Form<User> loginForm = Form.form(User.class).bindFromRequest();
	    if (loginForm.hasErrors()) {
	        return badRequest(views.html.login.render(loginForm));
	    } else {
	    	if (grid.getFinder().all().contains(new Aluno("", loginForm.get().getEmail(), loginForm.get().getPassword()))) {
	    		for (Aluno aluno : grid.getFinder().all()) {
	    			if (aluno.equals(new Aluno("", loginForm.get().getEmail(), loginForm.get().getPassword()))) {
	    				grid.setAluno(grid.getFinder().findMap().get(aluno.getId()));
	    				 break;
	    			}
	    		}
	    		grid.getAluno().getListaDePeriodos();
	    		session().clear();
		        session("email", loginForm.get().getEmail());
		        return redirect(routes.Application.index());
	    	} else {
	    		return badRequest(views.html.login.render(Form.form(User.class)));
	    	}
	    }
	}
    
}
