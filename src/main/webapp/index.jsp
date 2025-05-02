<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp">
	<jsp:param name="titulo" value="Home" />
</jsp:include>

<div class="row">
	<div class="col-md-8">
		<h1 class="mb-4">Bienvenido al Foro Académico</h1>

		<!-- Categorias -->
		<jsp:include page="_categories.jsp" />

		
		<!-- Temas Recientes -->
		<jsp:include page="_recentTopics.jsp" />
	</div>


	<div class="col-md-4">

		<!-- Notificaciones -->
		<jsp:include page="_myNotifications.jsp" />

		<!-- Mis Temas -->
        <jsp:include page="_myTopics.jsp" />
	</div>
</div>

<jsp:include page="footer.jsp" />
