package models.validador;

public enum ValidacaoCreditos implements ValidadorDeCreditos {

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
