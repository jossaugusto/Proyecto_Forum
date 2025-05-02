package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entitys.Topic_E;
import interfaces.Topic_I;


@WebServlet("/Category_S")
public class Category_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Category_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "viewTopics":
				viewTopics(request, response);
				break;
			default:
				System.out.println("Accion no reconocida");
		}
	}
	
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	Topic_I topicDAO = daoFactory.getTopic();

	private void viewTopics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id_category = request.getParameter("id_category");
		String nombre_category = request.getParameter("nombre_category");

		if (id_category != null) {
			List<Topic_E> listTopicsByCategoryId = topicDAO.getTopicsByCategoryId(Integer.parseInt(id_category));
			request.setAttribute("category", nombre_category);
			request.setAttribute("listTopicsByCategoryId", listTopicsByCategoryId);
		}
		
		request.getRequestDispatcher("topicByCategory.jsp").forward(request, response);
	}
	
}
