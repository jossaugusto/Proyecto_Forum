<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Perfil de Usuario" />
</jsp:include>

<div class="container mt-4">
    <h2>Perfil de Usuario</h2>
    <div class="card shadow-sm border-0">
        <div class="card-body">
            <ul class="list-unstyled">
                <li class="mb-2">
                    <strong>Nombre:</strong> ${sessionScope.currentUser.nombre} ${sessionScope.currentUser.apellido}
                </li>
                <li class="mb-2">
                    <strong>Email:</strong> ${sessionScope.currentUser.email}
                </li>
                <li class="mb-2">
                    <strong>Rol:</strong>
                    <c:choose>
                        <c:when test="${sessionScope.currentUser.tipo_usuario == 'admin'}">
                            <span class="badge bg-danger">Administrador</span>
                        </c:when>
                        <c:when test="${sessionScope.currentUser.tipo_usuario == 'profesor'}">
                            <span class="badge bg-warning text-dark">Profesor</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-primary">Estudiante</span>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="mb-2">
                    <strong>Fecha de registro:</strong>
                    <fmt:formatDate value="${sessionScope.currentUser.fecha_registro}" pattern="dd/MM/yyyy HH:mm" />
                </li>
            </ul>

            <div>
                <a href="${pageContext.request.contextPath}/editProfile.jsp" 
                   class="btn" style="background-color: steelblue; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;">
                    Editar Perfil
                </a>
            </div>
        </div>
    </div>
</div>



<jsp:include page="footer.jsp" />