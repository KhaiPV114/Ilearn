package com.onlinelearning.Services;

import com.onlinelearning.Models.Comment;
import java.util.List;

public interface CommentService {

    List<Comment> getAllCommentsByLessonId(Integer lessonId);

    Comment getCommentById(Integer id);

    List<Comment> getChildCommentByParentId(Integer parentId);

    Comment createComment(Comment comment);

    Comment deleteComment(Comment comment);

}
