package models.validador;

import models.Disciplina;
import models.Periodo;

/**
 *  PADRAO STRATEGY
 *  
 *  Interface que defini a classificacao do periodo.
 *  
 *  Regras:
 *  		Se periodo atual eh p e existem n's periodos, entao p':
 *  
 *  		=> p' < p: tem minimo, mas nao maximo;
 *  		=> p <= p' < n: tem maximo e minimo;
 *  		=> n: nao tem maximo
 */
public abstract class ValidadorDeCreditos {
	
	protected static final int MINIMO_DE_CREDITOS = 12;
	protected static final int MAXIMO_DE_CREDITOS = 28;
	
	public abstract boolean podeAdicionar(Disciplina disciplina, Periodo periodo);

}
