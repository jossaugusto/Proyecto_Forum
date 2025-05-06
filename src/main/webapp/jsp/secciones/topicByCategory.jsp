<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../inicio/header.jsp">
    <jsp:param name="titulo" value="Temas en Categoría" />
</jsp:include>

<div class="container mt-4">
    <h2>Temas disponibles en la categoría: <strong>${category}</strong></h2>

    <c:choose>
        <c:when test="${not empty listTopicsByCategoryId}">
            <div class="list-group">
                <c:forEach var="tema" items="${listTopicsByCategoryId}">
                    <a href="${pageContext.request.contextPath}/Topic_S?action=viewTopic&id_tema=${tema.id_tema}" class="list-group-item list-group-item-action mb-2 border rounded card shadow-sm border-0 topic-card-hover">
                        <div>
                            <h5 class="mb-1">${tema.titulo}</h5>
                            <small class="text-muted"><fmt:formatDate value="${tema.fecha_publicacion}" pattern="dd MMM HH:mm" /></small>
                        </div>
                        <p class="mb-1">${tema.contenido}</p>
                        <small class="text-muted">
                            Por: ${tema.nombreUsuario} ${tema.apellidoUsuario} |
                            Categoría: <span class="badge bg-secondary">${tema.nombreCategoria}</span>
                        </small>
                    </a>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info" role="alert">
                No hay temas disponibles para esta categoría.
                <c:if test="${not empty sessionScope.currentUser}">
                    <a href="${pageContext.request.contextPath}/Topic_S?action=newTopic" class="alert-link">
                        Crear un nuevo tema
                    </a>
                </c:if>
            </div>
        </c:otherwise>
    </c:choose>
</div>


<jsp:include page="../inicio/footer.jsp" />
