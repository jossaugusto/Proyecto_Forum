package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import entitys.Category_E;
import entitys.Notification_E;
import entitys.Topic_E;
import entitys.User_E;
import interfaces.Category_I;
import interfaces.Notification_I;
import interfaces.Reply_I;
import interfaces.Topic_I;

@WebServlet("/InitialConfi_S")
public class InitialConfi_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InitialConfi_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		// Instanciar la sesión
		HttpSession session = request.getSession();
		User_E currentUser = (User_E) session.getAttribute("currentUser");
		
		// Llamar a los DAOs
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Topic_I topicDAO = daoFactory.getTopic();
		Category_I categoryDAO = daoFactory.getCategory();
		Reply_I replyDAO = daoFactory.getReply();
		Notification_I notificationDAO = daoFactory.getNotification();
		
		// Obtener los datos de la base de datos
		List<Topic_E> listTopics = topicDAO.getAllTopics(null,"DESC");
		List<Category_E> listCategories = categoryDAO.getAllCategories(null, "ASC");
		
		// Contar la cantidad de respuestas por tema
		for (Topic_E tema : listTopics) {
		    int cantidad = replyDAO.getQuantityReplyByTopicId(tema.getId_tema());
		    tema.setCantidadRespuestas(cantidad);
		}

		// Enviar la lista de temas a la vista
		session.setAttribute("listTopics", listTopics);
		
		// Enviar la lista de categorías a la vista
		session.setAttribute("listCategories", listCategories);
		
		// Verificar si el usuario está logueado
		if (currentUser != null) {
			// Si usuario está logueado, obtener sus temas y notificaciones
			
			// Obtener los temas del usuario
		    List<Topic_E> listTopicsByUser = topicDAO.getTopicsByUserId(currentUser.getId_usuario());
		    
		    // Obtener notificaciones todas o las no leídas 
			boolean read = Boolean.parseBoolean(request.getParameter("showUnread"));

			// Muestra todas las notificaciones por defecto
		    List<Notification_E> listNotifications = notificationDAO.getNotificationsByUserId(currentUser.getId_usuario(), !read);
		    
		    // Contador de notificaciones no leídas
		    int unreadCount = notificationDAO.countUnreadNotifications(currentUser.getId_usuario());
		    
		    // Setear la cantidad de respuestas por tema
			for (Topic_E tema : listTopicsByUser) {
			    int cantidad = replyDAO.getQuantityReplyByTopicId(tema.getId_tema());
			    tema.setCantidadRespuestas(cantidad);
			}
			
			// Enviar los datos a la vista
			request.setAttribute("showUnread", read);
		    request.setAttribute("listTopicsByUser", listTopicsByUser);
		    request.setAttribute("listNotifications", listNotifications);
		    request.setAttribute("unreadCount", unreadCount);
		}

		// Redireccionar a la vista
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
