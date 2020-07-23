package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class CRUDUser {
	
	Statement state;
	ResultSet resultset;
	ConnectionDatabase conex = new ConnectionDatabase();
	
	public ArrayList<String[]> retornaUsuario() {
		conex.conectar();
		
		String sql = "SELECT * FROM tbusuario";
		ArrayList<String[]> str = new ArrayList<>();
		
		try {
			state = (Statement) ConnectionDatabase.con.createStatement();
			resultset = state.executeQuery(sql);
			
			while(resultset.next()) {
				String[] linha = new String[4];
				linha[0] = resultset.getString(1);
				linha[1] = resultset.getString(2);
				linha[2] = resultset.getString(3);
				linha[3] = resultset.getString(4);
				str.add(linha);
			}
			
		} catch (SQLException erro) {
			System.out.println("N�o foi poss�vel realizar a consulta");
		}

		conex.desconectar();
		return str;
	}
	
	public void insereUsuario(String nomeUsuario, String emailUsuario, String fotoUsuario) {
		String sql = "INSERT INTO tbusuario(nomeUsuario, emailUsuario, fotoUsuario)"+
						"VALUES('" + nomeUsuario + "', '" + emailUsuario + "', '" + fotoUsuario + "')";

		conex.conectar();
		
		try {
			state = (Statement) ConnectionDatabase.con.createStatement();
			state.execute(sql);
			
		} catch (SQLException erro) {
			System.out.println("Problema ao tentar inserir");
		}

		conex.desconectar();
	}

	public void deletarUsuario(int idUsuario) {
		
		
		String sql = "DELETE FROM tbusuario "
						+ "WHERE idUsuario = " + idUsuario;

		conex.conectar();
		
		try {
			state = (Statement) ConnectionDatabase.con.createStatement();
			state.execute(sql);
			
		} catch (SQLException erro) {
			System.out.println("Problema ao tentar excluir");
		}

		conex.desconectar();
	}

	public void alterarUsuario(int idUsuario, String nomeUsuario, String emailUsuario) {
		String sql = "UPDATE tbusuario"
				+ "SET nomeUsuario = '" + nomeUsuario + "', emailUsuario = '" + emailUsuario + "'" +
						" WHERE idUsuario = '" + idUsuario + "'";

		conex.conectar();
		
		try {
			state = (Statement) ConnectionDatabase.con.createStatement();
			state.execute(sql);
			
		} catch (SQLException erro) {
			System.out.println("Problema ao tentar alterar");
		}
	
		conex.desconectar();
	}
}
