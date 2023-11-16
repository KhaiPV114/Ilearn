package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.DAL.CourseDAO;
import com.onlinelearning.DAL.Impl.CourseDAOImpl;
import com.onlinelearning.DAL.Impl.LessonDAOImpl;
import com.onlinelearning.DAL.LessonDAO;
import com.onlinelearning.Enums.TrackingStatus;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.TrackingServiceImpl;
import com.onlinelearning.Services.TrackingService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "LearnerLessonStatusUpdate", urlPatterns = {"/learn/status"})
public class LearnerLessonStatusUpdate extends HttpServlet {

    private final TrackingService trackingService = new TrackingServiceImpl();
    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lessonIdString = request.getParameter("lessonId");
        String courseIdString = request.getParameter("courseId");
        if (lessonIdString == null || StringUtils.isBlank(lessonIdString)) {
            response.sendRedirect(request.getContextPath() + "/error/400.jsp");
            return;
        }
        Integer lessonId = Integer.valueOf(lessonIdString);
        User user = authService.getUser(request);
        trackingService.changeUserTracking(user.getId(), lessonId);
        response.sendRedirect(request.getContextPath() + "/learn?courseId=" + courseIdString + "&lessonId=" + lessonIdString + "#change-status");
    }

}
