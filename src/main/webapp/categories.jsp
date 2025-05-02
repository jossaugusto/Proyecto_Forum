<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
	<jsp:param name="titulo" value="Categorias" />
</jsp:include>

<div class="container mt-5">
	<div class="d-flex justify-content-between align-items-center mb-4">
		<h2 class="fw-bold">Categorías</h2>
		<c:if
			test="${not empty sessionScope.currentUser && sessionScope.currentUser.tipo_usuario == 'admin'}">
			<a href="${pageContext.request.contextPath}/registerCategory.jsp"
				class="btn btn-success"> <i class="bi bi-plus-circle me-1"></i>
				Nueva Categoría
			</a>
		</c:if>
	</div>

	<c:choose>
		<c:when test="${not empty listCategories}">
			<div class="row g-4">
  <c:forEach var="category" items="${listCategories}">
    <div class="col-md-6 col-lg-4">
      <div class="card card-category h-100 text-center p-4">
        <c:choose>
          <c:when test="${not empty category.imagen}">
            <img src="${pageContext.request.contextPath}/imgs/${category.imagen}"
                 alt="Imagen de ${category.nombre}"
                 class="category-img mx-auto">
          </c:when>
          <c:otherwise>
            <div class="d-flex justify-content-center align-items-center bg-light text-muted category-img">
              <i class="bi bi-image" style="font-size: 2rem;"></i>
            </div>
          </c:otherwise>
        </c:choose>

        <h5 class="fw-bold mt-2">${category.nombre}</h5>
        <p class="text-muted">${category.descripcion}</p>
        <form action="${pageContext.request.contextPath}/Category_S" class="mt-auto">
          <input type="hidden" name="id_category" value="${category.id_categoria}" />
          <input type="hidden" name="nombre_category" value="${category.nombre}" />
          <button name="action" value="viewTopics" class="btn btn-primary w-100">Ver temas</button>
        </form>
      </div>
    </div>
  </c:forEach>
</div>

		</c:when>
		<c:otherwise>
			<div class="alert alert-warning mt-4" role="alert">No hay
				categorías disponibles.</div>
		</c:otherwise>
	</c:choose>
</div>




<jsp:include page="footer.jsp" />
