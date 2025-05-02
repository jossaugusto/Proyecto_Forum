<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="card mb-4 shadow-sm">
	<div class="card-header">
		<h5 class="mb-0">Notificaciones:</h5>
	</div>
	<div class="card-body">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<div>
				<a
					href="${pageContext.request.contextPath}/InitialConfi_S?showUnread=false"
					class="btn btn-outline-primary ${showUnread ? '' : 'active'}">
					Todas </a> <a
					href="${pageContext.request.contextPath}/InitialConfi_S?showUnread=true"
					class="btn btn-outline-primary ${showUnread ? 'active' : ''}">
					No leídas <span class="badge bg-danger">${unreadCount}</span>
				</a>
			</div>
		</div>

		<c:choose>
			<c:when test="${not empty listNotifications}">
				<div class="list-group">
					<c:forEach var="notificacion" items="${listNotifications}">
						<form action="${pageContext.request.contextPath}/Notification_S"
							method="get" class="list-group-item p-0 border-0">
							<input type="hidden" name="action" value="getNotificationById" />
							<input type="hidden" name="id_notificacion"
								value="${notificacion.id_notificacion}" />
							<button type="submit"
								class="w-100 text-start btn btn-light list-group-item-action d-flex justify-content-between align-items-start ${notificacion.leida ? 'opacity-50' : ''}">
								<div class="ms-2 me-auto">
									<div class="fw-bold">${notificacion.tipo_notificacion}</div>
									<span>${notificacion.contenido}</span>
								</div>
								<small> <fmt:formatDate value="${notificacion.fecha}"
										pattern="dd/MM/yyyy HH:mm" />
								</small>
							</button>
						</form>

					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<p>No tienes notificaciones.</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>