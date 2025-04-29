<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Editar Categoría" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Editar Categoría</h2>

            <form action="${pageContext.request.contextPath}/Admin_S?action=SaveCategory"  method="post" enctype="multipart/form-data">
               	<input type="hidden" name="id_categoria" value="${category.id_categoria}" />
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control" value="${category.nombre}" required>
                </div>

                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" class="form-control" required>${category.descripcion}</textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Imagen actual:</label><br>
                    <c:if test="${not empty category.imagen}">
                        <img src="${pageContext.request.contextPath}/imgs/${category.imagen}" alt="Imagen" width="100" height="100" class="rounded mb-2">
                    </c:if>
                    <c:if test="${empty category.imagen}">
                        <span class="text-muted">Sin imagen</span>
                    </c:if>
                    <input type="hidden" name="imagen_actual" value="${category.imagen}" />
                </div>

                <div class="mb-3">
                    <label for="imagen" class="form-label">Cambiar imagen:</label>
                    <input type="file" id="imagen" name="imagen" class="form-control">
                </div>
                <div class="d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/Admin_S?action=ManageCategories" class="btn btn-secondary">
                        <i class="bi bi-arrow-left-circle"></i> Cancelar
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-save"></i> Guardar Cambios
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
