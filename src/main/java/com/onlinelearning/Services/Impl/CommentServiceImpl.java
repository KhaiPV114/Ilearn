package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.CommentDAO;
import com.onlinelearning.DAL.Impl.CommentDAOImpl;
import com.onlinelearning.DAL.UserDAO;
import com.onlinelearning.Models.Comment;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.CommentService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO = new CommentDAOImpl();

    private final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public List<Comment> getAllCommentsByLessonId(Integer lessonId) {
        List<Comment> comments = new ArrayList<>();
        comments = commentDAO.getAllCommentsByLessonId(lessonId);
        if (comments != null) {
            for (Comment comment : comments) {
                User user = userService.getUser(comment.getUserId());
                comment.setUser(user);
                List<Comment> childComments = commentDAO.getChildCommentByParentId(comment.getId());
                if (childComments != null) {
                    for (Comment child : childComments) {
                        User childUser = userService.getUser(child.getUserId());
                        child.setUser(childUser);
                    }
                }
                comment.setChildComments(childComments);
            }
        }
        return comments;
    }

    @Override
    public Comment getCommentById(Integer id) {
        Comment comment = commentDAO.getCommentById(id);
        User user = userService.getUser(comment.getUserId());
        comment.setUser(user);
        List<Comment> childComments = commentDAO.getChildCommentByParentId(comment.getId());
        if (childComments != null) {
            for (Comment child : childComments) {
                User childUser = userService.getUser(child.getUserId());
                child.setUser(childUser);
            }
        }
        comment.setChildComments(childComments);
        return comment;
    }

    @Override
    public List<Comment> getChildCommentByParentId(Integer parentId) {
        return null;
    }

    @Override
    public Comment createComment(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        return commentDAO.addComment(comment);
    }

    @Override
    public Comment deleteComment(Comment comment) {
        return commentDAO.deleteComment(comment);
    }

}
