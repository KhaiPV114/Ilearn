package com.onlinelearning.Controllers.Learner;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "LearnerLearnController", urlPatterns = {"/learn"})
public class LearnerLearnController extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/learning.jsp";

    private final SectionService sectionService = new SectionServiceImpl();

    private final LessonService lessonService = new LessonServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseIdParam = request.getParameter("courseId");
        String lessonIdParam = request.getParameter("lessonId");
        if (StringUtils.isBlank(courseIdParam)) {
            response.sendRedirect(request.getContextPath() + "/error/404.jsp");
            return;
        }

        Integer lessonId = null;
        //Lesson handler
        if (!StringUtils.isBlank(lessonIdParam)) {
            lessonId = Integer.valueOf(lessonIdParam);
            Lesson lesson = lessonService.getLessonById(lessonId);
            request.setAttribute("lesson", lesson);
            request.setAttribute("lessonId", lessonId);
        }

        //Course handler
        Integer courseId = Integer.valueOf(courseIdParam);
        List<Section> sections = sectionService.getSectionsByCourseId(courseId);
        Map<Integer, List<Lesson>> lessonsList = new HashMap<>();
        Integer previousLessonId = null, nextLessonId = null;
        for (Section section : sections) {
            List<Lesson> lesson = lessonService.getLessonsBySectionId(section.getId());
            lessonsList.put(section.getId(), lesson);
        }
        request.setAttribute("sections", sections);
        request.setAttribute("lessonsList", lessonsList);
        request.setAttribute("courseId", courseId);

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
