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
		<title>Informaci�n sobre Salas Reservadas</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
		</head>
		<body class="is-preload">

	<!-- Header -->
			<header>
		<div class="container-fluid">
			<a href="../index.jsp"><img src="../imagenes/atiNombreCarrera.png" alt="Administraci�n de Tecnolog�as de Informaci�n" title="Logotipo" width="150" height="150"></a>
			<h1>Sistema para Reservaci�n de Salas - �rea Acad�mica de ati</h1>

				<div class="row">
					<div class="col-md-4 colorATI border border-primary colocacion2"><a  href="Salas.jsp">Consultar Salas</a></div>
				<%if(!usuario.equals("")){ %>
					<div class="col-md-4 colorATI border border-primary colocacion2"><a href="Reservar.jsp">Reservar Salas</a></div>
				<%} else{%>
					
					<div class="col-md-4 colorATI border border-primary colocacion2"><a href="FormularioLogIn.jsp">Reservar Salas</a></div>
					
				<%} %>
					<div class="col-md-4 colorATI border border-primary colocacion2"><a href="Reservaciones.jsp">Informaci�n sobre disponibilidad de Salas</a></div>
				
				</div>

		</div>
	</header>

	<br><br><br><br>

			<h3>Informaci�n de las Salas Reservadas</h3>
						
			<div>
						<br>	<table class="table  colorATI">
								<thead>
									<tr>
										<th>Sala</th>
										<th>Correo Solicitante</th>
										<th>Fecha</th>
										<th>Hora de Inicio</th>
										<th>Hora de Finalizaci�n</th>
										<th>Descripci�n</th>
									</tr>
								</thead>
								<tbody>
	<%
		
		ReservationManager reservationManager = new ReservationManager();
		
		
		for(JavaBeanReservation reservation : reservationManager.getReservationList()){%>
								<tr>
										<td><%= reservationManager.getRoomInformation(reservation.getRoomId(),new RoomManager()).getNombre() %></td>
										<td><%= reservation.getConsumerEmail() %></td>
										<td><%= reservation.getDate() %></td>
										<td><%= reservation.getStartHour() %></td>
										<td><%= reservation.getEndHour() %></td>
										<td><%= reservation.getDescription() %></td>
										<%} 
%>
									</tr>
								
								</tbody></table></div>
			
	<br>
		
		<form action="../index.jsp" method="get">
		  <button class="btn btn-secondary">Regresar</button>
		</form>

	<br><br>

	<footer>
	    <ul>
	    <%if(usuario.equals("")){
			%><li><a href="FormularioLogIn.jsp">Iniciar Sesi�n</a></li>
			<li>� 2018 ati</li>
			<% }else{ %>
			<li>
				<form action="../sessionManagerLogOut" method="post" class="gestionarSesion">
					<input class="btn btn-secondary " type="submit" value="Cerrar Sesi�n"/>
				</form>
			</li>
			<li>Bienvenido <%= session.getAttribute("usuario") %></li>
			<%} %>
	        <li>�rea Acad�mica de Administraci�n de Tecnolog�as de Informaci�n</li>
	        <!--  <li><a href="mailto:asistenciaCoordinacionati@gmail.com" title="Solicitar Asistencia Sistema de Reservaci�n ati">
			Notificar error mediante correo</a></li>-->
	    </ul>
	    <% DB2Management.deleteConnection(); %>
	</footer>
			
</body>
</html>