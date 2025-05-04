<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
	<jsp:param name="titulo" value="Tema" />
</jsp:include>


<div class="container mt-4">
    <div class="mb-4 p-3 bg-light rounded">
        <h3>${topic.titulo}</h3>
        <p class="text-muted">
            Publicado el:
            <fmt:formatDate value="${topic.fecha_publicacion}" pattern="dd/MM/yyyy HH:mm" />
            <br>Por: ${topic.nombreUsuario} ${topic.apellidoUsuario} |
            Categoría: <span class="badge bg-secondary">${topic.nombreCategoria}</span><br>
            Estado: <span class="badge ${topic.estado == 'activo' ? 'bg-success' : 'bg-danger'}">${topic.estado}</span>
        </p>
		<c:if test="${not empty sessionScope.currentUser && (sessionScope.currentUser.id_usuario == topic.id_usuario || sessionScope.currentUser.tipo_usuario == 'admin')}">
            <form action="${pageContext.request.contextPath}/Admin_S" method="post" class="d-inline">
                <input type="hidden" name="id_topic" value="${topic.id_tema}" />
                <button type="submit" name="action" value="DeleteTopic" class="btn btn-danger btn-sm"
                        onclick="return confirm('¿Estás seguro de eliminar este tema?');">
                    <i class="bi bi-trash"></i> Eliminar
                </button>
            </form>
            <c:if test="${sessionScope.currentUser.tipo_usuario == 'admin'}"></c:if>
            <a href="${pageContext.request.contextPath}/Admin_S?action=EditTopic&id_topic=${topic.id_tema}&comesBack=true" class="btn btn-primary btn-sm">
                <i class="bi bi-pencil"></i> Editar
            </a>
		
		</c:if>
    </div>

    <div class="mb-4 p-3 bg-white border rounded">
        <p>${topic.contenido}</p>
        <div class="text-muted">Vistas: ${topic.vistas}</div>
    </div>

    <hr class="my-4">

    <div>
        <h4>Respuestas (${fn:length(listRepliesByTopicId)})</h4>

        <c:choose>
            <c:when test="${not empty listRepliesByTopicId}">
                <c:forEach var="respuesta" items="${listRepliesByTopicId}">
                    <c:if test="${respuesta.id_respuesta_padre == 0}">
                        <div class="mb-3 p-3 bg-light border rounded" style="position: relative;">

                            <div class="d-flex justify-content-end mb-2" style="position: absolute; right: 1rem;">
                                <c:if test="${sessionScope.currentUser.tipo_usuario == 'admin' or sessionScope.currentUser.tipo_usuario == 'profesor'}">
                                    <form action="${pageContext.request.contextPath}/Reply_S" method="post" class="me-2">
                                        <input type="hidden" name="id_topic" value="${topic.id_tema}" />
                                        <input type="hidden" name="id_reply" value="${respuesta.id_respuesta}" />
                                        <input type="hidden" name="is_accept" value="${respuesta.es_respuesta_aceptada ? false : true}" />
                                        <button type="submit" name="action" value="acceptedReply" class="btn btn-sm ${respuesta.es_respuesta_aceptada ? 'btn-warning' : 'btn-outline-warning'}">
                                            <i class="bi ${respuesta.es_respuesta_aceptada ? 'bi-star-fill' : 'bi-star'}"></i> Votar
                                        </button>
                                    </form>
                                    <c:if test="${not empty param.error}">
                                        <p class="text-danger">${param.error}</p>
                                    </c:if>
                                </c:if>

                                <c:if test="${respuesta.id_usuario == sessionScope.currentUser.id_usuario or sessionScope.currentUser.tipo_usuario == 'admin'}">
                                    <form action="${pageContext.request.contextPath}/Reply_S" method="post" class="me-2">
                                        <input type="hidden" name="id_topic" value="${topic.id_tema}" />
                                        <input type="hidden" name="id_reply" value="${respuesta.id_respuesta}" />
                                        <button type="submit" name="action" value="deleteReply" class="btn btn-sm btn-danger"
                                                onclick="return confirm('¿Estás seguro de eliminar esta respuesta?');">
                                            <i class="bi bi-trash"></i>
                                        </button>
                                    </form>
                                </c:if>
                            </div>

                            <p>${respuesta.contenido}</p>
                            <small class="text-muted">
                                <i class="bi bi-person-fill"></i> ${respuesta.nombreUsuario} ${respuesta.apellidoUsuario} |
                                <i class="bi bi-clock-fill"></i> <fmt:formatDate value="${respuesta.fecha_publicacion}" pattern="dd/MM/yyyy HH:mm" />
                            </small>

                            <div class="collapse mt-2" id="editReply-${respuesta.id_respuesta}">
                                <div class="card card-body">
                                    <form action="${pageContext.request.contextPath}/Reply_S" method="post" class="row g-2">
                                        <div class="col-12">
                                            <textarea class="form-control" name="contenido" rows="2" required>${respuesta.contenido}</textarea>
                                        </div>
                                        <input type="hidden" name="id_topic" value="${topic.id_tema}" />
                                        <input type="hidden" name="id_reply" value="${respuesta.id_respuesta}" />
                                        <input type="hidden" name="id_user" value="${respuesta.id_usuario}" />
                                        <div class="col-auto">
                                            <button type="submit" name="action" value="editReply" class="btn btn-primary btn-sm">Guardar</button>
                                        </div>
                                        <div class="col-auto">
                                            <button type="button" class="btn btn-secondary btn-sm" data-bs-toggle="collapse" href="#editReply-${respuesta.id_respuesta}">Cancelar</button>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <c:if test="${not empty repliesByParent[respuesta.id_respuesta]}">
                                <div class="mt-2">
                                    <a href="#" data-bs-toggle="collapse" data-bs-target="#replies-${respuesta.id_respuesta}" aria-expanded="false" aria-controls="replies-${respuesta.id_respuesta}" class="btn btn-link btn-sm">
                                        <i class="bi bi-chevron-down"></i> Ver ${fn:length(repliesByParent[respuesta.id_respuesta])} respuesta(s)
                                    </a>
                                </div>
                                <div class="collapse ms-3" id="replies-${respuesta.id_respuesta}">
                                    <ul class="list-unstyled">
                                        <c:forEach var="subRespuesta" items="${repliesByParent[respuesta.id_respuesta]}">
                                            <li class="mb-2 p-2 bg-light border rounded" style="position: relative;">
                                                <div class="d-flex justify-content-end mb-2" style="position: absolute; right: 1rem;">
                                                    <c:if test="${sessionScope.currentUser.tipo_usuario == 'admin'}">
                                                        <form action="${pageContext.request.contextPath}/Reply_S" method="post" class="me-2">
                                                            <input type="hidden" name="id_topic" value="${topic.id_tema}" />
                                                            <input type="hidden" name="id_reply" value="${subRespuesta.id_respuesta}" />
                                                            <input type="hidden" name="is_accept" value="${subRespuesta.es_respuesta_aceptada ? false : true}" />
                                                            <button type="submit" name="action" value="acceptedReply" class="btn btn-sm ${subRespuesta.es_respuesta_aceptada ? 'btn-warning' : 'btn-outline-warning'}">
                                                                <i class="bi ${subRespuesta.es_respuesta_aceptada ? 'bi-star-fill' : 'bi-star'}"></i> Votar
                                                            </button>
                                                        </form>
                                                    </c:if>
                                                    <c:if test="${subRespuesta.id_usuario == sessionScope.currentUser.id_usuario or sessionScope.currentUser.tipo_usuario == 'admin'}">
                                                        <form action="${pageContext.request.contextPath}/Reply_S" method="post" class="me-2">
                                                            <input type="hidden" name="id_topic" value="${topic.id_tema}" />
                                                            <input type="hidden" name="id_reply" value="${subRespuesta.id_respuesta}" />
                                                            <button type="submit" name="action" value="deleteReply" class="btn btn-sm btn-danger"
                                                                    onclick="return confirm('¿Estás seguro de eliminar esta respuesta?');">
                                                                <i class="bi bi-trash"></i>
                                                            </button>
                                                        </form>
                                                    </c:if>
                                                </div>
                                                <p>${subRespuesta.contenido}</p>
                                                <small class="text-muted">
                                                    <i class="bi bi-person-fill"></i> ${subRespuesta.nombreUsuario} ${subRespuesta.apellidoUsuario} |
                                                    <i class="bi bi-clock-fill"></i> <fmt:formatDate value="${subRespuesta.fecha_publicacion}" pattern="dd/MM/yyyy HH:mm" />
                                                </small>
                                                <div class="collapse mt-2" id="editReply-${subRespuesta.id_respuesta}">
                                                    <div class="card card-body">
                                                        <form action="${pageContext.request.contextPath}/Reply_S" method="post" class="row g-2">
                                                            <div class="col-12">
                                                                <textarea class="form-control" name="contenido" rows="2" required>${subRespuesta.contenido}</textarea>
                                                            </div>
                                                            <input type="hidden" name="id_topic" value="${topic.id_tema}" />
                                                            <input type="hidden" name="id_reply" value="${subRespuesta.id_respuesta}" />
                                                            <input type="hidden" name="id_user" value="${subRespuesta.id_usuario}" />
                                                            <div class="col-auto">
                                                                <button type="submit" name="action" value="editReply" class="btn btn-primary btn-sm">Guardar</button>
                                                            </div>
                                                            <div class="col-auto">
                                                                <button type="button" class="btn btn-secondary btn-sm" data-bs-toggle="collapse" href="#editReply-${subRespuesta.id_respuesta}">Cancelar</button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <c:if test="${not empty sessionScope.currentUser}">
                                                    <form action="${pageContext.request.contextPath}/Reply_S" method="post" class="mt-2 row g-2">
                                                        <input type="hidden" name="id_tema" value="${topic.id_tema}" />
                                                        <input type="hidden" name="id_respuesta_padre" value="${subRespuesta.id_respuesta}" />
                                                        <div class="col-md-9">
                                                            <textarea class="form-control form-control-sm" name="contenido" rows="2" placeholder="Responder a esta respuesta..." required></textarea>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <button type="submit" name="action" value="newReply" class="btn btn-primary btn-sm w-100">Responder</button>
                                                        </div>
                                                    </form>
                                                </c:if>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:if>

                            <c:if test="${not empty sessionScope.currentUser}">
                                <form action="${pageContext.request.contextPath}/Reply_S" method="post" class="mt-3 row g-2">
                                    <input type="hidden" name="id_tema" value="${topic.id_tema}" />
                                    <input type="hidden" name="id_respuesta_padre" value="${respuesta.id_respuesta}" />
                                    <div class="col-md-9">
                                        <textarea class="form-control form-control-sm" name="contenido" rows="2" placeholder="Responder a esta respuesta..." required></textarea>
                                    </div>
                                    <div class="col-md-3">
                                        <button type="submit" name="action" value="newReply" class="btn btn-primary btn-sm w-100">Responder</button>
                                    </div>
                                </form>
                            </c:if>
                        </div>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="alert alert-info" role="alert">No hay respuestas aún. ¡Sé el primero en responder!</div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="mt-4 p-3 bg-light border rounded">
        <c:choose>
            <c:when test="${not empty sessionScope.currentUser}">
                <h5>Agregar una respuesta</h5>
                <form action="${pageContext.request.contextPath}/Reply_S" method="post" onsubmit="return validarRespuesta()" class="row g-2">
                    <input type="hidden" name="id_tema" value="${topic.id_tema}" />
                    <div class="col-md-12">
                        <textarea class="form-control" name="contenido" rows="4" required placeholder="Escribe tu respuesta aquí..."></textarea>
                    </div>
                    <div class="col-md-12">
                        <button type="submit" name="action" value="newReply" class="btn btn-primary">Publicar respuesta</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <p class="alert alert-warning" role="alert">
                    Debes <a href="${pageContext.request.contextPath}/login.jsp" class="alert-link">iniciar sesión</a> para responder.
                </p>
            </c:otherwise>
        </c:choose>
        <c:if test="${not empty requestScope.message}">
            <div class="alert alert-danger mt-2" role="alert">
                ${requestScope.message}
            </div>
        </c:if>
    </div>
</div>

<script>
    function validarRespuesta() {
        const contenido = document.querySelector('textarea[name="contenido"]').value.trim();
        if (contenido === '') {
            alert('Por favor, escribe tu respuesta.');
            return false;
        }
        return true;
    }

    // JavaScript para mostrar/ocultar respuestas anidadas
    document.addEventListener('DOMContentLoaded', function() {
        const collapseElements = document.querySelectorAll('[data-bs-toggle="collapse"]');
        collapseElements.forEach(collapseElement => {
            collapseElement.addEventListener('click', function() {
                const targetId = this.getAttribute('data-bs-target');
                const targetElement = document.querySelector(targetId);
                if (targetElement) {
                    const isCollapsed = !targetElement.classList.contains('show');
                    const icon = this.querySelector('i');
                    if (icon) {
                        icon.classList.toggle('bi-chevron-down', isCollapsed);
                        icon.classList.toggle('bi-chevron-up', !isCollapsed);
                    } else if (this.innerText.includes('Ver')) {
                        this.innerText = isCollapsed ? this.innerText.replace('🔽 Ver', '🔼 Ocultar') : this.innerText.replace('🔼 Ocultar', '🔽 Ver');
                    }
                }
            });
        });
    });
</script>

<jsp:include page="footer.jsp" />