<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Importa a taglib criada para o campo data --%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum" %>
<html>

	<%--Importa o jquery e os arquivos css. --%>
	<link href="css/jquery.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	
	<body>
	<c:import url="cabecalho.jsp"/>
		
		<form action="mvc" method="post">
			<caelum:campoTexto label="Nome:" name="nome" id="nome"/><br/>
			<caelum:campoTexto name="email" id="email" label="Email:"/><br/>
			<caelum:campoTexto label="Endereço" name="endereco" id="endereco"/><br/>
			Data Nascimento: <caelum:campoData id="dataNascimento"/><br/>
			<input type="hidden" name="logica" value="AdicionarContatoLogica">
			<input type="submit" value="Gravar"/>
		</form>
	</body>
</html>