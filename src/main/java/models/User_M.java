package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entitys.User_E;
import interfaces.User_I;

public class User_M implements User_I {

	// Database procedures
	public static final String CREATE_USER = "INSERT INTO usuarios (nombre, apellido, email, password, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
	public static final String GET_ALL_USERS = "SELECT nombre, apellido, email, tipo_usuario, fecha_registro FROM usuarios where flgstate = 1;";
	public static final String VALIDATE_USER = "SELECT * FROM usuarios WHERE email = ? AND password = ? AND flgstate = 1;";
	public static final String UPDATE_USER = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, password = ?, tipo_usuario = ? WHERE id = ?";
	public static final String DELETE_USER = "UPDATE usuarios SET flgstate = 0 WHERE id = ?;";
	public static final String GET_USER_BY_EMAIL = "SELECT id_usuario,nombre, apellido, email, tipo_usuario, fecha_registro FROM usuarios WHERE email = ? AND flgstate = 1;";
	public static final String GET_USER_BY_ID = "SELECT nombre, apellido, email, tipo_usuario, fecha_registro FROM usuarios WHERE id_usuario = ? AND flgstate = 1;";
	
	// Ready
	@Override
	public List<User_E> getAllUsers() {
		List<User_E> listUsers = new ArrayList<User_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_USERS);
			rs = ps.executeQuery();
			while (rs.next()) {
				User_E user = new User_E();
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setTipo_usuario(rs.getString("tipo_usuario"));
				user.setFecha_registro(rs.getDate("fecha_registro"));
				listUsers.add(user);
			}
		} catch (Exception e) {
			System.out.println("Error getting all users>>> " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				System.out.println("Error closing resources>>> " + e.getMessage());
			}
		}
		return listUsers;
	}
	
	// Ready
	@Override
	public User_E getUserById(int id) {
		User_E user = null;
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			cn = MySQLConnection.getConexion();
			ps = cn.prepareStatement(GET_USER_BY_EMAIL);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User_E();
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setTipo_usuario(rs.getString("tipo_usuario"));
				user.setFecha_registro(rs.getDate("fecha_registro"));
			}
		} catch (Exception e) {
			System.out.println("Error getting user>>>" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				System.out.println("Error closing resources>>> " + e.getMessage());
			}
		}
		return user;
	}

	// Ready
	@Override
	public User_E getUserByEmail(String email) {
		User_E user = null;
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			cn = MySQLConnection.getConexion();
			ps = cn.prepareStatement(GET_USER_BY_EMAIL);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User_E();
				user.setId_usuario(rs.getInt("id_usuario"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setTipo_usuario(rs.getString("tipo_usuario"));
				user.setFecha_registro(rs.getDate("fecha_registro"));
			}
		} catch (Exception e) {
			System.out.println("Error getting user>>>" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				System.out.println("Error closing resources>>> " + e.getMessage());
			}
		}
		return user;
	}

	// Ready
	@Override
	public boolean createUser(User_E user) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(CREATE_USER);
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellido());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getTipo_usuario());

			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error creating user>>> " + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				System.out.println("Error closing resources>>> " + e.getMessage());
			}
		}
		return result;
	}
	
	// Ready
	@Override
	public boolean updateUser(User_E user) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(UPDATE_USER);
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellido());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getTipo_usuario());
			ps.setInt(6, user.getId_usuario());

			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error updating user>>> " + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				System.out.println("Error closing resources>>> " + e.getMessage());
			}
		}
		return result;
	}

	// Ready
	@Override
	public boolean deleteUser(int id) {
		boolean success = false;
        Connection cn = null;
        PreparedStatement psm = null;
        try {
            cn = MySQLConnection.getConexion();
            psm = cn.prepareStatement(DELETE_USER);
            psm.setInt(1, id);
            success = psm.executeUpdate() > 0;
        } catch (Exception e) {
			System.out.println("Error deleting user>>>" + e.getMessage());
        } finally {
            try {
                if (psm != null) psm.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
            	System.out.println("Error closing resources>>> " + e.getMessage());
            }
        }
        return success;
	}

	// Ready
	@Override
	public boolean validateUser(String email, String password) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(VALIDATE_USER);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error validating user>>> " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				System.out.println("Error closing resources>>> " + e.getMessage());
			}
		}
		return result;
	}

}
