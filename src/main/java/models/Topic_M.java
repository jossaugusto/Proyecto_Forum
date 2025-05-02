package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entitys.Topic_E;
import interfaces.Topic_I;

public class Topic_M implements Topic_I{
	// Variables globales
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	// Metodo para Exception y finalizar la conexion
	void closeResources(Connection con, PreparedStatement ps, ResultSet rs, Exception e, String txt) {
	    System.out.println("Error getting all"+ txt +">>> " + e.getMessage());
	    try {
	        if (rs != null) rs.close();
	        if (ps != null) ps.close();
	        if (con != null) con.close();
	    } catch (Exception ex) {
	        System.out.println("Error closing resources >>> " + ex.getMessage());
	    }
	}
	
    public static final String GET_ALL_TOPICS = "CALL sp_tema_get_all(?)";
    public static final String GET_TOPICS_BY_SEARCH = "CALL sp_tema_search(?, ?)";
    
    public static final String GET_ALL_DELETED_TOPICS = "CALL sp_tema_get_all_deleted(?)";
    public static final String GET_DELETED_TOPICS_BY_SEARCH = "CALL sp_tema_search_deleted(?,?)";
    
    public static final String GET_TOPIC_BY_ID = "CALL sp_tema_get_by_id(?)";
    
    public static final String CREATE_TOPIC = "CALL sp_tema_create(?,?,?,?)";
    public static final String UPDATE_TOPIC = "CALL sp_tema_update(?,?,?,?,?)";
    public static final String DELETE_USER = "CALL sp_tema_delete(?)";
    public static final String RESTORE_USER = "CALL sp_tema_restore(?)";
    
    public static final String GET_TOPICS_BY_CATEGORY_ID = "CALL sp_tema_get_by_category(?)";
    public static final String GET_TOPICS_BY_USER_ID = "CALL sp_tema_get_by_user(?)";
    
    public static final String COUNT_TOPICS = "CALL sp_tema_count()";
	
	// Ready
	@Override
	public List<Topic_E> getAllTopics(String keyword, String order) {
		String sql = "";
		if (keyword != null && !keyword.isEmpty()) {
			sql = GET_TOPICS_BY_SEARCH;
		} else {
			// Validar que el parámetro "order" sea uno de los valores válidos (ASC o DESC)
	        if (!order.equalsIgnoreCase("ASC") && !order.equalsIgnoreCase("DESC")) {
	            throw new IllegalArgumentException("El orden debe ser 'ASC' o 'DESC'.");
	        }
			sql = GET_ALL_TOPICS;
		}
		
		List<Topic_E> listTopics = new ArrayList<Topic_E>();
		try {
			
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(sql);
			
			if (keyword != null && !keyword.isEmpty()) {
				ps.setString(1, keyword);
				ps.setString(2, order);
			} else {
				ps.setString(1, order);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Topic_E topic = new Topic_E();
				topic.setId_tema(rs.getInt("id_tema"));
				topic.setTitulo(rs.getString("titulo"));
				topic.setContenido(rs.getString("contenido"));
				topic.setId_usuario(rs.getInt("id_usuario"));
				topic.setId_categoria(rs.getInt("id_categoria"));
				topic.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
				topic.setEstado(rs.getString("estado"));
				topic.setVistas(rs.getInt("vistas"));
				topic.setNombreUsuario(rs.getString("nombreUsuario"));
				topic.setApellidoUsuario(rs.getString("apellidoUsuario"));
				topic.setNombreCategoria(rs.getString("nombreCategoria"));
				listTopics.add(topic);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "getAllTopics");
		}
		return listTopics;
	}

	@Override
	public List<Topic_E> getAllDeletedTopics(String keyword, String order) {
		String sql = "";
		
		if (keyword != null && !keyword.isEmpty()) {
			sql = GET_DELETED_TOPICS_BY_SEARCH;
		} else {
			// Validar que el parámetro "order" sea uno de los valores válidos (ASC o DESC)
	        if (!order.equalsIgnoreCase("ASC") && !order.equalsIgnoreCase("DESC")) {
	            throw new IllegalArgumentException("El orden debe ser 'ASC' o 'DESC'.");
	        }
			sql = GET_ALL_DELETED_TOPICS;
		}
		
		List<Topic_E> listTopics = new ArrayList<Topic_E>();
		
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(sql);
			
			if (keyword != null && !keyword.isEmpty()) {
				ps.setString(1, keyword);
				ps.setString(2, order);
			} else {
				ps.setString(1, order);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Topic_E topic = new Topic_E();
				topic.setId_tema(rs.getInt("id_tema"));
				topic.setTitulo(rs.getString("titulo"));
				topic.setContenido(rs.getString("contenido"));
				topic.setId_usuario(rs.getInt("id_usuario"));
				topic.setId_categoria(rs.getInt("id_categoria"));
				topic.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
				topic.setEstado(rs.getString("estado"));
				topic.setVistas(rs.getInt("vistas"));
				topic.setNombreUsuario(rs.getString("nombreUsuario"));
				topic.setApellidoUsuario(rs.getString("apellidoUsuario"));
				topic.setNombreCategoria(rs.getString("nombreCategoria"));
				listTopics.add(topic);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e , "getAllDeletedTopics");
		}
		return listTopics;
	}
	
	// Ready
	@Override
	public Topic_E getTopicById(int id) {
		Topic_E topic = null;

		try {

			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_TOPIC_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				topic = new Topic_E();
				topic.setId_tema(rs.getInt("id_tema"));
				topic.setTitulo(rs.getString("titulo"));
				topic.setContenido(rs.getString("contenido"));
				topic.setId_usuario(rs.getInt("id_usuario"));
				topic.setId_categoria(rs.getInt("id_categoria"));
				topic.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
				topic.setEstado(rs.getString("estado"));
				topic.setVistas(rs.getInt("vistas"));
				topic.setNombreUsuario(rs.getString("nombreUsuario"));
				topic.setApellidoUsuario(rs.getString("apellidoUsuario"));
				topic.setNombreCategoria(rs.getString("nombreCategoria"));
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e , "getTopicById");
		}
		return topic;
	}

	// Ready
	@Override
	public boolean createTopic(Topic_E topic) {
		boolean result = false;

		try {
			System.out.println("Entrando a crear tema");
			
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(CREATE_TOPIC);
			ps.setString(1, topic.getTitulo());
			ps.setString(2, topic.getContenido());
			ps.setInt(3, topic.getId_usuario());
			ps.setInt(4, topic.getId_categoria());

			System.out.println("titulo: " + topic.getTitulo());
			System.out.println("contenido: " + topic.getContenido());
			System.out.println("id_usuario: " + topic.getId_usuario());
			System.out.println("id_categoria: " + topic.getId_categoria());
			
			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "createTopic");
		}
		return result;
	}

	// Ready
	@Override
	public boolean updateTopic(Topic_E topic) {
		boolean result = false;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(UPDATE_TOPIC);
			ps.setInt(1, topic.getId_tema());
			ps.setString(2, topic.getTitulo());
			ps.setString(3, topic.getContenido());
			ps.setInt(4, topic.getId_categoria());
			ps.setString(5, topic.getEstado());

			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "updateTopic");
		}
		return result;
	}

	// Ready
	@Override
	public boolean deleteTopic(int id) {
		boolean success = false;

        try {
            con = MySQLConnection.getConexion();
            ps = con.prepareStatement(DELETE_USER);
            ps.setInt(1, id);
            success = ps.executeUpdate() > 0;
        } catch (Exception e) {
			closeResources(con, ps, rs, e, "deleteTopic");
        }
        return success;
	}
	
	@Override
	public boolean restoreTopic(int id) {
		boolean success = false;

        try {
            con = MySQLConnection.getConexion();
            ps = con.prepareStatement(RESTORE_USER);
            ps.setInt(1, id);
            success = ps.executeUpdate() > 0;
        } catch (Exception e) {
			closeResources(con, ps, rs, e, "restoreTopic");
        }
        return success;
	}

	// Ready
	@Override
	public List<Topic_E> getTopicsByCategoryId(int categoryId) {
		List<Topic_E> listTopics = new ArrayList<Topic_E>();

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_TOPICS_BY_CATEGORY_ID);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Topic_E topic = new Topic_E();
				topic.setId_tema(rs.getInt("id_tema"));
				topic.setTitulo(rs.getString("titulo"));
				topic.setContenido(rs.getString("contenido"));
				topic.setId_usuario(rs.getInt("id_usuario"));
				topic.setId_categoria(rs.getInt("id_categoria"));
				topic.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
				topic.setEstado(rs.getString("estado"));
				topic.setVistas(rs.getInt("vistas"));
				topic.setNombreUsuario(rs.getString("nombreUsuario"));
				topic.setApellidoUsuario(rs.getString("apellidoUsuario"));
				topic.setNombreCategoria(rs.getString("nombreCategoria"));
				listTopics.add(topic);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "getTopicsByCategoryId");
		}
		return listTopics;
	}

	// Ready
	@Override
	public List<Topic_E> getTopicsByUserId(int userId) {
		List<Topic_E> listTopics = new ArrayList<Topic_E>();

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_TOPICS_BY_USER_ID);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Topic_E topic = new Topic_E();
				topic.setId_tema(rs.getInt("id_tema"));
				topic.setTitulo(rs.getString("titulo"));
				topic.setContenido(rs.getString("contenido"));
				topic.setId_usuario(rs.getInt("id_usuario"));
				topic.setId_categoria(rs.getInt("id_categoria"));
				topic.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
				topic.setEstado(rs.getString("estado"));
				topic.setVistas(rs.getInt("vistas"));
				topic.setNombreCategoria(rs.getString("nombreCategoria"));
				listTopics.add(topic);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "getTopicsByUserId");
		}
		return listTopics;
	}

	public void updateTopicViews(int id_tema) {
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement("UPDATE temas SET vistas = vistas + 1 WHERE id_tema = ?;");
			ps.setInt(1, id_tema);
			ps.executeUpdate();
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "updateTopicViews");
		}
	}

	@Override
	public int countTopics() {
		int count = 0;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(COUNT_TOPICS);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "countTopics");
		}
		return count;
	}




}
