package model;

public class Text {
	
	private String texto;
	private int nivel;
	
	public Text(String[] linha) {
		this.texto = linha[0];
		this.nivel = Integer.parseInt(linha[1]);
	}
	
	// SET
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	// GET
	public String getTexto() {
		return texto;
	}
	
	public int getNivel() {
		return nivel;
	}

}
