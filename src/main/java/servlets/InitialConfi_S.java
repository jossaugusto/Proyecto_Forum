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

@WebServlet("/InitialConfi_S")
public class InitialConfi_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InitialConfi_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Topic_I topicDAO = daoFactory.getTopic();
		List<Topic_E> listTopics = topicDAO.getAllTopics();
		
		request.setAttribute("listTopics", listTopics);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
