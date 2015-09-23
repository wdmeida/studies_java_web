package br.com.caelum.mvc.logica;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.model.Contato;

public class RemoverContatoLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		
		Contato contato = new Contato();
		contato.setId(id);
		
		//Obtém a conexão com o banco de dados.
		Connection conn = (Connection) request.getAttribute("conexao");
		
		new ContatoDAO(conn).deletarContato(contato);
		
		System.out.println("Excluindo contato...");
		
		return "mvc?logica=ListarContatosLogica";
		
	}
}
