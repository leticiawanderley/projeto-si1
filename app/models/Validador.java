package models;

/**
 *  PADRAO STRATEGY
 *  
 *  Interface que defini a classificacao do periodo.
 *  
 *  Regras:
 *  		
 *  		Se periodo atual eh p e existem n's periodos, entao p':
 *  
 *  		=> p' < p: tem minimo, mas nao maximo;
 *  		=> p <= p' < n: tem maximo e minimo;
 *  		=> n: nao tem maximo
 *  	
 *  
 *
 */
public interface Validador {

}
