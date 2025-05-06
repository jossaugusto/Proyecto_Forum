<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Foro Académico - Inicio de sesión</title>
    <!-- En el <head> -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow rounded-4 border-0">
                <div class="card-body p-4">
                    <h2 class="card-title text-center mb-4">Bienvenido al Foro</h2>
                    <p class="text-center">Por favor, inicie sesión para continuar o cree una cuenta.</p>

                    <form action="${pageContext.request.contextPath}/Auth_S" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label">Correo electrónico</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary" name="type" value="login">Iniciar sesión</button>
                        </div>
                    </form>

                    <div class="mt-3 text-center">
                        <p>También puedes: <a href="${pageContext.request.contextPath}/InitialConfi_S" class="text-decoration-none">Ingresar como invitado</a></p>
                    </div>

                    <c:if test="${not empty message}">
                        <div class="alert alert-danger mt-3 text-center" role="alert">
                            ${message}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>


<%-- <jsp:include page="deletedAccountModal.jsp" /> --%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<c:if test="${deletedAccount == true}">
    <script>
        const modal = new bootstrap.Modal(document.getElementById('deletedAccountModal'));
        modal.show();
    </script>
</c:if>

</body>
</html>
