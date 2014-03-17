package models.validador;


public class ValidadorMaxDeCreditos extends ValidadorDeCreditos {

	@Override
	public boolean permiteNumeroDeCreditos(int numeroDeCreditos) {
		return numeroDeCreditos <= MAXIMO_DE_CREDITOS;
	}

}
