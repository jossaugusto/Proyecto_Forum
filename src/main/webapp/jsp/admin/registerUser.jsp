<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Foro - Registro</title>
   	<!-- Estilos personalizados -->
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header bg-success text-white">
                    <h3 class="text-center font-weight-light my-4">Registro</h3>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/Auth_S" method="post" onsubmit="return validarFormulario()">
                        <div class="mb-3">
                            <label for="name" class="form-label">Nombre:</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label for="apellido" class="form-label">Apellido:</label>
                            <input type="text" class="form-control" id="apellido" name="lastname" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Correo electrónico:</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="mb-3">
                            <label for="confirm_password" class="form-label">Confirmar Contraseña:</label>
                            <input type="password" class="form-control" id="confirm_password" name="confirm_password" required>
                        </div>
                        <div class="mb-3">
                            <label for="tipo_usuario" class="form-label">Rol:</label>
                            <select class="form-select" name="tipo_usuario">
                                <option value="admin">Admin</option>
                                <option value="profesor">Profesor</option>
                                <option value="estudiante">Estudiante</option>
                            </select>
                        </div>
                        <div class="d-grid">
                            <button type="submit" name="type" value="register" class="btn btn-success btn-block">Registrarse</button>
                        </div>
                    </form>
                    <div class="mt-3 text-center">
                        <p class="mb-0">
                            <a href="javaScript:history.back()" class="text-info">Cancelar</a>
                        </p>
                    </div>
                    <c:if test="${not empty message}">
                        <div class="alert alert-danger mt-3" role="alert">
                            ${message}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function validarFormulario() {
        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirm_password").value;

        // Validar formato de correo
        var correoRegex = /^[a-zA-Z0-9._%+-]+@forum\.pe$/;
        if (!correoRegex.test(email)) {
            alert("El correo debe tener el formato usuario@forum.pe");
            return false;
        }

        // Validar longitud mínima de la contraseña
        if (password.length < 8) {
            alert("La contraseña debe tener al menos 8 caracteres.");
            return false;
        }

        // Validar coincidencia de contraseñas
        if (password !== confirmPassword) {
            alert("Las contraseñas no coinciden.");
            return false;
        }

        return true;
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
