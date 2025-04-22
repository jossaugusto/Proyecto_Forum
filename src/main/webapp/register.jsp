<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
</head>
<body>
	<div>
		<h1>Registro</h1>
		<form action="Auth_S" method="post">

			<div>
				<label for="name">Nombre:</label> <input type="text" id="name"
					name="name" required>
			</div>
			<div>
				<label for="name">Apellido:</label> <input type="text" id="apellido"
					name="lastname" required>
			</div>
			<div>
				<label for="email">Correo electrónico:</label> <input type="email"
					id="email" name="email" required>
			</div>
			<div>
				<label for="password">Contraseña:</label> <input type="password"
					id="password" name="password" required>
			</div>
			<!-- Reaalizar la validacion de la contraseña -->

			<div class="col-md-6">
				<label>Tipo de usuario:</label> <select name="tipo_usuario">
					<option value="estudiante">Estudiante</option>
					<option value="profesor">Profesor</option>
					<option value="admin">Admin</option>
				</select>
			</div>

			<button type="submit" name="type" value="register">Registrarse</button>
		</form>
		<p>
			¿Ya tienes una cuenta? <a href="login.jsp">Inicia sesión aquí</a>
		</p>
	</div>
</body>
</html>