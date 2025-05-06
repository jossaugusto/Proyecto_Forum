<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
	<jsp:param name="titulo" value="Home" />
</jsp:include>

<div class="container mt-5">
	<!-- Título principal -->
	<div class="text-center mb-5">
		<h1 class="fw-bold">
			Bienvenido al <span class="text-primary">Foro Académico</span>
		</h1>
		<p class="text-muted">Explora, participa y comparte conocimiento.</p>
	</div>

	<!-- Notificaciones y mis temas -->
	<div class="row mb-5">
		<div class="col-lg-6 mb-4">
			<div class="card shadow-sm border-0 h-100">
				<div class="card-body">
					<h5 class="card-title text-steelblue mb-3">
						<i class="bi bi-bell-fill me-2"></i>Mis Notificaciones
					</h5>
					<jsp:include page="../secciones/_myNotifications.jsp" />
				</div>
			</div>
		</div>

		<div class="col-lg-6 mb-4">
			<div class="card shadow-sm border-0 h-100">
				<div class="card-body">
					<h5 class="card-title text-steelblue mb-3">
						<i class="bi bi-chat-dots-fill me-2"></i>Mis Temas
					</h5>
					<jsp:include page="../secciones/_myTopics.jsp" />
				</div>
			</div>
		</div>
	</div>

	<!-- Categorías y temas recientes -->
	<div class="row mb-5">
		<div class="col-lg-6 mb-4">
			<div class="card shadow-sm border-0 h-100">
				<div class="card-body">
					<jsp:include page="../secciones/_categories.jsp" />
				</div>
			</div>
		</div>

		<div class="col-lg-6 mb-4">
			<div class="card shadow-sm border-0 h-100">
				<div class="card-body">
					<jsp:include page="../secciones/_recentTopics.jsp" />
				</div>
			</div>
		</div>
	</div>


</div>

<jsp:include page="footer.jsp" />

