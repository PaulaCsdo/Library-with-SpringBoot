<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar titulo</title>
</head>
<body>
<h2>Buscador de títulos</h2>

<form action="/cliente/buscarTitulos" method="post">
	<p>Introduce el título o palabra clave: <input type="text" name="titulo"></p>
	</br>
	<input type="submit" value="Buscar"/>
	</br>
	<input type="reset" value="Borrar"/>
	</br>
</form>

<table border="1">
		<tr>
			<th>Título</th>
			<th>Autor</th>
			<th>Precio</th>
			<th>Detalles</th>
			<th>Añadir al carrito</th>
		</tr>
		<c:forEach var="ele" items="${listaTitulo}">
		<tr>
			<td>${ele.titulo}</td>
			<td>${ele.autor}</td>
			<td>${ele.precioUnitario}</td>
			<td><a href="/cliente/verDetalle/${ele.isbn}">Detalle</td>
			<td><a href="/cliente/añadirCarrito/${ele.isbn}">Añadir</td>
		</tr>
		</c:forEach>
</table>
</br>
<sec:authorize access="hasAuthority('ADMON')">
<p><a href="/admon/inicio">Volver</a></p>
</sec:authorize>
<sec:authorize access="hasAuthority('CLIENTE')">
<p><a href="/cliente/inicio">Volver</a></p>
</sec:authorize>
</body>
</html>