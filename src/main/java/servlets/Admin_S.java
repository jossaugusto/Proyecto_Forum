package servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DAOFactory;
import entitys.Category_E;
import entitys.Topic_E;
import entitys.User_E;
import interfaces.Category_I;
import interfaces.Reply_I;
import interfaces.Topic_I;
import interfaces.User_I;

@MultipartConfig
@WebServlet("/Admin_S")
public class Admin_S extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Admin_S() {
		super();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User_E user = (User_E) session.getAttribute("currentUser");

		if (user != null) {
			String action = request.getParameter("action");		
			
			switch (action) {
			// General
				case "adminPanel":
					adminPanel(request, response);
					break;
			// Usuarios		
				case "ManageUsers":
					manageUsers(request, response);
					break;
				case "ManageDeletedUsers":
					manageDeletedUsers(request, response);
					break;
				case "EditUser":
					editUser(request, response);
					break;
				case "SaveUser":
					saveUser(request, response);
					break;
				case "DeleteUser":
					deleteUser(request, response);
					break;
				case "RestoreUser":
					restoreUser(request, response);
					break;
			// Temas
				case "ManageTopics":
					manageTopics(request, response);
					break;
				case "ManageDeletedTopics":
					manageDeletedTopics(request, response);
					break;
				case "EditTopic":
					editTopic(request, response);
					break;
				case "SaveTopic":
					saveTopic(request, response);
					break;
				case "DeleteTopic":
					deleteTopic(request, response);
					break;
				case "RestoreTopic":
					restoreTopic(request, response);
					break;
			// Categorias
				case "ManageCategories":
					manageCategories(request, response);
					break;
				case "ManageDeletedCategories":
					manageDeletedCategories(request, response);
					break;
				case "EditCategory":
					editCategory(request, response);
					break;
				case "SaveCategory":
					saveCategory(request, response);
					break;
				case "DeleteCategory":
					deleteCategory(request, response);
					break;
				case "RestoreCategory":
					restoreCategory(request, response);
					break;
				case "RegisterCategory":
					registerCategory(request, response);
					break;
				default:
					System.out.println("Accion no valida en Admin_S");
			}
		} else {
			request.setAttribute("message", "Debe iniciar sesion");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}
	


	// Panel de administracion
	private void adminPanel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			User_I userDAO = daoFactory.getUser();
			Topic_I topicDAO = daoFactory.getTopic();
			Reply_I replyDAO = daoFactory.getReply();

			int cantidadUsuarios = userDAO.countUsers();
			int cantidadTemas = topicDAO.countTopics();
			int cantidadRespuestas = replyDAO.countReplies();

			request.setAttribute("cantidadUsuarios", cantidadUsuarios);
			request.setAttribute("cantidadTemas", cantidadTemas);
			request.setAttribute("cantidadRespuestas", cantidadRespuestas);
			request.getRequestDispatcher("admin.jsp").forward(request, response);

		
	}
	
	//----------------------------------------------------------
	
	
	// Gestionar usuarios
	private void manageUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<User_E> listUsers = null;
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		User_I userDAO = daoFactory.getUser();
		
		if (keyword != null && !keyword.isEmpty()) {
			listUsers = userDAO.getAllUsersBySearch(keyword);
		} else {
			listUsers = userDAO.getAllUsers();
		}
		
		request.setAttribute("listUsers", listUsers);
		request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
	}
	
	// Gestionar usuarios eliminados
	private void manageDeletedUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<User_E> listDeletedUsers = null;
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		User_I userDAO = daoFactory.getUser();
		
		
		if (keyword != null && !keyword.isEmpty()) {
			listDeletedUsers = userDAO.getAllDeletedUsersBySearch(keyword);
		} else {
			listDeletedUsers = userDAO.getAllDeletedUsers();
		}
		
		request.setAttribute("listDeletedUsers", listDeletedUsers);
		request.getRequestDispatcher("manageDeletedUsers.jsp").forward(request, response);
	}

	// Editar usuario
	private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_usuario = request.getParameter("id_usuario");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		User_I userDAO = daoFactory.getUser();
		User_E user = userDAO.getUserById(Integer.parseInt(id_usuario));
		
		request.setAttribute("user", user);
		request.setAttribute("id_usuario", id_usuario);
		request.getRequestDispatcher("editUser.jsp").forward(request, response);
	}

	// Guardar usuario
	private void saveUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_usuario = request.getParameter("id_usuario");
		String nombre = request.getParameter("nombre").trim();
		String apellido = request.getParameter("apellido").trim();
		String email = request.getParameter("email").trim();
		String tipo_usuario = request.getParameter("tipo_usuario");
		
		User_E user = new User_E();
		user.setId_usuario(Integer.parseInt(id_usuario));
		user.setNombre(nombre);
		user.setApellido(apellido);
		user.setEmail(email);
		user.setTipo_usuario(tipo_usuario);
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		User_I userDAO = daoFactory.getUser();
		
		boolean result = userDAO.updateUser(user, false);
		
		if (result) {
			request.setAttribute("exito", "Éxito al actualizar el usuario");
			request.getRequestDispatcher("Admin_S?action=ManageUsers").forward(request, response);

		} else {
			request.setAttribute("error", "Error al actualizar el usuario");
			request.getRequestDispatcher("editUser.jsp").forward(request, response);
		}
	}
	
	// Eliminar usuario
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_usuario = request.getParameter("id_usuario");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		User_I userDAO = daoFactory.getUser();
		
		boolean result = userDAO.deleteUser(Integer.parseInt(id_usuario));
		
		if (result) {
			request.setAttribute("exito", "Éxito al eliminar el usuario");
			request.getRequestDispatcher("Admin_S?action=ManageUsers").forward(request, response);
		} else {
			request.setAttribute("error", "Error al eliminar el usuario");
			request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
		}
	}
	
	// Restaurar usuario
	private void restoreUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_usuario = request.getParameter("id_usuario");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		User_I userDAO = daoFactory.getUser();
		
		boolean result = userDAO.restoreUser(Integer.parseInt(id_usuario));
		
		if (result) {
			request.setAttribute("exito", "Éxito al restaurar usuario");
			request.getRequestDispatcher("Admin_S?action=ManageUsers").forward(request, response);
		} else {
			request.setAttribute("error", "Error al restaurar el usuario");
			request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
		}
	}

	
	//----------------------------------------------------------
	
	// Gestionar temas
	private void manageTopics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Topic_E> listTopics = null;
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Topic_I topicDAO = daoFactory.getTopic();

		if (keyword != null && !keyword.isEmpty()) {
			listTopics = topicDAO.getAllTopicsBySearch(keyword);
		} else {
			listTopics = topicDAO.getAllTopics();
		}
		
		request.setAttribute("listTopics", listTopics);
		request.getRequestDispatcher("manageTopics.jsp").forward(request, response);
	}

	// Gestionar temas eliminados
	private void manageDeletedTopics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Topic_E> listDeletedUsers = null;
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Topic_I topicDAO = daoFactory.getTopic();

		if (keyword != null && !keyword.isEmpty()) {
			listDeletedUsers = topicDAO.getAllDeletedTopicsBySearch(keyword);
		} else {
			listDeletedUsers = topicDAO.getAllDeletedTopics();
		}
		
		request.setAttribute("listDeletedTopics", listDeletedUsers);
		request.getRequestDispatcher("manageDeletedTopics.jsp").forward(request, response);
	}
	
	// Editar tema
	private void editTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_tema = request.getParameter("id_tema");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		
		Topic_I userDAO = daoFactory.getTopic();
		Topic_E topic = userDAO.getTopicById(Integer.parseInt(id_tema));
		
		Category_I categoryDAO = daoFactory.getCategory();
		List<Category_E> listCategories = categoryDAO.getAllCategories();
		
		request.setAttribute("topic", topic);
		request.setAttribute("listCategories", listCategories);
		request.getRequestDispatcher("editTopic.jsp").forward(request, response);
	}

	// Guardar tema
	private void saveTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String contenido = request.getParameter("contenido");
		int categoria = Integer.parseInt(request.getParameter("id_categoria"));
		int id_tema = Integer.parseInt(request.getParameter("id_tema"));
		String estado = request.getParameter("estado");
		
		Topic_E topic = new Topic_E();
		topic.setTitulo(titulo);
		topic.setContenido(contenido);
		topic.setId_categoria(categoria);
		topic.setEstado(estado);
		topic.setId_tema(id_tema);
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Topic_I topicDAO = daoFactory.getTopic();
		
		boolean result = topicDAO.updateTopic(topic);
		
		if (result) {
			request.setAttribute("exito", "Éxito al actualizar el tema");
			request.getRequestDispatcher("Admin_S?action=ManageTopics").forward(request, response);

		} else {
			request.setAttribute("error", "Error al actualizar el tema");
			request.getRequestDispatcher("editTopic.jsp").forward(request, response);
		}
	}
	
	// Eliminar tema
	private void deleteTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_tema = request.getParameter("id_tema");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Topic_I topicDAO = daoFactory.getTopic();
		
		boolean result = topicDAO.deleteTopic(Integer.parseInt(id_tema));
		
		if (result) {
			request.setAttribute("exito", "Éxito al eliminar el tema");
			request.getRequestDispatcher("Admin_S?action=ManageTopics").forward(request, response);
		} else {
			request.setAttribute("error", "Error al eliminar el tema");
			request.getRequestDispatcher("manageTopics.jsp").forward(request, response);
		}
	}
		
	// Restaurar tema
	private void restoreTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_usuario = request.getParameter("id_usuario");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Topic_I topicDAO = daoFactory.getTopic();
		
		boolean result = topicDAO.restoreTopic(Integer.parseInt(id_usuario));
		
		if (result) {
			request.setAttribute("exito", "Éxito al restaurar usuario");
			request.getRequestDispatcher("Admin_S?action=ManageTopics").forward(request, response);
		} else {
			request.setAttribute("error", "Error al restaurar el usuario");
			request.getRequestDispatcher("manageTopics.jsp").forward(request, response);
		}
	}
	
	
	//----------------------------------------------------------
	
	// Gestionar Categorias
	private void manageCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Category_E> listCategories = null;
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Category_I categoryDAO = daoFactory.getCategory();

		if (keyword != null && !keyword.isEmpty()) {
			listCategories = categoryDAO.getAllCategoriesBySearch(keyword);
		} else {
			listCategories = categoryDAO.getAllCategories();
		}
		
		request.setAttribute("listCategories", listCategories);
		request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
	}

	private void manageDeletedCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Category_E> listDeletedCategories = null;
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Category_I categoryDAO = daoFactory.getCategory();

		if (keyword != null && !keyword.isEmpty()) {
			listDeletedCategories = categoryDAO.getAllDeletedCategoriesBySearch(keyword);
		} else {
			listDeletedCategories = categoryDAO.getAllDeletedCategories();
		}
		
		request.setAttribute("listDeletedCategories", listDeletedCategories);
		request.getRequestDispatcher("manageDeletedCategories.jsp").forward(request, response);
	}

	private void registerCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nombre = request.getParameter("nombre");
	    String descripcion = request.getParameter("descripcion");

	    Part filePart = request.getPart("imagen");
	    
	    // Obtener el nombre original sin extensión
	    String originalName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	    String baseName = originalName.contains(".") ? originalName.substring(0, originalName.lastIndexOf('.')) : originalName;
	    
	    // Nombre de archivo nuevo forzado como .png
	    String newFileName = baseName + ".png";

	    // Directorio de subida
	    String uploadPath = getServletContext().getRealPath("") + File.separator + "imgs";
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs();
	    }

	    // Ruta completa en el servidor
	    String filePath = uploadPath + File.separator + newFileName;
	    filePart.write(filePath);

	    // Ruta relativa para guardar en la BD
	    String imagePath = "imgs/" + newFileName;

	    // Crear objeto categoría
	    Category_E category = new Category_E();
	    category.setNombre(nombre);
	    category.setDescripcion(descripcion);
	    category.setImagen(imagePath);

	    DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	    Category_I categoryDAO = daoFactory.getCategory();
	    boolean result = categoryDAO.createCategory(category);

	    if (result) {
	        request.setAttribute("exito", "Éxito al registrar la categoría");
	        request.getRequestDispatcher("Admin_S?action=ManageCategories").forward(request, response);
	    } else {
	        request.setAttribute("error", "Error al registrar la categoría");
	        request.getRequestDispatcher("registerCategory.jsp").forward(request, response);
	    }
	}


	
	private void editCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_categoria = request.getParameter("id_categoria");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		
		Category_I categoryDAO = daoFactory.getCategory();
		Category_E category = categoryDAO.getCategoryById(Integer.parseInt(id_categoria));
		
		request.setAttribute("category", category);
		request.getRequestDispatcher("editCategory.jsp").forward(request, response);
	}

	private void saveCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
		
		Part filePart = request.getPart("imagen");
	    
	    // Obtener el nombre original sin extensión
	    String originalName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	    String baseName = originalName.contains(".") ? originalName.substring(0, originalName.lastIndexOf('.')) : originalName;
	    
	    // Nombre de archivo nuevo forzado como .png
	    String newFileName = baseName + ".png";

	    // Directorio de subida
	    String uploadPath = getServletContext().getRealPath("") + File.separator + "imgs";
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs();
	    }

	    // Ruta completa en el servidor
	    String filePath = uploadPath + File.separator + newFileName;
	    filePart.write(filePath);

	    // Ruta relativa para guardar en la BD
	    String imagePath = newFileName;
		
		
		Category_E category = new Category_E();
		category.setNombre(nombre);
		category.setDescripcion(descripcion);
		category.setImagen(imagePath);
		category.setId_categoria(id_categoria);
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Category_I categoryDAO = daoFactory.getCategory();
		
		boolean result = categoryDAO.updateCategory(category);
		
		if (result) {
			request.setAttribute("exito", "Éxito al actualizar la categoria");
			request.getRequestDispatcher("Admin_S?action=ManageCategories").forward(request, response);

		} else {
			request.setAttribute("error", "Error al actualizar la categoria");
			request.getRequestDispatcher("editCategory.jsp").forward(request, response);
		}
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_categoria = request.getParameter("id_categoria");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Category_I categoryDAO = daoFactory.getCategory();
		
		boolean result = categoryDAO.deleteCategory(Integer.parseInt(id_categoria));
		
		if (result) {
			request.setAttribute("exito", "Éxito al eliminar la categoria");
			request.getRequestDispatcher("Admin_S?action=ManageCategories").forward(request, response);
		} else {
			request.setAttribute("error", "Error al eliminar la categoria");
			request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
		}
	}

	private void restoreCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_categoria = request.getParameter("id_categoria");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Category_I categoryDAO = daoFactory.getCategory();
		
		boolean result = categoryDAO.restoreCategory(Integer.parseInt(id_categoria));
		
		if (result) {
			request.setAttribute("exito", "Éxito al restaurar la categoria");
			request.getRequestDispatcher("Admin_S?action=ManageCategories").forward(request, response);
		} else {
			request.setAttribute("error", "Error al restaurar la categoria");
			request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
		}
	}
	

}
