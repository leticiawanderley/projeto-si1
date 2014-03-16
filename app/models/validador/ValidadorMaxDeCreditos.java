package models.validador;


public class ValidadorMaxDeCreditos extends ValidadorDeCreditos {
	
	public ValidadorMaxDeCreditos(int numeroDeCreditos) {
		super(numeroDeCreditos);
	}

	@Override
	public boolean permiteNumeroDeCreditos() {
		return numeroDeCreditos <= MAXIMO_DE_CREDITOS;
	}

}
