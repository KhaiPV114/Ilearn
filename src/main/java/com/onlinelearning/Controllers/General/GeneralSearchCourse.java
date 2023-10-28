package com.onlinelearning.Controllers.General;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CategoryService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CategoryServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import jakarta.servlet.RequestDispatcher;
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

@WebServlet(name = "LearnerFindCourse", urlPatterns = {"/search"})
public class GeneralSearchCourse extends HttpServlet {

    private final CourseService courseService = new CourseServiceImpl();

    private final CategoryService categoryService = new CategoryServiceImpl();

    private final String VIEW_PATH = "/general/course-search.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);

        List<Course> courses = courseService.getAllCourses();

        List<Category> categorys = categoryService.getAllCategories();
        request.setAttribute("categorys", categorys);

        String filterPrice = request.getParameter("filterPrice");
        request.setAttribute("filterPrice", filterPrice);

        String courseKeyword = request.getParameter("courseKeyword");
        request.setAttribute("courseKeyword", courseKeyword);

        String categories = request.getParameter("filterCategory");

        String priceRange = request.getParameter("priceRange");
        request.setAttribute("priceRange", priceRange);

        //CourseKeyword is blank
        if (StringUtils.isBlank(courseKeyword)) {
            //filterPrice and categories is blank
            if (StringUtils.isBlank(filterPrice) && StringUtils.isBlank(categories)) {
                request.setAttribute("courses", courses);
                dispatcher.forward(request, response);
                return;
            }
            //filterPrice is not blank and categories is blank
            if (StringUtils.isNotBlank(filterPrice) && StringUtils.isBlank(categories)) {
                if (filterPrice.equals("hightolow")) {
                    courses = courseService.getAllCourses();
                    Collections.sort(courses, new Comparator<Course>() {
                        public int compare(Course a, Course b) {
                            return (int) (b.getPrice() - a.getPrice());
                        }
                    });
                    request.setAttribute("courses", courses);
                    dispatcher.forward(request, response);
                    return;
                }
                if (filterPrice.equals("lowtohigh")) {
                    courses = courseService.getAllCourses();
                    Collections.sort(courses, new Comparator<Course>() {
                        public int compare(Course a, Course b) {
                            return (int) (a.getPrice() - b.getPrice());
                        }
                    });
                    request.setAttribute("courses", courses);
                    dispatcher.forward(request, response);
                    return;
                }
            }
            //filterPrice is blank, categories is not blank
            if (StringUtils.isBlank(filterPrice) && StringUtils.isNotBlank(categories)) {
                courses = courseService.getCourseByCategory(categories);
                request.setAttribute("courses", courses);
                dispatcher.forward(request, response);
                return;
            }
            //Both filterPrice and categories are not blank
            if (StringUtils.isNotBlank(filterPrice) && StringUtils.isNotBlank(categories)) {
                if (filterPrice.equals("hightolow")) {
                    courses = courseService.getCourseByCategory(categories);
                    Collections.sort(courses, new Comparator<Course>() {
                        public int compare(Course a, Course b) {
                            return (int) (b.getPrice() - a.getPrice());
                        }
                    });
                    request.setAttribute("courses", courses);
                    dispatcher.forward(request, response);
                    return;
                }
                if (filterPrice.equals("lowtohigh")) {
                    courses = courseService.getCourseByCategory(categories);
                    Collections.sort(courses, new Comparator<Course>() {
                        public int compare(Course a, Course b) {
                            return (int) (a.getPrice() - b.getPrice());
                        }
                    });
                    request.setAttribute("courses", courses);
                    dispatcher.forward(request, response);
                    return;
                }
            }
        }

        //coursekeyword is not blank 
        if (StringUtils.isNotBlank(courseKeyword)) {
            //filterPrice and categories is blank
            if (StringUtils.isBlank(filterPrice) && StringUtils.isBlank(categories)) {
                courses = courseService.getCourseByKeyword(courseKeyword);
                request.setAttribute("courses", courses);
                request.getRequestDispatcher(VIEW_PATH).forward(request, response);
                return;
            }
            //filterPrice is not blank and categories is blank 
            if (StringUtils.isNotBlank(filterPrice) && StringUtils.isBlank(categories)) {
                
                if (filterPrice.equals("hightolow")) {
                    
                    courses = courseService.getCourseByKeyword(courseKeyword);
                    Collections.sort(courses, new Comparator<Course>() {
                        public int compare(Course a, Course b) {
                            return (int) (b.getPrice() - a.getPrice());
                        }
                    });
                    request.setAttribute("courses", courses);
                    dispatcher.forward(request, response);
                    return;
                }
                if (filterPrice.equals("lowtohigh")) {

                    if (StringUtils.isNotBlank(courseKeyword)) {
                        courses = courseService.getCourseByKeyword(courseKeyword);
                        Collections.sort(courses, new Comparator<Course>() {
                            public int compare(Course a, Course b) {
                                return (int) (a.getPrice() - b.getPrice());
                            }
                        });
                        request.setAttribute("courses", courses);
                        dispatcher.forward(request, response);
                        return;
                    }
                }
            }
                //filterPrice is blank and categories is not blank
                if (StringUtils.isBlank(filterPrice) && StringUtils.isNotBlank(categories)) {
                    List<Course> courses1 = new ArrayList<>();
                    courses = courseService.getCourseByKeyword(courseKeyword);
                    Category category = categoryService.getCategoryByName(categories);

                    for (Course c : courses) {
                        if (c.getCategoryId().equals(category.getId())) {
                            courses1.add(c);
                        }
                    }
                    request.setAttribute("courses", courses1);
                    dispatcher.forward(request, response);
                    return;
                }
                //filterPrice and categories are not blank
                if (StringUtils.isNotBlank(filterPrice) && StringUtils.isNotBlank(categories)) {
                    List<Course> courses1 = new ArrayList<>();
                    if (filterPrice.equals("hightolow")) {
                        courses = courseService.getCourseByKeyword(courseKeyword);
                        Category category = categoryService.getCategoryByName(categories);

                        for (Course c : courses) {
                            if (c.getCategoryId().equals(category.getId())) {
                                courses1.add(c);
                            }
                        }
                        Collections.sort(courses1, new Comparator<Course>() {
                            public int compare(Course a, Course b) {
                                return (int) (b.getPrice() - a.getPrice());
                            }
                        });
                        request.setAttribute("courses", courses1);
                        dispatcher.forward(request, response);
                        return;
                    }
                    if (filterPrice.equals("lowtohigh")) {
                        courses = courseService.getCourseByKeyword(courseKeyword);
                        Category category = categoryService.getCategoryByName(categories);

                        for (Course c : courses) {
                            if (c.getCategoryId().equals(category.getId())) {
                                courses1.add(c);
                            }
                        }
                        
                        Collections.sort(courses1, new Comparator<Course>() {
                            public int compare(Course a, Course b) {
                                return (int) (a.getPrice() - b.getPrice());
                            }
                        });
                        request.setAttribute("courses", courses1);
                        dispatcher.forward(request, response);
                        return;
                    }
                }
            }
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);

    }

}
