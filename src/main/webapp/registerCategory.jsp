<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Registrar Categoría" />
</jsp:include>

<div class="container mt-4">
    <h2>Registrar Nueva Categoría</h2>

    <div class="card shadow-sm border-0">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/Admin_S?action=RegisterCategory" method="post" enctype="multipart/form-data" class="row g-3">
                <div class="col-md-6">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                </div>
                <div class="col-md-6">
                    <label for="descripcion" class="form-label">Descripción:</label>
                    <textarea class="form-control" id="descripcion" name="descripcion" required></textarea>
                </div>
                <div class="col-12">
                    <label for="imagen" class="form-label">Imagen:</label>
                    <input type="file" class="form-control" id="imagen" name="imagen">
                    <small class="form-text text-muted">Opcional. Se aceptan archivos JPG, PNG, JPEG, ICO.</small>
                </div>
                <div class="col-12">
                    <a href="${pageContext.request.contextPath}/Admin_S?action=ManageCategories" class="btn btn-secondary">
                        <i class="bi bi-arrow-left me-2"></i> Cancelar
                    </a>
                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-plus-square me-2"></i> Registrar Categoría
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
