package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
			//Cria o objeto PreparedStatement para obter a conex�o e executar o comando sql.
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
	}//adicionarContato()
	
	/*
	 * Obt�m uma lista com todos os contatos cadastrados no banco.
	 */
	public List<Contato> getLista() {
		try {
			//Cria a lista que receber� os contatos.
			List<Contato> contatos = new ArrayList<Contato>();
			
			String sql = "select * from contatos";
			
			//Cria o objeto PreparedStatement para obter a conex�o e executar o comando sql.
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Realiza a query e obt�m os dados.
			ResultSet resultSet = stmt.executeQuery();
			
			//Obt�m os dados e insere na lista.
			while(resultSet.next()){
				Contato contato = new Contato();
				
				contato.setId(resultSet.getLong("id"));
				contato.setNome(resultSet.getString("nome"));
				contato.setEmail(resultSet.getString("email"));
				contato.setEndereco(resultSet.getString("endereco"));
				
				//Obt�m a data atrav�s do calendar.
				Calendar data = Calendar.getInstance();
				data.setTime(resultSet.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				
				//Adiciona o contato a lista.
				contatos.add(contato);
			}
			
			//Fecha os objetos ResultSet e PreparedStatement.
			resultSet.close();
			stmt.close();
			
			//Retorna a lista com os contatos.
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}//getLista()
	
	//Altera as informa��es de um contato atrav�s do id do contato.
	public void alterarContato(Contato contato){
		//Cria o comando respons�vel pela atualiza��o dos dados.
		String sql = "update contatos set nome=?, email=?, endereco=?, dataNascimento=? where id=?;";
		
		try {
			//Cria o objeto PreparedStatement para obter a conex�o e executar o comando sql.
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Seta os novos valores.
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			
			//Seta o id do contato que ser� modificado.
			stmt.setLong(5, contato.getId());
			
			//Executa a atualiza��o e encerra o objeto PreparedStatement.
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}//alterarContato()
	
	//Remove um contato atrav�s do seu id.
	public void deletarContato(Contato contato){
		//Cria o comando sql respons�vel por apagar o contato.
		String sql = "delete from contatos where id=?;";
		
		try {
			//Cria o objeto PreparedStatement para obter a conex�o e executar o comando sql.
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Seta o id do contato que ser� apagado.
			stmt.setLong(1, contato.getId());
			
			//Executa a exclus�o e fecha encerra a conex�o do objeto PreparedStetement.
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}//deletarContato()
	
	//Encerra a conex�o com o banco de dados, caso esta esteja aberta.
	public void encerrarConexao() {
		if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
	}//encerrarConexao
}//class ContatoDAO
