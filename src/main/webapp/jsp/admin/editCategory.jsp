<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../inicio/header.jsp">
    <jsp:param name="titulo" value="Editar Categoría" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Editar Categoría</h4>
        </div>

        <div class="card-body">
            <form action="${pageContext.request.contextPath}/Admin_S?action=UpdateCategory"
                  method="post" enctype="multipart/form-data">
                
                <input type="hidden" name="id_category" value="${category.id_categoria}" />
                <input type="hidden" name="imagen_actual" value="${category.imagen}" />

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control"
                           value="${category.nombre}" required>
                </div>

                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" class="form-control"
                              rows="3" required>${category.descripcion}</textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Imagen actual:</label><br>
                    <c:choose>
                        <c:when test="${not empty category.imagen}">
                            <img src="${pageContext.request.contextPath}/imgs/${category.imagen}"
                                 alt="Imagen" class="img-thumbnail" width="100" height="100">
                        </c:when>
                        <c:otherwise>
                            <span class="text-muted">Sin imagen</span>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="mb-3">
                    <label for="imagen" class="form-label">Cambiar imagen:</label>
                    <input type="file" id="imagen" name="imagen" class="form-control">
                </div>

                <div class="d-flex justify-content-between">
                    <a href="javaScript:history.back()"
                       class="btn btn-secondary">Cancelar</a>
                    <button type="submit" class="btn btn-success">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </div>
</div>


<jsp:include page="../inicio/footer.jsp" />
