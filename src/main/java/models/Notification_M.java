package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entitys.Notification_E;
import interfaces.Notification_I;

public class Notification_M implements Notification_I{

	// Variables globales
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	// Metodo para Exception y finalizar la conexion
	void closeResources(Connection con, PreparedStatement ps, ResultSet rs, Exception e) {
	    System.out.println("Error getting all notification>>> " + e.getMessage());
	    try {
	        if (rs != null) rs.close();
	        if (ps != null) ps.close();
	        if (con != null) con.close();
	    } catch (Exception ex) {
	        System.out.println("Error closing resources >>> " + ex.getMessage());
	    }
	}

	// Constantes DB
	public static final String GET_ALL_NOTIFICATIONS = "SELECT id_notificacion, id_usuario_destino, tipo_notificacion, contenido, fecha FROM notificaciones;";
	public static final String GET_NOTIFICATION_BY_ID = "SELECT id_notificacion, id_usuario_destino, tipo_notificacion, contenido, fecha, leida FROM notificaciones WHERE id_notificacion = ?;";
	public static final String CREATE_NOTIFICATION = "INSERT INTO notificaciones (id_usuario_destino, tipo_notificacion, contenido) VALUES (?, ?, ?);";
	public static final String MARK_AS_READ = "UPDATE notificaciones SET leida = true WHERE id_notificacion = ?;";
	public static final String DELETE_NOTIFICATION = "UPDATE notificaciones SET flgstate = 0 WHERE id_notificacion = ?;";
	public static final String GET_NOTIFICATIONS_BY_USER_ID = "SELECT id_notificacion, id_usuario_destino, tipo_notificacion, contenido, fecha FROM notificaciones WHERE id_usuario_destino = ?;";
	public static final String GET_UNREAD_NOTIFICATIONS_BY_USER_ID = "SELECT id_notificacion, id_usuario_destino, tipo_notificacion, contenido, fecha FROM notificaciones WHERE id_usuario_destino = ? AND leida = 0;";
	public static final String COUNT_UNREAD_NOTIFICATIONS = "SELECT COUNT(*) FROM notificaciones WHERE id_usuario_destino = ? AND leida = 0;";
	
	//------------------------------------------------
	
	@Override
	public List<Notification_E> getAllNotifications() {	
		List<Notification_E> listNotifications = new ArrayList<Notification_E>();
		
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_NOTIFICATIONS);
			rs = ps.executeQuery();
			while (rs.next()) {
				Notification_E notification = new Notification_E();
				notification.setId_notificacion(rs.getInt("id_notificacion"));
				notification.setId_usuario_destino(rs.getInt("id_usuario_destino"));
				notification.setTipo_notificacion(rs.getString("tipo_notificacion"));
				notification.setContenido(rs.getString("contenido"));
				notification.setFecha(rs.getTimestamp("fecha"));
				listNotifications.add(notification);
				}
			} catch (Exception e) {
				closeResources(con, ps, rs, e);
			}
		return listNotifications;
	}

	@Override
	public Notification_E getNotificationById(int id) {
		Notification_E notification = null;
		
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_NOTIFICATION_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				notification = new Notification_E();
				notification.setId_notificacion(rs.getInt("id_notificacion"));
				notification.setId_usuario_destino(rs.getInt("id_usuario_destino"));
				notification.setTipo_notificacion(rs.getString("tipo_notificacion"));
				notification.setContenido(rs.getString("contenido"));
				notification.setFecha(rs.getTimestamp("fecha"));
				notification.setLeida(rs.getBoolean("leida"));
				}
			} catch (Exception e) {
				closeResources(con, ps, rs, e);
			}
		return notification;
	}
	
	@Override
	public boolean createNotification(int userId, String type, String content) {
		boolean result = false;
		
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(CREATE_NOTIFICATION);
			ps.setInt(1, userId);
			ps.setString(2, type);
			ps.setString(3, content);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				result = true;
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		}
		return result;
	}

	@Override
	public boolean deleteNotification(int id) {
		boolean result = false;
		
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(DELETE_NOTIFICATION);
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				result = true;
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		}
		return result;
	}

	@Override
	public List<Notification_E> getNotificationsByUserId(int userId, boolean read) {
		String sql = null;
		
		if(read) {
			sql = GET_NOTIFICATIONS_BY_USER_ID;
		} else {
			sql = GET_UNREAD_NOTIFICATIONS_BY_USER_ID;
		}

		List<Notification_E> listNotifications = new ArrayList<Notification_E>();
		
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Notification_E notification = new Notification_E();
				notification.setId_notificacion(rs.getInt("id_notificacion"));
				notification.setId_usuario_destino(rs.getInt("id_usuario_destino"));
				notification.setTipo_notificacion(rs.getString("tipo_notificacion"));
				notification.setContenido(rs.getString("contenido"));
				notification.setFecha(rs.getTimestamp("fecha"));
				listNotifications.add(notification);
				}
			} catch (Exception e) {
				closeResources(con, ps, rs, e);
			}
		return listNotifications;
	}

	@Override
	public void markAsRead(int notificationId) {

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(MARK_AS_READ);
			ps.setInt(1, notificationId);
			ps.executeUpdate();
			
		} catch (Exception e) {
			closeResources(con, ps, rs, e);
		}
	}

	@Override
	public int countUnreadNotifications(int userId) {
		int count = 0;
		
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(COUNT_UNREAD_NOTIFICATIONS);
			ps.setInt(1, userId);
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
