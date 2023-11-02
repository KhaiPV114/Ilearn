package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.DAL.Impl.LessonDAOImpl;
import com.onlinelearning.DAL.LessonDAO;
import com.onlinelearning.Models.Lesson;
import com.onlinelearning.Models.Section;
import com.onlinelearning.Services.Impl.LessonServiceImpl;
import com.onlinelearning.Services.Impl.SectionServiceImpl;
import com.onlinelearning.Services.LessonService;
import com.onlinelearning.Services.SectionService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InstructorLessonEdit", urlPatterns = {"/instructor/lesson/edit"})
public class InstructorLessonEdit extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/instructor/lesson-form.jsp";

    private final LessonService lessonService = new LessonServiceImpl();

    private final SectionService sectionService = new SectionServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lessonIdParam = request.getParameter("id");
        if (StringUtils.isBlank(lessonIdParam)) {
            response.sendRedirect(request.getContextPath() + "/error/404.jsp");
            return;
        }
        Integer lessonId = Integer.valueOf(lessonIdParam);
        Lesson lesson = lessonService.getLessonById(lessonId);
        request.setAttribute("lesson", lesson);

        Section section = sectionService.getSectionById(lesson.getSectionId());
        request.setAttribute("courseId", section.getCourseId());

        request.getRequestDispatcher(FORM_PATH).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lessonIdParam = request.getParameter("id");
        if (StringUtils.isBlank(lessonIdParam)) {
            response.sendRedirect(request.getContextPath() + "/error/404.jsp");
            return;
        }

        Integer lessonId = Integer.valueOf(lessonIdParam);
        Lesson lesson = lessonService.getLessonById(lessonId);
        if (lesson == null) {
            response.sendRedirect(request.getContextPath() + "/error/404.jsp");
            return;
        }

        String content = request.getParameter("content");
        lesson.setContent(content);

        try {
            Lesson updatedLesson = lessonService.updateLesson(lesson);
            request.setAttribute("lesson", lesson);
            request.setAttribute("success", "Updated lesson successfully!");
        } catch (Exception ex) {
            request.setAttribute("error", "Updated lesson failed! Please try again!");
        }

        Section section = sectionService.getSectionById(lesson.getSectionId());
        request.setAttribute("courseId", section.getCourseId());

        request.getRequestDispatcher(FORM_PATH).forward(request, response);
    }

}
