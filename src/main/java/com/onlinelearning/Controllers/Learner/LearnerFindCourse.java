package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.CategoryService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CategoryServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import com.onlinelearning.Services.Impl.UserServiceImpl;
import com.onlinelearning.Services.UserService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "LearnerFindCourse", urlPatterns = {"/course/find"})
public class LearnerFindCourse extends HttpServlet {

    private final CourseService courseService = new CourseServiceImpl();

    private final CategoryService categoryService = new CategoryServiceImpl();

    private final String VIEW_PATH = "/dashboard/learner/course-find-and-filter.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("");
        System.out.println("Start process: ");

        List<Course> courses = courseService.getAllCourses();
        System.out.println(courses);
        List<Category> categorys = categoryService.getAllCategories();
        request.setAttribute("categorys", categorys);
        String filterShort = request.getParameter("filterShort");
        request.setAttribute("filterShort", filterShort);
        System.out.println(filterShort);
        String courseKeyword = request.getParameter("courseKeyword");
        request.setAttribute("courseKeyword", courseKeyword);
        System.out.println(courseKeyword);

        if (StringUtils.isBlank(courseKeyword) && StringUtils.isBlank(filterShort)) {
            System.out.println(courses);
            request.setAttribute("courses", courses);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }

        if (StringUtils.isNotBlank(courseKeyword) && StringUtils.isBlank(filterShort)) {
            courses = courseService.getCourseByKeyword(courseKeyword);
            System.out.println(courses);
            request.setAttribute("courses", courses);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }
        
        
        if(StringUtils.isBlank(courseKeyword) && filterShort.equals("hightolow")){
            courses = courseService.getCourseByKeywordOrderByPriceDesc(courseKeyword);
            request.setAttribute("courses", courses);
            System.out.println(courses);
            request.getRequestDispatcher(filterShort).forward(request, response);
        }
        
        if(StringUtils.isBlank(courseKeyword) && filterShort.equals("lowtohigh")){
            courses = courseService.getCourseByKeywordOrderByPriceAsc(courseKeyword);
            request.setAttribute("courses", courses);
            System.out.println(courses);
            request.getRequestDispatcher(filterShort).forward(request, response);
        }
        
        if (StringUtils.isBlank(courseKeyword) && filterShort.equals("hightolow")) {
            courses = courseService.getAllCourseOrderByPriceDesc();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }
        
        if (StringUtils.isBlank(courseKeyword) && filterShort.equals("lowtohigh")) {
            courses = courseService.getAllCourseOrderByPriceAsc();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }
        
        
        
        
        

//        if (StringUtils.isNotBlank(filterShort)) {
//            if (StringUtils.isNotBlank(courseKeyword) && courseKeyword == "hightolow") {
//                courses = courseService.getCourseByKeyword(courseKeyword);
//                Collections.sort(courses, new Comparator<Course>() {
//                    public int compare(Course a, Course b) {
//                        return (int) (b.getPrice() - a.getPrice());
//                    }
//                });
//                System.out.println(courses);
//            }
//            else{
//            courses = courseService.getCourseByKeyword(courseKeyword);
//                Collections.sort(courses, new Comparator<Course>() {
//                    public int compare(Course a, Course b) {
//                        return (int) (a.getPrice() - b.getPrice());
//                    }
//                });
//                System.out.println(courses);
//                request.getRequé
//        }
//        }
//        
//        if (StringUtils.isBlank(filterShort)) {
//            if (StringUtils.isNotBlank(courseKeyword) && courseKeyword == "hightolow") {
//                courses = courseService.getCourseByKeyword(courseKeyword);
//                Collections.sort(courses, new Comparator<Course>() {
//                    public int compare(Course a, Course b) {
//                        return (int) (b.getPrice() - a.getPrice());
//                    }
//                });
//                System.out.println(courses);
//            }
//            else{
//            courses = courseService.getCourseByKeyword(courseKeyword);
//                Collections.sort(courses, new Comparator<Course>() {
//                    public int compare(Course a, Course b) {
//                        return (int) (a.getPrice() - b.getPrice());
//                    }
//                });
//                System.out.println(courses);
//        }
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);

//        String courseKeyword = request.getParameter("courseKeyword");
//        
//        System.out.println(courseKeyword);
//        
//        List<Course> courses = courseService.getCourseByKeyword(courseKeyword);
//        System.out.println(courses);
//        request.setAttribute("courses", courses);
//        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
