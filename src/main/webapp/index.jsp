<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Home" />
</jsp:include>
<div class="row">
    <div class="col-md-8">
        <h1 class="mb-4">Bienvenido al Foro Académico</h1>
        
        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h5 class="mb-0">Categorías de Discusión</h5>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${not empty listCategories}">
                        <div class="row">
                            <c:forEach var="categoria" items="${listCategories}">
                                <div class="col-md-6 mb-3">
                                    <div class="card h-100 shadow-sm">
                                        <div class="card-body">
                                            <h5 class="card-title">${categoria.nombre}</h5>
                                            <p class="card-text">${categoria.descripcion}</p>
                                            <a href="${pageContext.request.contextPath}/Topic_S?action=viewTopics&category=${categoria.id_categoria}" 
                                               class="btn btn-primary">Ver temas</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>No hay categorías disponibles.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        
        <div class="card mb-4 shadow-sm">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5 class="mb-0">Temas Recientes</h5>
                <a href="${pageContext.request.contextPath}/Category_S?action=listCategories" class="btn btn-sm btn-success">
                    <i class="bi bi-plus-circle"></i> Nuevo Tema
                </a>
            </div>
            <div class="card-body">
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
                                        Categoría: ${tema.nombreCategoria} |
                                        <i class="bi bi-chat-dots"></i> ${tema.cantidadRespuestas} respuestas
                                    </small>
                                </a>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>No hay temas recientes.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    
    <div class="col-md-4">
    
	<!-- Notificaciones -->
	<div class="card mb-4 shadow-sm">
	    <div class="card-header">
	        <h5 class="mb-0">Notificaciones:</h5>
	    </div>
	    <div class="card-body">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <div>
            <a href="${pageContext.request.contextPath}/Notification_S?action=getNotificationsByUserId&showUnread=false" 
               class="btn btn-outline-primary ${empty param.showUnread ? 'active' : ''}">
                Todas
            </a>
            <a href="${pageContext.request.contextPath}/Notification_S?action=getNotificationsByUserId&showUnread=true" 
               class="btn btn-outline-primary ${not empty param.showUnread ? 'active' : ''}">
                No leídas 
                <span class="badge bg-danger">${unreadCount}</span>
            </a>
        </div>
    </div>

    <c:choose>
        <c:when test="${not empty listNotifications}">
            <div class="list-group">
                <c:forEach var="notificacion" items="${listNotifications}">
                    <a href="${pageContext.request.contextPath}/Notification_S?action=getNotificationById&id_notificacion=${notificacion.id_notificacion}"
                       class="list-group-item list-group-item-action d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold">${notificacion.tipo_notificacion}</div>
                            <span>${notificacion.contenido}</span>
                        </div>
                        <small>
                            <fmt:formatDate value="${notificacion.fecha}" pattern="dd/MM/yyyy HH:mm" />
                        </small>
                    </a>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <p>No tienes notificaciones.</p>
        </c:otherwise>
    </c:choose>
</div>

	</div>

        
        <div class="card shadow-sm">
            <div class="card-header">
                <h5 class="mb-0">Mis temas:</h5>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${not empty listTopicsByUser}">
                        <div class="list-group">
                            <c:forEach var="tema" items="${listTopicsByUser}">
                                <a href="${pageContext.request.contextPath}/Topic_S?action=viewTopic&id_tema=${tema.id_tema}" 
                                   class="list-group-item list-group-item-action">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1">${tema.titulo}</h5>
                                        <small><fmt:formatDate value="${tema.fecha_publicacion}" pattern="dd/MM/yyyy" /></small>
                                    </div>
                                    <p class="mb-1">${tema.contenido}</p>
                                    <small>
                                        Categoría: ${tema.nombreCategoria} |
                                       <i class="bi bi-eye"></i> (${tema.vistas}) vistas
                                        <i class="bi bi-chat-dots"></i> (${tema.cantidadRespuestas}) respuestas
                                    </small>
                                </a>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>No tienes temas creados.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        
    </div>
</div>

<jsp:include page="footer.jsp" />
