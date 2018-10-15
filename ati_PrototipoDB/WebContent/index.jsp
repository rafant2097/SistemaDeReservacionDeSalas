<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<%
String usuario = "";
try{
	
	usuario = session.getAttribute("usuario").toString();
	
}
catch(Exception e){
	
	
	
} %>
<head>
	<title>Sistema para Reservaci�n de Salas - ati</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<meta name="description" content="Sistema para reservaci�n de salas en edificio D7, Administraci�n de Tecnolog�a de Informaci�n" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<header>
		<div class="container-fluid">
			<img src="imagenes/atiNombreCarrera.png" alt="Administraci�n de Tecnolog�as de Informaci�n" title="Logotipo" width="120" height="120">
			<h1>Sistema para Reservaci�n de Salas - �rea Acad�mica de ati</h1>
				<div class="row">
					<div class="col-md-4 colorATI border border-primary colocacion"><a  href="JSP/Salas.jsp">Consultar Salas</a></div>
				<%if(!usuario.equals("")){ %>
					<div class="col-md-4 colorATI border border-primary colocacion"><a href="JSP/Reservar.jsp">Reservar Salas</a></div>
				<%} else{%>
					
					<div class="col-md-4 colorATI border border-primary colocacion"><a href="JSP/FormularioLogIn.jsp">Reservar Salas</a></div>
					
				<%} %>
					<div class="col-md-4 colorATI border border-primary colocacion"><a href="JSP/Reservaciones.jsp">Informaci�n sobre disponibilidad de Salas</a></div>
				
				</div>

		</div>
	</header>

	<br><br><br>

	<div class="container-fluid colorATI posicionar">
		<section class="main row">

			<article class="col-md-7">
				<h2>�Qui�nes somos?</h2>
				<p>ati es una carrera acreditada por el SINAES e impartida en el Instituto Tecnol�gico de Costa Rica en la sede central.
				La carrera, Administraci�n de Tecnolog�as de Informaci�n, busca generar sofisticaci�n e innovaci�n en los negocios 
				mediante la gesti�n de TI</p>
				<a class="linked" target="_blanck" href="http://www.tec-ati.com">Conoce m�s sobre ati</a>
			</article>

			<aside class=col-md-5>
				<h2>Sobre la p�gina:</h2>
				<p>La p�gina web desplegada es una herramienta dise�ada para gestionar el proceso de reservaci�n de salas en el edificio de la carrera Administraci�n de Tecnolog�as de Informaci�n del ITCR en la sede central, edificio D7
				</p>
			</aside>

		</section>

	<br>
	<hr>	

	<h2 class="colorATI">Funcionalidades del Sistema de Reservaci�n:</h2>	

	<br>

	<div class="row" class="colorATI">
		

		<div class="col-md-4 colorATI">
			
			<h3><a  href="JSP/Salas.jsp">Informaci�n por Sala:</a></h3>
			<p>Accede para conocer las caracter�sticas de las salas que el �rea acad�mica tiene a su disposici�n para
			ser reservadas</p>

		</div>

		<div class="col-md-4 colorATI">

			<h3><a href="JSP/Reservaciones.jsp">Informaci�n de las Salas Reservadas:</a></h3>
			<p>Conoce cuales salas ya han sido reservadas y sus respectivas caracter�sticas</p>

		</div>
		<div class="col-md-4 colorATI">
			
			<h3>
			<%if(usuario.equals("")){ %>
			<a href="JSP/FormularioLogIn.jsp">Reservar una Sala:</a>
			<%}else{ %>
				<a href="JSP/Reservar.jsp">Reservar una Sala:</a>
			<%} %>
			</h3>
			<p>Entra y aparta la sala que necesites</p>

		</div>
	</div>	

	</div>

</body>

	<br><br><br>

	<footer>
	    <ul>
	    <%
				if(usuario.equals("")){
			%><li><a href="JSP/FormularioLogIn.jsp">Iniciar Sesi�n</a></li>
			
			<li>� 2018 ati</li>
			
			<% }else{ %>
			<li>
				<form action="sessionManagerLogOut" method="post" class="gestionarSesion">
					<input class="btn btn-secondary " type="submit" value="Cerrar Sesi�n"/>
				</form>
			</li>
			<li>Bienvenido <%= session.getAttribute("usuario") %></li>
			<%} %>
	        <li>�rea Acad�mica de Administraci�n de Tecnolog�as de Informaci�n</li>
	        <!--  <li><a href="mailto:asistenciaCoordinacionati@gmail.com" title="Solicitar Asistencia Sistema de Reservaci�n ati">
			Notificar error mediante correo</a></li>-->
	    </ul>
	</footer>
	
	
	
</html>