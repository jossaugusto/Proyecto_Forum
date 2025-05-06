<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../inicio/header.jsp">
    <jsp:param name="titulo" value="Editar Tema" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-warning text-dark">
            <h4 class="mb-0">Editar Tema</h4>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/Admin_S" method="post" onsubmit="return validarFormulario()">
                <input type="hidden" name="id_topic" value="${topic.id_tema}" />

                <div class="mb-3">
                    <label for="title" class="form-label">Título:</label>
                    <input type="text" id="title" name="title" class="form-control" value="${topic.titulo}" required>
                </div>

                <div class="mb-3">
                    <label for="content" class="form-label">Contenido:</label>
                    <textarea id="content" name="content" rows="5" class="form-control" required>${topic.contenido}</textarea>
                </div>

                <div class="mb-3">
                    <label for="category" class="form-label">Categoría:</label>
                    <select id="category" name="id_category" class="form-select" required>
                        <c:forEach var="category" items="${listCategories}">
                            <option value="${category.id_categoria}" 
                                <c:if test="${category.id_categoria == topic.id_categoria}">selected</c:if>>
                                ${category.nombre}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="state" class="form-label">Estado:</label>
                    <select id="state" name="state" class="form-select" required>
                        <option value="activo" <c:if test="${topic.estado == 'activo'}">selected</c:if>>Activo</option>
                        <option value="cerrado" <c:if test="${topic.estado == 'cerrado'}">selected</c:if>>Cerrado</option>
                    </select>
                </div>
                
                <input type="hidden" name="comesBack" value="${comesBack}" />
                <div class="d-flex justify-content-between">
                    <a href="javascript:history.back()" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" name="action" value="UpdateTopic" class="btn btn-primary">Actualizar Tema</button>
                </div>
            </form>
        </div>
    </div>
</div>


<jsp:include page="../inicio/footer.jsp" />
