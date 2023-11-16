package com.onlinelearning.Controllers.Instructor;

import com.google.gson.JsonObject;
import com.onlinelearning.Models.Section;
import com.onlinelearning.Services.Impl.SectionServiceImpl;
import com.onlinelearning.Services.SectionService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InstructorSectionMove", urlPatterns = {"/instructor/section/move"})
public class InstructorSectionMove extends HttpServlet {

    private final SectionService sectionService = new SectionServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentSectionIdParam = request.getParameter("currentId");
        String previousSectionIdParam = request.getParameter("previousId");
        String nextSectionParam = request.getParameter("nextId");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        JsonObject result = new JsonObject();
        if (StringUtils.isBlank(currentSectionIdParam)) {
            result.addProperty("error", "INVALID_SECTION_ID");
            out.write(result.toString());
            return;
        }
        Integer currentSectionId = Integer.valueOf(currentSectionIdParam);
        Integer previousSectionId = null, nextSectionId = null;
        if (previousSectionIdParam != null) {
            previousSectionId = Integer.valueOf(previousSectionIdParam);
        }
        if (nextSectionParam != null) {
            nextSectionId = Integer.valueOf(nextSectionParam);
        }
        Section section = null;
        try {
            section = sectionService.updateSectionOrderNumber(currentSectionId, previousSectionId, nextSectionId);
            if (section == null) {
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
