<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Categor�as Eliminadas" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Categorías Eliminadas</h2>

            <!-- Buscador -->
            <form method="get" action="${pageContext.request.contextPath}/Admin_S" class="row g-3 mb-4">
                <div class="col-md-8">
                    <input type="text" name="keyword" class="form-control" placeholder="Buscar por nombre o descripción..." value="${param.keyword}">
                </div>
                
   				<input type="hidden" name="order"
					value="${order == 'ASC' ? 'DESC' : 'ASC'}" />

				<div class="col-md-2">
					<button type="submit" name="action" value="ManageDeletedCategories"
						class="btn btn-outline-secondary w-100">
						Ordenar:
						<c:choose>
							<c:when test="${order == 'DESC'}">⬆ ASC</c:when>
							<c:otherwise>⬇ DESC</c:otherwise>
						</c:choose>
					</button>
				</div>
                
                <div class="col-md-2">
                    <button type="submit" name="action" value="ManageDeletedCategories" class="btn btn-primary w-100">
                        <i class="bi bi-search"></i> Buscar
                    </button>
                </div>
            </form>

            <!-- Mensaje si no hay resultados -->
            <c:if test="${empty listDeletedCategories}">
                <div class="alert alert-info">No hay categorías eliminadas.</div>
            </c:if>

            <!-- Tabla de categor�as -->
            <c:if test="${not empty listDeletedCategories}">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>Imagen</th>
                                <th>Fecha de Creación</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="category" items="${listDeletedCategories}">
                                <tr>
                                    <td>${category.id_categoria}</td>
                                    <td>${category.nombre}</td>
                                    <td>${category.descripcion}</td>
                                    <td>
                                        <c:if test="${not empty category.imagen}">
                                            <img src="${pageContext.request.contextPath}/imgs/${category.imagen}" alt="Imagen" width="60" height="60" class="rounded">
                                        </c:if>
                                        <c:if test="${empty category.imagen}">
                                            <span class="text-muted">Sin imagen</span>
                                        </c:if>
                                    </td>
                                    <td><fmt:formatDate value="${category.fecha_creacion}" pattern="dd/MM/yyyy HH:mm" /></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/Admin_S?action=RestoreCategory&id_category=${category.id_categoria}" 
                                           class="btn btn-sm btn-success">
                                            <i class="bi bi-arrow-counterclockwise"></i> Restaurar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <!-- Bot�n para volver -->
            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/Admin_S?action=adminPanel" class="btn btn-secondary">
                    <i class="bi bi-arrow-left-circle"></i> Volver al Panel
                </a>
                <a href="${pageContext.request.contextPath}/Admin_S?action=ManageCategories" class="btn btn-secondary">
                    <i class="bi bi-arrow-left-circle"></i> Volver a Categorías
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
