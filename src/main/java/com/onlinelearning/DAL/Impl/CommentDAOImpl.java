package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.CommentDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Models.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDAOImpl implements CommentDAO {

    private final DBContext dbContext = new DBContextImpl();

    private Comment commentResultSetMapper(ResultSet rs) throws SQLException {
        Comment comment = Comment.builder()
                .id(rs.getInt("comment_id"))
                .userId(rs.getInt("user_id"))
                .lessonId(rs.getInt("lesson_id"))
                .content(rs.getString("content"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .parentId(rs.getInt("parent_id"))
                .build();
        return comment;
    }

    @Override
    public List<Comment> getAllCommentsByLessonId(Integer lessonId) {
        String sql = "select * from comments where lesson_id = ? and parent_id is null";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, lessonId);
            List<Comment> comments = new ArrayList<>();
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    comments.add(commentResultSetMapper(rs));
                }
            }
            return comments;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Comment getCommentById(Integer id) {
        String sql = "select * from comments where id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return commentResultSetMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Comment> getChildCommentByParentId(Integer parentId) {
        String sql = "select * from comments where parent_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, parentId);
            try ( ResultSet rs = ps.executeQuery()) {
                List<Comment> comments = new ArrayList<>();
                while (rs.next()) {
                    comments.add(commentResultSetMapper(rs));
                }
                return comments;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Comment addComment(Comment comment) {
        String sql = "insert into comments (lesson_id, user_id, content, created_at, parent_id) \n"
                + "values (?, ?, ?, ?, ?);";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, comment.getLessonId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getContent());
            ps.setTimestamp(4, Timestamp.valueOf(comment.getCreatedAt()));
            if (comment.getParentId() == null) {
                ps.setNull(5, Types.VARCHAR);
            } else {
                ps.setInt(5, comment.getParentId());
            }
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        comment.setId(rs.getInt(1));
                        return comment;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Comment deleteComment(Comment comment) {
        String sql = "delete from comments where comment_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, comment.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return comment;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
