package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import entitys.Reply_E;
import entitys.Topic_E;
import entitys.User_E;
import interfaces.Notification_I;
import interfaces.Reply_I;
import interfaces.Topic_I;


@WebServlet("/Reply_S")
public class Reply_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Reply_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "newReply":
				newReply(request, response);
				break;
			case "acceptedReply":
				acceptedReply(request, response);
				break;
			case "deleteReply":
				deleteReply(request, response);
				break;
			case "editReply":
				editReply(request, response);
				break;
			default:
				System.out.println("Accion no reconocida");
		}

	}
	
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	Reply_I replyDAO = daoFactory.getReply();
	Topic_I topicDAO = daoFactory.getTopic();
	Notification_I notificationDAO = daoFactory.getNotification();
	
	private void newReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		User_E user = (User_E) session.getAttribute("currentUser");
		
		boolean value = false;
		int id_usuario = user.getId_usuario();
		String id_tema = request.getParameter("id_tema");
		String contenido = request.getParameter("contenido");
		String id_respuesta_padre = request.getParameter("id_respuesta_padre");
		
		Topic_E topic = topicDAO.getTopicById(Integer.parseInt(id_tema));
		int id = topic.getId_usuario();
		String title = topic.getTitulo();
		
		if (id_respuesta_padre != null && !id_respuesta_padre.isEmpty()) {
			// Es una sub-respuesta
			Reply_E reply = new Reply_E();
			reply.setId_tema(Integer.parseInt(id_tema));
			reply.setId_usuario(id_usuario);
			reply.setContenido(contenido);
			reply.setId_respuesta_padre(Integer.parseInt(id_respuesta_padre));

			value = replyDAO.createSubReply(reply);

			if (value) {
				notificationDAO.createNotification(id, "respuesta","Tu respuesta en el tema '" + title + "' ha recibido una respuesta.");
			}
		} else {
		    // Es una respuesta directa al tema
			Reply_E reply = new Reply_E();
			reply.setId_tema(Integer.parseInt(id_tema));
			reply.setId_usuario(id_usuario);
			reply.setContenido(contenido);
			
			value = replyDAO.createReply(reply);

			if (value)
				notificationDAO.createNotification(id, "respuesta","Tu tema sobre '" + title + "' ha recibido una respuesta.");
		}
		
		if (value) {
			response.sendRedirect("Topic_S?action=viewTopic&id_tema=" + id_tema);
		} else {
			String message = "Error al crear la respuesta";
			response.sendRedirect("Topic_S?action=viewTopic&id_tema=" + id_tema + "&message=" + message);
		}
	}

	private void acceptedReply(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		int id_topic = Integer.parseInt(request.getParameter("id_topic"));
		int id_reply = Integer.parseInt(request.getParameter("id_reply"));
		boolean is_accept = Boolean.parseBoolean(request.getParameter("is_accept"));
		
		boolean result = replyDAO.acceptedReply(id_reply, is_accept);
		if (result) {
			response.sendRedirect("Topic_S?action=viewTopic&id_tema=" + id_topic + "&accept=" + is_accept);
		} else {
			response.sendRedirect("Topic_S?action=viewTopic&id_tema=" + id_topic + "&error=Error al aceptar la respuesta");
		}
	}
	
	private void deleteReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id_reply = Integer.parseInt(request.getParameter("id_reply"));
		int id_topic = Integer.parseInt(request.getParameter("id_topic"));
		
		boolean result = replyDAO.deleteReply(id_reply);
		if (result) {
			response.sendRedirect("Topic_S?action=viewTopic&id_tema=" + id_topic);
		} else {
			response.sendRedirect("Topic_S?action=viewTopic&id_tema=" + id_topic);
		}
	}
	
	private void editReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id_reply = Integer.parseInt(request.getParameter("id_reply"));
		String contenido = request.getParameter("contenido");
		int id_topic = Integer.parseInt(request.getParameter("id_topic"));
		int id_user = Integer.parseInt(request.getParameter("id_user"));
		
		Reply_E reply = new Reply_E();
		reply.setId_tema(id_topic);
		reply.setId_usuario(id_user);
		reply.setContenido(contenido);
		reply.setId_respuesta(id_reply);
		
		boolean result = replyDAO.updateReply(reply);
		if (result) {
			response.sendRedirect("Topic_S?action=viewTopic&id_tema=" + id_topic);
		} else {
			response.sendRedirect("Topic_S?action=viewTopic&id_tema=" + id_topic);
		}
	}
	
}















