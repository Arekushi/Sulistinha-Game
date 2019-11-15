package backend;

import java.util.ArrayList;

import data.CRUDUsuario;
import model.Usuario;

public class ArmazenaUsuario {
	
	ArrayList<Usuario> usuarios;
	
	public ArmazenaUsuario() {
		
		CRUDUsuario crud = new CRUDUsuario();
		usuarios = new ArrayList<Usuario>();
		
		ArrayList<String[]> linha = crud.retornaUsuario();
		
		for (int i = 0; i < linha.size(); i++) {
			usuarios.add(new Usuario(linha.get(i)));
		}
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}

}
