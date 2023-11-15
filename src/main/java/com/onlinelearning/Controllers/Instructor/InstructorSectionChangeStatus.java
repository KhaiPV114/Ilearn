package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.Enums.SectionStatus;
import com.onlinelearning.Models.Section;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.SectionServiceImpl;
import com.onlinelearning.Services.SectionService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "InstructorSectionChangeStatus", urlPatterns = {"/instructor/section/change-status"})
public class InstructorSectionChangeStatus extends HttpServlet {

    private final SectionService sectionService = new SectionServiceImpl();

    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        Integer sectionId = Integer.valueOf(request.getParameter("sectionId"));
        Section section = sectionService.getSectionById(sectionId);
        if (section.getStatus() != SectionStatus.HIDDEN) {
            section.setStatus(SectionStatus.HIDDEN);
        } else {
            section.setStatus(SectionStatus.ACTIVE);
        }
        try {
            sectionService.updateSection(section);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/instructor/section?courseId=" + courseId);
    }

}
