package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.DAL.CourseDAO;
import com.onlinelearning.DAL.Impl.CourseDAOImpl;
import com.onlinelearning.DAL.Impl.LessonDAOImpl;
import com.onlinelearning.DAL.LessonDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LearnerLessonStatusUpdate", urlPatterns = {"/learn/status"})
public class LearnerLessonStatusUpdate extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAOImpl();
    private final LessonDAO lessonDAO = new LessonDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
