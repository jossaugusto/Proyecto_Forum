package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entitys.Reply_E;
import interfaces.Reply_I;

public class Reply_M implements Reply_I{

	// Variables globales
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	// Metodo para Exception y finalizar la conexion
	void closeResources(Connection con, PreparedStatement ps, ResultSet rs, Exception e) {
	    System.out.println("Error getting all replies>>> " + e.getMessage());
	    try {
	        if (rs != null) rs.close();
	        if (ps != null) ps.close();
	        if (con != null) con.close();
	    } catch (Exception ex) {
	        System.out.println("Error closing resources >>> " + ex.getMessage());
	    }
	}
	
	// CRUD operations
	public static final String GET_ALL_REPLIES = "CALL sp_respuesta_get_all()";
	public static final String GET_REPLY_BY_ID = "CALL sp_respuesta_get_by_id(?)";
	public static final String CREATE_REPLY = "CALL sp_respuesta_create(?, ?, ?)";
	public static final String CREATE_SUB_REPLY = "CALL sp_respuesta_create_sub(?, ?, ?, ?)";
	public static final String UPDATE_REPLY = "CALL sp_respuesta_update(?, ?, ?, ?)";
	public static final String DELETE_REPLY = "CALL sp_respuesta_delete(?)";
	public static final String GET_REPLIES_BY_TOPIC_ID = "CALL sp_respuesta_get_by_topic_id(?)";
	public static final String GET_REPLIES_BY_TOPIC_ID_AND_PARENT_ID_NOT_NULL = "CALL sp_respuesta_get_by_topic_id_and_with_parent(?)";
	public static final String GET_REPLIES_BY_USER_ID = "CALL sp_respuesta_get_by_user_id(?)";
	public static final String GET_QUANTITY_REPLY_BY_TOPIC_ID = "CALL sp_respuesta_get_quantity_by_topic(?)";
	public static final String GET_REPLIES_BY_PARENT_ID = "CALL sp_respuesta_get_by_parent_id(?)";
	public static final String COUNT_REPLIES = "CALL sp_respuesta_count_all()";

	
	// Ready
	@Override
	public List<Reply_E> getAllReplies() {
		List<Reply_E> listReplies = new ArrayList<Reply_E>();

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_REPLIES);
			rs = ps.executeQuery();
			while (rs.next()) {
				Reply_E reply = new Reply_E();
				reply.setId_respuesta(rs.getInt("id_respuesta"));
				reply.setId_tema(rs.getInt("id_tema"));
				reply.setId_usuario(rs.getInt("id_usuario"));
				reply.setContenido(rs.getString("contenido"));
				reply.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
				reply.setEs_respuesta_aceptada(rs.getBoolean("es_respuesta_aceptada"));
				reply.setId_respuesta_padre(rs.getInt("id_respuesta_padre"));
				listReplies.add(reply);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		}
		
		return listReplies;
	}

	// Ready
	@Override
	public Reply_E getReplyById(int id) {
		Reply_E reply = null;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_REPLY_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				reply = new Reply_E();
				reply.setId_respuesta(rs.getInt("id_respuesta"));
				reply.setId_tema(rs.getInt("id_tema"));
				reply.setId_usuario(rs.getInt("id_usuario"));
				reply.setContenido(rs.getString("contenido"));
				reply.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
				reply.setEs_respuesta_aceptada(rs.getBoolean("es_respuesta_aceptada"));
				reply.setId_respuesta_padre(rs.getInt("id_respuesta_padre"));
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		}
		return reply;
	}

	// Ready
	@Override
	public boolean createReply(Reply_E reply) {
		boolean result = false;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(CREATE_REPLY);
			ps.setInt(1, reply.getId_tema());
			ps.setInt(2, reply.getId_usuario());
			ps.setString(3, reply.getContenido());
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) result = true;
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
			return false;
		}
		return result;
	}

	// Ready
	@Override
	public boolean updateReply(Reply_E reply) {
		boolean result = false;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(UPDATE_REPLY);
			ps.setInt(1, reply.getId_tema());
			ps.setInt(2, reply.getId_usuario());
			ps.setString(3, reply.getContenido());
			ps.setInt(4, reply.getId_respuesta());
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) result = true;
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		}
		return result;
	}

	// Ready
	@Override
	public boolean deleteReply(int id) {
		boolean result = false;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(DELETE_REPLY);
			ps.setInt(1, id);
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) result = true;
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		}
		return result;
	}

	// Ready
	@Override
	public List<Reply_E> getRepliesByTopicId(int topicId) {
		List<Reply_E> listReplies = new ArrayList<Reply_E>();

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_REPLIES_BY_TOPIC_ID);
			ps.setInt(1, topicId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Reply_E reply = new Reply_E();
				reply.setId_respuesta(rs.getInt("id_respuesta"));
				reply.setId_tema(rs.getInt("id_tema"));
				reply.setId_usuario(rs.getInt("id_usuario"));
				reply.setContenido(rs.getString("contenido"));
				reply.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
				reply.setEs_respuesta_aceptada(rs.getBoolean("es_respuesta_aceptada"));
				reply.setId_respuesta_padre(rs.getInt("id_respuesta_padre"));
				reply.setNombreUsuario(rs.getString("nombreUsuario"));
				reply.setApellidoUsuario(rs.getString("apellidoUsuario"));
				listReplies.add(reply);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		}
		return listReplies;
	}

	// Ready
	@Override
	public List<Reply_E> getRepliesByUserId(int userId) {
		List<Reply_E> listReplies = new ArrayList<Reply_E>();

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_REPLIES_BY_USER_ID);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Reply_E reply = new Reply_E();
				reply.setId_respuesta(rs.getInt("id_respuesta"));
				reply.setId_tema(rs.getInt("id_tema"));
				reply.setId_usuario(rs.getInt("id_usuario"));
				reply.setContenido(rs.getString("contenido"));
				reply.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
				reply.setEs_respuesta_aceptada(rs.getBoolean("es_respuesta_aceptada"));
				reply.setId_respuesta_padre(rs.getInt("id_respuesta_padre"));
				listReplies.add(reply);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		} 
		return listReplies;
	}
	
	@Override
	public int getQuantityReplyByTopicId(int id_tema) {
		int cantidad = 0;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_QUANTITY_REPLY_BY_TOPIC_ID);
			ps.setInt(1, id_tema);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getInt("cantidad");
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		} 
		return cantidad;
	}

	
	@Override
	public List<Reply_E> getRepliesByParentId(int parentId) {
		List<Reply_E> listReplies = new ArrayList<Reply_E>();

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_REPLIES_BY_PARENT_ID);
			ps.setInt(1, parentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Reply_E reply = new Reply_E();
				reply.setId_respuesta(rs.getInt("id_respuesta"));
				reply.setId_tema(rs.getInt("id_tema"));
				reply.setId_usuario(rs.getInt("id_usuario"));
				reply.setContenido(rs.getString("contenido"));
				reply.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
				reply.setEs_respuesta_aceptada(rs.getBoolean("es_respuesta_aceptada"));
				reply.setId_respuesta_padre(rs.getInt("id_respuesta_padre"));
				reply.setNombreUsuario(rs.getString("nombreUsuario"));
				reply.setApellidoUsuario(rs.getString("apellidoUsuario"));
				listReplies.add(reply);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		}
		return listReplies;
	}

	@Override
	public boolean createSubReply(Reply_E reply) {
		boolean result = false;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(CREATE_SUB_REPLY);
			ps.setInt(1, reply.getId_tema());
			ps.setInt(2, reply.getId_usuario());
			ps.setString(3, reply.getContenido());
			ps.setInt(4, reply.getId_respuesta_padre());
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) result = true;
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
			return false;
		}
		return result;
	}

	@Override
	public int countReplies() {
		int count = 0;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(COUNT_REPLIES);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		}
		return count;
	}

}
