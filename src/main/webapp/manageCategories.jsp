<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
	<jsp:param name="titulo" value="Gestionar Categorías" />
</jsp:include>

<div class="container py-5">
    <div class="mb-4">
        <h2 class="text-center">Categorías Registradas</h2>
    </div>

    <form method="get" action="${pageContext.request.contextPath}/Admin_S" class="row g-2 justify-content-center mb-4">
        <div class="col-md-6">
            <input type="text" name="keyword" class="form-control" placeholder="Buscar por nombre o descripción..." value="${keyword}">
        </div>

        <input type="hidden" name="order" value="${order == 'ASC' ? 'DESC' : 'ASC'}" />

        <div class="col-auto">
            <button type="submit" name="action" value="ManageCategories" class="btn" style="background-color: steelblue; color: white;">
                Ordenar:
                <c:choose>
                    <c:when test="${order == 'DESC'}">⬆ ASC</c:when>
                    <c:otherwise>⬇ DESC</c:otherwise>
                </c:choose>
            </button>
        </div>

        <div class="col-auto">
            <button type="submit" name="action" value="ManageCategories" class="btn" style="background-color: steelblue; color: white;">
                Buscar
            </button>
        </div>
    </form>

    <div class="text-center mb-3">
        <a href="${pageContext.request.contextPath}/registerCategory.jsp" class="btn btn-outline-primary rounded-pill px-4">
            Registrar Nueva Categoría
        </a>
    </div>

    <c:if test="${empty listCategories}">
        <div class="alert alert-info text-center">No hay categorías registradas.</div>
    </c:if>

    <c:if test="${not empty listCategories}">
        <div class="table-responsive">
            <table class="table table-bordered table-hover align-middle text-center">
                <thead class="table-light">
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
                    <c:forEach var="category" items="${listCategories}">
                        <tr>
                            <td>${category.id_categoria}</td>
                            <td>${category.nombre}</td>
                            <td>${category.descripcion}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty category.imagen}">
                                        <img src="${pageContext.request.contextPath}/imgs/${category.imagen}" alt="Imagen" class="img-thumbnail" width="60" height="60">
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-muted">Sin imagen</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><fmt:formatDate value="${category.fecha_creacion}" pattern="dd/MM/yyyy HH:mm" /></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/Admin_S?action=EditCategory&id_category=${category.id_categoria}" class="btn btn-sm btn-outline-secondary me-1">Editar</a>
                                <a href="${pageContext.request.contextPath}/Admin_S?action=DeleteCategory&id_category=${category.id_categoria}" class="btn btn-sm btn-outline-danger"
                                   onclick="return confirm('¿Estás seguro de eliminar esta categoría?');">
                                    Eliminar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <div class="d-flex justify-content-between mt-4">
        <a href="${pageContext.request.contextPath}/Admin_S?action=adminPanel" class="btn btn-link">← Volver al Panel</a>
        <a href="${pageContext.request.contextPath}/Admin_S?action=ManageDeletedCategories" class="btn btn-link">Ver categorías eliminadas →</a>
    </div>
</div>



<jsp:include page="footer.jsp" />
