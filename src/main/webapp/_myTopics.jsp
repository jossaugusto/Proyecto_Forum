<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="card shadow-sm">
	<div class="card-header">
		<h5 class="mb-0">Mis temas:</h5>
	</div>
	<div class="card-body">
		<c:choose>
			<c:when test="${not empty listTopicsByUser}">
				<div class="list-group">
					<c:forEach var="topic" items="${listTopicsByUser}">
						<form action="${pageContext.request.contextPath}/Topic_S" method="post"
							class="list-group-item list-group-item-action p-0 border-0">
							<input type="hidden" name="id_tema" value="${topic.id_tema}" />
							<input type="hidden" name="action" value="viewTopic" />
							
							<button type="submit" class="w-100 text-start bg-white border-0 p-3">
								<div class="d-flex w-100 justify-content-between">
									<h5 class="mb-1 text-dark">${topic.titulo}</h5>
									<small class="text-muted"><fmt:formatDate
											value="${topic.fecha_publicacion}" pattern="dd/MM/yyyy" /></small>
								</div>
								<p class="mb-1 text-dark">${tema.contenido}</p> 
								<small class="text-muted"> Categoría:
									${topic.nombreCategoria} | <i class="bi bi-eye"></i>
									(${topic.vistas}) vistas <i class="bi bi-chat-dots"></i>
									(${topic.cantidadRespuestas}) respuestas
								</small>
							</button>
						</form>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<p>No tienes temas creados.</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>