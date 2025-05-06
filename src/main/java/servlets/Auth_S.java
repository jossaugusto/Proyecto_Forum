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
import entitys.User_E;
import interfaces.Notification_I;
import interfaces.User_I;

@WebServlet("/Auth_S")
public class Auth_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Auth_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
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

	DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	User_I userDAO = daoFactory.getUser();
	Notification_I notificationDAO = daoFactory.getNotification();
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		boolean result = userDAO.validateUser(email, password);
		
		if (result) {
			User_E user = userDAO.getUserByEmail(email);

			if (user.getFlgstate() == 0) {
	            request.setAttribute("deletedAccount", true);
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			HttpSession session = request.getSession();
			
			session.setAttribute("currentUser", user);
			request.getRequestDispatcher("InitialConfi_S").forward(request, response);
			
		} else {
			request.setAttribute("message", "usuario y/o contraseña incorrectos");
			request.getRequestDispatcher("jsp/autenticacion/login.jsp").forward(request, response);
		}
		
	}
		

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String tipo_usuario = request.getParameter("tipo_usuario");
		
		User_E user = new User_E();
		user.setNombre(name);
		user.setApellido(lastname);
		user.setEmail(email);
		user.setPassword(password);
		user.setTipo_usuario(tipo_usuario);

		boolean result = userDAO.createUser(user);
		
		if (result) {		
			User_E createdUser = userDAO.getUserByEmail(email);
			int idCreatedUser = createdUser.getId_usuario();
			
			notificationDAO.createNotification(idCreatedUser, "bienvenida","Bienvenido al foro, " + name + "!");
			
			List<User_E> admins = userDAO.getAllUsers("admin", null);
			for (User_E admin : admins) {
				int id_admin = admin.getId_usuario();
				notificationDAO.createNotification(id_admin, "registro","Un nuevo usuario se ha registrado en el foro. " + name + " " + lastname + " con el email: " + email);
			}
			request.setAttribute("exito", "Se registró correctamente el usuario");
			request.getRequestDispatcher("InitialConfi_S").forward(request, response);
		} else {
			request.setAttribute("message", "Error al registrar el usuario");
			request.getRequestDispatcher("jsp/admin/registerUser.jsp").forward(request, response);
		}
	}


}
