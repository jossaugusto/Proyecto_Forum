<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="">
    <div class="mb-3 d-flex justify-content-between align-items-center">
        <div>
            <a href="${pageContext.request.contextPath}/InitialConfi_S?showUnread=false"
               class="btn btn-sm ${showUnread ? 'btn-outline-secondary' : 'btn-primary'} me-2">
                Todas
            </a>
            <a href="${pageContext.request.contextPath}/InitialConfi_S?showUnread=true"
               class="btn btn-sm ${showUnread ? 'btn-primary' : 'btn-outline-secondary'}">
                No leídas
                <span class="badge bg-danger ms-1">${unreadCount}</span>
            </a>
        </div>
    </div>

    <c:choose>
        <c:when test="${not empty listNotifications}">
			<div class="list-group scrollable-mynotifications-list p-2">
			    <c:forEach var="notificacion" items="${listNotifications}">
			        <button type="button"
			                class="list-group-item list-group-item-action d-flex justify-content-between align-items-start
			                       ${notificacion.leida ? 'opacity-50' : ''} border-0 border-bottom text-start"
			                onclick="mostrarNotificacion(${notificacion.id_notificacion},${tema.id_tema})">
			            <div>
			                <div class="fw-bold text-dark">${notificacion.tipo_notificacion}</div>
			                <div class="text-muted small">${notificacion.contenido}</div>
			            </div>
			            <small class="text-muted d-block mt-1">
			                <i class="bi bi-clock me-1"></i>
			                <fmt:formatDate value="${notificacion.fecha}" pattern="dd/MM/yyyy HH:mm" />
			            </small>
			        </button>
			    </c:forEach>
			</div>

            <!-- Modal vacío -->
			<div class="modal fade" id="notificationModal" tabindex="-1" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content" id="notificationModalContent">
			    </div>
			  </div>
			</div>
			            
            
        </c:when>
        <c:otherwise>
            <p class="text-muted">No tienes notificaciones.</p>
        </c:otherwise>
    </c:choose>
</div>

<script>
function mostrarNotificacion(id_notificacion, id_tema) {
    fetch('${pageContext.request.contextPath}/Notification_S?action=viewNotification&id_notificacion=' + id_notificacion + '&id_tema=' + id_tema)
        .then(response => response.text())
        .then(html => {
            const contentDiv = document.getElementById('notificationModalContent');
            contentDiv.innerHTML = html;

            const modalElement = document.getElementById('notificationModal');
            const modal = new bootstrap.Modal(modalElement);
            modal.show();

            modalElement.addEventListener('hidden.bs.modal', () => {
                location.reload(); // Fuerza el recargado al cerrar
            }, { once: true });
        })
        .catch(error => {
            console.error('Error al cargar el modal:', error);
        });
}
modalElement.addEventListener('hidden.bs.modal', () => {
    console.log('Modal cerrado. Recargando...');
    location.reload();
});

</script>