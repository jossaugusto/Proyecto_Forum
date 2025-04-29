<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="titulo" value="Editar Perfil" />
</jsp:include>

<div class="container mt-5">
    <div class="card">
        <div class="card-header text-center">
            <h2>Editar Perfil</h2>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/Admin_S" method="post">
                <input type="hidden" name="id_usuario" value="${id_usuario}" />

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control" value="${user.nombre}" required>
                </div>

                <div class="mb-3">
                    <label for="apellido" class="form-label">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" class="form-control" value="${user.apellido}" required>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Correo Electrónico:</label>
                    <input type="email" id="email" name="email" class="form-control" value="${user.email}" required>
                </div>
                
				<div class="mb-3">
				    <label for="tipo_usuario" class="form-label">Rol:</label>
				    <select id="tipo_usuario" name="tipo_usuario" class="form-select" required>
				        <option value="estudiante" ${user.tipo_usuario == 'estudiante' ? 'selected' : ''}>Estudiante</option>
				        <option value="admin" ${user.tipo_usuario == 'admin' ? 'selected' : ''}>Admin</option>
				    </select>
				</div>

                <div class="mt-4 text-center">
                    <button type="submit" name="action" value="SaveUser" class="btn btn-primary">Guardar Cambios</button>
                    <a href="${pageContext.request.contextPath}/Admin_S?action=ManageUsers" class="btn btn-secondary ms-2">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
