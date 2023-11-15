package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Enums.TrackingStatus;
import com.onlinelearning.Models.Comment;
import com.onlinelearning.Models.Lesson;
import com.onlinelearning.Models.Section;
import com.onlinelearning.Models.Tracking;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CommentService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CommentServiceImpl;
import com.onlinelearning.Services.Impl.LessonServiceImpl;
import com.onlinelearning.Services.Impl.SectionServiceImpl;
import com.onlinelearning.Services.Impl.TrackingServiceImpl;
import com.onlinelearning.Services.LessonService;
import com.onlinelearning.Services.SectionService;
import com.onlinelearning.Services.TrackingService;
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

    private final AuthService authService = new AuthServiceImpl();

    private final SectionService sectionService = new SectionServiceImpl();

    private final LessonService lessonService = new LessonServiceImpl();

    private final CommentService commentService = new CommentServiceImpl();

    private final TrackingService trackingService = new TrackingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseIdParam = request.getParameter("courseId");
        String lessonIdParam = request.getParameter("lessonId");
        if (StringUtils.isBlank(courseIdParam)) {
            response.sendRedirect(request.getContextPath() + "/error/404.jsp");
            return;
        }

        User user = authService.getUser(request);

        Integer lessonId = null;
        //Lesson handler
        if (!StringUtils.isBlank(lessonIdParam)) {
            lessonId = Integer.valueOf(lessonIdParam);
            Lesson lesson = lessonService.getLessonById(lessonId);
            request.setAttribute("lesson", lesson);
            request.setAttribute("lessonId", lessonId);

            List<Comment> comments = commentService.getAllCommentsByLessonId(lessonId);
            request.setAttribute("comments", comments);

            Tracking tracking = trackingService.getUserTrackingByLessonId(user.getId(), lessonId);
            request.setAttribute("tracking", tracking);
        }

        //Course handler
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

        Map<Integer, TrackingStatus> trackings = trackingService.mapUserTrackingLessonsByUserIdAndCourseId(user.getId(), courseId);
        request.setAttribute("trackings", trackings);

        //Calculate progress
        int numberOfLessons = lessonsList.size();
        if (numberOfLessons == 0) {
            request.setAttribute("progress", 0);
        } else {
            int numberOfLeanerdLessons = 0;
            for (TrackingStatus status : trackings.values()) {
                if (status == TrackingStatus.FINISHED) {
                    ++numberOfLeanerdLessons;
                }
            }
            Float learningProgress = numberOfLeanerdLessons * 1.0f / numberOfLessons * 100;
            Integer rounedLearningProgess = Math.round(learningProgress);
            request.setAttribute("progress", rounedLearningProgess);
        }

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
