<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Editar Tema" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Editar Tema</h2>

            <form action="${pageContext.request.contextPath}/Admin_S" method="post" onsubmit="return validarFormulario()">
                <input type="hidden" name="id_tema" value="${topic.id_tema}" />

                <div class="mb-3">
                    <label for="titulo" class="form-label">Título:</label>
                    <input type="text" id="titulo" name="titulo" class="form-control" value="${topic.titulo}" required>
                </div>

                <div class="mb-3">
                    <label for="contenido" class="form-label">Contenido:</label>
                    <textarea id="contenido" name="contenido" class="form-control" rows="5" required>${topic.contenido}</textarea>
                </div>

                <div class="mb-3">
                    <label for="categoria" class="form-label">Categoría:</label>
                    <select id="categoria" name="id_categoria" class="form-select" required>
                        <c:forEach var="categoria" items="${listCategories}">
                            <option value="${categoria.id_categoria}" 
                                <c:if test="${categoria.id_categoria == topic.id_categoria}">selected</c:if>>
                                ${categoria.nombre}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="estado" class="form-label">Estado:</label>
                    <select id="estado" name="estado" class="form-select" required>
                        <option value="activo" <c:if test="${topic.estado == 'activo'}">selected</c:if>>Activo</option>
                        <option value="cerrado" <c:if test="${topic.estado == 'cerrado'}">selected</c:if>>Cerrado</option>
                    </select>
                </div>

                <div class="d-grid gap-2">
                    <button type="submit" name="action" value="SaveTopic" class="btn btn-primary">Actualizar Tema</button>
                    <a href="${pageContext.request.contextPath}/Admin_S?action=ManageTopics" class="btn btn-secondary">Cancelar</a>
                </div>

            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
