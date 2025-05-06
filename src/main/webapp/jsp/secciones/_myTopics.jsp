<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container mt-4">

    <c:choose>
        <c:when test="${not empty listTopicsByUser}">
            <div class="">
                <c:forEach var="topic" items="${listTopicsByUser}">
                    <div class="col-12 scrollable-mytopics-list p-2">
                        <form action="${pageContext.request.contextPath}/Topic_S" method="post">
                            <input type="hidden" name="id_tema" value="${topic.id_tema}" />
                            <input type="hidden" name="action" value="viewTopic" />

                            <button type="submit" class="btn w-100 text-start p-0 border-0 bg-transparent">
                                <div class="card shadow-sm border-0 topic-card-hover">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <h5 class="card-title mb-1 text-dark">${topic.titulo}</h5>
                                            <small class="text-muted">
                                                <i class="bi bi-calendar-event me-1"></i>
                                                <fmt:formatDate value="${topic.fecha_publicacion}" pattern="dd/MM/yyyy" />
                                            </small>
                                        </div>
                                        <p class="card-text text-muted mt-2">${topic.contenido}</p>
                                        <div class="text-muted small mt-2">
                                            <i class="bi bi-folder2-open me-1"></i>${topic.nombreCategoria} &nbsp;|&nbsp;
                                            <i class="bi bi-eye me-1"></i>${topic.vistas} vistas &nbsp;|&nbsp;
                                            <i class="bi bi-chat-dots me-1"></i>${topic.cantidadRespuestas} respuestas
                                        </div>
                                    </div>
                                </div>
                            </button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <p class="text-muted">No tienes temas creados.</p>
        </c:otherwise>
    </c:choose>
</div>

