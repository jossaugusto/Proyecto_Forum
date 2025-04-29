<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Foro - Registro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        // Función para validar la coincidencia de las contraseñas
        function validarContraseñas() {
            var pass1 = document.getElementById("password").value;
            var pass2 = document.getElementById("confirm_password").value;
            if (pass1 !== pass2) {
                alert("Las contraseñas no coinciden.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Registro</h1>
        <form action="Auth_S" method="post" class="col-md-6 mx-auto">
            <div class="mb-3">
                <label for="name" class="form-label">Nombre:</label>
                <input type="text" id="name" name="name" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="apellido" class="form-label">Apellido:</label>
                <input type="text" id="apellido" name="lastname" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Correo electrónico:</label>
                <input type="email" id="email" name="email" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="confirm_password" class="form-label">Confirmar Contraseña:</label>
                <input type="password" id="confirm_password" name="confirm_password" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="tipo_usuario" class="form-label">Rol:</label>
                <select name="tipo_usuario" class="form-select">
                    <option value="estudiante">Estudiante</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            <button type="submit" name="type" value="register" class="btn btn-primary w-100">Registrarse</button>
        </form>
        <p class="mt-3 text-center">
            ¿Ya tienes una cuenta? <a href="login.jsp">Inicia sesión aquí</a>
        </p>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
