<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container mt-4">
	<div class="d-flex justify-content-between align-items-center mb-3" style="position: relative;">
		<h5 class="card-title text-steelblue mb-3">
			<i class="bi bi-clock-history me-2"></i>Temas Recientes
		</h5>
		<a href="${pageContext.request.contextPath}/jsp/secciones/newTopic.jsp"
			class="btn btn-sm mb-3"
			style="background-color: steelblue; color: white; position: absolute; right: 2.5rem">
			<i class="bi bi-plus-circle me-1"></i>Nuevo Tema
		</a>
	</div>

	<c:choose>
		<c:when test="${not empty listTopics}">
			<div class="row g-3">
				<div class="scrollable-topic-list p-2">

					<c:forEach var="tema" items="${listTopics}">
						<div class="col-12 mb-3">
							<form action="${pageContext.request.contextPath}/Topic_S"
								method="post">
								<input type="hidden" name="id_tema" value="${tema.id_tema}" />
								<input type="hidden" name="action" value="viewTopic" />

								<button type="submit"
									class="btn w-100 text-start p-0 border-0 bg-transparent">
									<div class="card shadow-sm border-0 topic-card-hover">
										<div class="card-body">
											<div class="d-flex justify-content-between">
												<h5 class="card-title mb-1 text-dark">${tema.titulo}</h5>
												<small class="text-muted"> <i
													class="bi bi-calendar-event me-1"></i> <fmt:formatDate
														value="${tema.fecha_publicacion}"
														pattern="dd/MM/yyyy HH:mm" />
												</small>
											</div>
											<p class="card-text text-muted mt-2">${tema.contenido}</p>
											<div class="text-muted small mt-2">
												<i class="bi bi-person-circle me-1"></i>${tema.nombreUsuario}
												${tema.apellidoUsuario} &nbsp;|&nbsp; <i
													class="bi bi-folder2-open me-1"></i>${tema.nombreCategoria}
												&nbsp;|&nbsp; <i class="bi bi-chat-dots me-1"></i>${tema.cantidadRespuestas}
												respuestas | Estado: <span
													class="badge ${tema.estado == 'activo' ? 'bg-success' : 'bg-danger'}">
													${tema.estado} </span>
											</div>
										</div>
									</div>
								</button>
							</form>
						</div>
					</c:forEach>

				</div>

			</div>
		</c:when>
		<c:otherwise>
			<p class="text-muted">No hay temas recientes.</p>
		</c:otherwise>
	</c:choose>
</div>

