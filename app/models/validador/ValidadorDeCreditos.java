package models.validador;

/**
 * PADRAO STRATEGY
 * 
 * Interface que defini a classificacao do periodo.
 * 
 * Regras que se aplicam aos periodos do aluno: Se periodo atual eh p e existem
 * n's periodos, entao p':
 * 
 * => p'
 * < p: tem minimo, mas nao maximo; * =>
 * p <= p' < n: tem maximo e minimo; => n: nao tem maximo
 */
public interface ValidadorDeCreditos {

	/**
	 * 
	 * @param numeroDeCreditos
	 *            numero de creditos do periodo
	 * @return o numero de creditos eh permitido
	 */
	boolean permiteNumeroDeCreditos(int numeroDeCreditos);

	/**
	 * @param numeroDeCreditos
	 *            numero de creditos do periodo
	 * @return String indicando se a quantidade de credito eh maior ou menor que
	 *         o esperado
	 */
	String getTipoValidacao(int numeroDeCreditos);

}
