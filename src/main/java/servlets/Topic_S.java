package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import interfaces.User_I;

@WebServlet("/Topic_S")
public class Topic_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Topic_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "viewTopic":
				viewTopic(request, response);
				break;
			case "newTopic":
				newTopic(request, response);
				break;

			default:
				System.out.println("Accion no reconocida");
		}
	}
	
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	Topic_I topicDAO = daoFactory.getTopic();
	Reply_I replayDAO = daoFactory.getReply();
	Notification_I notificationDAO = daoFactory.getNotification();
	User_I userDAO = daoFactory.getUser();

	private void viewTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		User_E currentUser = (User_E) session.getAttribute("currentUser");
		
		String id_tema = request.getParameter("id_tema");
		
		if (id_tema != null) {
			
			if(currentUser != null) {
				topicDAO.updateTopicViews(Integer.parseInt(id_tema), currentUser.getId_usuario());		
			}
			
			Topic_E topic = topicDAO.getTopicById(Integer.parseInt(id_tema));
			List<Reply_E> listRepliesByTopicId = replayDAO.getRepliesByTopicId(Integer.parseInt(id_tema));
			Map<Integer, List<Reply_E>> repliesByParent = new HashMap<>();
			for (Reply_E reply : listRepliesByTopicId) {
			    int parentId = reply.getId_respuesta_padre();
			    if (parentId > 0) {
			        List<Reply_E> childReplies = replayDAO.getRepliesByParentId(parentId);
			        if (!childReplies.isEmpty()) {
			            repliesByParent.put(parentId, childReplies);
			        }
			    }
			}			
			
			request.setAttribute("topic", topic);
			request.setAttribute("listRepliesByTopicId", listRepliesByTopicId);
			request.setAttribute("repliesByParent", repliesByParent);
			request.getRequestDispatcher("jsp/secciones/topic.jsp").forward(request, response);
			
		} else {
			System.out.println("No se ha encontrado el id del tema");
		}
	}

	private void newTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
	    String titulo = request.getParameter("titulo");
	    String contenido = request.getParameter("contenido");
	    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));

	    Topic_E nuevoTema = new Topic_E();
	    nuevoTema.setTitulo(titulo);
	    nuevoTema.setContenido(contenido);
	    nuevoTema.setId_usuario(id_usuario);
	    nuevoTema.setId_categoria(id_categoria);

		boolean result = topicDAO.createTopic(nuevoTema);
		
		if (result) {
			List<User_E> admins = userDAO.getAllUsers("admin", null);
			for (User_E admin : admins) {
				int id_admin = admin.getId_usuario();
				notificationDAO.createNotification(id_admin, "tema","Se ha creado un nuevo tema: " + titulo);
			}
			
	        response.sendRedirect("InitialConfi_S");
	    } else {
	    	request.setAttribute("error", "Error al crear el tema");
	    	request.getRequestDispatcher("jsp/secciones/newTopic.jsp").forward(request, response);
	    }
	}

}
