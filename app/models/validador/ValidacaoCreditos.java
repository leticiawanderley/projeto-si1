package models.validador;

/**
 * Implementacoes do Validador de creditos dos periodos dos alunos
 *
 */
public enum ValidacaoCreditos implements ValidadorDeCreditos {

	/**
	 * Validador minimo
	 */
	MIN {
		@Override
		public boolean permiteNumeroDeCreditos(int numeroDeCreditos) {
			return numeroDeCreditos >= 12;
		}

		@Override
		public String getTipoValidacao(int numeroDeCreditos) {
			return "menos";
		}
	},
	
	/**
	 * Validador maximo
	 */
	MAX {
		@Override
		public boolean permiteNumeroDeCreditos(int numeroDeCreditos) {
			return numeroDeCreditos <= 28;
		}

		@Override
		public String getTipoValidacao(int numeroDeCreditos) {
			return "mais";
		}
	},
	
	/**
	 * Validador maximo e minimo
	 */
	MINMAX {
		@Override
		public boolean permiteNumeroDeCreditos(int numeroDeCreditos) {
			return numeroDeCreditos <= 28 && numeroDeCreditos >= 12;
		}

		@Override
		public String getTipoValidacao(int numeroDeCreditos) {
			String retorno = "";
			if (numeroDeCreditos < 12) {
				retorno = "menos";
			}
			else if (numeroDeCreditos > 28) {
				retorno = "mais";
			}
			return retorno;
		}		
	}
	
}
