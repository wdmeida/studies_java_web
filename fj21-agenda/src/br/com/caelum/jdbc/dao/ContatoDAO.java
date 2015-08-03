package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.model.Contato;

/*
 * Utiliza um desing pattern para manipular os dados no banco de dados.
 * Será realizado um CRUD como exemplo.
 */
public class ContatoDAO {
	//Atributo que recebe a conexão com o banco de dados.
	Connection connection;
	
	//Obtém a conexão com o banco de dados no construtor. Isso evita que sejam criadas várias conexões diferentes com o banco.
	public ContatoDAO() {
		connection = new ConnectionFactory().getConnection();
	}//ContatoDAO()
	
	/*
	 * Adiciona um contato no banco de dados. Deve ser feito de forma completamente encapsulada, por isso o método receberá as informações
	 * a serem inseridas no banco através do objeto contato, e dentro do método as informações serão separadas afim de realizar a inserção.
	 */
	public void adicionarContato(Contato contato) {
		
		//Cria o comando responsável pela inserção dos dados.
		String sql = "insert into contatos (nome,email,endereco,dataNascimento) values (?,?,?,?);";
		
		try {
			//Cria o objeto PreparedStatement e obtém a conexão para realizar a inserção.
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Seta os valores para inserção.
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			
			//Executa e encerra o objeto preparedStatement.
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}//class ContatoDAO
