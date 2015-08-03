package br.com.caelum.jdbc.teste;

import java.util.Calendar;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.model.Contato;

public class Teste {
	
	//Testa as operações do banco de dados.
	
	public static void main(String[] args) {
		//pronto para gravar
		Contato contato = new Contato();
		contato.setNome("Wagner");
		contato.setEmail("wdmeida@gmail.com");
		contato.setEndereco("R. Teste 8080 fj21");
		contato.setDataNascimento(Calendar.getInstance());
		
		//Grava os dados no banco.
		ContatoDAO dao = new ContatoDAO();
		dao.adicionarContato(contato);
		
		System.out.println("Gravado!");
		
		dao.encerrarConexao();
	}//main

}
