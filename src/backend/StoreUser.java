package backend;

import java.util.ArrayList;

import data.CRUDUser;
import model.User;

public class StoreUser {
	
	ArrayList<User> users;
	
	public StoreUser() {
		
		CRUDUser crud = new CRUDUser();
		users = new ArrayList<>();
		
		ArrayList<String[]> linha = crud.retornaUsuario();

		for (String[] strings : linha) {
			users.add(new User(strings));
		}
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}

}
