package backend;

import java.util.ArrayList;

import data.CRUDQuestion;
import model.Question;

public class StoreQuestion {
	
	ArrayList<Question> questoes;
	
	public StoreQuestion() {
	
		CRUDQuestion crud = new CRUDQuestion();
		questoes = new ArrayList<>();
		
		ArrayList<String[]> linha = crud.retornaQuestoes();

		for (String[] strings : linha) {
			questoes.add(new Question(strings));
		}
		
	}
	
	public ArrayList<Question> getQuestoes() {
		return this.questoes;
	}
}
