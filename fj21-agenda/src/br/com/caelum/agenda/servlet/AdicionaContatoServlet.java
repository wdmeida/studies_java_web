package br.com.caelum.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.model.Contato;

//Cria a classe servlet responsável por receber as informações para adicionar no banco de dados.
//Utiliza a notação abaixo para registrar a servlet, sem ser necessário realizar a declaração no arquivo web.xml.
@WebServlet("/adicionaContato")
public class AdicionaContatoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Cria o objeto e busca o writer
		PrintWriter out = response.getWriter();
	
		//Busca os parâmetros na requisição (request).
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String dataNascimento = request.getParameter("dataNascimento");
		Calendar data = null;
		
		//Converte a data.
		try {
			//Cria um objeto Date e converte a data em string para o formato dd/MM/yyyy. Caso a coversão não seja realizada, dispará uma exceção e encerra o método.
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento);
			
			data = Calendar.getInstance();
			data.setTime(date);
		} catch (java.text.ParseException e) {
			out.println("Erro de conversão da data.");
			return;
		}
		
		//Cria um objeto contato e salva os atributos nele.
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(data);
		
		//Cria um objeto ContatoDAO para realizar a persistência dos dados.
		ContatoDAO dao = new ContatoDAO();
		dao.adicionarContato(contato);
		
		request.getRequestDispatcher("/contato-adicionado.jsp").forward(request, response);
	}
}//class AdicionaContatoServlet
