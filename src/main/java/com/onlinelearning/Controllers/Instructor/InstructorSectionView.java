package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.DAL.Impl.SectionDAOImpl;
import com.onlinelearning.DAL.SectionDAO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InstructorSectionView", urlPatterns = {"/instructor/section"})
public class InstructorSectionView extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/instructor/section-view.jsp";

    private final SectionDAO sectionDAO = new SectionDAOImpl();

    private final SectionService sectionService = new SectionServiceImpl();

    private final LessonService lessonService = new LessonServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseIdParam = request.getParameter("courseId");
        if (StringUtils.isBlank(courseIdParam)) {
            response.sendRedirect(request.getContextPath() + "/error/404.jsp");
            return;
        }
        Integer courseId = Integer.valueOf(courseIdParam);
        List<Section> sections = sectionService.getSectionsByCourseId(courseId);
        Map<Integer, List<Lesson>> lessonsList = new HashMap<>();
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
