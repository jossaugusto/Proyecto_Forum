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
import entitys.User_E;
import interfaces.Reply_I;


@WebServlet("/Reply_S")
public class Reply_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Reply_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String action = request.getParameter("action");
		
		switch (action) {
			case "newReply":
				newReply(request, response);
				break;

			default:
				System.out.println("Accion no reconocida");
		}

	}

	private void newReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		User_E user = (User_E) session.getAttribute("currentUser");
		
		String id_tema = request.getParameter("id_tema");
		String contenido = request.getParameter("contenido");
		int id_usuario = user.getId_usuario();
		
		Reply_E reply = new Reply_E();
		reply.setId_tema(Integer.parseInt(id_tema));
		reply.setId_usuario(id_usuario);
		reply.setContenido(contenido);
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Reply_I replyDAO = daoFactory.getReply();
		boolean value = replyDAO.createReply(reply);
		
		if (value) {
			response.sendRedirect("Topic_S?action=viewTopic&id_tema=" + id_tema);
		} else {
			String message = "Error al crear la respuesta";
			response.sendRedirect("Topic_S?action=viewTopic&id_tema=" + id_tema + "&message=" + message);
		}
	}

}















