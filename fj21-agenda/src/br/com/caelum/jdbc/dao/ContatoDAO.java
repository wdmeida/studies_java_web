package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.model.Contato;

/*
 * Utiliza um desing pattern para manipular os dados no banco de dados.
 * Ser� realizado um CRUD como exemplo.
 */
public class ContatoDAO {
	//Atributo que recebe a conex�o com o banco de dados.
	Connection connection;
	
	//Obt�m a conex�o com o banco de dados no construtor. Isso evita que sejam criadas v�rias conex�es diferentes com o banco.
	public ContatoDAO() {
		connection = new ConnectionFactory().getConnection();
	}//ContatoDAO()
	
	/*
	 * Adiciona um contato no banco de dados. Deve ser feito de forma completamente encapsulada, por isso o m�todo receber� as informa��es
	 * a serem inseridas no banco atrav�s do objeto contato, e dentro do m�todo as informa��es ser�o separadas afim de realizar a inser��o.
	 */
	public void adicionarContato(Contato contato) {
		
		//Cria o comando respons�vel pela inser��o dos dados.
		String sql = "insert into contatos (nome,email,endereco,dataNascimento) values (?,?,?,?);";
		
		try {
			//Cria o objeto PreparedStatement e obt�m a conex�o para realizar a inser��o.
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Seta os valores para inser��o.
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
