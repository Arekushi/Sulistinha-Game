package backend;

import java.util.ArrayList;
import data.CRUDAnimation;
import model.Frame;

public class StoreFrame {
	
	private ArrayList<Frame> frames;
	
	public StoreFrame(String nome) {
	
		CRUDAnimation crud = new CRUDAnimation();
		frames = new ArrayList<>();
		
		ArrayList<String[]> linha = crud.retornaFrames(nome);

		for (String[] strings : linha) {
			frames.add(new Frame(strings));
		}
		
	}
	
	public ArrayList<Frame> getFrames() {
		return this.frames;
	}
}
