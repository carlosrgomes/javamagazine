package br.com.javamagazine.bean;

public class Telefone {

	private Long id;
	private String ddd;
	private String numero;
	private TipoTelefone tipoTelefone;
	private Long idPessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	@Override
	public String toString() {
		return String.format("Telefone [id=%s, ddd=%s, numero=%s, tipoTelefone=%s, idPessoa=%s]", id, ddd, numero,
				tipoTelefone, idPessoa);
	}
	
	

}
