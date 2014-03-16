package models.validador;


public class ValidadorMinDeCreditos extends ValidadorDeCreditos {
	
	public ValidadorMinDeCreditos(int numeroDeCreditos) {
		super(numeroDeCreditos);
	}

	@Override
	public boolean permiteNumeroDeCreditos() {
		return numeroDeCreditos >= MINIMO_DE_CREDITOS;
	}

}
