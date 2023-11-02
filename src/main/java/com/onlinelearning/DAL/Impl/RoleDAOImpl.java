package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.RoleDAO;
import com.onlinelearning.Models.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDAOImpl implements RoleDAO {

    private final DBContext dbContext = new DBContextImpl();
    private final String ROLE_TABLE = "roles";

    public RoleDAOImpl() {
    }

    @Override
    public Role addRole(Role role) {
        String sql = "insert into " + ROLE_TABLE
                + "(name)"
                + " values (?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, role.toString());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return role;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Integer getRoleIdByRoleName(Role role) {
        String sql = "select role_id"
                + " from " + ROLE_TABLE
                + " where name = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, role.toString());
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("role_id");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Role getRoleById(int id) {
        String sql = "select name"
                + " from " + ROLE_TABLE
                + " where role_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Role.valueOf(rs.getString("name"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
