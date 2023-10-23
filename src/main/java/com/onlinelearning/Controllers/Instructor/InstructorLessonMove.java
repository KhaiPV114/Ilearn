package com.onlinelearning.Controllers.Instructor;

import com.google.gson.JsonObject;
import com.onlinelearning.Models.Lesson;
import com.onlinelearning.Services.Impl.LessonServiceImpl;
import com.onlinelearning.Services.LessonService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InstructorLessonMove", urlPatterns = {"/instructor/lesson/move"})
public class InstructorLessonMove extends HttpServlet {

    private final LessonService lessonService = new LessonServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentLessonIdParam = request.getParameter("currentId");
        String previousLessonIdParam = request.getParameter("previousId");
        String nextLessonParam = request.getParameter("nextId");
        Integer sectionId = Integer.valueOf(request.getParameter("sectionId"));
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        JsonObject result = new JsonObject();
        if (StringUtils.isBlank(currentLessonIdParam)) {
            result.addProperty("error", "INVALID_SECTION_ID");
            out.write(result.toString());
            return;
        }
        Integer currentLessonId = Integer.valueOf(currentLessonIdParam);
        Integer previousLessonId = null, nextLessonId = null;
        if (previousLessonIdParam != null) {
            previousLessonId = Integer.valueOf(previousLessonIdParam);
        }
        if (nextLessonParam != null) {
            nextLessonId = Integer.valueOf(nextLessonParam);
        }
        Lesson lesson = null;
        try {
            lesson = lessonService.updateLessonOrderNumber(currentLessonId, previousLessonId, nextLessonId, sectionId);
            if (lesson == null) {
                result.addProperty("error", "MOVED_FAILED");
            } else {
                result.addProperty("success", "MOVED_SUCCESSFULLY");
            }
        } catch (Exception ex) {
            result.addProperty("error", ex.getMessage());
        }
        out.write(result.toString());
    }

}
