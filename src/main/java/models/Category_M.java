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
	
	public static final String GET_ALL_CATEGORIES = "CALL sp_categoria_get_activas(?)";
	public static final String GET_ALL_CATEGORIES_BY_SEARCH = "CALL sp_categoria_buscar_activas(?,?)";
	
	public static final String GET_ALL_DELETED_CATEGORIES = "CALL sp_categoria_get_eliminadas(?)";
	public static final String GET_ALL_DELETED_CATEGORIES_BY_SEARCH = "CALL sp_categoria_buscar_eliminadas(?,?)";
	
	public static final String GET_CATEGORY_BY_ID = "CALL sp_categoria_get_by_id(?)";
	public static final String GET_CATEGORIES_BY_USER_ID = "CALL sp_categoria_get_by_usuario(?)";
	
	public static final String CREATE_CATEGORY = "CALL sp_categoria_create(?, ?, ?)";
	public static final String UPDATE_CATEGORY = "CALL sp_categoria_update(?, ?, ?, ?)";
	public static final String DELETE_CATEGORY = "CALL sp_categoria_delete(?)";
	public static final String RESTORE_CATEGORY = "CALL sp_categoria_restore(?)";
	
	public static final String COUNT_CATEGORIES = "CALL sp_categoria_count_activas()";

	@Override
	public List<Category_E> getAllCategories(String keyword, String order) {
		String sql = "";
		if (keyword != null && !keyword.isEmpty()) {
			sql = GET_ALL_CATEGORIES_BY_SEARCH;
		} else {
			sql = GET_ALL_CATEGORIES;
		}
		
		List<Category_E> listCategories = new ArrayList<Category_E>();

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
				Category_E category = new Category_E();
				category.setId_categoria(rs.getInt("id_categoria"));
				category.setNombre(rs.getString("nombre"));
				category.setDescripcion(rs.getString("descripcion"));
				category.setImagen(rs.getString("imagen"));
				category.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
				listCategories.add(category);				
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "getAllCategories");
		}
		return listCategories;
	}

	@Override
	public List<Category_E> getAllDeletedCategories(String keyword, String order) {
		String sql = "";
		if (keyword != null && !keyword.isEmpty()) {
			sql = GET_ALL_DELETED_CATEGORIES_BY_SEARCH;
		} else {
			sql = GET_ALL_DELETED_CATEGORIES;
		}
		
		List<Category_E> listCategories = new ArrayList<Category_E>();

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
				Category_E category = new Category_E();
				category.setId_categoria(rs.getInt("id_categoria"));
				category.setNombre(rs.getString("nombre"));
				category.setDescripcion(rs.getString("descripcion"));
				category.setImagen(rs.getString("imagen"));
				category.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
				listCategories.add(category);				
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "getAllDeletedCategories");
		}
		return listCategories;
	}

	@Override
	public Category_E getCategoryById(int id) {
		Category_E category = null;
		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(GET_CATEGORY_BY_ID);
			ps.setInt(1, id);
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
			closeResources(con, ps, rs, e, "getCategoryById");
		}
		return category;
	}

	@Override
	public boolean createCategory(Category_E category) {
		boolean result = false;

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
			closeResources(con, ps, rs, e, "createCategory");
		}
		return result;
	}

	@Override
	public boolean updateCategory(Category_E category) {
		boolean result = false;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(UPDATE_CATEGORY);
			ps.setInt(1, category.getId_categoria());
			ps.setString(2, category.getNombre());
			ps.setString(3, category.getDescripcion());
			ps.setString(4, category.getImagen());
			
			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "updateCategory");
		}
		return result;
	}

	@Override
	public boolean deleteCategory(int id) {
		boolean result = false;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(DELETE_CATEGORY);
			ps.setInt(1, id);
			
			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "deleteCategory");
		}
		return result;
	}

	@Override
	public boolean restoreCategory(int id) {
		boolean result = false;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(RESTORE_CATEGORY);
			ps.setInt(1, id);
			
			int value = ps.executeUpdate();
			if (value > 0) {
				result = true;
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "restoreCategory");
		}
		return result;
	}
	
	@Override
	public int countCategories() {
		int count = 0;

		try {
			con = MySQLConnection.getConexion();
			ps = con.prepareStatement(COUNT_CATEGORIES);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			closeResources(con, ps, rs, e, "countCategories");
		}
		return count;
	}


}
