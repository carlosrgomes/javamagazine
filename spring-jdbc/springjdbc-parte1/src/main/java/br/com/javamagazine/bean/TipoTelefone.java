package br.com.javamagazine.bean;

public enum TipoTelefone {
	
	CELULAR, COMERCIAL, RESIDENCIAL;

	public static TipoTelefone findBy(String value) {
		TipoTelefone tipo = null;
		
		TipoTelefone[] tiposTelefones = TipoTelefone.values();
		for (TipoTelefone tipoTelefone : tiposTelefones) {
			if(value.equals(tipoTelefone.name())){
				tipo =  tipoTelefone;
				break;
			}
		}
		return tipo;
	}

}
