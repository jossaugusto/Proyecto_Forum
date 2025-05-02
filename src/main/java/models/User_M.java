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
	
	// Variables globales
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	// Metodo para Exception y finalizar la conexion
	void closeResources(Connection con, PreparedStatement ps, ResultSet rs, Exception e, String txt) {
	    System.out.println("Error getting "+ txt +" >>> " + e.getMessage());
	    try {
	        if (rs != null) rs.close();
	        if (ps != null) ps.close();
	        if (con != null) con.close();
	    } catch (Exception ex) {
	        System.out.println("Error closing resources >>> " + ex.getMessage());
	    }
	}
	
	// Database procedures
    public static final String CREATE_USER = "CALL sp_usuario_create(?, ?, ?, ?, ?)";
    
    public static final String GET_ALL_USERS = "CALL sp_usuario_get_all(?)";
    public static final String GET_USERS_BY_SEARCH = "CALL sp_usuario_search(?,?)";
    
    public static final String GET_ALL_DELETED_USERS = "CALL sp_usuario_get_all_deleted(?)";
    public static final String GET_DELETED_USER_BY_SEARCH = "CALL sp_usuario_search_deleted(?,?)";
    
    public static final String VALIDATE_USER = "CALL sp_usuario_validate(?, ?)";
    
    public static final String UPDATE_USER_AND_PASSWORD = "CALL sp_usuario_update_with_password(?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_USER = "CALL sp_usuario_update(?, ?, ?, ?, ?)";
    
    public static final String DELETE_USER = "CALL sp_usuario_delete(?)";
    public static final String RESTORE_USER = "CALL sp_usuario_restore(?)";
    
    public static final String GET_USER_BY_EMAIL = "CALL sp_usuario_get_by_email(?)";
    public static final String GET_USER_BY_ID = "CALL sp_usuario_get_by_id(?)";
    
    public static final String COUNT_USERS = "CALL sp_usuario_count()";
	

	
	// Lista de usuarios en general, por busqueda o por rol
	@Override
	public List<User_E> getAllUsers(String keyword, String order) {
		String sql = "";
		
		if (keyword != null && !keyword.isEmpty()) {
			sql = GET_USERS_BY_SEARCH;
		} else {
			// Validar que el parámetro "order" sea uno de los valores válidos (ASC o DESC)
	        if (!order.equalsIgnoreCase("ASC") && !order.equalsIgnoreCase("DESC")) {
	            throw new IllegalArgumentException("El orden debe ser 'ASC' o 'DESC'.");
	        }
			sql = GET_ALL_USERS;
		}
		
		List<User_E> listUsers = new ArrayList<User_E>();

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(sql);
			if (keyword != null && !keyword.isEmpty()) {
				String searchKeyword = "%" + keyword + "%";
				ps.setString(1, searchKeyword);
				ps.setString(2, order);
			} else {
				ps.setString(1, order);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				User_E user = new User_E();
				user.setId_usuario(rs.getInt("id_usuario"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setTipo_usuario(rs.getString("tipo_usuario"));
				user.setFecha_registro(rs.getTimestamp("fecha_registro"));
				listUsers.add(user);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "getAllUsers");
		}
			
		return listUsers;
	}
	
	@Override
	public List<User_E> getAllDeletedUsers(String keyword, String order) {
		
		String sql = "";
		if (keyword != null && !keyword.isEmpty()) {
			sql = GET_DELETED_USER_BY_SEARCH;
		} else {
			// Validar que el parámetro "order" sea uno de los valores válidos (ASC o DESC)
	        if (!order.equalsIgnoreCase("ASC") && !order.equalsIgnoreCase("DESC")) {
	            throw new IllegalArgumentException("El orden debe ser 'ASC' o 'DESC'.");
	        }
			sql = GET_ALL_DELETED_USERS;
		}
		
		List<User_E> listUsers = new ArrayList<User_E>();
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(sql);
			if (keyword != null && !keyword.isEmpty()) {
				String searchKeyword = "%" + keyword + "%";
				ps.setString(1, searchKeyword);
				ps.setString(2, order);
			} else {
				ps.setString(1, order);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				User_E user = new User_E();
				user.setId_usuario(rs.getInt("id_usuario"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setTipo_usuario(rs.getString("tipo_usuario"));
				user.setFecha_registro(rs.getTimestamp("fecha_registro"));
				listUsers.add(user);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "getAllDeletedUsers");
		}
		return listUsers;
	}
	
	// Obtener usuario por ID
	@Override
	public User_E getUserById(int id) {
		User_E user = null;

		try {

			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_USER_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User_E();
				user.setId_usuario(rs.getInt("id_usuario"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setTipo_usuario(rs.getString("tipo_usuario"));
				user.setFecha_registro(rs.getTimestamp("fecha_registro"));
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "getUserById");
		}
		return user;
	}
	
	// Obtener usuario por email
	@Override
	public User_E getUserByEmail(String email) {
		User_E user = null;

		try {

			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_USER_BY_EMAIL);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User_E();
				user.setId_usuario(rs.getInt("id_usuario"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setTipo_usuario(rs.getString("tipo_usuario"));
				user.setFecha_registro(rs.getTimestamp("fecha_registro"));
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "getUserByEmail");
		}
		return user;
	}

	// Crear usuario
	@Override
	public boolean createUser(User_E user) {
		boolean result = false;

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
			closeResources(con, ps, rs, e, "createUser");
		}
		return result;
	}
	
	// Actualizar usuario
	@Override
	public boolean updateUser(User_E user , boolean updatePassword) {
	    String sql;
	    if (updatePassword) {
	        sql = UPDATE_USER_AND_PASSWORD;
	    } else {
	        sql = UPDATE_USER;
	    }
		
		boolean result = false;
		
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId_usuario());
			ps.setString(2, user.getNombre());
			ps.setString(3, user.getApellido());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getTipo_usuario());
			if (updatePassword) {
				ps.setString(6, user.getPassword());
			}
			
			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "updateUser");
		}
		return result;
	}

	// Eliminar usuario
	@Override
	public boolean deleteUser(int id) {
		boolean success = false;
        try {
            con = MySQLConnection.getConexion();
            ps = con.prepareStatement(DELETE_USER);
            ps.setInt(1, id);
            success = ps.executeUpdate() > 0;
        } catch (Exception e) {
			closeResources(con, ps, rs, e, "deleteUser");
        }
        return success;
	}

	// Restaurar usuario
	@Override
	public boolean restoreUser(int id) {
		boolean success = false;
        try {
            con = MySQLConnection.getConexion();
            ps = con.prepareStatement(RESTORE_USER);
            ps.setInt(1, id);
            success = ps.executeUpdate() > 0;
        } catch (Exception e) {
			closeResources(con, ps, rs, e, "restoreUser");
        }
        return success;
	}
	
	// Validar usuario
	@Override
	public boolean validateUser(String email, String password) {
		boolean result = false;
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
			closeResources(con, ps, rs, e, "validateUser");
		}
		return result;
	}
	
	// Contador de usuarios
	@Override
	public int countUsers() {
		int count = 0;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(COUNT_USERS);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "countUsers");
		}
		return count;
	}

	




	




	
}
