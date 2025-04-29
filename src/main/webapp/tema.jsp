<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="header.jsp">
	<jsp:param name="titulo" value="Tema" />
</jsp:include>

<div class="container mt-5">
	<div class="card">
		<div class="card-header">
			<h3>${topic.titulo}</h3>
		</div>
		<div class="card-body">
			<p class="text-muted">
				Publicado el:
				<fmt:formatDate value="${topic.fecha_publicacion}"
					pattern="dd/MM/yyyy HH:mm" />
				<br> Por: ${topic.nombreUsuario} ${topic.apellidoUsuario} |
				Categoría: ${topic.nombreCategoria}
			</p>
			<hr>
			<p>${topic.contenido}</p>
		</div>
		<div class="card-footer text-muted">Vistas: ${topic.vistas}</div>
	</div>

	<div class="mt-4">
		<h4>Respuestas (${fn:length(listRepliesByTopicId)})</h4>

		<c:if test="${not empty listRepliesByTopicId}">
			<div class="list-group">
				<c:forEach var="respuesta" items="${listRepliesByTopicId}">
					<c:if test="${respuesta.id_respuesta_padre == 0}">
						<!-- Mostrar solo respuestas principales -->
						<div class="list-group-item mb-3">
							<div class="d-flex justify-content-between">
								<div>
									<p class="mb-1">${respuesta.contenido}</p>
									<small class="text-muted"> <i
										class="bi bi-person-circle"></i> ${respuesta.nombreUsuario}
										${respuesta.apellidoUsuario} | <i class="bi bi-clock"></i> <fmt:formatDate
											value="${respuesta.fecha_publicacion}"
											pattern="dd/MM/yyyy HH:mm" />
									</small>
								</div>
							</div>


							<c:if test="${not empty repliesByParent[respuesta.id_respuesta]}">
								<div class="mt-3">
									<a href="#" class="link-primary slide-toggle"
										data-target="replies-${respuesta.id_respuesta}"> <i
										class="bi bi-chevron-down"></i> Ver
										${fn:length(repliesByParent[respuesta.id_respuesta])}
										respuesta(s)
									</a>

									<div id="replies-${respuesta.id_respuesta}"
										class="mt-2 subrespuestas-container"
										style="max-height: 0; overflow: hidden; transition: max-height 0.5s ease;">
										<ul class="list-group">
											<c:forEach var="subRespuesta"
												items="${repliesByParent[respuesta.id_respuesta]}">
												<li class="list-group-item bg-light">
													<p>${subRespuesta.contenido}</p> <small class="text-muted">
														<i class="bi bi-person-circle"></i>
														${subRespuesta.nombreUsuario}
														${subRespuesta.apellidoUsuario} | <i class="bi bi-clock"></i>
														<fmt:formatDate value="${subRespuesta.fecha_publicacion}"
															pattern="dd/MM/yyyy HH:mm" />
												</small>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</c:if>




							<!-- Formulario de respuesta subordinada -->
							<c:if test="${not empty sessionScope.currentUser}">
								<form action="${pageContext.request.contextPath}/Reply_S"
									method="post" class="mt-3">
									<input type="hidden" name="id_tema" value="${topic.id_tema}" />
									<input type="hidden" name="id_respuesta_padre"
										value="${respuesta.id_respuesta}" />
									<div class="mb-2">
										<textarea name="contenido" class="form-control" rows="2"
											placeholder="Responder a esta respuesta..." required></textarea>
									</div>
									<button type="submit" name="action" value="newReply"
										class="btn btn-sm btn-outline-primary">Responder</button>
								</form>
							</c:if>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</c:if>

		<c:if test="${empty listRepliesByTopicId}">
			<div class="alert alert-info mt-3">No hay respuestas aún. ¡Sé
				el primero en responder!</div>
		</c:if>
	</div>




	<div class="mt-4">
		<c:choose>
			<c:when test="${not empty sessionScope.currentUser}">
				<h5>Agregar una respuesta</h5>
				<form action="${pageContext.request.contextPath}/Reply_S"
					method="post" onsubmit="return validarRespuesta()">
					<input type="hidden" name="id_tema" value="${topic.id_tema}">
					<div class="mb-3">
						<textarea name="contenido" class="form-control" rows="4" required
							placeholder="Escribe tu respuesta aquí..."></textarea>
					</div>
					<button type="submit" name="action" value="newReply"
						class="btn btn-primary">Publicar respuesta</button>
				</form>
			</c:when>
			<c:otherwise>
				<p class="text-muted">
					Debes <a href="${pageContext.request.contextPath}/login.jsp">iniciar
						sesión</a> para responder.
				</p>
			</c:otherwise>
		</c:choose>
		<div>
			<span style="color: red;">${requestScope.message}</span>
		</div>
	</div>

</div>

<jsp:include page="footer.jsp" />