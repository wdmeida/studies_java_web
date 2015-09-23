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
 * Será realizado um CRUD como exemplo.
 */
public class ContatoDAO {
	//Atributo que recebe a conexão com o banco de dados.
	private Connection connection;
	
	//Obtém a conexão com o banco de dados no construtor. Isso evita que sejam criadas várias conexões diferentes com o banco.
	public ContatoDAO() {
		connection = new ConnectionFactory().getConnection();
	}//ContatoDAO()
	
	public ContatoDAO(Connection connection){
		this.connection = connection;
	}
	
	/*
	 * Adiciona um contato no banco de dados. Deve ser feito de forma completamente encapsulada, por isso o método receberá as informações
	 * a serem inseridas no banco através do objeto contato, e dentro do método as informações serão separadas afim de realizar a inserção.
	 */
	public void adicionarContato(Contato contato) {
		
		//Cria o comando responsável pela inserção dos dados.
		String sql = "insert into contatos (nome,email,endereco,dataNascimento) values (?,?,?,?);";
		
		try {
			//Cria o objeto PreparedStatement para obter a conexão e executar o comando sql.
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
	}//adicionarContato()
	
	/*
	 * Obtém uma lista com todos os contatos cadastrados no banco.
	 */
	public List<Contato> getLista() {
		try {
			//Cria a lista que receberá os contatos.
			List<Contato> contatos = new ArrayList<Contato>();
			
			String sql = "select * from contatos";
			
			//Cria o objeto PreparedStatement para obter a conexão e executar o comando sql.
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Realiza a query e obtém os dados.
			ResultSet resultSet = stmt.executeQuery();
			
			//Obtém os dados e insere na lista.
			while(resultSet.next()){
				Contato contato = new Contato();
				
				contato.setId(resultSet.getLong("id"));
				contato.setNome(resultSet.getString("nome"));
				contato.setEmail(resultSet.getString("email"));
				contato.setEndereco(resultSet.getString("endereco"));
				
				//Obtém a data através do calendar.
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
	
	//Altera as informações de um contato através do id do contato.
	public void alterarContato(Contato contato){
		//Cria o comando responsável pela atualização dos dados.
		String sql = "update contatos set nome=?, email=?, endereco=?, dataNascimento=? where id=?;";
		
		try {
			//Cria o objeto PreparedStatement para obter a conexão e executar o comando sql.
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Seta os novos valores.
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			
			//Seta o id do contato que será modificado.
			stmt.setLong(5, contato.getId());
			
			//Executa a atualização e encerra o objeto PreparedStatement.
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}//alterarContato()
	
	//Remove um contato através do seu id.
	public void deletarContato(Contato contato){
		//Cria o comando sql responsável por apagar o contato.
		String sql = "delete from contatos where id=?;";
		
		try {
			//Cria o objeto PreparedStatement para obter a conexão e executar o comando sql.
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Seta o id do contato que será apagado.
			stmt.setLong(1, contato.getId());
			
			//Executa a exclusão e fecha encerra a conexão do objeto PreparedStetement.
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}//deletarContato()
	
	//Encerra a conexão com o banco de dados, caso esta esteja aberta.
	public void encerrarConexao() {
		if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
	}//encerrarConexao
}//class ContatoDAO
