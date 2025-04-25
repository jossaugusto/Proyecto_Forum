package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entitys.Reply_E;
import entitys.Topic_E;
import interfaces.Reply_I;
import interfaces.Topic_I;

@WebServlet("/Topic_S")
public class Topic_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Topic_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "viewTopic":
				viewTopic(request, response);
				break;
			case "newTopic":
				newTopic(request, response);
				break;
			case "viewTopics":
				viewTopics(request, response);
				break;
			default:
				System.out.println("Accion no reconocida");
		}
	}

	private void viewTopics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String category = request.getParameter("category");
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Topic_I topicDAO = daoFactory.getTopic();
		List<Topic_E> listTopics = topicDAO.getTopicsByCategoryId(Integer.parseInt(category));
		
		request.setAttribute("listTopics", listTopics);
		request.getRequestDispatcher("temasCategoria.jsp").forward(request, response);
	}

	private void viewTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id_tema = request.getParameter("id_tema");
		
		if (id_tema != null) {
			String message = request.getParameter("message");
			
			DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			Topic_I topicDAO = daoFactory.getTopic();
			
			topicDAO.updateTopicViews(Integer.parseInt(id_tema));			
			Topic_E topic = topicDAO.getTopicById(Integer.parseInt(id_tema));
			
			Reply_I replayDAO = daoFactory.getReply();
			List<Reply_E> listRepliesByTopicId = replayDAO.getRepliesByTopicId(Integer.parseInt(id_tema));
			
			if (message != null) {
				request.setAttribute("message", message);
			}
			request.setAttribute("topic", topic);
			request.setAttribute("listRepliesByTopicId", listRepliesByTopicId);
			request.getRequestDispatcher("tema.jsp").forward(request, response);
			
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
	    
	    DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Topic_I topicDAO = daoFactory.getTopic();
		boolean result = topicDAO.createTopic(nuevoTema);
		
		if (result) {
	        response.sendRedirect("InitialConfi_S");
	    } else {
	    	request.setAttribute("message", "Error al crear el tema");
	    	request.getRequestDispatcher("temaNuevo.jsp").forward(request, response);
	    }
	}

}
