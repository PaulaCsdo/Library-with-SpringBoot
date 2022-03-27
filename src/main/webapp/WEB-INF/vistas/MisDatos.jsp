<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis datos</title>
</head>
<body>
<h2>Mis datos</h2>
<table border="1">
		<tr>
			<th>Nombre de usuario</th>
			<th>Nombre</th>
			<th>Apellidos</th>
			<th>Email</th>
			<th>Dirección</th>
		</tr>
		<tr>
			<td>${usuario.username}</td>
			<td>${usuario.nombre}</td>
			<td>${usuario.apellido}</td>
			<td>${usuario.email}</td>
			<td>${usuario.direccion}</td>
		</tr>
</table>
<p><a href="/cliente/inicio">Volver</a></p>
</body>
</html>