package servlets;

import java.io.IOException;

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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User_E user = (User_E) session.getAttribute("currentUser");
		
		if (user == null) {
			request.setAttribute("message", "Debe iniciar sesion");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "viewNotification":
				viewNotification(request, response);
				break;
			case "deleteNotification":
				deleteNotification(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request type");
		}
	}

	DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	Notification_I notificationDAO = daoFactory.getNotification();
	
	private void deleteNotification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_notificacion = request.getParameter("id_notificacion");

		if (id_notificacion != null) {
			int id = Integer.parseInt(id_notificacion);
			notificationDAO.deleteNotification(id);
			request.setAttribute("exito", "Notificacion eliminada");
			request.getRequestDispatcher("InitialConfi_S").forward(request, response);
		} else {
			request.setAttribute("error", "Error al eliminar la notificacion");
		}
	}

	private void viewNotification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String id_notificacion = request.getParameter("id_notificacion");

		Notification_E notification = notificationDAO.getNotificationById(Integer.parseInt(id_notificacion));
		
		if (notification != null) {
			// Mark the notification as read
			notificationDAO.markAsRead(notification.getId_notificacion());
			request.setAttribute("notification", notification);
			request.getRequestDispatcher("notificationDetailsModal.jsp").forward(request, response);
		}

	}
	

}
