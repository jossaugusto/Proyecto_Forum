<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Temas eliminados" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Temas Eliminados</h2>

            <!-- Buscador -->
            <form method="get" action="${pageContext.request.contextPath}/Admin_S" class="row g-3 mb-4">
                <div class="col-md-8">
                    <input type="text" name="keyword" class="form-control" placeholder="Buscar por titulo, contenido ,categoria, estado o autor..." value="${param.keyword}">
                </div>
                
                <input type="hidden" name="order"
					value="${order == 'ASC' ? 'DESC' : 'ASC'}" />

				<div class="col-md-2">
					<button type="submit" name="action" value="ManageDeletedTopics"
						class="btn btn-outline-secondary w-100">
						Ordenar:
						<c:choose>
							<c:when test="${order == 'DESC'}">⬆ ASC</c:when>
							<c:otherwise>⬇ DESC</c:otherwise>
						</c:choose>
					</button>
				</div>
                
                <div class="col-md-2">
                    <button type="submit" name="action" value="ManageDeletedTopics" class="btn btn-primary w-100">
                        <i class="bi bi-search"></i> Buscar
                    </button>
                </div>
            </form>

            <c:if test="${empty listDeletedTopics}">
                <div class="alert alert-info">No hay temas eliminados.</div>
            </c:if>

            <c:if test="${not empty listDeletedTopics}">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead class="table-dark">
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
                                        <c:choose>
                                            <c:when test="${topic.estado == 'activo'}">
                                                <span class="badge bg-success">Activo</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">Cerrado</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    
                                    <td>
                                        <a href="${pageContext.request.contextPath}/Admin_S?action=RestoreTopic&id_topic=${topic.id_tema}" 
                                           class="btn btn-sm btn-danger" 
                                           onclick="return confirm('¿Estás seguro de restaurar este tema?');">
                                            <i class="bi bi-trash"></i> Restaurar
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
                <a href="${pageContext.request.contextPath}/Admin_S?action=ManageTopics" class="btn btn-secondary">
                    <i class="bi bi-arrow-left-circle"></i> Ver temas
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
