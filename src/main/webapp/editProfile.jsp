<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Editar Perfil" />
</jsp:include>

<div class="container mt-5">
    <div class="card">
        <div class="card-header text-center">
            <h2>Editar Perfil</h2>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/User_S" method="post">

                <div class="mb-3">
                    <label for="name" class="form-label">Nombre:</label>
                    <input type="text" id="name" name="name" class="form-control" value="${sessionScope.currentUser.nombre}" required>
                </div>

                <div class="mb-3">
                    <label for="lastName" class="form-label">Apellido:</label>
                    <input type="text" id="lastName" name="lastName" class="form-control" value="${sessionScope.currentUser.apellido}" required>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Correo Electrónico:</label>
                    <input type="email" id="email" name="email" class="form-control" value="${sessionScope.currentUser.email}" required>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Nueva Contraseña:</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Dejar en blanco si no deseas cambiarla">
                </div>

                <div class="mt-4 text-center">
                    <button type="submit" name="action" value="UpdateProfile" class="btn btn-primary">Guardar Cambios</button>
                    <a href="${pageContext.request.contextPath}/profile.jsp" class="btn btn-secondary ms-2">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
