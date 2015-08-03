package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	/*
	 * Obt�m uma conex�o com o banco de dados para manipula��o das informa��es.
	 */
	public Connection getConnection(){
		try {
			/*
			 * N�o esquecer de registrar a classe do Driver. Este registro n�o � necess�rio a partir da vers�o
			 * 1.5 do Java, mas utilizando mysql n�o funcionou sem o registro. Verificar depois o motivo. 
			 */
				Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/fj21", "aluno", "Aluno123#@!");
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException();
		}
	}//getConnection()
}
