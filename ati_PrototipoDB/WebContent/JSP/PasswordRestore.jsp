<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="logicaDeNegocio.*" %>
<!DOCTYPE html>
<html>
<%
String usuario = "";
try{
	
	usuario = session.getAttribute("usuario").toString();
	
}
catch(Exception e){
	
	
	
} %>
<head>
		<title>Cambio de Contraseña</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<meta name="description" content="" />
		<meta name="keywords" content="" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<link rel="stylesheet" type="text/css" href="../css/estilos.css">
	</head>
<body class="is-preload">

	<!-- Header -->
			<header>
		<div class="container-fluid">
			<a href="../index.jsp"><img src="../imagenes/atiNombreCarrera.png" alt="Administración de Tecnologías de Información" title="Logotipo" width="150" height="150"></a>
			<h1>Sistema para Reservación de Salas - Área Académica de ati</h1>

				<div class="row">
					<div class="col-md-4 colorATI border border-primary colocacion2"><a  href="Salas.jsp">Consultar Salas</a></div>
				<%if(!usuario.equals("")){ %>
					<div class="col-md-4 colorATI border border-primary colocacion2"><a href="Reservar.jsp">Reservar Salas</a></div>
				<%} else{%>
					
					<div class="col-md-4 colorATI border border-primary colocacion2"><a href="FormularioLogIn.jsp">Reservar Salas</a></div>
					
				<%} %>
					<div class="col-md-4 colorATI border border-primary colocacion2"><a href="Reservaciones.jsp">Información sobre disponibilidad de Salas</a></div>
				
				</div>

		</div>
	</header>

	<br><br><br><br>
	
	<h2>Cambiar Contraseña</h2>
	
	<br><br>
	
	<form action="../passwordManage" method="post" accept-charset="UTF-8">
		
		<table class="table  colorATI">
				<thead>
				<tbody>
				<tr>
					<td>Nombre: </td>
					<td><input type="text" name="name" required /></td>
				</tr>
				<tr>
					<td>Apellido: </td>
					<td><input type="text" name="lastName" required /></td>
				</tr>
				<tr>
					<td>Correo Electrónico: </td>
					<td><input type="email" name="user" required /></td>
				</tr>
				<tr>
					<td>Nueva Contraseña: </td>
					<td><input type="text" name="password" required /></td>
				</tr>
				<tr>
					<td>Repita la Contraseña: </td>
					<td><input type="text" name="password2" required /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Cambiar Contraseña"/></td>
				</tr>
				</tbody>
		</table>
	
	</form>
	
	
	<br><br>
	
	
	
		<form action="../index.jsp" method="get">
		  <button class="btn btn-secondary">Regresar</button>
		</form>
		
		<br><br>
			
		<footer>
	    <ul>
	    <%
				if(usuario.equals("")){
			%><li><a href="FormularioLogIn.jsp">Iniciar Sesión</a></li>
			<li>© 2018 ati</li>
			<% }else{ %>
			<li>
				<form action="../sessionManagerLogOut" method="post" class="gestionarSesion">
					<input class="btn btn-secondary " type="submit" value="Cerrar Sesión"/>
				</form>
			</li>
			<li>Bienvenido <%= session.getAttribute("usuario") %></li>
			<%} %>
	        <li>Área Académica de Administración de Tecnologías de Información</li>
	        <!--  <li><a href="mailto:asistenciaCoordinacionati@gmail.com" title="Solicitar Asistencia Sistema de Reservación ati">
			Notificar error mediante correo</a></li>-->
	    </ul>
	</footer>
			
</body>
</html>