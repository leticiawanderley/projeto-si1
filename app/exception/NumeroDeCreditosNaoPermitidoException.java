package exception;

/**
 * TODO Ainda usaremos
 * Excecao para um periodo que possui o numero de creditos nao permitido
 *
 */
public class NumeroDeCreditosNaoPermitidoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private final static String ERROR_MESSAGE = "Este periodo nao aluno nao pode conter este numero de creditos";

	/**
	 * Construtor da excecao
	 */
	public NumeroDeCreditosNaoPermitidoException() {
		super(ERROR_MESSAGE);
	}

}
