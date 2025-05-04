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

@WebServlet("/User_S")
public class User_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public User_S() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
        User_E currentUser = (User_E) session.getAttribute("currentUser");
        
        if(currentUser == null) {
        	request.setAttribute("message", "Debes iniciar sesión.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "UpdateProfile":
				updateProfile(request, response);
				break;
			default:
				System.out.println("Acción no válida.");
		}
	}

	private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            User_E currentUser = (User_E) session.getAttribute("currentUser");

    		String name = request.getParameter("name");
    		String lastName = request.getParameter("lastName");
    		String email = request.getParameter("email");
    		String password = request.getParameter("password");

            // Actualizamos los datos
            currentUser.setNombre(name);
            currentUser.setApellido(lastName);
            currentUser.setEmail(email);
            currentUser.setId_usuario(currentUser.getId_usuario());
            currentUser.setTipo_usuario(currentUser.getTipo_usuario());
            if (!password.isEmpty()) {
                currentUser.setPassword(password);
            }

    		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    		User_I userDAO = daoFactory.getUser();
    		boolean updated = userDAO.updateUser(currentUser, !password.isEmpty());

            if (updated) {
                session.setAttribute("currentUser", currentUser);
                request.setAttribute("exito", "Perfil actualizado correctamente.");
                request.getRequestDispatcher("profileUser.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Hubo un error al actualizar el perfil.");
                request.getRequestDispatcher("editProfile.jsp").forward(request, response);
            }
    }
}
