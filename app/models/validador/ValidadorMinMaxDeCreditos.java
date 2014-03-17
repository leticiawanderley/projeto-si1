package models.validador;


public class ValidadorMinMaxDeCreditos extends ValidadorDeCreditos {

	@Override
	public boolean permiteNumeroDeCreditos(int numeroDeCreditos) {
		return numeroDeCreditos >= MINIMO_DE_CREDITOS && numeroDeCreditos >= MAXIMO_DE_CREDITOS;
	}

}
