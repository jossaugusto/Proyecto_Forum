<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
	<jsp:param name="titulo" value="Categorias" />
</jsp:include>

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="fw-bold">Categorías</h2>
        <c:if test="${not empty sessionScope.currentUser && (sessionScope.currentUser.tipo_usuario == 'admin' or sessionScope.currentUser.tipo_usuario == 'profesor')}">
            <a href="${pageContext.request.contextPath}/jsp/admin/registerCategory.jsp" class="btn btn-primary">
                <i class="bi bi-plus-circle me-1"></i>Nueva Categoría
            </a>
        </c:if>
    </div>

    <c:choose>
        <c:when test="${not empty listCategories}">
            <div class="row g-4">
                <c:forEach var="category" items="${listCategories}">
                    <div class="col-md-4">
                        <div class="card h-100 shadow-sm border-0 category-card">
                            <c:choose>
                                <c:when test="${not empty category.imagen}">
                                    <div class="card">
									  <div class="image-container">
									    <img src="${pageContext.request.contextPath}/imgs/${category.imagen}" 
									         alt="Imagen de ${category.nombre}">
									  </div>
									</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="d-flex align-items-center justify-content-center bg-light" style="height: 200px;">
                                        <i class="bi bi-image fs-1 text-muted"></i>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title">${category.nombre}</h5>
                                <p class="card-text flex-grow-1">${category.descripcion}</p>
                                <form action="${pageContext.request.contextPath}/Category_S">
                                    <input type="hidden" name="id_category" value="${category.id_categoria}" />
                                    <input type="hidden" name="nombre_category" value="${category.nombre}" />
                                    <button name="action" value="viewTopics" class="btn btn-outline-primary w-100">
                                        Ver temas
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning text-center">
                No hay categorías disponibles.
            </div>
        </c:otherwise>
    </c:choose>
</div>


<jsp:include page="footer.jsp" />
