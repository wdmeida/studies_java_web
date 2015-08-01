package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Cria uma classe que extende HttpServlet para ser usada como exemplo de criação
 * da primeira servlet. (Página dinâmica)
 */

public class OiMundo extends HttpServlet {
	
	/*
	 * Sobrescreve o método service da classe HttpServlet. Ele é responsável por tratar as requisições
	 * e respostas feitas ao servidor pelo usuário.
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Cria um objeto para enviar a resposta a requisição feita, no caso uma página html básica.
		PrintWriter out = response.getWriter();
		
		//Cria a resposta a solicitação.
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Primeira Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Oi mundo Servlet!</h1>");
		out.println("</body>");
		out.println("</html>");	
	}//service()
	
}//class OiMundo
