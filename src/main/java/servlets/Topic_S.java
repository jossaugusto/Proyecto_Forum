package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entitys.Topic_E;
import interfaces.Topic_I;

@WebServlet("/Topic_S")
public class Topic_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Topic_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String id_tema = request.getParameter("id_tema");
		
		if (id_tema != null) {
			DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			Topic_I topicDAO = daoFactory.getTopic();
			Topic_E topic = topicDAO.getTopicById(Integer.parseInt(id_tema));
			
			request.setAttribute("tema", topic);
			request.getRequestDispatcher("tema.jsp").forward(request, response);
			
		} else {
			System.out.println("No se ha encontrado el id del tema");
		}
	}

}
