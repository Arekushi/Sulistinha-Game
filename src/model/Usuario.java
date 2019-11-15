package model;

public class Usuario {
	
	private int idUsuario;
	private String nomeUsuario;
	private String emailUsuario;
	private String fotoUsuario;
	
	public Usuario(String linha[]) {
		this.idUsuario = Integer.parseInt(linha[0]);
		this.nomeUsuario = linha[1];
		this.emailUsuario = linha[2];
		this.fotoUsuario = linha[3];
	}
	
	// SET
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}
	
	// GET
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public String getEmailUsuario() {
		return emailUsuario;
	}
	
	public String getFotoUsuario() {
		return fotoUsuario;
	}

}
