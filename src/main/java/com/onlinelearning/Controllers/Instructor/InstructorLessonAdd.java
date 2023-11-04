package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.DAL.Impl.LessonDAOImpl;
import com.onlinelearning.DAL.LessonDAO;
import com.onlinelearning.Models.Lesson;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InstructorLessonAdd", urlPatterns = {"/instructor/lesson/add"})
public class InstructorLessonAdd extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/instructor/lesson-view.jsp";

    private final LessonDAO lessonDAO = new LessonDAOImpl();

    private final InstructorSectionView instructorSectionView = new InstructorSectionView();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);

        boolean check = true;

        //Get name
        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            request.setAttribute("nameError", "Lesson name must not be empty!");
            check = false;
        }

        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        Integer sectionId = Integer.valueOf(request.getParameter("sectionId"));

        if (!check) {
            request.setAttribute("name", name);
            requestDispatcher.forward(request, response);
            return;
        }

        Lesson lesson = Lesson.builder()
                .name(name)
                .sectionId(sectionId)
                .build();

        Lesson addedLesson = lessonDAO.createLesson(lesson);
        if (addedLesson == null) {
            request.setAttribute("error", "Created lesson failed! Please try again!");
//            requestDispatcher.forward(request, response);
            instructorSectionView.doGet(request, response);
            return;
        }

        request.setAttribute("success", "Created lesson successfully!");
//        requestDispatcher.forward(request, response);

        request.setAttribute("callbankSectionPosition", sectionId);

//        instructorSectionView.doGet(request, response);
        response.sendRedirect(request.getContextPath() + "/instructor/section?courseId=" + courseId + "&current-section=" + sectionId);
    }

}
