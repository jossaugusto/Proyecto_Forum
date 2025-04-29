<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Gestionar Usuarios" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Usuarios Registrados</h2>

            <!-- Buscador -->
            <form method="get" action="${pageContext.request.contextPath}/Admin_S" class="row g-3 mb-4">
                <div class="col-md-10">
                    <input type="text" name="keyword" class="form-control" placeholder="Buscar por nombre, apellido, rol o email..." value="${param.keyword}">
                </div>
                <div class="col-md-2">
                    <button type="submit" name="action" value="ManageUsers" class="btn btn-primary w-100">
                        <i class="bi bi-search"></i> Buscar
                    </button>
                </div>
            </form>

            <c:if test="${empty listUsers}">
                <div class="alert alert-info">No hay usuarios registrados.</div>
            </c:if>

            <c:if test="${not empty listUsers}">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead class="table-dark">
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
                            <c:forEach var="usuario" items="${listUsers}">
                                <tr>
                                    <td>${usuario.id_usuario}</td>
                                    <td>${usuario.nombre} ${usuario.apellido}</td>
                                    <td>${usuario.email}</td>
                                    <td>${usuario.tipo_usuario}</td>
                                    <td><fmt:formatDate value="${usuario.fecha_registro}" pattern="dd/MM/yyyy HH:mm" /></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/Admin_S?action=EditUser&id_usuario=${usuario.id_usuario}" 
                                           class="btn btn-sm btn-warning me-1">
                                            <i class="bi bi-pencil-square"></i> Editar
                                        </a>
                                        <a href="${pageContext.request.contextPath}/Admin_S?action=DeleteUser&id_usuario=${usuario.id_usuario}" 
                                           class="btn btn-sm btn-danger" 
                                           onclick="return confirm('¿Estás seguro de eliminar este usuario?');">
                                            <i class="bi bi-trash"></i> Eliminar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/Admin_S?action=adminPanel" class="btn btn-secondary">
                    <i class="bi bi-arrow-left-circle"></i> Volver al Panel
                </a>
                <a href="${pageContext.request.contextPath}/Admin_S?action=ManageDeletedUsers" class="btn btn-secondary">
                    <i class="bi bi-arrow-left-circle"></i> Ver usuarios eliminados
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
