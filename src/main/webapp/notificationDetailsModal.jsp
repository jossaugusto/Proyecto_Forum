<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="modal-header">
    <h5 class="modal-title">Nuevo: <span>${notification.tipo_notificacion}</span></h5>
    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
</div>
<div class="modal-body">
    <p>${notification.contenido}</p>

    <c:choose>
        <c:when test="${notification.tipo_notificacion == 'registro'}">
            <a href="${pageContext.request.contextPath}/Admin_S?action=ManageUsers" style="color: steelblue;">Ver usuarios</a>
        </c:when>
    </c:choose>
</div>
<div class="modal-footer">
    <form method="post" action="${pageContext.request.contextPath}/Notification_S">
        <input type="hidden" name="action" value="deleteNotification" />
        <input type="hidden" name="id_notificacion" value="${notification.id_notificacion}" />
        <button type="submit" class="btn" style="background-color: steelblue; color: white;"
                onclick="return confirm('¿Estás seguro de eliminar esta notificación?');">
            Eliminar Notificación
        </button>
    </form>
</div>


<script>
// Cierra el diálogo si se hace clic fuera del contenido
window.onclick = function(event) {
    if (event.target == document.getElementById('notificationDialog')) {
        document.getElementById('notificationDialog').style.display = 'none';
    }
}
</script>
