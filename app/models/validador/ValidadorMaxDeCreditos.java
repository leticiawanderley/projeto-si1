package models.validador;

/**
 *  Validador para periodo que possui maximo de creditos
 *
 */
public class ValidadorMaxDeCreditos extends ValidadorDeCreditos {

	@Override
	public boolean permiteNumeroDeCreditos(int numeroDeCreditos) {
		return numeroDeCreditos <= MAXIMO_DE_CREDITOS;
	}

}
