package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import entitys.User_E;
import interfaces.User_I;

@WebServlet("/Auth_S")
public class Auth_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Auth_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type") != null ? request.getParameter("type") : "login";
		switch (type) {
			case "login":
				login(request, response);
				break;
			case "register":
				register(request, response);
				break;
			default:
				System.out.println("Invalid type parameter>>>");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		User_I userDAO = daoFactory.getUser();
		boolean result = userDAO.validateUser(email, password);
		
		if (result) {
			User_E user = userDAO.getUserByEmail(email);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.getRequestDispatcher("Admin_S").forward(request, response);
			
		} else {
			request.setAttribute("message", "usuario y/o contraseña incorrectos");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}
		

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String tipo_usario = request.getParameter("tipo_usuario");
		
		User_E user = new User_E();
		user.setNombre(name);
		user.setApellido(lastname);
		user.setEmail(email);
		user.setPassword(password);
		user.setTipo_usuario(tipo_usario);
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		User_I userDAO = daoFactory.getUser();
		boolean result = userDAO.createUser(user);
		if (result) {;
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.getRequestDispatcher("Admin_S").forward(request, response);
		} else {
			System.out.println("Error creating user");
		}
	}


}
