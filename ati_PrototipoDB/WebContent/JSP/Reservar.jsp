<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="logicaDeConexion.*" %>
<%@ page import="logicaDeNegocio.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Collections" %>
<!DOCTYPE html>
<html>
<head>
		<title>Agregar Reservación</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="" />
		<meta name="keywords" content="" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<script type="text/javascript" src="../js/main.js"></script>
	</head>
<%
String usuario = "";
try{
	
	usuario = session.getAttribute("usuario").toString();
	
}
catch(Exception e){
	
	
	
} %>
<body class="is-preload">
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
	
	<br>
	<br><br>

	<!-- Heading -->
			<div>
				<h1>Reservar una Sala</h1>
			<h1 class="label">Salas Reservadas</h1>	
			</div>

	<div class="row">
	<div class="table-wrapper col-md-6 col-xs-6">
		<form action="../reservationComplete" method="post" accept-charset="UTF-8">
		
			<table class="table colorATI">
				<tr><td> Sala: </td><td>
				<select name = "room">
					<%
					
						RoomManager roomMng = new RoomManager();
						ReservationManager reservationMg = new ReservationManager();
						
						ArrayList<String> listaFuncion = new ArrayList<String>();
						
						
						listaFuncion.add("showReservated()");
						listaFuncion.add("showReservated2()");
						listaFuncion.add("showReservated3()");
						listaFuncion.add("showReservated4()");
						int contador = 0;
						
						for(JavaBeanRoom javaBean : roomMng.getRoomList()){
					
					%>
					
					<option onclick=<%= listaFuncion.get(contador) %> value=<%= javaBean.getId()%>> <%= javaBean.getNombre() %></option>
					
					<% contador++;} 
					
					%>
				</select>
				
				</tr>
				<tr><td> Email: </td><td><input required  type="text" name="pEmail" value=<%= session.getAttribute("correo") %>></tr>
				<tr><td> Día: </td><td><input required  type="date" name="Date"></tr>
				<tr><td> Hora de Inicio: </td><td><input required  type="time" name="StartTime"></tr>
				<tr><td> Hora de Finalización: </td><td><input required  type="time" name="EndTime"></tr>
				<tr><td>Descripción: </td><td><textarea required  class="description" name="Description"></textarea></tr>
			</table>
		<input class="btn btn-primary" type="submit" value="Reservar">
		</form>
	</div>
	
		<div class="table-wrapper col-md-6 col-xs-6">
		<table id="tabla0" style="display:none">
			
			<thead>
				<tr>
					<th>Fecha</th>
					<th>Hora Inicio</th>
					<th>Hora Finalización</th>
					<th>Descripción</th>
				</tr>
			</thead>
			
			<tbody>
				<%for(JavaBeanReservation javaBean : reservationMg.getReservationList()){ 
					if(javaBean.getRoomId().equals("IdSala001")){
				%>
				<tr>	
					<td><%= javaBean.getDate() %></td>
					<td><%= javaBean.getStartHour() %></td>
					<td><%= javaBean.getEndHour() %></td>
					<td><%= javaBean.getDescription() %></td>
				</tr>
				<%	} 
				}%>
			</tbody>
		
		</table>
		
		<table id="tabla1" style="display:none">
			
			<thead>
				<tr>
					<th>Fecha</th>
					<th>Hora Inicio</th>
					<th>Hora Finalización</th>
					<th>Descripción</th>
				</tr>
			</thead>
			
			<tbody>
				<%for(JavaBeanReservation javaBean : reservationMg.getReservationList()){ 
					if(javaBean.getRoomId().equals("IdSala002")){
				%>
				<tr>	
					<td><%= javaBean.getDate() %></td>
					<td><%= javaBean.getStartHour() %></td>
					<td><%= javaBean.getEndHour() %></td>
					<td><%= javaBean.getDescription() %></td>
				</tr>
				<%	} 
				}%>
			</tbody>
		
		</table>
		
		<table id="tabla2" style="display:none">
			
			<thead>
				<tr>
					<th>Fecha</th>
					<th>Hora Inicio</th>
					<th>Hora Finalización</th>
					<th>Descripción</th>
				</tr>
			</thead>
			
			<tbody>
				<%for(JavaBeanReservation javaBean : reservationMg.getReservationList()){ 
					if(javaBean.getRoomId().equals("IdSala003")){
				%>
				<tr>	
					<td><%= javaBean.getDate() %></td>
					<td><%= javaBean.getStartHour() %></td>
					<td><%= javaBean.getEndHour() %></td>
					<td><%= javaBean.getDescription() %></td>
				</tr>
				<%	} 
				}%>
			</tbody>
		
		</table>
		
		<table id="tabla3" style="display:none">
			
			<thead>
				<tr>
					<th>Fecha</th>
					<th>Hora Inicio</th>
					<th>Hora Finalización</th>
					<th>Descripción</th>
				</tr>
			</thead>
			
			<tbody>
				<%for(JavaBeanReservation javaBean : reservationMg.getReservationList()){ 
					if(javaBean.getRoomId().equals("IdSala004")){
				%>
				<tr>	
					<td><%= javaBean.getDate() %></td>
					<td><%= javaBean.getStartHour() %></td>
					<td><%= javaBean.getEndHour() %></td>
					<td><%= javaBean.getDescription() %></td>
				</tr>
				<%	} 
				}%>
			</tbody>
		
		</table>
		</div>
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