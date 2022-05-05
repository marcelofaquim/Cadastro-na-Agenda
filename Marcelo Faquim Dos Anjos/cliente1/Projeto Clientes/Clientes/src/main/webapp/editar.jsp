<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="icon" href="imagens/user.png">
<title>Agenda de Contatos</title>
</head>
<body>
	<div class="container">
	<img alt="" src="imagens/agenda.png">
	<h1 class="home-title">Editar Contatos</h1>
	<form name="frmContato" action="update" >
<table class="camp">

			<tr>
			<td><input type="text" name="idcon" id="caixa03" readonly
			value="<%out.print(request.getAttribute("idcon"));%>"></td>
			
			</tr>
			
			<td> <input type="text" name="nome" class="Caixa1"
			value="<%out.print(request.getAttribute("nome"));%>"></td>
			
			<tr>
			
			<td><input type="text" name="fone" class="Caixa2"
			value="<%out.print(request.getAttribute("fone"));%>">
			</td>
			
			</tr>
			
			<tr>
			
			<td><input type="text" name="email" class="Caixa1"
			value="<%out.print(request.getAttribute("email"));%>">
			</td>
			
			</tr>
			
			<tr>
			
			<td><input type="text" name="endereco" class="Caixa1"
			value="<%out.print(request.getAttribute("endereco"));%>">
			</td>
			
			</tr>
			
			
			<tr>
			
			<td><input type="text" name="bairro" class="Caixa1"
			value="<%out.print(request.getAttribute("email"));%>">
			</td>
			
			</tr>
			
			
		</table>
		
		<input type="submit" value="Editar" class="Botao1" onclick="validar()">
	</form>
	
	<script src="scripts/Validador.js"></script>
		
	</div>
</body>
</html>
			