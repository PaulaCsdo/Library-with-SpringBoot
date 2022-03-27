<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro</title>
</head>
<body>
<h2>Registro de usuario</h2>
<form action="/registro" method="post">
 <fieldset>
 
	 
	 <p>Nombre de usuario <input  type="text" name="username"/></p>
	  </br>
	  <p>Nombre <input  type="text" name="nombre"/></p>
	  </br>
	  <p>Apellidos <input  type="text" name="apellido"/></p>
	  </br>
	  <p>Direccion <input  type="text" name="direccion"/></p>
	  </br>
	  <p>Email <input  type="text" name="email"/></p>
	 </br>
	  <p>Contraseña <input  type="text" name="password"/></p>
	   <br/>
	   <input type="submit" value="Registrarse" />
       <br/> 
        <br/>
       <input type="reset" value="Limpiar formulario" />
       <br/>
        <br/>
        <p>${mensaje}</p>
 </fieldset>
</form>
<p><a href="/login">Volver a inicio de sesión</a></p>
</body>
</html>