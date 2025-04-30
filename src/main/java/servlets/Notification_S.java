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
import entitys.Notification_E;
import entitys.User_E;
import interfaces.Notification_I;

@WebServlet("/Notification_S")
public class Notification_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Notification_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User_E user = (User_E) session.getAttribute("currentUser");
		
		if (user == null) {
			request.setAttribute("message", "Debe iniciar sesion");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "getAllNotifications":
				// Call the method to get all notifications
				break;
			case "getNotificationById":
				getNotificationById(request, response);
				break;
			case "createNotification":
				// Call the method to create a notification
				break;
			case "deleteNotification":
				deleteNotification(request, response);
				break;
			/*case "getNotificationsByUserId":
				getNotificationsByUserId(request, response);
				break;*/
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request type");
		}
	}


	private void deleteNotification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_notificacion = request.getParameter("id_notificacion");
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Notification_I notificationDAO = daoFactory.getNotification();
		
		if (id_notificacion != null) {
			int id = Integer.parseInt(id_notificacion);
			notificationDAO.deleteNotification(id);
			request.setAttribute("exito", "Notificacion eliminada");
			request.getRequestDispatcher("InitialConfi_S").forward(request, response);
		} else {
			request.setAttribute("error", "Error al eliminar la notificacion");
		}
	}

	/*private void getNotificationsByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		HttpSession session = request.getSession();
		User_E user = (User_E) session.getAttribute("currentUser");
		boolean read = Boolean.parseBoolean(request.getParameter("showUnread"));
		System.out.println("en Notifi_S showUnread: " + read);
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Notification_I notificationDAO = daoFactory.getNotification();
		List<Notification_E> listNotifications = notificationDAO.getNotificationsByUserId(user.getId_usuario(),read);
	    request.setAttribute("listNotifications", listNotifications);
	    request.getRequestDispatcher("InitialConfi_S").forward(request, response);
	}*/

	private void getNotificationById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String id_tema = request.getParameter("id_tema");
		System.out.println("ID del tema: es getNotificationByiD" + id_tema);
		String id_notificacion = request.getParameter("id_notificacion");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Notification_I notificationDAO = daoFactory.getNotification();
		Notification_E notification = notificationDAO.getNotificationById(Integer.parseInt(id_notificacion));
		if (notification != null) {
			// Mark the notification as read
			notificationDAO.markAsRead(notification.getId_notificacion());
			request.setAttribute("notification", notification);
			request.getRequestDispatcher("notificationDetails.jsp").forward(request, response);
		}

	}
	

}
