package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.RoleDAO;
import com.onlinelearning.DAL.UserDAO;
import com.onlinelearning.Models.Role;
import com.onlinelearning.Models.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {

    private final DBContext dbContext = new DBContextImpl();

    private final RoleDAO roleDAO = new RoleDAOImpl();

    public UserDAOImpl() {
    }

    private User userRowMapper(ResultSet rs) throws SQLException {
        String password = null;
        if (rs.findColumn("password") > 0) {
            password = rs.getString("password");
        }
        User user = User.builder().id(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .password(password)
                .email(rs.getString("email"))
                .googleEmail(rs.getString("google_email"))
                .fullName(rs.getString("full_name"))
                .dob(rs.getDate("dob"))
                .phoneNumber(rs.getString("phone_number"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .status(rs.getString("status"))
                .build();
        return user;
    }

    @Override
    public User addUser(User user) {
        String sql = "insert into users(username, password, email, google_email, full_name, dob, phone_number, created_at, status) \n"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            if (user.getEmail() == null) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, user.getEmail());
            }
            if (user.getGoogleEmail() == null) {
                ps.setNull(4, Types.VARCHAR);
            } else {
                ps.setString(4, user.getGoogleEmail());
            }
            if (user.getFullName() == null) {
                ps.setNull(5, Types.VARCHAR);
            } else {
                ps.setString(5, user.getFullName());
            }
            if (user.getDob() == null) {
                ps.setNull(6, Types.DATE);
            } else {
                ps.setDate(6, user.getDob());
            }
            if (user.getPhoneNumber() == null) {
                ps.setNull(7, Types.VARCHAR);
            } else {
                ps.setString(7, user.getPhoneNumber());
            }
            ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(9, user.getStatus());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                        user.setPassword(null);
                        return user;
                    }
                }
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new InternalError(ex);
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        String sql = "select user_id, username, password, email, google_email, "
                + "full_name, dob, phone_number, created_at, status from users "
                + "where user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return userRowMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "select user_id, username, password, email, google_email, "
                + "full_name, dob, phone_number, created_at, status from users "
                + "where username = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, username);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return userRowMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "select user_id, username, password, email, google_email, "
                + "full_name, dob, phone_number, created_at, status from users "
                + "where email = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, email);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return userRowMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User getUserByGoogleEmail(String email) {
        String sql = "select user_id, username, password, email, google_email, "
                + "full_name, dob, phone_number, created_at, status from users "
                + "where google_email = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, email);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return userRowMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean isUsernameExisted(String username) {
        String sql = "select user_id from users where username = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, username);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean isEmailExisted(String email) {
        String sql = "select user_id from users where email = ? or google_email = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, email);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public User updateUser(User user) {
        String sql = "update users set username = ?, password = ?, email = ?,  "
                + "google_email = ?, full_name = ?, dob = ?, phone_number = ?, "
                + "status = ? where user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            if (user.getEmail() == null) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, user.getEmail());
            }
            if (user.getGoogleEmail() == null) {
                ps.setNull(4, Types.VARCHAR);
            } else {
                ps.setString(4, user.getGoogleEmail());
            }
            if (user.getFullName() == null) {
                ps.setNull(5, Types.VARCHAR);
            } else {
                ps.setString(5, user.getFullName());
            }
            if (user.getDob() == null) {
                ps.setNull(6, Types.DATE);
            } else {
                ps.setDate(6, user.getDob());
            }
            if (user.getPhoneNumber() == null) {
                ps.setNull(7, Types.VARCHAR);
            } else {
                ps.setString(7, user.getPhoneNumber());
            }
            ps.setString(8, user.getStatus());
            ps.setInt(9, user.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User deleteUser(User user) {
        String sql = "delete from users where user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User addUserRole(User user, Role role) {
        Integer roleID = roleDAO.getRoleIdByRoleName(role);
        if (roleID == null) {
            return null;
        }
        String sql = "insert into user_roles(user_id, role_id) VALUES (?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            ps.setInt(2, roleID);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new InternalError(ex);
        }
        return null;
    }

    @Override
    public HashSet<Role> getRoles(User user) {
        String getRoleIDsSql = "select role_id from user_roles where user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(getRoleIDsSql)) {
            ps.setInt(1, user.getId());
            try ( ResultSet rs = ps.executeQuery()) {
                List<Integer> roleList = new ArrayList<>();
                while (rs.next()) {
                    roleList.add(rs.getInt("role_id"));
                }
                if (roleList.isEmpty()) {
                    return null;
                }
                HashSet<Role> roles = new HashSet<>();
                roleList.forEach((Integer roleId) -> {
                    roles.add(roleDAO.getRoleById(roleId));
                });
                return roles;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User deleteUserRole(User user, Role role) {
        Integer roleID = roleDAO.getRoleIdByRoleName(role);
        if (roleID == null) {
            return null;
        }
        String sql = "delete from user_roles where user_id = ? and role_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            ps.setInt(2, roleID);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<User> searchAllUsersPaging(String searchField, String searchText, int size, int page) {
        String sql = "SELECT id, username, password, first_name, last_name, email, google_email \n"
                + "FROM users \n"
                + "WHERE " + searchField + " LIKE CONCAT('%', ?, '%') "
                + "ORDER BY id OFFSET ? LIMIT ?";
        int offset = size * (page - 1);
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, searchText);
            ps.setInt(2, offset);
            ps.setInt(3, size);
            try ( ResultSet rs = ps.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (rs.next()) {
                    User user = userRowMapper(rs);
                    HashSet<Role> roles = getRoles(user);
                    user.setRoles(roles);
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int countAllNumberOfSearchingRows(String searchField, String searchText) {
        String sql = "SELECT COUNT(*) AS total "
                + "FROM users "
                + "WHERE " + searchField + " LIKE CONCAT('%', ?, '%')";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setString(1, searchText);
            try ( ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public boolean changePassword(int id, String newPassword) {
        String sql = "update users set password = ? where user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setInt(2, id);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
