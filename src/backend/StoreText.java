package backend;

import java.util.ArrayList;

import data.CRUDText;
import model.Text;

public class StoreText {
	
	ArrayList<Text> texts;
	
	public StoreText(int nivelDificuldade) {
	
		CRUDText crud = new CRUDText();
		texts = new ArrayList<>();
		
		ArrayList<String[]> linha = crud.retornaTexto(nivelDificuldade);

		for (String[] strings : linha) {
			texts.add(new Text(strings));
		}
		
	}
	
	public ArrayList<Text> getQuestoes() {
		return this.texts;
	}

}
