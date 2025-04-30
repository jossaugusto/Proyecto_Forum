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
		
		HttpSession session = request.getSession();
		User_E currentUser = (User_E) session.getAttribute("currentUser");
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Topic_I topicDAO = daoFactory.getTopic();
		Category_I categoryDAO = daoFactory.getCategory();
		Reply_I replyDAO = daoFactory.getReply();
		Notification_I notificationDAO = daoFactory.getNotification();
		
		List<Topic_E> listTopics = topicDAO.getAllTopics();
		List<Category_E> listCategories = categoryDAO.getAllCategories();
		
		request.setAttribute("listTopics", listTopics);
		
		for (Topic_E tema : listTopics) {
		    int cantidad = replyDAO.getQuantityReplyByTopicId(tema.getId_tema());
		    tema.setCantidadRespuestas(cantidad);
		}
		
		request.setAttribute("listCategories", listCategories);
		
		if (currentUser != null) {
		    List<Topic_E> listTopicsByUser = topicDAO.getTopicsByUserId(currentUser.getId_usuario());
		    
			boolean read = Boolean.parseBoolean(request.getParameter("showUnread"));
			System.out.println("en Initial showUnread: " + read);
		    List<Notification_E> listNotifications = notificationDAO.getNotificationsByUserId(currentUser.getId_usuario(), !read);
		    int unreadCount = notificationDAO.countUnreadNotifications(currentUser.getId_usuario());
		    
			for (Topic_E tema : listTopicsByUser) {
			    int cantidad = replyDAO.getQuantityReplyByTopicId(tema.getId_tema());
			    tema.setCantidadRespuestas(cantidad);
			}
			
			request.setAttribute("showUnread", read);	
		    request.setAttribute("listTopicsByUser", listTopicsByUser);
		    request.setAttribute("listNotifications", listNotifications);
		    request.setAttribute("unreadCount", unreadCount);
		}

		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
