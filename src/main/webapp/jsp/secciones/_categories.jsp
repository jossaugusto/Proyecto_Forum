<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container mt-4">
<div>
	<h5 class="card-title text-steelblue mb-3">
		<i class="bi bi-tags-fill me-2"></i>Categorías
	</h5>
</div>
	<div class="">
		<div class="row g-4">
			<c:choose>
				<c:when test="${not empty listCategories}">
					<c:forEach var="category" items="${listCategories}">
						<div class="col-md-6 col-lg-4 scrollable-category-list">
							<div class="card shadow-sm h-100 border-0">
								<div
									class="card-body d-flex flex-column justify-content-between">
									<div>
										<h5 class="card-title text-dark">${category.nombre}</h5>
										<p class="card-text text-muted">${category.descripcion}</p>
									</div>
									<form action="${pageContext.request.contextPath}/Category_S"
										method="post" class="mt-3">
										<input type="hidden" name="id_category"
											value="${category.id_categoria}" /> <input type="hidden"
											name="nombre_category" value="${category.nombre}" />
										<button class="btn btn-sm btn-outline-primary w-100"
											name="action" value="viewTopics">
											<i class="bi bi-arrow-right-circle me-1"></i>Ver temas
										</button>
									</form>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p class="text-muted">No hay categorías disponibles.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>



