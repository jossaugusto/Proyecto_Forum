package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entitys.Category_E;
import interfaces.Category_I;


@WebServlet("/Category_S")
public class Category_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Category_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "viewCategories":
				viewCategories(request, response);
				break;
			case "listCategories":
				listCategory(request, response);
				break;
			default:
				System.out.println("Accion no reconocida");
		}
	}

	private void listCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Category_I categoryDAO = daoFactory.getCategory();
		List<Category_E> listCategories = categoryDAO.getAllCategories();
		request.setAttribute("listCategories", listCategories);
		request.getRequestDispatcher("temaNuevo.jsp").forward(request, response);
	}

	private void viewCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Category_I categoryDAO = daoFactory.getCategory();
		List<Category_E> listCategories = categoryDAO.getAllCategories();
		
		request.setAttribute("listCategories", listCategories);
		request.getRequestDispatcher("categorias.jsp").forward(request, response);
	}

}
