package com.onlinelearning.DAL;

import com.onlinelearning.Models.Comment;
import java.util.List;

public interface CommentDAO {

    List<Comment> getAllCommentsByLessonId(Integer lessonId);

    Comment getCommentById(Integer id);

    List<Comment> getChildCommentByParentId(Integer parentId);

    Comment addComment(Comment comment);

    Comment deleteComment(Comment comment);
}
