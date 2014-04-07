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
	
	TipoDeFluxograma(String tipoDeFluxoGrama) {
		this.tipoDeFluxoGrama = tipoDeFluxoGrama;
	}
	
	public String getTipoDeFluxoGrama() {
		return this.tipoDeFluxoGrama;
	}
	
}
