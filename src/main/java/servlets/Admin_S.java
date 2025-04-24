package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entitys.User_E;

@WebServlet("/Admin_S")
public class Admin_S extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Admin_S() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		HttpSession session = request.getSession();
		User_E user = (User_E) session.getAttribute("currentUser");

		if (user != null) {
			request.getRequestDispatcher("InitialConfi_S").forward(request, response);
//			String userType = user.getTipo_usuario();
//			switch (userType) {
//			case "admin":
//				request.setAttribute("message", "Welcome Admin");
//				request.getRequestDispatcher("index.jsp").forward(request, response);
//				break;
//			case "estudiante":
//				request.setAttribute("message", "Welcome Student");
//				request.getRequestDispatcher("index.jsp").forward(request, response);
//				break;
//			case "profesor":
//				request.setAttribute("message", "Welcome Professor");
//				request.getRequestDispatcher("index.jsp").forward(request, response);
//				break;
//			default:
//				System.out.println("Invalid user type>>>");
//			}
		} else {
			request.setAttribute("message", "Debe iniciar sesion");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}
	
	
}
