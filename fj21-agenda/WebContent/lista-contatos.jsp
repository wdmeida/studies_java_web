<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%--Importa a taglib --%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
    <%--Importa a taglib display tag para exibição de tabelas. --%>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Contatos</title>
</head>
<body>
	<%--Importa o uma página com o cabeçalho da página --%>
	<c:import url="cabecalho.jsp"/>	
	
	<%--Cria o DAO utilizando a jsp:useBean 
		A sintaxe é:
		id = nome a ser utilizado
		class = nome completamente qualificado da classe.
	
	
	<jsp:useBean id="dao" class="br.com.caelum.jdbc.dao.ContatoDAO"/>
	
	--%>
	<%-- Tabela exibida com forEach --%>
	<h2>Tabela exibida com forEach</h2>
	<table>
		<tr>
			<th>Nome</th>
			<th>Endereço</th>
			<th>Email</th>
			<th>Data de Nascimento</th>
		</tr>
		<!-- Percorre os dados mostrando as linhas da tabela através do forEach -->
		<c:forEach var="contato" items="${requestScope.contatos }" varStatus="id">
			<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }">
				<td>${contato.nome }</td>
				<td>
					<%-- Utiliza o choose para verificar se o email está preenchido. --%>
					<c:choose>
						<%-- not empty antes do valor serve para verificar se este é vazio. --%>
						<c:when test="${not empty contato.email }">
							<a href="mailto:${contato.email }">${contato.email }</a>
						</c:when>
						<c:otherwise>
							Email não cadastrado.
						</c:otherwise>
					</c:choose>
				
				</td>
				<td>${contato.endereco }</td>
				
				<%--Imprme a data formatada utilizando o formateDate da taglib fmt --%>
				<td><fmt:formatDate value="${contato.dataNascimento.time }" pattern="dd/MM/yyyy"/> </td>
				<td><a href="mvc?logica=RemoverContatoLogica&id=${contato.id }">Remover</a>
			</tr>
		</c:forEach>
		
	</table>
	
	<%-- Exibe a tabela utilizando displaytag --%>
	
	<h2>Tabela exibida com display tag</h2>
	<display:table name="${requestScope.contatos }" >
		<display:column property="nome" title="Nome"/>
		<display:column property="email" title="Email"/>
		<display:column property="endereco" title="Endereço"/>
		<display:column property="dataNascimento.time" format="{0,date,dd/MM/yyyy}" title="Data de Nascimento"/>	
	</display:table>
</body>
</html>