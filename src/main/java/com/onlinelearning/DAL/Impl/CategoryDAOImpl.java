package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.CategoryDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Models.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAOImpl implements CategoryDAO {

    private final DBContext dbContext = new DBContextImpl();

    @Override
    public Category getCategoryById(Integer id) {
        String sql = "select category_id, name, image_url, description from categories where category_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category category = Category.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .imageUrl(rs.getString("image_url"))
                            .description(rs.getString("description"))
                            .build();
                    return category;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Category getCategoryByName(String name) {
        String sql = "select category_id, name, image_url, description from categories where name = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(2, name);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category category = Category.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .imageUrl(rs.getString("image_url"))
                            .description(rs.getString("description"))
                            .build();
                    return category;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = "select category_id, name, image_url, description from categories";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = Category.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .imageUrl(rs.getString("image_url"))
                        .description(rs.getString("description"))
                        .build();
                categories.add(category);
            }
            return categories;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Category createCategory(Category category) {
        String sql = "insert into categories(name, image_url, description) values (?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getImageUrl());
            ps.setString(3, category.getDescription());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        category.setId(rs.getInt(1));
                        return category;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        String sql = "update categories set name = ?, image_url = ?, description = ? where category_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getImageUrl());
            ps.setString(3, category.getDescription());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return category;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Category deleteCategory(Category category) {
        String sql = "delete from categories where category_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, category.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return category;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
