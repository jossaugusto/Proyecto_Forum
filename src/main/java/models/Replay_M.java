package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entitys.Replay_E;
import interfaces.Replay_I;

public class Replay_M implements Replay_I{

	// CRUD operations
	public static final String GET_ALL_REPLIES = "SELECT * FROM respuestas WHERE flgstate = 1;";
	public static final String GET_REPLY_BY_ID= "SELECT id_respuesta, id_tema, id_usuario, contenido, fecha_publicacion, es_respuesta_aceptada, id_respuesta_padre FROM respuestas WHERE id_respuesta = ? and flgstate = 1;";
	public static final String CREATE_REPLY = "INSERT INTO respuestas (id_tema, id_usuario, contenido) VALUES (?,?,?);";
	public static final String UPDATE_REPLY = "UPDATE respuestas SET id_tema = ?, id_usuario = ?, contenido = ? WHERE id_respuesta = ?;";
	public static final String DELETE_REPLY = "UPDATE respuestas SET flgstate = 0 WHERE id_respuesta = ?;";
	public static final String GET_REPLIES_BY_TOPIC_ID = "SELECT id_respuesta, id_tema, id_usuario, contenido, fecha_publicacion, es_respuesta_aceptada, id_respuesta_padre FROM respuestas WHERE id_tema = ? and flgstate = 1;";
	public static final String GET_REPLIES_BY_USER_ID = "SELECT id_respuesta, id_tema, id_usuario, contenido, fecha_publicacion, es_respuesta_aceptada, id_respuesta_padre FROM respuestas WHERE id_usuario = ? and flgstate = 1;";
	
	// Ready
	@Override
	public List<Replay_E> getAllReplies() {
		List<Replay_E> listReplies = new ArrayList<Replay_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_REPLIES);
			rs = ps.executeQuery();
			while (rs.next()) {
				Replay_E reply = new Replay_E();
				reply.setId_respuesta(rs.getInt("id_respuesta"));
				reply.setId_tema(rs.getInt("id_tema"));
				reply.setId_usuario(rs.getInt("id_usuario"));
				reply.setContenido(rs.getString("contenido"));
				reply.setFecha_publicacion(rs.getDate("fecha_publicacion"));
				reply.setEs_respuesta_aceptada(rs.getBoolean("es_respuesta_aceptada"));
				reply.setId_respuesta_padre(rs.getInt("id_respuesta_padre"));
				listReplies.add(reply);
			}
		} catch (Exception e) {
			System.out.println("Error getting all replies>>> " + e.getMessage());
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
		return listReplies;
	}

	// Ready
	@Override
	public Replay_E getReplyById(int id) {
		Replay_E reply = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_REPLY_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				reply = new Replay_E();
				reply.setId_respuesta(rs.getInt("id_respuesta"));
				reply.setId_tema(rs.getInt("id_tema"));
				reply.setId_usuario(rs.getInt("id_usuario"));
				reply.setContenido(rs.getString("contenido"));
				reply.setFecha_publicacion(rs.getDate("fecha_publicacion"));
				reply.setEs_respuesta_aceptada(rs.getBoolean("es_respuesta_aceptada"));
				reply.setId_respuesta_padre(rs.getInt("id_respuesta_padre"));
			}
		} catch (Exception e) {
			System.out.println("Error getting reply by ID>>> " + e.getMessage());
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
		return reply;
	}

	// Ready
	@Override
	public boolean createReply(Replay_E reply) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(CREATE_REPLY);
			ps.setInt(1, reply.getId_tema());
			ps.setInt(2, reply.getId_usuario());
			ps.setString(3, reply.getContenido());
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) result = true;
		} catch (Exception e) {
			System.out.println("Error creating reply>>> " + e.getMessage());
			return false;
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
	public boolean updateReply(Replay_E reply) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;
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
			System.out.println("Error updating reply>>> " + e.getMessage());
			return false;
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
	public boolean deleteReply(int id) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(DELETE_REPLY);
			ps.setInt(1, id);
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) result = true;
		} catch (Exception e) {
			System.out.println("Error deleting reply>>> " + e.getMessage());
			return false;
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
	public List<Replay_E> getRepliesByTopicId(int topicId) {
		List<Replay_E> listReplies = new ArrayList<Replay_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_REPLIES_BY_TOPIC_ID);
			ps.setInt(1, topicId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Replay_E reply = new Replay_E();
				reply.setId_respuesta(rs.getInt("id_respuesta"));
				reply.setId_tema(rs.getInt("id_tema"));
				reply.setId_usuario(rs.getInt("id_usuario"));
				reply.setContenido(rs.getString("contenido"));
				reply.setFecha_publicacion(rs.getDate("fecha_publicacion"));
				reply.setEs_respuesta_aceptada(rs.getBoolean("es_respuesta_aceptada"));
				reply.setId_respuesta_padre(rs.getInt("id_respuesta_padre"));
				listReplies.add(reply);
			}
		} catch (Exception e) {
			System.out.println("Error getting replies by topic ID>>> " + e.getMessage());
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
		return listReplies;
	}

	// Ready
	@Override
	public List<Replay_E> getRepliesByUserId(int userId) {
		List<Replay_E> listReplies = new ArrayList<Replay_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_REPLIES_BY_USER_ID);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Replay_E reply = new Replay_E();
				reply.setId_respuesta(rs.getInt("id_respuesta"));
				reply.setId_tema(rs.getInt("id_tema"));
				reply.setId_usuario(rs.getInt("id_usuario"));
				reply.setContenido(rs.getString("contenido"));
				reply.setFecha_publicacion(rs.getDate("fecha_publicacion"));
				reply.setEs_respuesta_aceptada(rs.getBoolean("es_respuesta_aceptada"));
				reply.setId_respuesta_padre(rs.getInt("id_respuesta_padre"));
				listReplies.add(reply);
			}
		} catch (Exception e) {
			System.out.println("Error getting replies by user ID>>> " + e.getMessage());
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
		return listReplies;
	}

}
