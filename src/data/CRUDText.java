package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class CRUDText {
	
	Statement state;
	ResultSet resultset;
	ConnectionDatabase conex = new ConnectionDatabase();
	
	public ArrayList<String[]> retornaTexto(int nivelDificuldade) {
		conex.conectar();
		
		String sql = "SELECT texto, nivel FROM tbTexto WHERE nivel = "+nivelDificuldade;
		ArrayList<String[]> str = new ArrayList<>();
		
		try {
			state = (Statement) ConnectionDatabase.con.createStatement();
			resultset = state.executeQuery(sql);
			
			while(resultset.next()) {
				String[] linha = new String[2];
				linha[0] = resultset.getString(1);
				linha[1] = resultset.getString(2);
				str.add(linha);
			}
			
		} catch (SQLException erro) {
			System.out.println("N�o foi poss�vel realizar a consulta");
		}

		conex.desconectar();
		return str;
	}

}
