<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Panel de Administraci�n" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">¡Bienvenido, ${sessionScope.currentUser.nombre}!</h2>

            <div class="row mb-4">
                <!-- Estad�sticas -->
                <div class="col-md-4">
                    <div class="card text-white bg-primary mb-3">
                        <div class="card-header">Usuarios Registrados</div>
                        <div class="card-body">
                            <h5 class="card-title">${cantidadUsuarios}</h5>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card text-white bg-success mb-3">
                        <div class="card-header">Temas Publicados</div>
                        <div class="card-body">
                            <h5 class="card-title">${cantidadTemas}</h5>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card text-white bg-warning mb-3">
                        <div class="card-header">Respuestas Totales</div>
                        <div class="card-body">
                            <h5 class="card-title">${cantidadRespuestas}</h5>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Opciones de Administraci�n -->
            <div class="row">
                <div class="col-md-4 mb-3">
                    <a href="${pageContext.request.contextPath}/Admin_S?action=ManageUsers&order=ASC" class="btn btn-outline-primary w-100">
                        <i class="bi bi-people"></i> Gestionar Usuarios
                    </a>
                </div>
                <div class="col-md-4 mb-3">
                    <a href="${pageContext.request.contextPath}/Admin_S?action=ManageTopics&order=ASC" class="btn btn-outline-success w-100">
                        <i class="bi bi-tags"></i> Gestionar Temas
                    </a>
                </div>
                <div class="col-md-4 mb-3">
                    <a href="${pageContext.request.contextPath}/Admin_S?action=ManageCategories" class="btn btn-outline-warning w-100">
                        <i class="bi bi-journal-text"></i> Gestionar Categorías
                    </a>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
