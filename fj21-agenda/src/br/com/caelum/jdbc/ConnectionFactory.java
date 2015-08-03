package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	/*
	 * Obtém uma conexão com o banco de dados para manipulação das informações.
	 */
	public Connection getConnection(){
		try {
			/*
			 * Não esquecer de registrar a classe do Driver. Este registro não é necessário a partir da versão
			 * 1.5 do Java, mas utilizando mysql não funcionou sem o registro. Verificar depois o motivo. 
			 */
				Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/fj21", "aluno", "Aluno123#@!");
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException();
		}
	}//getConnection()
}
