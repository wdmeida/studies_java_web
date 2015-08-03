package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Cria uma classe que extende HttpServlet para ser usada como exemplo de cria��o
 * da primeira servlet. (P�gina din�mica)
 */

public class OiMundo extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Sobrescreve o m�todo service da classe HttpServlet. Ele � respons�vel por tratar as requisi��es
	 * e respostas feitas ao servidor pelo usu�rio.
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Cria um objeto para enviar a resposta a requisi��o feita, no caso uma p�gina html b�sica.
		PrintWriter out = response.getWriter();
		
		//Cria a resposta a solicita��o.
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
