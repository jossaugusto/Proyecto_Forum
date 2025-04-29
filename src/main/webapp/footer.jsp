<footer class="bg-dark text-white mt-5 py-4">
	<div class="container text-center">
		<p class="mb-0">&copy; 2025 Mi Sitio Web - Todos los derechos reservados.</p>
		<small class="text-secondary">Desarrollado por tu equipo favorito 🛠️</small>
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

<!-- Estilos adicionales -->
<style>
.subrespuestas-container {
    transition: max-height 0.5s ease;
    overflow: hidden;
}
</style>

</body>
</html>
