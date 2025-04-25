<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Tema" />
</jsp:include>

<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h3>${topic.titulo}</h3>
        </div>
        <div class="card-body">
            <p class="text-muted">
                Publicado el: <fmt:formatDate value="${topic.fecha_publicacion}" pattern="dd/MM/yyyy HH:mm" /><br>
                Por: ${topic.nombreUsuario} ${topic.apellidoUsuario} |
                Categoría: ${topic.nombreCategoria}
            </p>
            <hr>
            <p>${topic.contenido}</p>
        </div>
        <div class="card-footer text-muted">
            Vistas: ${topic.vistas}
        </div>
    </div>

	<!-- Respuestas al tema -->
	<div class="mt-4">
	    <h4>Respuestas (${fn:length(listRepliesByTopicId)})</h4>
	    
	    <c:if test="${not empty listRepliesByTopicId}">
	        <ul class="list-group">
	            <c:forEach var="respuesta" items="${listRepliesByTopicId}">
	                <li class="list-group-item">
	                    <p>${respuesta.contenido}</p>
	                    <small class="text-muted">
	                        Por: ${respuesta.nombreUsuario} ${respuesta.apellidoUsuario} | 
	                        <fmt:formatDate value="${respuesta.fecha_publicacion}" pattern="dd/MM/yyyy HH:mm" />
	                    </small>
	                </li>
	            </c:forEach>
	        </ul>
	    </c:if>
	    
	    <c:if test="${empty listRepliesByTopicId}">
	        <p class="text-muted">No hay respuestas aún. ¡Sé el primero en responder!</p>
	    </c:if>
	</div>

	<!-- Formulario para nueva respuesta -->
	<div class="mt-4">
	    <c:choose>
	        <c:when test="${not empty sessionScope.currentUser}">
	            <h5>Agregar una respuesta</h5>
	            <form action="${pageContext.request.contextPath}/Reply_S" method="post">
	                <input type="hidden" name="id_tema" value="${topic.id_tema}">
	                <div class="mb-3">
	                    <textarea name="contenido" class="form-control" rows="4" required placeholder="Escribe tu respuesta aquí..."></textarea>
	                </div>
	                <button type="submit" name="action" value="newReply" class="btn btn-primary">Publicar respuesta</button>
	            </form>
	        </c:when>
	        <c:otherwise>
	            <p class="text-muted">Debes <a href="${pageContext.request.contextPath}/login.jsp">iniciar sesión</a> para responder.</p>
	        </c:otherwise>
	    </c:choose>
    	<div>
			<span style="color: red;">${requestScope.message}</span>
	    </div>
	</div>

</div>

<jsp:include page="footer.jsp" />