package com.onlinelearning.Controllers.Instructor;

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

@WebServlet(name = "InstructorSectionView", urlPatterns = {"/instructor/section"})
public class InstructorSectionView extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/instructor/section-view.jsp";

    private final SectionService sectionService = new SectionServiceImpl();

    private final LessonService lessonService = new LessonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseIdParam = request.getParameter("courseId");
        if (StringUtils.isBlank(courseIdParam)) {
            response.sendRedirect(request.getContextPath() + "/error/404.jsp");
            return;
        }
        Integer courseId = Integer.valueOf(courseIdParam);
        request.setAttribute("courseId", courseId);

        //Active sections
        List<Section> sections = sectionService.getActiveSectionByCourseId(courseId);
        Map<Integer, List<Lesson>> lessonsList = new HashMap<>();
        if (sections != null) {
            for (Section section : sections) {
                List<Lesson> lesson = lessonService.getLessonsBySectionId(section.getId());
                lessonsList.put(section.getId(), lesson);
            }
        }
        request.setAttribute("sections", sections);
        request.setAttribute("lessonsList", lessonsList);

        //Hidden sections
        List<Section> sections2 = sectionService.getHiddenSectionByCourseId(courseId);
        Map<Integer, List<Lesson>> lessonsList2 = new HashMap<>();
        if (sections2 != null) {
            for (Section section : sections2) {
                List<Lesson> lesson = lessonService.getLessonsBySectionId(section.getId());
                lessonsList2.put(section.getId(), lesson);
            }
        }
        request.setAttribute("sections2", sections2);
        request.setAttribute("lessonsList2", lessonsList2);

        String currentSection = request.getParameter("current-section");
        if (currentSection != null) {
            request.setAttribute("currentSection", currentSection);
        }

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
