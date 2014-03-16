package models.validador;


public class ValidadorMinMaxDeCreditos extends ValidadorDeCreditos {
	
	public ValidadorMinMaxDeCreditos(int numeroDeCreditos) {
		super(numeroDeCreditos);
	}

	@Override
	public boolean permiteNumeroDeCreditos() {
		return numeroDeCreditos >= MINIMO_DE_CREDITOS && numeroDeCreditos <= MAXIMO_DE_CREDITOS;
	}

}
