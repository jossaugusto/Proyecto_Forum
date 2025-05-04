<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Panel de Administraci�n" />
</jsp:include>

<div class="container mt-4">
    <div class="text-center mb-4">
        <h2 class="fw-bold">¡Bienvenido, ${sessionScope.currentUser.nombre}!</h2>
    </div>

    <div class="row g-3 mb-4 text-center">
        <div class="col-md-4">
            <div class="card shadow-sm border-0 h-100 stat-card">
                <div class="card-body">
                    <i class="bi bi-people-fill fs-2 text-primary"></i>
                    <div class="fw-semibold mt-2">Usuarios Registrados</div>
                    <h5 class="mt-1 text-dark">${cantidadUsuarios}</h5>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card shadow-sm border-0 h-100 stat-card">
                <div class="card-body">
                    <i class="bi bi-chat-square-text fs-2 text-primary"></i>
                    <div class="fw-semibold mt-2">Temas Publicados</div>
                    <h5 class="mt-1 text-dark">${cantidadTemas}</h5>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card shadow-sm border-0 h-100 stat-card">
                <div class="card-body">
                    <i class="bi bi-reply-all-fill fs-2 text-primary"></i>
                    <div class="fw-semibold mt-2">Respuestas Totales</div>
                    <h5 class="mt-1 text-dark">${cantidadRespuestas}</h5>
                </div>
            </div>
        </div>
    </div>

    <div class="row g-3 text-center">
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/Admin_S?action=ManageUsers&order=ASC" class="btn w-100 admin-btn">
                <i class="bi bi-person-gear me-2"></i>Gestionar Usuarios
            </a>
        </div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/Admin_S?action=ManageTopics&order=ASC" class="btn w-100 admin-btn">
                <i class="bi bi-chat-dots me-2"></i>Gestionar Temas
            </a>
        </div>
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/Admin_S?action=ManageCategories" class="btn w-100 admin-btn">
                <i class="bi bi-folder-fill me-2"></i>Gestionar Categorías
            </a>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp" />
