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
import entitys.Topic_E;
import entitys.User_E;
import interfaces.Category_I;
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
		
		List<Topic_E> listTopics = topicDAO.getAllTopics();
		List<Category_E> listCategories = categoryDAO.getAllCategories();
		
		request.setAttribute("listTopics", listTopics);
		request.setAttribute("listCategories", listCategories);
		
		if (currentUser != null) {
		    // Datos exclusivos para usuarios registrados
		    List<Topic_E> listTopicsByUser = topicDAO.getTopicsByUserId(currentUser.getId_usuario());
		    request.setAttribute("listTopicsByUser", listTopicsByUser);

		    // Agrega también otros datos como estadísticas o temas vistos si los necesitas
		}

		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
