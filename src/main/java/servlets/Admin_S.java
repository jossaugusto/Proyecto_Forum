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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
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
				case "UpdateUser":
					UpdateUser(request, response);
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
				case "UpdateTopic":
					updateTopic(request, response);
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
				case "UpdateCategory":
					updateCategory(request, response);
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
			request.setAttribute("message", "Debe iniciar sesion.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}
	
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	User_I userDAO = daoFactory.getUser();
	Topic_I topicDAO = daoFactory.getTopic();
	Category_I categoryDAO = daoFactory.getCategory();

	// Panel de administracion
	private void adminPanel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String order = request.getParameter("order") != null ? request.getParameter("order") : "ASC";
		
		List<User_E> listUsers = null;
		
		if (keyword != null && !keyword.isEmpty()) {
			listUsers = userDAO.getAllUsers(keyword, order);			
		} else {
			listUsers = userDAO.getAllUsers(null, order);
		}
		
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("order", order);
		request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
	}
	
	// Gestionar usuarios eliminados
	private void manageDeletedUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String order = request.getParameter("order") != null ? request.getParameter("order") : "ASC";
		List<User_E> listDeletedUsers = null;
		
		if (keyword != null && !keyword.isEmpty()) {
			listDeletedUsers = userDAO.getAllDeletedUsers(keyword,order);
		} else {
			listDeletedUsers = userDAO.getAllDeletedUsers(null,order);
		}
		
		request.setAttribute("listDeletedUsers", listDeletedUsers);
		request.setAttribute("order", order);
		request.getRequestDispatcher("manageDeletedUsers.jsp").forward(request, response);
	}

	// Editar usuario
	private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_user = request.getParameter("id_user");
		
		if (id_user != null) {
			User_E user = userDAO.getUserById(Integer.parseInt(id_user));
			
			request.setAttribute("user", user);
		} else {
			request.setAttribute("error", "Error al obtener el usuario");
		}

		request.getRequestDispatcher("editUser.jsp").forward(request, response);
	}

	// Guardar usuario
	private void UpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_user = request.getParameter("id_user");
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String tipo_user = request.getParameter("tipo_user");
		
		if (id_user != null) {
			User_E user = new User_E();
			user.setId_usuario(Integer.parseInt(id_user));
			user.setNombre(name);
			user.setApellido(lastName);
			user.setEmail(email);
			user.setTipo_usuario(tipo_user);
			
			boolean result = userDAO.updateUser(user, false);
			
			if (result) {
				request.setAttribute("exito", "Éxito al actualizar el usuario con ID: " + id_user);
				request.getRequestDispatcher("Admin_S?action=ManageUsers").forward(request, response);
			} else {
				request.setAttribute("error", "Error al actualizar el usuario con ID: " + id_user);
				request.getRequestDispatcher("editUser.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "Error al obtener el usuario");
			request.getRequestDispatcher("editUser.jsp").forward(request, response);
		}
	}
	
	// Eliminar usuario
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_user = request.getParameter("id_user");
		boolean result = userDAO.deleteUser(Integer.parseInt(id_user));
		
		if (result) {
			request.setAttribute("exito", "Éxito al eliminar el usuario con ID: " + id_user);
			request.getRequestDispatcher("Admin_S?action=ManageUsers").forward(request, response);
		} else {
			request.setAttribute("error", "Error al eliminar el usuario");
			request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
		}
	}
	
	// Restaurar usuario
	private void restoreUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_user = request.getParameter("id_user");
		boolean result = userDAO.restoreUser(Integer.parseInt(id_user));
		
		if (result) {
			request.setAttribute("exito", "Éxito al restaurar usuario con ID: " + id_user);
			request.getRequestDispatcher("Admin_S?action=ManageUsers").forward(request, response);
		} else {
			request.setAttribute("error", "Error al restaurar el usuario con ID: " + id_user);
			request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
		}
	}
	
	//----------------------------------------------------------
	
	// Gestionar temas
	private void manageTopics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String order = request.getParameter("order") != null ? request.getParameter("order") : "ASC";
		
		List<Topic_E> listTopics = null;

		if (keyword != null && !keyword.isEmpty()) {
			listTopics = topicDAO.getAllTopics(keyword, order);
		} else {
			listTopics = topicDAO.getAllTopics(null, order);
		}
		
		request.setAttribute("order", order);
		request.setAttribute("listTopics", listTopics);
		request.getRequestDispatcher("manageTopics.jsp").forward(request, response);
	}

	// Gestionar temas eliminados
	private void manageDeletedTopics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String order = request.getParameter("order") != null ? request.getParameter("order") : "ASC";
		
		List<Topic_E> listDeletedUsers = null;
		
		if (keyword != null && !keyword.isEmpty()) {
			listDeletedUsers = topicDAO.getAllDeletedTopics(keyword, order);
		} else {
			listDeletedUsers = topicDAO.getAllDeletedTopics(null, order);
		}
		
		request.setAttribute("order", order);
		request.setAttribute("listDeletedTopics", listDeletedUsers);
		request.getRequestDispatcher("manageDeletedTopics.jsp").forward(request, response);
	}
	
	// Editar tema
	private void editTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_tema = request.getParameter("id_topic");
		
		Topic_I userDAO = daoFactory.getTopic();
		Topic_E topic = userDAO.getTopicById(Integer.parseInt(id_tema));
		
		request.setAttribute("topic", topic);
		request.getRequestDispatcher("editTopic.jsp").forward(request, response);
	}

	// Guardar tema
	private void updateTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_topic = Integer.parseInt(request.getParameter("id_topic"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int id_category = Integer.parseInt(request.getParameter("id_category"));
		String state = request.getParameter("state");
		
		Topic_E topic = new Topic_E();
		topic.setId_tema(id_topic);
		topic.setTitulo(title);
		topic.setContenido(content);
		topic.setId_categoria(id_category);
		topic.setEstado(state);
		
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
		String id_topic = request.getParameter("id_topic");
		System.out.println("ID_TEMA en delete: " + id_topic);
		
		boolean result = topicDAO.deleteTopic(Integer.parseInt(id_topic));
		
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
		String id_topic = request.getParameter("id_topic");
		
		boolean result = topicDAO.restoreTopic(Integer.parseInt(id_topic));
		
		if (result) {
			request.setAttribute("exito", "Éxito al restaurar tema");
			request.getRequestDispatcher("Admin_S?action=ManageTopics").forward(request, response);
		} else {
			request.setAttribute("error", "Error al restaurar el tema");
			request.getRequestDispatcher("manageTopics.jsp").forward(request, response);
		}
	}
	
	//----------------------------------------------------------
	
	// Gestionar Categorias
	private void manageCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String order = request.getParameter("order") != null ? request.getParameter("order") : "ASC";
		
		List<Category_E> listCategories = null;

		if (keyword != null && !keyword.isEmpty()) {
			listCategories = categoryDAO.getAllCategories(keyword, order);
		} else {
			listCategories = categoryDAO.getAllCategories(null, order);
		}
		
		request.setAttribute("order", order);
		request.setAttribute("listCategories", listCategories);
		request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
	}

	private void manageDeletedCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String order = request.getParameter("order") != null ? request.getParameter("order") : "ASC";
		
		List<Category_E> listDeletedCategories = null;
		
		if (keyword != null && !keyword.isEmpty()) {
			listDeletedCategories = categoryDAO.getAllDeletedCategories(keyword, order);
		} else {
			listDeletedCategories = categoryDAO.getAllDeletedCategories(null, order);
		}
		
		request.setAttribute("order", order);
		request.setAttribute("listDeletedCategories", listDeletedCategories);
		request.getRequestDispatcher("manageDeletedCategories.jsp").forward(request, response);
	}

	private void registerCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
	    String descripcion = request.getParameter("descripcion");
	    Part filePart = request.getPart("imagen");
	    
	    // Obtener el nombre y extensión del archivo
	    String originalName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	    String extension = "";
	    
	    // Verificar si el archivo tiene extensión
	    if (originalName.contains(".")) {
	        extension = originalName.substring(originalName.lastIndexOf('.')).toLowerCase();
	        // Comprobar que sea una extensión permitida
	        if (!".ico".equals(extension) && !".png".equals(extension) && !".jpg".equals(extension) && !".jpeg".equals(extension)) {
	            request.setAttribute("error", "Formato de imagen no permitido. Use .ico, .png, .jpg o .jpeg");
	            request.getRequestDispatcher("registerCategory.jsp").forward(request, response);
	            return;
	        }
	    } else {
	        request.setAttribute("error", "El archivo debe tener una extensión válida (.ico, .png, .jpg, .jpeg)");
	        request.getRequestDispatcher("registerCategory.jsp").forward(request, response);
	        return;
	    }
	    
	    // Generar un nombre único para evitar colisiones, manteniendo la extensión original
	    String baseName = System.currentTimeMillis() + "_" + originalName.substring(0, originalName.lastIndexOf('.'));
	    String newFileName = baseName + extension;
	    
	    String uploadPath = getServletContext().getInitParameter("project-images-dir");
	    
	    // Asegurarse de que el directorio exista
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) uploadDir.mkdirs();

	    // Ruta completa en el servidor para guardar el archivo
	    String filePath = uploadPath + File.separator + newFileName;
	    filePart.write(filePath);
	    
	    // Ruta relativa para guardar en la BD
	    String imagePath = newFileName;
	    
	    // Crear objeto categoría
	    Category_E category = new Category_E();
	    category.setNombre(nombre);
	    category.setDescripcion(descripcion);
	    category.setImagen(imagePath);
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
		String id_category = request.getParameter("id_category");
		
		Category_E category = categoryDAO.getCategoryById(Integer.parseInt(id_category));
		
		request.setAttribute("category", category);
		request.getRequestDispatcher("editCategory.jsp").forward(request, response);
	}

	
	private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		int id_category = Integer.parseInt(request.getParameter("id_category"));
		
		Part filePart = request.getPart("imagen");
	    
	    // Obtener el nombre y extensión del archivo
	    String originalName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	    String extension = "";
	    
	    // Verificar si el archivo tiene extensión
	    if (originalName.contains(".")) {
	        extension = originalName.substring(originalName.lastIndexOf('.')).toLowerCase();
	        // Comprobar que sea una extensión permitida
	        if (!".ico".equals(extension) && !".png".equals(extension) && !".jpg".equals(extension) && !".jpeg".equals(extension)) {
	            request.setAttribute("error", "Formato de imagen no permitido. Use .ico, .png, .jpg o .jpeg");
	            request.getRequestDispatcher("registerCategory.jsp").forward(request, response);
	            return;
	        }
	    } else {
	        request.setAttribute("error", "El archivo debe tener una extensión válida (.ico, .png, .jpg, .jpeg)");
	        request.getRequestDispatcher("registerCategory.jsp").forward(request, response);
	        return;
	    }
	    
	   // Generar un nombre único para evitar colisiones, manteniendo la extensión original
	    String baseName = System.currentTimeMillis() + "_" + originalName.substring(0, originalName.lastIndexOf('.'));
	    String newFileName = baseName + extension;

	    // Directorio de subida
	    String uploadPath = getServletContext().getInitParameter("project-images-dir");
	    
	    // Asegurarse de que el directorio exista
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) uploadDir.mkdirs();

	    // Ruta completa en el servidor
	    String filePath = uploadPath + File.separator + newFileName;
	    filePart.write(filePath);

	    // Ruta relativa para guardar en la BD
	    String imagePath = newFileName;
	    
		Category_E category = new Category_E();
		category.setNombre(nombre);
		category.setDescripcion(descripcion);
		category.setImagen(imagePath);
		category.setId_categoria(id_category);
		
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
		String id_category = request.getParameter("id_category");
		
		boolean result = categoryDAO.deleteCategory(Integer.parseInt(id_category));
		
		if (result) {
			request.setAttribute("exito", "Éxito al eliminar la categoria");
			request.getRequestDispatcher("Admin_S?action=ManageCategories").forward(request, response);
		} else {
			request.setAttribute("error", "Error al eliminar la categoria");
			request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
		}
	}

	
	private void restoreCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_category = request.getParameter("id_category");
		
		boolean result = categoryDAO.restoreCategory(Integer.parseInt(id_category));
		
		if (result) {
			request.setAttribute("exito", "Éxito al restaurar la categoria");
			request.getRequestDispatcher("Admin_S?action=ManageCategories").forward(request, response);
		} else {
			request.setAttribute("error", "Error al restaurar la categoria");
			request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
		}
	}
	

}
