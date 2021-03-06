package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class CRUDQuestion {
	
	Statement state;
	ResultSet resultset;
	ConnectionDatabase conex = new ConnectionDatabase();
	
	public ArrayList<String[]> retornaQuestoes() {
		String sql = "SELECT * FROM tbquestao";

		conex.conectar();
		ArrayList<String[]> str = new ArrayList<>();
		
		try {
			state = (Statement) ConnectionDatabase.con.createStatement();
			resultset = state.executeQuery(sql);
			
			while(resultset.next()) {
				String[] linha = new String[6];
				linha[0] = resultset.getString(1);
				linha[1] = resultset.getString(2);
				linha[2] = resultset.getString(3);
				linha[3] = resultset.getString(4);
				linha[4] = resultset.getString(5);
				linha[5] = resultset.getString(6);
				str.add(linha);
			}
			
		} catch (SQLException erro) {
			System.out.println("N�o foi poss�vel realizar a consulta");
		}

		conex.desconectar();
		return str;
	}
	
	public void insereQuestao(String pergunta, String correta, String[] errada) {
		String sql = "INSERT INTO tbquestao(pergunta, correta, errada1, errada2, errada3)"+
						"VALUES('" + pergunta + "', '" + correta + "', '" + errada[0] + "', '" + errada[1] + "', '" + errada[2] + "')";

		conex.conectar();
		
		try {
			state = (Statement) ConnectionDatabase.con.createStatement();
			state.execute(sql);
			
		} catch (SQLException erro) {
			System.out.println("Problema ao tentar inserir");
		}

		conex.desconectar();
	}

	public void deletarQuestao(int idQuestao) {
		
		
		String sql = "DELETE FROM tbquestao "
					+ "WHERE idQuestao = " + idQuestao;

		conex.conectar();
		
		try {
			state = (Statement) ConnectionDatabase.con.createStatement();
			state.execute(sql);
			
		} catch (SQLException erro) {
			System.out.println("Problema ao tentar excluir");
		}

		conex.desconectar();
	}

	public void alterarQuestao(int idQuestao, String pergunta, String correta, String[] errada) {
		String sql = "UPDATE tbquestao"
				+ " SET pergunta = '" + pergunta + "', correta = '" + correta + "', errada1 = '" + errada[0] + "', errada2 = '" + errada[1] + "', errada3 = '" + errada[2] + "'" +
						" WHERE idQuestao = '" + idQuestao + "'";

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
