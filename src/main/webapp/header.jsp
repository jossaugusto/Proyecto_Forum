<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Foro Académico - ${param.titulo}</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<link
    href="${pageContext.request.contextPath}/css/styles.css"
    rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>


</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/InitialConfi_S"> <i
				class="bi bi-mortarboard-fill me-1"></i>Foro Académico
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarContent">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse justify-content-between"
				id="navbarContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/InitialConfi_S">Inicio</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/categories.jsp">Categorías</a>
					</li>
				</ul>

				<ul class="navbar-nav mb-2 mb-lg-0">
					<c:choose>
						<c:when test="${not empty sessionScope.currentUser}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="userDropdown"
								role="button" data-bs-toggle="dropdown"> <i
									class="bi bi-person-circle me-1"></i>${sessionScope.currentUser.nombre}
							</a>
								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="userDropdown">
									<li><a class="dropdown-item"
										href="${pageContext.request.contextPath}/profileUser.jsp">Mi
											Perfil</a></li>
									<c:if
										test="${sessionScope.currentUser.tipo_usuario == 'admin'}">
										<li><a class="dropdown-item"
											href="${pageContext.request.contextPath}/Admin_S?action=adminPanel">Administración</a></li>
									</c:if>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item"
										href="${pageContext.request.contextPath}/Logout_S">Cerrar
											Sesión</a></li>
								</ul></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/login.jsp">Iniciar
									Sesión</a></li>
							<c:if test="${sessionScope.currentUser.tipo_usuario == 'admin'}">
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/register.jsp">Registrar
										nueva usuario</a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container mt-3">
		<c:if test="${not empty requestScope.error}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">
				<i class="bi bi-exclamation-triangle-fill me-2"></i>${requestScope.error}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Cerrar"></button>
			</div>
		</c:if>

		<c:if test="${not empty requestScope.exito}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<i class="bi bi-check-circle-fill me-2"></i>${requestScope.exito}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Cerrar"></button>
			</div>
		</c:if>
	</div>