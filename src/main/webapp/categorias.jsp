<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Index" />
</jsp:include>
	<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="mb-0">Categorías</h2>
        <c:if test="${not empty sessionScope.currentUser && sessionScope.currentUser.tipo_usuario == 'admin'}">
            <a href="${pageContext.request.contextPath}/categoriaNueva.jsp" class="btn btn-success">
                <i class="bi bi-plus-circle"></i> Nueva Categoría
            </a>
        </c:if>
    </div>

    <c:choose>
        <c:when test="${not empty listCategories}">
            <div class="row">
                <c:forEach var="categoria" items="${listCategories}">
                    <div class="col-md-6 mb-4">
                        <div class="card h-100 shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">${categoria.nombre}</h5>
                                <p class="card-text">${categoria.descripcion}</p>
                                <a href="${pageContext.request.contextPath}/temas?categoria=${categoria.id_categoria}" class="btn btn-primary">
                                    Ver Temas
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning" role="alert">
                No hay categorías disponibles.
            </div>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="footer.jsp" />