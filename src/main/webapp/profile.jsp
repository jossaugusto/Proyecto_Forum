<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Perfil de Usuario" />
</jsp:include>

<div class="container mt-5">
    <div class="card">
        <div class="card-header text-center">
            <h2>Perfil de Usuario</h2>
        </div>
        <div class="card-body">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <strong>Nombre:</strong> ${sessionScope.currentUser.nombre} ${sessionScope.currentUser.apellido}
                        </li>
                        <li class="list-group-item">
                            <strong>Email:</strong> ${sessionScope.currentUser.email}
                        </li>
                        <li class="list-group-item">
                            <strong>Rol:</strong> 
                            <c:choose>
                                <c:when test="${sessionScope.currentUser.tipo_usuario == 'admin'}">
                                    <span class="badge bg-danger">Administrador</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-primary">Estudiante</span>
                                </c:otherwise>
                            </c:choose>
                        </li>
						<li class="list-group-item">
                            <strong>Fecha de registro:</strong> 
                            <fmt:formatDate value="${sessionScope.currentUser.fecha_registro}" pattern="dd/MM/yyyy HH:mm" />
                        </li>
                    </ul>

                    <div class="mt-4 text-center">
                        <a href="${pageContext.request.contextPath}/editProfile.jsp" class="btn btn-outline-primary">
                            Editar Perfil
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />