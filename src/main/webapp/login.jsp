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
		<h1>Bienvenido a Forum</h1>
		<p>Por favor, inicie sesión para continuar o cree una cuenta</p>
		<div>
			<form action="Auth_S" method="post">
				<div>
					<label for="email">Correo electrónico:</label> <input type="email"
						id="email" name="email" required>
				</div>
				<div>
					<label for="password">Contraseña:</label> <input type="password"
						id="password" name="password" required>
				</div>
				<button type="submit" name="type" value="login">Iniciar
					sesión</button>
			</form>
			<p>
				¿No tienes una cuenta? <a href="register.jsp">Regístrate aquí</a>
			</p>
		</div>
	</div>
	<div>
		<span style="color: red;">${requestScope.message}</span>
	</div>
</body>
</html>