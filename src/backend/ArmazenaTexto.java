package backend;

import java.util.ArrayList;

import data.CRUDTexto;
import model.Texto;

public class ArmazenaTexto {
	
	ArrayList<Texto> textos;
	
	public ArmazenaTexto(int nivelDificuldade) {
	
		CRUDTexto crud = new CRUDTexto();
		textos = new ArrayList<Texto>();
		
		ArrayList<String[]> linha = crud.retornaTexto(nivelDificuldade);
		
		for(int i = 0; i < linha.size(); i++) {
			textos.add(new Texto(linha.get(i)));
		}
		
	}
	
	public ArrayList<Texto> getQuestoes() {
		return this.textos;
	}

}
