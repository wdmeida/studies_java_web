package br.com.caelum.mvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.mvc.logica.Logica;

@WebServlet("/mvc")
public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtém o nome da lógica a ser executada e registra o nome da classe.
		String nome = request.getParameter("logica");
		
		String nomeClasse = "br.com.caelum.mvc.logica." + nome;
		
		try {
			Class<?> classe = Class.forName(nomeClasse);
			
			Logica logica = (Logica) classe.newInstance();
			String url = logica.executa(request, response);
			
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
