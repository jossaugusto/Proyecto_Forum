<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Temas en Categoría" />
</jsp:include>

<div class="container mt-4">
    <h2>Temas disponibles en la categoría: ${category}</h2>

    <c:choose>
        <c:when test="${not empty listTopicsByCategoryId}">
            <div class="list-group">
                <c:forEach var="tema" items="${listTopicsByCategoryId}">
                    <a href="${pageContext.request.contextPath}/Topic_S?action=viewTopic&id_tema=${tema.id_tema}" 
                       class="list-group-item list-group-item-action">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">${tema.titulo}</h5>
                            <small><fmt:formatDate value="${tema.fecha_publicacion}" pattern="dd MMM yyyy HH:mm" /></small>
                        </div>
                        <p class="mb-1">${tema.contenido}</p>
                        <small>
                            Por: ${tema.nombreUsuario} ${tema.apellidoUsuario} |
                            Categoría: ${tema.nombreCategoria}
                        </small>
                    </a>
                </c:forEach>
            </div>
            
        </c:when>
        <c:otherwise>
            <div class="alert alert-info mt-3">No hay temas disponibles para esta categoría.</div>
            <c:if test="${not empty sessionScope.currentUser}">
                <a href="${pageContext.request.contextPath}/Category_S?action=listCategories" class="btn btn-primary mt-3">Crear un nuevo tema</a>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="footer.jsp" />
