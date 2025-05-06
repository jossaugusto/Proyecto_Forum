<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../inicio/header.jsp">
    <jsp:param name="titulo" value="Editar Perfil" />
</jsp:include>

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Editar Usuario</h4>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/Admin_S" method="post">
                <input type="hidden" name="id_user" value="${user.id_usuario}" />

                <div class="mb-3">
                    <label for="name" class="form-label">Nombre:</label>
                    <input type="text" id="name" name="name" class="form-control" value="${user.nombre}" required>
                </div>

                <div class="mb-3">
                    <label for="lastName" class="form-label">Apellido:</label>
                    <input type="text" id="lastName" name="lastName" class="form-control" value="${user.apellido}" required>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Correo Electrónico:</label>
                    <input type="email" id="email" name="email" class="form-control" value="${user.email}" required>
                </div>

                <div class="mb-3">
                    <label for="tipo_user" class="form-label">Rol:</label>
                    <select id="tipo_user" name="tipo_user" class="form-select" required>
                        <option value="admin" ${user.tipo_usuario == 'admin' ? 'selected' : ''}>Admin</option>
                        <option value="estudiante" ${user.tipo_usuario == 'estudiante' ? 'selected' : ''}>Estudiante</option>
                        <option value="profesor" ${user.tipo_usuario == 'profesor' ? 'selected' : ''}>Profesor</option>
                    </select>
                </div>

                <div class="d-flex justify-content-between">
                    <a href="javaScript:history.back()" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" name="action" value="UpdateUser" class="btn btn-success">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function validarFormulario() {
        var email = document.getElementById("email").value;

        // Validar formato de correo
        var correoRegex = /^[a-zA-Z0-9._%+-]+@forum\.pe$/;
        if (!correoRegex.test(email)) {
            alert("El correo debe tener el formato usuario@forum.pe");
            return false;
        }

        return true;
    }
</script>

<jsp:include page="../inicio/footer.jsp" />
