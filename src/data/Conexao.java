package data;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexao {

	// cria uma conexao chamada con
	static Connection con;

	// metodo responsavel para se conectar ao bd
	public void conectar() {
		try {
			// para carregar o driver
			Class.forName("com.mysql.jdbc.Driver");

			// jdbc - e o driver da sun responsavel pela conexao (Java DataBase Connection)
			// estabelece uma conexao
			
//			String url = "jdbc:mysql://remotemysql.com/5TSdttcB2c?autoReconnect=true&useSSL=false";
//			String user = "5TSdttcB2c";
//			String dbPassword = "CDUJcpnetw";
			
			String url = "jdbc:mysql://localhost:3306/bdGame";
			String user = "root";
			String dbPassword = "";
			
			con = DriverManager.getConnection(url, user, dbPassword);

//			System.out.println("Conexão realizada com sucesso.");
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver JDBC-ODBC não encontrado");
		} catch (SQLException ex) {
			System.out.println("Problemas na conexão com o banco de dados.");
		}
	}

	public void desconectar() {
		try {
			con.close();
//			System.out.println("Conexao finalizada com sucesso.\n");
		} catch (SQLException ex) {
//			System.out.println("Problemas ao encerrar a conexão.");
		}
	}

}