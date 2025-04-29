<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Notificación" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title">Nuevo: <span class="text-primary">${notification.tipo_notificacion}</span></h2>
            <p class="card-text">${notification.contenido}</p>

            <c:choose>
                <c:when test="${notification.tipo_notificacion == 'registro'}">
                    <a href="${pageContext.request.contextPath}/Admin_S?action=ManageUsers" class="btn btn-primary">Ver usuarios</a>
                </c:when>
                <c:when test="${notification.tipo_notificacion == 'respuesta'}">
                    <a href="${pageContext.request.contextPath}/Admin_S?action=ManageReplies" class="btn btn-primary">Ver respuestas</a>
                </c:when>
                <c:when test="${notification.tipo_notificacion == 'voto'}">
                    <a href="${pageContext.request.contextPath}/Admin_S?action=ManageVotes" class="btn btn-primary">Ver votos</a>
                </c:when>
            </c:choose>

            <!-- Botón eliminar -->
            <form method="post" action="${pageContext.request.contextPath}/Notification_S" class="mt-3">
                <input type="hidden" name="action" value="deleteNotification" />
                <input type="hidden" name="id_notificacion" value="${notification.id_notificacion}" />
                <button type="submit" class="btn btn-danger"
                        onclick="return confirm('¿Estás seguro de eliminar esta notificación?');">
                    <i class="bi bi-trash"></i> Eliminar Notificación
                </button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
