package data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionDatabase {

	static Connection con;

	public void conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/bdGame";
			String user = "root";
			String dbPassword = "";
			
			con = DriverManager.getConnection(url, user, dbPassword);

		} catch (ClassNotFoundException ex) {
			System.out.println("Driver JDBC-ODBC não encontrado");

		} catch (SQLException ex) {
			System.out.println("Problemas na conexão com o banco de dados.");
		}
	}

	public void desconectar() {
		try {
			con.close();
			System.out.println("Conexão finalizada com sucesso.\n");
			
		} catch (SQLException ex) {
			System.out.println("Problemas ao encerrar a conexão.");
		}
	}

}