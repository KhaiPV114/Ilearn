package com.onlinelearning.Controllers.General;

import com.onlinelearning.Models.Comment;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CommentService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CommentServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "LessonCommentAdd", urlPatterns = {"/learn/comment/add"})
public class LessonCommentAdd extends HttpServlet {

    private final AuthService authService = new AuthServiceImpl();

    private final CommentService commentService = new CommentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        Integer lessonId = Integer.valueOf(request.getParameter("lessonId"));
        String pidString = request.getParameter("pid");
        Integer pid = null;
        if (!StringUtils.isBlank(pidString)) {
            pid = Integer.valueOf(pidString);
        }
        String content = request.getParameter("content");

        User user = authService.getUser(request);

        Comment comment = Comment.builder()
                .lessonId(lessonId)
                .content(content)
                .userId(user.getId())
                .parentId(pid)
                .build();

        Comment addedComment = commentService.createComment(comment);

        response.sendRedirect(request.getContextPath() + "/learn?courseId=" + courseId + "&lessonId=" + lessonId);
    }

}
