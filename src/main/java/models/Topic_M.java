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
	
	public static final String GET_ALL_TOPICS = "SELECT  t.id_tema, t.titulo, t.contenido, t.id_usuario, t.id_categoria, t.fecha_publicacion, t.estado, t.vistas, u.nombre as nombreUsuario, u.apellido as apellidoUsuario, c.nombre as nombreCategoria\r\n"
			+ "FROM temas t\r\n"
			+ "INNER JOIN usuarios u ON t.id_usuario = u.id_usuario\r\n"
			+ "INNER JOIN categorias c ON t.id_categoria = c.id_categoria\r\n"
			+ "WHERE t.flgstate = 1\r\n"
			+ "ORDER BY id_tema DESC;";
	public static final String GET_ALL_DELETED_TOPICS = "SELECT  t.id_tema, t.titulo, t.contenido, t.id_usuario, t.id_categoria, t.fecha_publicacion, t.estado, t.vistas, u.nombre as nombreUsuario, u.apellido as apellidoUsuario, c.nombre as nombreCategoria\r\n"
			+ "FROM temas t\r\n"
			+ "INNER JOIN usuarios u ON t.id_usuario = u.id_usuario\r\n"
			+ "INNER JOIN categorias c ON t.id_categoria = c.id_categoria\r\n"
			+ "WHERE t.flgstate = 0\r\n"
			+ "ORDER BY id_tema DESC;";
	public static final String GET_TOPIC_BY_ID= "SELECT  t.id_tema, t.titulo, t.contenido, t.id_usuario, t.id_categoria, t.fecha_publicacion, t.estado, t.vistas, u.nombre as nombreUsuario, u.apellido as apellidoUsuario, c.nombre as nombreCategoria\r\n"
			+ "FROM temas t\r\n"
			+ "INNER JOIN usuarios u ON t.id_usuario = u.id_usuario\r\n"
			+ "INNER JOIN categorias c ON t.id_categoria = c.id_categoria\r\n"
			+ "WHERE t.id_tema = ? AND t.flgstate = 1;";
	public static final String CREATE_TOPIC = "INSERT INTO temas (titulo, contenido, id_usuario, id_categoria) VALUES (?,?,?,?);";
	public static final String UPDATE_TOPIC = "UPDATE temas SET titulo = ?, contenido = ?, id_categoria = ?, estado = ? WHERE id_tema = ?;";
	public static final String DELETE_USER = "UPDATE temas SET flgstate = 0 WHERE id_tema = ?;";
	public static final String RESTORE_USER = "UPDATE temas SET flgstate = 1 WHERE id_tema = ?;";
	public static final String GET_TOPICS_BY_CATEGORY_ID = "SELECT t.id_tema, t.titulo, t.contenido, t.id_usuario, t.id_categoria, t.fecha_publicacion, t.estado, t.vistas, u.nombre as nombreUsuario, u.apellido as apellidoUsuario, c.nombre as nombreCategoria\r\n"
			+ "FROM temas t\r\n"
			+ "INNER JOIN usuarios u ON t.id_usuario = u.id_usuario\r\n"
			+ "INNER JOIN categorias c ON t.id_categoria = c.id_categoria\r\n"
			+ "WHERE t.id_categoria = ? AND t.flgstate = 1;";
	public static final String GET_TOPICS_BY_USER_ID = "SELECT t.id_tema, t.titulo, t.contenido, t.id_usuario, t.id_categoria, t.fecha_publicacion, t.estado, t.vistas, c.nombre as nombreCategoria\r\n"
			+ "FROM temas t\r\n"
			+ "INNER JOIN categorias c ON t.id_categoria = c.id_categoria\r\n"
			+ "WHERE t.id_usuario = ? and t.flgstate = 1;";
	public static final String GET_TOPICS_BY_SEARCH = "SELECT t.id_tema, t.titulo, t.contenido, t.id_usuario, t.id_categoria, t.fecha_publicacion, t.estado, t.vistas, u.nombre as nombreUsuario, u.apellido as apellidoUsuario, c.nombre as nombreCategoria\r\n"
			+ "FROM temas t\r\n"
			+ "INNER JOIN usuarios u ON t.id_usuario = u.id_usuario\r\n"
			+ "INNER JOIN categorias c ON t.id_categoria = c.id_categoria\r\n"
			+ "WHERE (t.titulo LIKE ? OR t.contenido LIKE ? OR c.nombre LIKE ? OR t.estado LIKE ? OR u.nombre LIKE ? OR u.apellido LIKE ?) AND t.flgstate = 1;";
	public static final String GET_DELETED_TOPICS_BY_SEARCH = "SELECT t.id_tema, t.titulo, t.contenido, t.id_usuario, t.id_categoria, t.fecha_publicacion, t.estado, t.vistas, u.nombre as nombreUsuario, u.apellido as apellidoUsuario, c.nombre as nombreCategoria\r\n"
			+ "FROM temas t\r\n"
			+ "INNER JOIN usuarios u ON t.id_usuario = u.id_usuario\r\n"
			+ "INNER JOIN categorias c ON t.id_categoria = c.id_categoria\r\n"
			+ "WHERE (t.titulo LIKE ? OR t.contenido LIKE ? OR c.nombre LIKE ? OR t.estado LIKE ? OR u.nombre LIKE ? OR u.apellido LIKE ?) AND t.flgstate = 0;";
	public static final String COUNT_TOPICS = "SELECT COUNT(*) FROM temas WHERE flgstate = 1;";
	
	// Ready
	@Override
	public List<Topic_E> getAllTopics() {
		List<Topic_E> listTopics = new ArrayList<Topic_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_TOPICS);
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
			System.out.println("Error getting all topics>>> " + e.getMessage());
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
		return listTopics;
	}

	@Override
	public List<Topic_E> getAllDeletedTopics() {
		List<Topic_E> listTopics = new ArrayList<Topic_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_DELETED_TOPICS);
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
			System.out.println("Error getting all deleted topics>>> " + e.getMessage());
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
		return listTopics;
	}
	
	@Override
	public List<Topic_E> getAllTopicsBySearch(String keyword) {
	    List<Topic_E> listTopics = new ArrayList<Topic_E>();
	    Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_TOPICS_BY_SEARCH);
			String searchKeyword = "%" + keyword + "%";
			ps.setString(1, searchKeyword);
			ps.setString(2, searchKeyword);
			ps.setString(3, searchKeyword);
			ps.setString(4, searchKeyword);
			ps.setString(5, searchKeyword);
			ps.setString(6, searchKeyword);
			
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
		return listTopics;
	}
	
	@Override
	public List<Topic_E> getAllDeletedTopicsBySearch(String keyword) {
	    List<Topic_E> listTopics = new ArrayList<Topic_E>();
	    Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_DELETED_TOPICS_BY_SEARCH);
			String searchKeyword = "%" + keyword + "%";
			ps.setString(1, searchKeyword);
			ps.setString(2, searchKeyword);
			ps.setString(3, searchKeyword);
			ps.setString(4, searchKeyword);
			ps.setString(5, searchKeyword);
			ps.setString(6, searchKeyword);
			
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
		return listTopics;
	}
	
	// Ready
	@Override
	public Topic_E getTopicById(int id) {
		Topic_E topic = null;
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			cn = MySQLConnection.getConexion();
			ps = cn.prepareStatement(GET_TOPIC_BY_ID);
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
			System.out.println("Error getting topic>>>" + e.getMessage());
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
		return topic;
	}

	// Ready
	@Override
	public boolean createTopic(Topic_E topic) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;

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
			System.out.println("Error creating topic>>> " + e.getMessage());
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
	public boolean updateTopic(Topic_E topic) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(UPDATE_TOPIC);
			ps.setString(1, topic.getTitulo());
			ps.setString(2, topic.getContenido());
			ps.setInt(3, topic.getId_categoria());
			ps.setString(4, topic.getEstado());
			ps.setInt(5, topic.getId_tema());

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
	public boolean deleteTopic(int id) {
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
	
	@Override
	public boolean restoreTopic(int id) {
		boolean success = false;
        Connection cn = null;
        PreparedStatement psm = null;
        try {
            cn = MySQLConnection.getConexion();
            psm = cn.prepareStatement(RESTORE_USER);
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
	public List<Topic_E> getTopicsByCategoryId(int categoryId) {
		List<Topic_E> listTopics = new ArrayList<Topic_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
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
			System.out.println("Error getting topics by category ID>>> " + e.getMessage());
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
		return listTopics;
	}

	// Ready
	@Override
	public List<Topic_E> getTopicsByUserId(int userId) {
		List<Topic_E> listTopics = new ArrayList<Topic_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
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
			System.out.println("Error getting topics by category ID>>> " + e.getMessage());
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
		return listTopics;
	}

	public void updateTopicViews(int id_tema) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement("UPDATE temas SET vistas = vistas + 1 WHERE id_tema = ?;");
			ps.setInt(1, id_tema);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error updating views>>> " + e.getMessage());
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
	}

	@Override
	public int countTopics() {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(COUNT_TOPICS);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("Error counting topics>>> " + e.getMessage());
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
		return count;
	}




}
