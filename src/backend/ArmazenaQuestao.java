package backend;

import java.util.ArrayList;

import data.CRUDQuestao;
import model.Questao;

public class ArmazenaQuestao {
	
	ArrayList<Questao> questoes;
	
	public ArmazenaQuestao() {
	
		CRUDQuestao crud = new CRUDQuestao();
		questoes = new ArrayList<Questao>();
		
		ArrayList<String[]> linha = crud.retornaQuestoes();
		
		for(int i = 0; i < linha.size(); i++) {
			questoes.add(new Questao(linha.get(i)));
		}
		
	}
	
	public ArrayList<Questao> getQuestoes() {
		return this.questoes;
	}
}
