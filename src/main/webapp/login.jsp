<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forum - Inicio de sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Bienvenido al Foro</h1>
        <p class="text-center">Por favor, inicie sesión para continuar o cree una cuenta.</p>

        <div class="col-md-6 mx-auto">
            <form action="Auth_S" method="post" class="border p-4 rounded shadow-sm">
                <div class="mb-3">
                    <label for="email" class="form-label">Correo electrónico:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                <button type="submit" name="type" value="login" class="btn btn-primary w-100">Iniciar sesión</button>
            </form>

            <p class="mt-3 text-center">
                ¿No tienes una cuenta? <a href="register.jsp">Regístrate aquí</a>
            </p>
            <p class="text-center">
                También puedes: <a href="InitialConfi_S">Ingresar como invitado</a>
            </p>

            <div class="mt-3">
                <c:if test="${not empty requestScope.message}">
                    <div class="alert alert-danger text-center">
                        ${requestScope.message}
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
