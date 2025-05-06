<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<jsp:include page="../inicio/header.jsp">
    <jsp:param name="titulo" value="Crear Tema" />
</jsp:include>

<div class="container py-5">
    <c:choose>
        <c:when test="${not empty sessionScope.currentUser}">
            <h2 class="text-center mb-4">Crear Nuevo Tema</h2>

            <form action="${pageContext.request.contextPath}/Topic_S" method="post" onsubmit="return validarFormulario()">
                <input type="hidden" name="id_usuario" value="${sessionScope.currentUser.id_usuario}" />

                <div class="mb-3">
                    <label for="titulo" class="form-label">Título del Tema</label>
                    <input type="text" id="titulo" name="titulo" class="form-control" required placeholder="Escribe el título del tema">
                </div>

                <div class="mb-3">
                    <label for="contenido" class="form-label">Contenido</label>
                    <textarea id="contenido" name="contenido" class="form-control" rows="6" required placeholder="Desarrolla el contenido del tema aquí..."></textarea>
                </div>

                <div class="mb-3">
                    <label for="id_categoria" class="form-label">Categoría</label>
                    <select id="id_categoria" name="id_categoria" class="form-select" required>
                        <option value="">Seleccione una categoría</option>
                        <c:forEach var="categoria" items="${sessionScope.listCategories}">
                            <option value="${categoria.id_categoria}">${categoria.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="text-center">
                    <button type="submit" name="action" value="newTopic" class="btn btn-primary">Publicar Tema</button>
                </div>
            </form>

            <c:if test="${not empty requestScope.message}">
                <div class="alert alert-info mt-4 text-center">
                    ${requestScope.message}
                </div>
            </c:if>
        </c:when>

        <c:otherwise>
            <div class="alert alert-warning text-center">
                Debes <a href="${pageContext.request.contextPath}/jsp/autenticacion/login.jsp" class="alert-link">iniciar sesión</a> para crear un tema.
            </div>
        </c:otherwise>
    </c:choose>
</div>


<jsp:include page="../inicio/footer.jsp" />