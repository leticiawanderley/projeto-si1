package models;

/**
 * Excecao para caso o aluno tente pagar uma disciplina que ele nao possua os pre requisitos
 *
 */
public class AlunoNaoPossuiPreRequisitos extends Exception {
	
	private static final long serialVersionUID = 1L;
	private final static String ERROR_MESSAGE = "O aluno nao possui os pre-requisitos para esta disciplina";

	/**
	 * Construtor da excecao
	 */
	public AlunoNaoPossuiPreRequisitos() {
		super(ERROR_MESSAGE);
	}
	
}
