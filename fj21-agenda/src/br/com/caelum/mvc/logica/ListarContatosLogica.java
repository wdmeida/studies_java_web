package br.com.caelum.mvc.logica;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.model.Contato;

public class ListarContatosLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//Obtém a conexão com o banco de dados.
		Connection conn = (Connection) request.getAttribute("conexao");
		
		//Obtém a lista com os contatos do banco de dados.
		List<Contato> contatos = new ContatoDAO(conn).getLista();
		
		request.setAttribute("contatos", contatos);
		
		return "lista-contatos.jsp";
	}
}
