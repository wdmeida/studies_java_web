package br.com.caelum.agenda.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class FiltroTempoExecucao implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//Lembrar que o que está antes do método doFilter é executado antes de enviar a requisição e o que está depois na volta.
		
		//Pega o tempo inicial de requisição;
		long tempoInicial = System.currentTimeMillis();
		
		//Envia a requisição.
		chain.doFilter(request, response);
		
		long tempoFinal = System.currentTimeMillis();
		
		String uri = ((HttpServletRequest) request).getRequestURI();
		
		String parametros = ((HttpServletRequest)request).getParameter("logica");
		
		System.out.println("Tempo da requisicao de " + uri
				+ "?logica="
				+ parametros + " demorou (ms): "
				+ (tempoFinal - tempoInicial));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
