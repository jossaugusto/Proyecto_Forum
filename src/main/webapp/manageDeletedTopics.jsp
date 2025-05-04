<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Temas eliminados" />
</jsp:include>

<div class="container py-5">
    <div class="mb-4">
        <h2 class="text-center">Temas Eliminados</h2>
    </div>

    <form method="get" action="${pageContext.request.contextPath}/Admin_S" class="row g-2 justify-content-center mb-4">
        <div class="col-md-8">
            <input type="text" name="keyword" class="form-control" placeholder="Buscar por título, contenido, categoría, estado o autor..." value="${param.keyword}">
        </div>

        <input type="hidden" name="order" value="${order == 'ASC' ? 'DESC' : 'ASC'}" />

        <div class="col-auto">
            <button type="submit" name="action" value="ManageDeletedTopics" class="btn" style="background-color: steelblue; color: white;">
                Ordenar:
                <c:choose>
                    <c:when test="${order == 'DESC'}">⬆ ASC</c:when>
                    <c:otherwise>⬇ DESC</c:otherwise>
                </c:choose>
            </button>
        </div>

        <div class="col-auto">
            <button type="submit" name="action" value="ManageDeletedTopics" class="btn" style="background-color: steelblue; color: white;">
                Buscar
            </button>
        </div>
    </form>

    <c:if test="${empty listDeletedTopics}">
        <div class="alert alert-info text-center">No hay temas eliminados.</div>
    </c:if>

    <c:if test="${not empty listDeletedTopics}">
        <div class="table-responsive">
            <table class="table table-bordered table-hover align-middle text-center">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Autor</th>
                        <th>Título</th>
                        <th>Contenido</th>
                        <th>Categoría</th>
                        <th>Fecha de Publicación</th>
                        <th>Vistas</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="topic" items="${listDeletedTopics}">
                        <tr>
                            <td>${topic.id_tema}</td>
                            <td>${topic.nombreUsuario} ${topic.apellidoUsuario}</td>
                            <td>${topic.titulo}</td>
                            <td>${topic.contenido}</td>
                            <td>${topic.nombreCategoria}</td>
                            <td><fmt:formatDate value="${topic.fecha_publicacion}" pattern="dd/MM/yyyy HH:mm" /></td>
                            <td>${topic.vistas}</td>
                            <td>
                                <span class="badge ${topic.estado == 'activo' ? 'bg-success' : 'bg-secondary'}">
                                    ${topic.estado == 'activo' ? 'Activo' : 'Cerrado'}
                                </span>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/Admin_S?action=RestoreTopic&id_topic=${topic.id_tema}" 
                                   onclick="return confirm('¿Estás seguro de restaurar este tema?');" 
                                   class="btn btn-sm btn-success">
                                    Restaurar
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
        <a href="${pageContext.request.contextPath}/Admin_S?action=ManageTopics" class="btn btn-link">Ver temas activos →</a>
    </div>
</div>


<jsp:include page="footer.jsp" />
