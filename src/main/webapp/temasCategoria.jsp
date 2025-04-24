<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Tema" />
</jsp:include>

<div class="container mt-4">
    <h2>Temas disponibles en la categoría seleccionada</h2>

    <c:choose>
        <c:when test="${not empty listTopics}">
            <div class="list-group">
                <c:forEach var="tema" items="${listTopics}">
                    <a href="${pageContext.request.contextPath}/Topic_S?action=viewTopic&id_tema=${tema.id_tema}" 
                       class="list-group-item list-group-item-action">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">${tema.titulo}</h5>
                            <small><fmt:formatDate value="${tema.fecha_publicacion}" pattern="dd/MM/yyyy HH:mm" /></small>
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
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="footer.jsp" />