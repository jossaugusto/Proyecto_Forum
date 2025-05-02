<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="card mb-4 shadow-sm">
	<div class="card-header">
		<h5 class="mb-0">Categorías de Discusión</h5>
	</div>
	<div class="card-body">
		<c:choose>
			<c:when test="${not empty listCategories}">
				<div class="row">
					<c:forEach var="category" items="${listCategories}">
						<div class="col-md-6 mb-3">
							<div class="card h-100 shadow-sm">
								<div class="card-body">
									<h5 class="card-title">${category.nombre}</h5>
									<p class="card-text">${category.descripcion}</p>
									<form action="${pageContext.request.contextPath}/Category_S" method="post">
										<input type="hidden" name="id_category" value="${category.id_categoria }"/>
										<input type="hidden" name="nombre_category" value="${category.nombre}"/>
										<button name="action" value="viewTopics" class="btn btn-primary">Ver temas</button>
									</form>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<p>No hay categorías disponibles.</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>