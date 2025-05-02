<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Gestionar Usuarios" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Usuarios Eliminados</h2>

            <!-- Buscador -->
            <form method="get" action="${pageContext.request.contextPath}/Admin_S" class="row g-3 mb-4">
                <div class="col-md-8">
                    <input type="text" name="keyword" class="form-control" placeholder="Buscar por nombre, apellido, rol o email..." value="${param.keyword}">
                </div>
                
                			    <input type="hidden" name="order" value="${order == 'ASC' ? 'DESC' : 'ASC'}" />
			
			    <div class="col-md-2">
			        <button type="submit" name="action" value="ManageDeletedUsers" class="btn btn-outline-secondary w-100">
			            Ordenar: 
			            <c:choose>
			                <c:when test="${order == 'DESC'}">⬆ ASC</c:when>
			                <c:otherwise>⬇ DESC</c:otherwise>
			            </c:choose>
			        </button>
			    </div>
                
                <div class="col-md-2">
                    <button type="submit" name="action" value="ManageDeletedUsers" class="btn btn-primary w-100">
                        <i class="bi bi-search"></i> Buscar
                    </button>
                </div>
            </form>

            <c:if test="${empty listDeletedUsers}">
                <div class="alert alert-info">No hay usuarios eliminados.</div>
            </c:if>

            <c:if test="${not empty listDeletedUsers}">
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
                            <c:forEach var="user" items="${listDeletedUsers}">
                                <tr>
                                    <td>${user.id_usuario}</td>
                                    <td>${user.nombre} ${user.apellido}</td>
                                    <td>${user.email}</td>
                                    <td>${user.tipo_usuario}</td>
                                    <td>${user.fecha_registro}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/Admin_S?action=RestoreUser&id_user=${user.id_usuario}" 
                                           class="btn btn-sm btn-warning me-1"
                                           onclick="return confirm('¿Estás seguro de restaurar este usuario?');">
                                            <i class="bi bi-pencil-square"></i> Resturar
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
                <a href="${pageContext.request.contextPath}/Admin_S?action=ManageUsers" class="btn btn-secondary">
                    <i class="bi bi-arrow-left-circle"></i> Ver usuarios activos
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
