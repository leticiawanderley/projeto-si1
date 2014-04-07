package models;

/**
 * Tipos de fluxograma do sistema
 *
 */
public enum TipoDeFluxograma {
	
	COMUM("comum"),
	NOVO("novo"),
	OFICIAL("oficial");
	
	private String tipoDeFluxoGrama;
	
	/**
	 * 
	 * @param tipoDeFluxoGrama tipo/nome do tipo de fluxograma
	 */
	TipoDeFluxograma(String tipoDeFluxoGrama) {
		this.tipoDeFluxoGrama = tipoDeFluxoGrama;
	}
	
	/**
	 * 
	 * @return o tipo/nome do fluxograma
	 */
	public String getTipoDeFluxoGrama() {
		return this.tipoDeFluxoGrama;
	}
	
}
