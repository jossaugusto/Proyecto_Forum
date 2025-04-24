<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Index" />
</jsp:include>
<div class="row">
    <div class="col-md-8">
        <h1 class="mb-4">Bienvenido al Foro Académico</h1>
        
        <div class="card mb-4">
            <div class="card-header">
                <h5 class="mb-0">Categorías de Discusión</h5>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${not empty categorias}">
                        <div class="row">
                            <c:forEach var="categoria" items="${categorias}">
                                <div class="col-md-6 mb-3">
                                    <div class="card h-100">
                                        <div class="card-body">
                                            <h5 class="card-title">${categoria.nombre}</h5>
                                            <p class="card-text">${categoria.descripcion}</p>
                                            <a href="${pageContext.request.contextPath}/temas?categoria=${categoria.idCategoria}" 
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
        
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5 class="mb-0">Temas Recientes</h5>
                <a href="${pageContext.request.contextPath}/temaNuevo.jsp" class="btn btn-sm btn-success">
                    <i class="bi bi-plus-circle"></i> Nuevo Tema
                </a>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${not empty listTopics}">
                        <div class="list-group">
                            <c:forEach var="tema" items="${listTopics}">
                                <a href="${pageContext.request.contextPath}/Topic_S?id_tema=${tema.id_tema}" 
                                   class="list-group-item list-group-item-action">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1">${tema.titulo}</h5>
                                        <small><fmt:formatDate value="${tema.fecha_publicacion}" pattern="dd/MM/yyyy HH:mm" /></small>
                                    </div>
                                    <p class="mb-1">${tema.contenido}</p>
                                    <small>
                                        Por: ${tema.nombreUsuario} ${tema.apellidoUsuario} |
                                        Categoría: ${tema.nombreCategoria} |
<%--                                         <i class="bi bi-chat-dots"></i> ${tema.cantidadRespuestas} respuestas --%>
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
        <!-- Sidebar con estadísticas, usuarios activos, etc. -->
        <div class="card mb-4">
            <div class="card-header">
                <h5 class="mb-0">Estadísticas</h5>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        Usuarios registrados
                        <span class="badge bg-primary rounded-pill">${estadisticas.cantidadUsuarios}</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        Temas
                        <span class="badge bg-primary rounded-pill">${estadisticas.cantidadTemas}</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        Respuestas
                        <span class="badge bg-primary rounded-pill">${estadisticas.cantidadRespuestas}</span>
                    </li>
                </ul>
            </div>
        </div>
        
        <div class="card">
            <div class="card-header">
                <h5 class="mb-0">Usuarios Más Activos</h5>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <c:forEach var="usuario" items="${usuariosActivos}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            ${usuario.nombre} ${usuario.apellido}
                            <span class="badge bg-success rounded-pill">${usuario.puntos}</span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />