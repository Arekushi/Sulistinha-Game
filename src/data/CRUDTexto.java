package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class CRUDTexto {
	
	Statement state;
	ResultSet resultset;
	Conexao conex = new Conexao();
	
	public ArrayList<String[]> retornaTexto(int nivelDificuldade) {
		conex.conectar();
		
		String sql = "SELECT texto, nivel FROM tbTexto WHERE nivel = "+nivelDificuldade;
		ArrayList<String[]> str = new ArrayList<String[]>();
		
		try {
			state = (Statement) Conexao.con.createStatement();
			resultset = state.executeQuery(sql);
			
			while(resultset.next()) {
				String linha[] = new String[2];
				linha[0] = resultset.getString(1);
				linha[1] = resultset.getString(2);
				str.add(linha);
			}
			
		} catch (SQLException erro) {
			System.out.println("Não foi possível realizar a consulta");
		}

		conex.desconectar();
		return str;
	}

}
