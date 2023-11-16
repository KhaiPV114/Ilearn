package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.Enums.LessonStatus;
import com.onlinelearning.Models.Lesson;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.LessonServiceImpl;
import com.onlinelearning.Services.LessonService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "InstructorLessonChangeStatus", urlPatterns = {"/instructor/lesson/change-status"})
public class InstructorLessonChangeStatus extends HttpServlet {

    private final LessonService lessonService = new LessonServiceImpl();

    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        Integer lessonId = Integer.valueOf(request.getParameter("lessonId"));
        Lesson lesson = lessonService.getLessonById(lessonId);
        if (lesson.getStatus() != LessonStatus.HIDDEN) {
            lesson.setStatus(LessonStatus.HIDDEN);
        } else {
            lesson.setStatus(LessonStatus.ACTIVE);
        }
        try {
            lessonService.updateLesson(lesson);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/instructor/section?courseId=" + courseId);
    }

}
