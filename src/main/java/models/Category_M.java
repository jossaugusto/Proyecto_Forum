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
	public static final String GET_CATEGORY_BY_ID = "SELECT * FROM categorias WHERE id_categoria = ? AND flgstate = 1;";
	public static final String CREATE_CATEGORY = "INSERT INTO categorias (nombre, descripcion, imagen) VALUES (?, ?, ?);";
	public static final String UPDATE_CATEGORY = "UPDATE categorias SET nombre = ?, descripcion = ?, imagen = ? WHERE id_categoria = ?;";
	public static final String DELETE_CATEGORY = "UPDATE categorias SET flgstate = 0 WHERE id_categoria = ?;";
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
				category.setFecha_creacion(rs.getDate("fecha_creacion"));
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
				category.setFecha_creacion(rs.getDate("fecha_creacion"));		
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
			System.out.println("Entrando a crear tema");
			
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


}
