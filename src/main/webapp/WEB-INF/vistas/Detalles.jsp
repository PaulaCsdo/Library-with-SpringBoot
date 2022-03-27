<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalles</title>
</head>
<body>
<h2>Detalles del libro seleccionado</h2>

<table border="1">
		<tr>
			<th>Titulo</th>
			<th>Autor</th>
			<th>Número de páginas</th>
			<th>Tema</th>
			<th>Precio</th>
		</tr>
		<tr>
			<td>${libro.titulo}</td>
			<td>${libro.autor}</td>
			<td>${libro.paginas}</td>
			<td>${libro.tema.descTema}</td>
			<td>${libro.precioUnitario}</td>
		</tr>
</table>

<sec:authorize access="hasAuthority('ADMON')">
<p><a href="/admon/inicio">Volver</a></p>
</sec:authorize>
<sec:authorize access="hasAuthority('CLIENTE')">
<p><a href="/cliente/añadir/${libro.isbn}">Añadir al carrito</a></p>
</br>
<p><a href="/cliente/inicio">Volver</a></p>
</sec:authorize>
</body>
</html>