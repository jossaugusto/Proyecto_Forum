<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="header.jsp">
	<jsp:param name="titulo" value="Gestionar Categorías" />
</jsp:include>

<div class="container mt-5">
	<div class="card shadow-sm">
		<div class="card-body">
			<h2 class="card-title mb-4">Categorías Registradas</h2>

			<!-- Buscador -->
			<form method="get"
				action="${pageContext.request.contextPath}/Admin_S"
				class="row g-3 mb-4">
				<div class="col-md-10">
					<input type="text" name="keyword" class="form-control"
						placeholder="Buscar por nombre o descripción..."
						value="${param.keyword}">
				</div>
				<div class="col-md-2">
					<button type="submit" name="action" value="ManageCategories"
						class="btn btn-primary w-100">
						<i class="bi bi-search"></i> Buscar
					</button>
				</div>
			</form>

			<div>
				<a href="${pageContext.request.contextPath}/registerCategory.jsp"
					class="btn btn-success mb-3"> <i class="bi bi-plus-circle"></i>
					Registrar Nueva Categoría
				</a>
			</div>

			<!-- Mensaje de éxito o error -->
			<c:if test="${not empty message}">
				<div class="alert alert-${messageType}">${message}</div>
			</c:if>
		</div>

		<!-- Mensaje si no hay resultados -->
		<c:if test="${empty listCategories}">
			<div class="alert alert-info">No hay categorías registradas.</div>
		</c:if>

		<!-- Tabla de categorías -->
		<c:if test="${not empty listCategories}">
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
						<c:forEach var="category" items="${listCategories}">
							<tr>
								<td>${category.id_categoria}</td>
								<td>${category.nombre}</td>
								<td>${category.descripcion}</td>
								<td><c:if test="${not empty category.imagen}">
										<img
											src="${pageContext.request.contextPath}/imgs/${category.imagen}"
											alt="Imagen" width="60" height="60" class="rounded">
									</c:if> <c:if test="${empty category.imagen}">
										<span class="text-muted">Sin imagen</span>
									</c:if></td>
								<td><fmt:formatDate value="${category.fecha_creacion}"
										pattern="dd/MM/yyyy HH:mm" /></td>
								<td><a
									href="${pageContext.request.contextPath}/Admin_S?action=EditCategory&id_categoria=${category.id_categoria}"
									class="btn btn-sm btn-warning me-1"> <i
										class="bi bi-pencil-square"></i> Editar
								</a> <a
									href="${pageContext.request.contextPath}/Admin_S?action=DeleteCategory&id_categoria=${category.id_categoria}"
									class="btn btn-sm btn-danger"
									onclick="return confirm('¿Estás seguro de eliminar esta categoría?');">
										<i class="bi bi-trash"></i> Eliminar
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>

		<!-- Botones de navegación -->
		<div class="mt-4">
			<a
				href="${pageContext.request.contextPath}/Admin_S?action=adminPanel"
				class="btn btn-secondary"> <i class="bi bi-arrow-left-circle"></i>
				Volver al Panel
			</a> <a
				href="${pageContext.request.contextPath}/Admin_S?action=ManageDeletedCategories"
				class="btn btn-secondary"> <i class="bi bi-arrow-left-circle"></i>
				Ver categorías eliminadas
			</a>
		</div>
	</div>
</div>


<jsp:include page="footer.jsp" />
