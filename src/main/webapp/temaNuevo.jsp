<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Tema" />
</jsp:include>

<div class="container mt-5">
    <c:choose>
        <c:when test="${not empty sessionScope.currentUser}">
            <h2>Crear Nuevo Tema</h2>

            <form action="${pageContext.request.contextPath}/Topic_S" method="post">
                <input type="hidden" name="id_usuario" value="${sessionScope.currentUser.id_usuario}" />
                <div class="mb-3">
                    <label for="titulo" class="form-label">Título del Tema</label>
                    <input type="text" class="form-control" id="titulo" name="titulo" required placeholder="Escribe el título del tema">
                </div>

                <div class="mb-3">
                    <label for="contenido" class="form-label">Contenido</label>
                    <textarea class="form-control" id="contenido" name="contenido" rows="6" required placeholder="Desarrolla el contenido del tema aquí..."></textarea>
                </div>

                <div class="mb-3">
                    <label for="id_categoria" class="form-label">Categoría</label>
                    <select class="form-select" id="id_categoria" name="id_categoria" required>
                        <option value="">Seleccione una categoría</option>
                        <c:forEach var="categoria" items="${categorias}">
                            <option value="${categoria.idCategoria}">${categoria.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-success">Publicar Tema</button>
            </form>
        </c:when>

        <c:otherwise>
            <div class="alert alert-warning" role="alert">
                Debes <a href="${pageContext.request.contextPath}/login.jsp">iniciar sesión</a> para crear un tema.
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="footer.jsp" />