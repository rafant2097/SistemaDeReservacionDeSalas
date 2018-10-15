<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="logicaDeConexion.*" %>
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
		<title>Información sobre Salas</title>
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

	<%
		RoomManager roomMngr = new RoomManager();
	%>
		<h3>Información de las Salas Existentes</h3>
						<h4>Salas ati</h4>
						<div class="table-wrapper">
							<table class="table  colorATI">
								<thead>
									<tr>
										<th>Número de Sala</th>
										<th>Nombre</th>
										<th>Descripción</th>
										<th>Capacidad</th>
										<th>Departamento</th>
									</tr>
								</thead>
								<tbody>
								<%for(JavaBeanRoom room: roomMngr.getRoomList()) {%>
									<tr>
										<td><%= room.getNumSala() %></td>
										<td><%= room.getNombre() %></td>
										<td><%= room.getDescripcion() %></td>
										<td><%= room.getCapacidad() %></td>
										<td><%= room.getDepartamento() %></td>
									</tr>
									<%} %>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="2"></td>
									</tr>
								</tfoot>
							</table>
						</div>
	<br>
	<form action="../index.jsp" method="get">
		  <button class="btn btn-secondary">Regresar</button>
		</form>
	<br><br><br>
		
		
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
	    <% DB2Management.deleteConnection(); %>
	</footer>
			
</body>
</html>