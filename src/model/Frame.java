package model;

public class Frame {
	
	private String caminho;
	
	public Frame(String linha[]) {
		this.caminho = linha[0];
	}
	
	// SET
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
		
	// GET
	public String getCaminho() {
		return caminho;
	}
	
}
