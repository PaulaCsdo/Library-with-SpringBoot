<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
</head>
<body>
<h1>Menú</h1>
<p>Usuario: ${usuario.nombre}</p> <p>${usuario.apellido}</p>
<table border="1">
	<tr>
		<sec:authorize access="hasAuthority('ADMON')">
		<th><a href="/admon/buscarTemas">Buscar por tema</th>
		<th><a href="/admon/buscarTitulos">Buscar por título</th>
		</sec:authorize>
		<sec:authorize access="hasAuthority('CLIENTE')">
		<th><a href="/cliente/buscarTemas">Buscar por tema</th>
		<th><a href="/cliente/buscarTitulos">Buscar por título</th>
		<th><a href="/cliente/misDatos">Mis datos</th>
		<th><a href="/cliente/verCarrito">Ver carrito</th>
		</sec:authorize>
		<th><a href="/logout">Cerrar sesión</th>
	</tr>
</table>
</br>

<h3>Lista de libros nuevos</h3>
<table border="1">
		<tr>
			<th>Título</th>
			<th>Autor</th>
			<th>Precio</th>
			<th>Detalles</th>
			
		</tr>
		<c:forEach var="ele" items="${listaNovedades}">
		<tr>
			<td>${ele.titulo}</td>
			<td>${ele.autor}</td>
			<td>${ele.precioUnitario}</td>
			<sec:authorize access="hasAuthority('ADMON')">
			<td><a href="/admon/verDetalle/${ele.isbn}">Detalle</td>
			<td><a href="/admon/editar/${ele.isbn}">Editar</td>
			<td><a href="/admon/eliminar/${ele.isbn}">Eliminar</td>
			</sec:authorize>
			<sec:authorize access="hasAuthority('CLIENTE')">
			<td><a href="/cliente/verDetalle/${ele.isbn}">Detalle</td>
			<td><a href="/cliente/añadirCarrito/${ele.isbn}">Añadir</td>
			</sec:authorize>
		</tr>
		</c:forEach>
</table>
</br>
<p>${mensaje}</p>
</br>

<sec:authorize access="hasAuthority('ADMON')">
<h3>Lista de usuarios</h3>
<table border="1">
		<tr>
			<th>Nombre de usuario</th>
			<th>Nombre</th>
			<th>Apellidos</th>
			<th>Email</th>
			<th>Dirección</th>
		</tr>
		<c:forEach var="ele" items="${listaUsuarios}">
		<tr>
			<td>${ele.username}</td>
			<td>${ele.nombre}</td>
			<td>${ele.apellido}</td>
			<td>${ele.email}</td>
			<td>${ele.direccion}</td>
		</tr>
		</c:forEach>
</table>
</br>
<h3>Lista de perfiles</h3>
<table border="1">
		<tr>
			<th>ID Perfil</th>
			<th>Descripcion</th>
		</tr>
		<c:forEach var="ele" items="${listaPerfiles}">
		<tr>
			<td>${ele.idPerfil}</td>
			<td>${ele.descripcion}</td>
		</tr>
		</c:forEach>
</table>
</br>
<h3>Lista de temas</h3>
<table border="1">
		<tr>
			<th>ID Tema</th>
			<th>Abreviatura</th>
			<th>Descripcion</th>
		</tr>
		<c:forEach var="ele" items="${listaTemas}">
		<tr>
			<td>${ele.idTema}</td>
			<td>${ele.abreviatura}</td>
			<td>${ele.descTema}</td>
		</tr>
		</c:forEach>
</table>
</sec:authorize>
</body>
</html>