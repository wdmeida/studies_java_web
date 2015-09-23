<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%--Define os atributos utilizados no campo data --%>
<%@ attribute name="id" required="true" %>

<input id="${id }" name="${id }"/>
<script>
	$("#${id}").datepicker({dateFormat: 'dd/mm/yy'});
</script>
