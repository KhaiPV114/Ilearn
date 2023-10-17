package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.DAL.Impl.SectionDAOImpl;
import com.onlinelearning.DAL.SectionDAO;
import com.onlinelearning.Models.Section;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InstructorSectionAdd", urlPatterns = {"/instructor/section/add"})
public class InstructorSectionAdd extends HttpServlet {
    
    private static final String FORM_PATH = "/dashboard/instructor/section-view.jsp";
    
    private final SectionDAO sectionDAO = new SectionDAOImpl();
    
    private final InstructorSectionView instructorSectionView = new InstructorSectionView();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);
        
        boolean check = true;

        //Get name
        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            request.setAttribute("nameError", "Section name must not be empty!");
            check = false;
        }
        
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        
        if (!check) {
            request.setAttribute("name", name);
            requestDispatcher.forward(request, response);
            return;
        }
        
        Section section = Section.builder()
                .name(name)
                .courseId(courseId)
                .build();
        
        Section addedSection = sectionDAO.createSection(section);
        if (addedSection == null) {
            request.setAttribute("error", "Created section failed! Please try again!");
//            requestDispatcher.forward(request, response);
            instructorSectionView.doGet(request, response);
            return;
        }
        
        request.setAttribute("success", "Created section successfully!");
//        requestDispatcher.forward(request, response);
//        instructorSectionView.doGet(request, response);
        response.sendRedirect(request.getContextPath() + "/instructor/section?courseId=" + courseId);
    }
    
}
