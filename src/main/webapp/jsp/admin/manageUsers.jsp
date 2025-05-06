<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../inicio/header.jsp">
    <jsp:param name="titulo" value="Gestionar Usuarios" />
</jsp:include>

<div class="container py-5">
    <div class="mb-4">
        <h2 class="text-center">Usuarios Registrados</h2>
    </div>

    <form method="get" action="${pageContext.request.contextPath}/Admin_S" class="row g-2 justify-content-center mb-4">
        <div class="col-md-8">
            <input type="text" name="keyword" class="form-control"
                placeholder="Buscar por nombre, apellido, rol o email..." value="${param.keyword}">
        </div>

        <input type="hidden" name="order" value="${order == 'ASC' ? 'DESC' : 'ASC'}" />

        <div class="col-auto">
            <button type="submit" name="action" value="ManageUsers" class="btn" style="background-color: steelblue; color: white;">
                Ordenar:
                <c:choose>
                    <c:when test="${order == 'DESC'}">⬆ ASC</c:when>
                    <c:otherwise>⬇ DESC</c:otherwise>
                </c:choose>
            </button>
        </div>

        <div class="col-auto">
            <button type="submit" name="action" value="ManageUsers" class="btn" style="background-color: steelblue; color: white;">
                Buscar
            </button>
        </div>
    </form>

    <div class="mb-4 text-center">
        <a href="registerUser.jsp" class="btn btn-outline-primary rounded-pill px-4">Registrar nuevo usuario</a>
    </div>

    <c:if test="${empty listUsers}">
        <div class="alert alert-info text-center">No hay usuarios registrados.</div>
    </c:if>

    <c:if test="${not empty listUsers}">
        <div class="table-responsive">
            <table class="table table-bordered table-hover align-middle text-center">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Nombre Completo</th>
                        <th>Correo Electrónico</th>
                        <th>Rol</th>
                        <th>Fecha de Registro</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${listUsers}">
                        <tr>
                            <td>${user.id_usuario}</td>
                            <td>${user.nombre} ${user.apellido}</td>
                            <td>${user.email}</td>
                            <td>
                                <span class="badge ${user.tipo_usuario == 'admin' ? 'bg-primary' : 'bg-dark'}">
                                    ${user.tipo_usuario}
                                </span>
                            </td>
                            <td><fmt:formatDate value="${user.fecha_registro}" pattern="dd/MM/yyyy HH:mm" /></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/Admin_S?action=EditUser&id_user=${user.id_usuario}" class="btn btn-sm btn-primary">Editar</a>
                                <a href="${pageContext.request.contextPath}/Admin_S?action=DeleteUser&id_user=${user.id_usuario}"
                                   onclick="return confirm('¿Estás seguro de eliminar este usuario?');"
                                   class="btn btn-sm btn-danger">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <div class="d-flex justify-content-between mt-4">
        <a href="${pageContext.request.contextPath}/Admin_S?action=adminPanel" class="btn btn-link">← Volver al Panel</a>
        <a href="${pageContext.request.contextPath}/Admin_S?action=ManageDeletedUsers" class="btn btn-link">Ver usuarios eliminados →</a>
    </div>
</div>


<jsp:include page="../inicio/footer.jsp" />
