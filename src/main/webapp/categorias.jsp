<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Categorias" />
</jsp:include>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold">Categorías</h2>
        <c:if test="${not empty sessionScope.currentUser && sessionScope.currentUser.tipo_usuario == 'admin'}">
            <a href="${pageContext.request.contextPath}/registerCategory.jsp" class="btn btn-success">
                <i class="bi bi-plus-circle me-1"></i> Nueva Categoría
            </a>
        </c:if>
    </div>

    <c:choose>
        <c:when test="${not empty listCategories}">
            <div class="row g-4">
                <c:forEach var="categoria" items="${listCategories}">
                    <div class="col-md-6 col-lg-4">
                        <div class="card border-0 shadow-sm h-100">
                            <div class="card-body d-flex flex-column justify-content-between">
                                <div>
									<div class="text-center my-3">
									    <c:choose>
									        <c:when test="${not empty categoria.imagen}">
									            <img src="${pageContext.request.contextPath}/imgs/${categoria.imagen}"
									                 alt="Imagen de ${categoria.nombre}"
									                 class="rounded-circle border border-secondary shadow-sm"
									                 style="width: 80px; height: 80px; object-fit: cover;">
									        </c:when>
									        <c:otherwise>
									            <div class="d-inline-block bg-light text-muted rounded-pill px-3 py-2 border">
									                <i class="bi bi-image"></i> Sin imagen
									            </div>
									        </c:otherwise>
									    </c:choose>
									</div>

                                    <h5 class="card-title fw-semibold">${categoria.nombre}</h5>
                                    <p class="card-text text-muted">${categoria.descripcion}</p>
                                </div>
                                <div class="mt-3">
                                    <a href="${pageContext.request.contextPath}/Topic_S?action=viewTopics&category=${categoria.id_categoria}" class="btn btn-outline-primary w-100">
                                        Ver Temas
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning mt-4" role="alert">
                No hay categorías disponibles.
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="footer.jsp" />
