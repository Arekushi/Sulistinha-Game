package model;

import java.util.ArrayList;
import java.util.Collections;

import backend.Alternative;

public class Question {

	// Atributos
	private int id;
	private String enunciado;
	private ArrayList<Alternative> alternatives;
	
	// Construtor
	public Question(String[] linha) {
		this.id = Integer.parseInt(linha[0]);
		this.enunciado = linha[1];
		this.alternatives = new ArrayList<>();
		this.alternatives.add(new Alternative(linha[2], true));
		this.alternatives.add(new Alternative(linha[3], false));
		this.alternatives.add(new Alternative(linha[4], false));
		this.alternatives.add(new Alternative(linha[5], false));
		Collections.shuffle(alternatives);
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public ArrayList<Alternative> getAlternatives() {
		return alternatives;
	}

	public void setAlternatives(ArrayList<Alternative> alternatives) {
		this.alternatives = alternatives;
	}
	
}
