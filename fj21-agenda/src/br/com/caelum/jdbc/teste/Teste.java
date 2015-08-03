package br.com.caelum.jdbc.teste;
/*
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.model.Contato;*/

public class Teste {
	
	//Testa as operações básicas de um banco de dados (CRUD).
	
	public static void main(String[] args) {
		/*//Testa a inserção de um novo contato no banco de dados.
		Contato contato = new Contato();
		ContatoDAO dao = new ContatoDAO();
		contato.setNome("Wagner");
		contato.setEmail("wdmeida@gmail.com");
		contato.setEndereco("R. Teste 8080 fj21");
		contato.setDataNascimento(Calendar.getInstance());
		
		//Grava os dados no banco.
		
		dao.adicionarContato(contato);
		
		System.out.println("Gravado!");
		
		//Testa a obtenção dos resultados gravados.
		
		//Cria uma lista para obter as informações.
		List<Contato> contatos = dao.getLista();
		
		//Imprime os contatos cadastrados no banco.
		for(Contato con : contatos){
			System.out.println("Nome: " + con.getNome());
			System.out.println("Email: " + con.getEmail());
			System.out.println("Endereço: " + con.getEndereco());
			System.out.println("Data de Nascimento: " + con.getDataNascimento().getTime());
			System.out.printf("\n");
		}
		
		//Encerra a conexão com o banco que foi aberta.
		dao.encerrarConexao();*/
	}//main

}
