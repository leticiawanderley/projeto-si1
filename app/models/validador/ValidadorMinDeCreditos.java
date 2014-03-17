package models.validador;

/**
 *  Validador para periodo que possui minimo de creditos
 *
 */
public class ValidadorMinDeCreditos extends ValidadorDeCreditos {

	@Override
	public boolean permiteNumeroDeCreditos(int numeroDeCreditos) {
		return numeroDeCreditos >= MINIMO_DE_CREDITOS;
	}

}
