package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnection;
import entitys.Category_E;
 import interfaces.Category_I;

public class Category_M implements Category_I{
	
	public static final String GET_ALL_CATEGORIES = "SELECT * FROM categorias WHERE flgstate = 1;";
	public static final String GET_ALL_DELETED_CATEGORIES = "SELECT * FROM categorias WHERE flgstate = 0;";
	public static final String GET_ALL_CATEGORIES_BY_SEARCH = "SELECT * FROM categorias WHERE (nombre LIKE ? OR descripcion LIKE ?) AND flgstate = 1;";
	public static final String GET_ALL_DELETED_CATEGORIES_BY_SEARCH = "SELECT * FROM categorias WHERE (nombre LIKE ? OR descripcion LIKE ?) AND flgstate = 0;";
	public static final String GET_CATEGORY_BY_ID = "SELECT id_categoria, nombre, descripcion, imagen, fecha_creacion FROM categorias WHERE id_categoria = ? AND flgstate = 1;";
	public static final String CREATE_CATEGORY = "INSERT INTO categorias (nombre, descripcion, imagen) VALUES (?, ?, ?);";
	public static final String UPDATE_CATEGORY = "UPDATE categorias SET nombre = ?, descripcion = ?, imagen = ? WHERE id_categoria = ?;";
	public static final String DELETE_CATEGORY = "UPDATE categorias SET flgstate = 0 WHERE id_categoria = ?;";
	public static final String RESTORE_CATEGORY = "UPDATE categorias SET flgstate = 1 WHERE id_categoria = ?;";
	public static final String COUNT_CATEGORIES = "SELECT COUNT(*) FROM categorias WHERE flgstate = 1;";
	
	public static final String GET_CATEGORIES_BY_USER_ID = "SELECT * FROM categorias WHERE id_usuario = ? AND flgstate = 1;";

	@Override
	public List<Category_E> getAllCategories() {
		List<Category_E> listCategories = new ArrayList<Category_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_CATEGORIES);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category_E category = new Category_E();
				category.setId_categoria(rs.getInt("id_categoria"));
				category.setNombre(rs.getString("nombre"));
				category.setDescripcion(rs.getString("descripcion"));
				category.setImagen(rs.getString("imagen"));
				category.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
				listCategories.add(category);				
			}
		} catch (Exception e) {
			System.out.println("Error getting all categories>>> " + e.getMessage());
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
		return listCategories;
	}

	@Override
	public List<Category_E> getAllDeletedCategories() {
		List<Category_E> listCategories = new ArrayList<Category_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_DELETED_CATEGORIES);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category_E category = new Category_E();
				category.setId_categoria(rs.getInt("id_categoria"));
				category.setNombre(rs.getString("nombre"));
				category.setDescripcion(rs.getString("descripcion"));
				category.setImagen(rs.getString("imagen"));
				category.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
				listCategories.add(category);				
			}
		} catch (Exception e) {
			System.out.println("Error getting all deleted categories>>> " + e.getMessage());
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
		return listCategories;
	}

	@Override
	public List<Category_E> getAllCategoriesBySearch(String keyword) {
		List<Category_E> listCategories = new ArrayList<Category_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_CATEGORIES_BY_SEARCH);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Category_E category = new Category_E();
				category.setId_categoria(rs.getInt("id_categoria"));
				category.setNombre(rs.getString("nombre"));
				category.setDescripcion(rs.getString("descripcion"));
				category.setImagen(rs.getString("imagen"));
				category.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
				listCategories.add(category);				
			}
		} catch (Exception e) {
			System.out.println("Error getting all categories by search>>> " + e.getMessage());
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
		return listCategories;
	}

	@Override
	public List<Category_E> getAllDeletedCategoriesBySearch(String keyword) {
		List<Category_E> listCategories = new ArrayList<Category_E>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_DELETED_CATEGORIES_BY_SEARCH);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Category_E category = new Category_E();
				category.setId_categoria(rs.getInt("id_categoria"));
				category.setNombre(rs.getString("nombre"));
				category.setDescripcion(rs.getString("descripcion"));
				category.setImagen(rs.getString("imagen"));
				category.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
				listCategories.add(category);				
			}
		} catch (Exception e) {
			System.out.println("Error getting all deleted categories by search>>> " + e.getMessage());
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
		return listCategories;
	}
	
	@Override
	public Category_E getCategoryById(int id) {
		Category_E category = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_ALL_CATEGORIES);
			rs = ps.executeQuery();
			while (rs.next()) {
				category = new Category_E();
				category.setId_categoria(rs.getInt("id_categoria"));
				category.setNombre(rs.getString("nombre"));
				category.setDescripcion(rs.getString("descripcion"));
				category.setImagen(rs.getString("imagen"));
				category.setFecha_creacion(rs.getTimestamp("fecha_creacion"));		
			}
		} catch (Exception e) {
			System.out.println("Error getting category>>> " + e.getMessage());
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
		return category;
	}

	@Override
	public boolean createCategory(Category_E category) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(CREATE_CATEGORY);
			ps.setString(1, category.getNombre());
			ps.setString(2, category.getDescripcion());
			ps.setString(3, category.getImagen());
			
			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error creating category>>> " + e.getMessage());
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

	@Override
	public boolean updateCategory(Category_E category) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(UPDATE_CATEGORY);
			ps.setString(1, category.getNombre());
			ps.setString(2, category.getDescripcion());
			ps.setString(3, category.getImagen());
			ps.setInt(4, category.getId_categoria());
			
			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error updating category>>> " + e.getMessage());
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

	@Override
	public boolean deleteCategory(int id) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(DELETE_CATEGORY);
			ps.setInt(1, id);
			
			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error deleting category>>> " + e.getMessage());
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

	@Override
	public boolean restoreCategory(int id) {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(RESTORE_CATEGORY);
			ps.setInt(1, id);
			
			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error restoring category>>> " + e.getMessage());
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
	
	@Override
	public int countCategories() {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(COUNT_CATEGORIES);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("Error counting categories>>> " + e.getMessage());
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
