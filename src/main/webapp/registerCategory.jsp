<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Registrar Categoría" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Registrar Nueva Categoría</h2>

			<form action="${pageContext.request.contextPath}/Admin_S?action=RegisterCategory" method="post" enctype="multipart/form-data">
			    <div class="mb-3">
			        <label for="nombre" class="form-label">Nombre:</label>
			        <input type="text" id="nombre" name="nombre" class="form-control" required>
			    </div>
			    <div class="mb-3">
			        <label for="descripcion" class="form-label">Descripción:</label>
			        <textarea id="descripcion" name="descripcion" class="form-control" required></textarea>
			    </div>
			    <div class="mb-3">
			        <label for="imagen" class="form-label">Imagen:</label>
			        <input type="file" id="imagen" name="imagen" class="form-control">
			    </div>
			    <div class="d-flex justify-content-between">
			        <a href="${pageContext.request.contextPath}/Admin_S?action=ManageCategories" class="btn btn-secondary">
			            <i class="bi bi-arrow-left-circle"></i> Cancelar
			        </a>
			        <button type="submit" class="btn btn-success">
			            <i class="bi bi-plus-circle"></i> Registrar Categoría
			        </button>
			    </div>
			</form>
			
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
