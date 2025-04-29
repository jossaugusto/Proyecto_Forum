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
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
		
		String action = request.getParameter("action");
		switch (action) {
			case "updateProfile":
				updateProfile(request, response);
				break;
			default:
				response.sendRedirect("error.jsp");
		}
	}

	private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            User_E currentUser = (User_E) session.getAttribute("currentUser");

            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            String nombre = request.getParameter("nombre").trim();
            String apellido = request.getParameter("apellido").trim();
            String email = request.getParameter("email").trim();
            String password = request.getParameter("password").trim();

            // Actualizamos los datos
            currentUser.setNombre(nombre);
            currentUser.setApellido(apellido);
            currentUser.setEmail(email);
            currentUser.setId_usuario(id_usuario);
            if (!password.isEmpty()) {
                currentUser.setPassword(password); // Asumiendo que ya encriptas o proteges el password en UserDAO
            }

    		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    		User_I userDAO = daoFactory.getUser();
    		boolean updated = userDAO.updateUser(currentUser, !password.isEmpty());

            if (updated) {
                session.setAttribute("currentUser", currentUser); // Actualizar sesión
                response.sendRedirect(request.getContextPath() + "/perfil.jsp?success=true");
            } else {
                request.setAttribute("error", "Hubo un error al actualizar el perfil.");
                request.getRequestDispatcher("/editarPerfil.jsp").forward(request, response);
            }
    }
}
