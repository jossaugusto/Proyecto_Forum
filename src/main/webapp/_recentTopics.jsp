<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="card mb-4 shadow-sm">
	<div
		class="card-header d-flex justify-content-between align-items-center">
		<h5 class="mb-0">Temas Recientes</h5>
		<a href="${pageContext.request.contextPath}/newTopic.jsp"
			class="btn btn-sm btn-success"> <i class="bi bi-plus-circle"></i>
			Nuevo Tema
		</a>
	</div>
	<div class="card-body">
		<c:choose>
			<c:when test="${not empty listTopics}">
				<div class="list-group">
					<c:forEach var="tema" items="${listTopics}">
						<form action="${pageContext.request.contextPath}/Topic_S"
							method="post"
							class="list-group-item list-group-item-action p-0 border-0">
							<input type="hidden" name="id_tema" value="${tema.id_tema}" /> <input
								type="hidden" name="action" value="viewTopic" />

							<button type="submit"
								class="w-100 text-start bg-white border-0 p-3">
								<div class="d-flex w-100 justify-content-between">
									<h5 class="mb-1 text-dark">${tema.titulo}</h5>
									<small class="text-muted"> <fmt:formatDate
											value="${tema.fecha_publicacion}" pattern="dd/MM/yyyy HH:mm" />
									</small>
								</div>
								<p class="mb-1 text-dark">${tema.contenido}</p>
								<small class="text-muted"> Por: ${tema.nombreUsuario}
									${tema.apellidoUsuario} | Categoría: ${tema.nombreCategoria} |
									<i class="bi bi-chat-dots"></i> ${tema.cantidadRespuestas}
									respuestas
								</small>
							</button>
						</form>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<p>No hay temas recientes.</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>