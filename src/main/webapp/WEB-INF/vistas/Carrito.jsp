<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrito</title>
</head>
<body>
<h2>Carrito</h2>
<p> Usuario: ${usuario.nombre&&usuario.apellido}</p>

<h3>Lista de compra</h3>
<table border="1">
		<tr>
			<th>Título</th>
			<th>Precio</th>
			<th>Eliminar artículo</th>
		</tr>
		<c:forEach var="ele" items="${listarCarrito}">
		<tr>
			<td>${ele.titulo}</td>
			<td>${ele.precioUnitario}</td>
			<td><a href="/cliente/eliminarArticulo/${ele.isbn}">Eliminar</td>
		</tr>
		</c:forEach>
</table>
<p><a href="/cliente/comprar">Realizar compra</a></p>
</br>
<p> ${mensaje}</p>
<p><a href="/cliente/inicio">Volver</a></p>
</body>
</html>