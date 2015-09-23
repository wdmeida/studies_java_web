<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%-- Para realizar imports de outras classes, são utilizadas diretivas de controle. Sua sintaxe é:
   <%@ page import="nome_pacote_e_classe" %> --%>
   <%@ page import="br.com.caelum.jdbc.dao.ContatoDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%-- A sintaxe abaixo é chamada de scriptlet e server para utilizar código java dentro do jsp. --%>
	<%String mensagem = "Olá mundo!"; %>
	
	<%-- Para visualizar o conteúdo de uma váriável java basta apensar imprimir seu valor da seguinte forma. --%>
	<%=mensagem %><br/>

</body>
</html>