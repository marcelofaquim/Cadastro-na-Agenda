<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="utf-8"%>
<%@ page import ="model.JavaBeans" %>
<%@ page import = "java.util.ArrayList" %>

<% ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)
request.getAttribute("clientes"); %>


<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" type="text/css" href="style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
	<link rel="icon" href="imagens/fone.png">
	<link rel="icon" href="imagens/user.png">
	<title>Agenda de Contatos</title>
</head>
<body>

	<div class="container">
		<img alt="" src="imagens/agenda.png">
		<h1 class="home-title">Agenda de Contatos</h1>
		<a href="novo.html" class="home-button">Novo</a>
	
	
	<table id="tabela">
	<thead>
	<tr>
	<th> Id</th>
	<th> Nome</th>
	<th> Fone</th>
	<th> Email</th>
	</tr>
	</thead>
	<tbody>
	
	<%
	for (int i =0; i < lista.size(); i++) {%>
	<tr>
	 <td><%=lista.get(i).getIdcon() %> </td>
		<td><%=lista.get(i).getNome() %> </td>
		<td><%=lista.get(i).getFone() %> </td>
		<td><%=lista.get(i).getEmail() %> </td>
		<td><a href="select?idicon=<%=lista.get(i).getIdcon() %>" class="Botao1">Editar</a>
			<a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" class="Botao2">Excluir</a></td>
		</tr>
		
			
	
	<%} %>
	
	
	
	</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
	</div>


</body>
</html>