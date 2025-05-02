<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<footer class="bg-dark text-white mt-5 py-4">
	<ul class="list-disc pl-5 space-y-2">
	    <li>Mantén un tono respetuoso y profesional en todas las discusiones.</li>
	    <li>Cita adecuadamente las fuentes académicas cuando sea necesario.</li>
	    <li>Evita el plagio y respeta la propiedad intelectual.</li>
	    <li>Las críticas deben ser constructivas y basadas en argumentos.</li>
	    <li>Los moderadores se reservan el derecho de eliminar contenido inapropiado.</li>
    </ul>
	<div class="container text-center">
		<p class="mb-0">&copy; 2025 Mi Sitio Web - Todos los derechos reservados.</p>
		<small class="text-secondary">Desarrollado por tu equipo favorito.</small>
	</div>
</footer>

<!-- Script mejorado para el efecto deslizante -->
<script>
document.addEventListener('DOMContentLoaded', function() {
    const toggleLinks = document.querySelectorAll('.slide-toggle');
    
    toggleLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const targetId = this.getAttribute('data-target');
            const targetElement = document.getElementById(targetId);
            const icon = this.querySelector('i');
            
            if (targetElement.style.maxHeight === '0px' || targetElement.style.maxHeight === '') {
                // Abrir el panel
                targetElement.style.maxHeight = targetElement.scrollHeight + 'px';
                this.innerHTML = this.innerHTML.replace('Ver', 'Ocultar');
                icon.classList.remove('bi-chevron-down');
                icon.classList.add('bi-chevron-up');
            } else {
                // Cerrar el panel
                targetElement.style.maxHeight = '0px';
                this.innerHTML = this.innerHTML.replace('Ocultar', 'Ver');
                icon.classList.remove('bi-chevron-up');
                icon.classList.add('bi-chevron-down');
            }
        });
    });
});
</script>

<!-- Estilos adicionales para subrespuestas -->
<style>
.subrespuestas-container {
    transition: max-height 0.5s ease;
    overflow: hidden;
}
</style>

<!-- Estilos adicionales para categorias -->
<style>
.card-category {
    transition: transform 0.2s ease, box-shadow 0.2s ease;
    border: none;
    border-radius: 1rem;
    box-shadow: 0 0.5rem 1rem rgba(0,0,0,0.05);
}

.card-category:hover {
    transform: translateY(-4px);
    box-shadow: 0 1rem 2rem rgba(0,0,0,0.1);
}

.category-img {
    width: 100px;
    height: 100px;
    object-fit: cover;
    border-radius: 1rem;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    margin-bottom: 1rem;
}
</style>

</body>
</html>
