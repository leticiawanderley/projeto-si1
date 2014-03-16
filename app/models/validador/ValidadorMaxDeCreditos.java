package models.validador;

import models.Disciplina;
import models.Periodo;

public class ValidadorMaxDeCreditos extends ValidadorDeCreditos {
	
	@Override
	public boolean podeAdicionar(Disciplina disciplina, Periodo periodo) {
		// TODO Auto-generated method stub
		return false;
	}

}
