package br.com.caelum.mvc.logica;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.model.Contato;

public class AdicionarContatoLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Obtém os atributos
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String data = request.getParameter("dataNascimento");
		
		Calendar dataNascimento = Calendar.getInstance();
		
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			dataNascimento.setTime(date);
		} catch (ParseException e) {
			System.out.println("Erro conversão de data: " + e);
		}
		
		//Obtém a conexão com o banco de dados.
		Connection conn = (Connection) request.getAttribute("conexao");
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(dataNascimento);
		
		new ContatoDAO(conn).adicionarContato(contato);
		
		return "contato-adicionado.jsp";
	}
}
