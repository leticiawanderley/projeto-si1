package models.validador;


public class ValidadorMinDeCreditos extends ValidadorDeCreditos {

	@Override
	public boolean permiteNumeroDeCreditos(int numeroDeCreditos) {
		return numeroDeCreditos >= MINIMO_DE_CREDITOS;
	}

}
