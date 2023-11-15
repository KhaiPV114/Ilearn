package com.onlinelearning.Controllers.General;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "LearnerFindCourse", urlPatterns = {"/search"})
public class GeneralSearchCourse extends HttpServlet {

    private static final String VIEW_PATH = "/general/course-search.jsp";

    private final CourseService courseService = new CourseServiceImpl();

    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        double priceFrom = 0;
        double priceTo = 500; 
        final int pageSize = 2;
        List<Integer> enrolledCourseId = new ArrayList<>(0);
        
        HttpSession session = request.getSession();
        User user =  (User)  session.getAttribute("user");

       System.out.println(user);
        if(user != null){
            enrolledCourseId = courseService.getAllEnrolledCourseId(user.getId());
        }
                 System.out.println("========");
         System.out.println(enrolledCourseId.contains(1));
         System.out.println(enrolledCourseId.contains(3));
//        C1:----------------------
        System.out.println("");
        System.out.println("Start process: ");

        List<Category> categorys = categoryService.getAllCategories();
        request.setAttribute("categorys", categorys);

        String filterPrice = request.getParameter("filterPrice");
        request.setAttribute("filterPrice", filterPrice);

        String courseKeyword = request.getParameter("courseKeyword") == null ? "" : request.getParameter("courseKeyword");
        request.setAttribute("courseKeyword", courseKeyword);

        String categories = request.getParameter("filterCategory") == null ? "" : request.getParameter("filterCategory");
        System.out.println("FilterCategory: " + categories);

        
        
        String page = request.getParameter("page");

        Integer pageNumber = page == null ? 1 : Integer.parseInt(page);

        String priceRange = request.getParameter("priceRange");
        if (priceRange != null) {
            String[] priceRanges = priceRange.split("\\D+");
            priceFrom = Double.valueOf(priceRanges[1].trim());
            priceTo = Double.valueOf(priceRanges[2].trim());
        }
        System.out.println("Pricerange: " + priceRange);
        request.setAttribute("priceRange", priceRange);

        StringBuilder sql = new StringBuilder("select c.* from courses c join categories c2 on c.category_id = c2.category_id where ");
        sql.append(" c.name like concat('%','").append(courseKeyword).append("','%') and");
        sql.append(" c2.name like concat('%','").append(categories).append("','%') and");
        sql.append(" c.price between ").append(priceFrom).append(" and ").append(priceTo);
        if ("desc".equals(filterPrice)) {
            sql.append("order by c.price desc");
            request.setAttribute("sort", "desc");
        }
        if ("asc".equals(filterPrice)) {
            sql.append("order by c.price asc");
             request.setAttribute("sort", "asc");
        }
        int size = courseService.findAll(sql.toString()).size();
        sql.append(" limit ").append(pageSize).append(" offset ").append((pageNumber - 1) * pageSize);
        System.out.println(sql.toString());
        List<Course> courses = courseService.findAll(sql.toString());
        request.setAttribute("courses", courses);
        request.setAttribute("fromPrice", priceFrom);
        request.setAttribute("toPrice", priceTo);
        request.setAttribute("size", size);//in ra
            request.setAttribute("number", courses.size());//
             request.setAttribute("pageNumber", pageNumber);//
        request.setAttribute("maxPage", Math.ceil((double) size / pageSize)); // in dong nayd ra a xem nó tính dc bao nhieu
        request.setAttribute("enrolledCourseId", enrolledCourseId);
        request.setAttribute("filterCategory", categories);
        dispatcher.forward(request, response);
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
//
//        
////        C1:----------------------
//        System.out.println("");
//        System.out.println("Start process: ");
//
//        List<Course> courses = courseService.getAllCourses();
//
//        List<Category> categorys = categoryService.getAllCategories();
//        request.setAttribute("categorys", categorys);
//
//        String filterPrice = request.getParameter("filterPrice");
//        request.setAttribute("filterPrice", filterPrice);
//        System.out.println("FilterPrice: " + filterPrice);
//
//        String courseKeyword = request.getParameter("courseKeyword");
//        request.setAttribute("courseKeyword", courseKeyword);
//        System.out.println("CourseKeyword: " + courseKeyword);
//
//        String categories = request.getParameter("filterCategory");
//        System.out.println("FilterCategory: " + categories);
//
//        String priceRange = request.getParameter("priceRange");
//        System.out.println("Pricerange: " + priceRange);
//        request.setAttribute("priceRange", priceRange);
//
//
//
//        //CourseKeyword is blank
////        if (StringUtils.isBlank(courseKeyword)) {
////            //filterPrice and categories is blank
////            if (StringUtils.isBlank(filterPrice) && StringUtils.isBlank(categories)) {
////                System.out.print("Blank coursekeyword: ");
////                System.out.println(courses);
////                request.setAttribute("courses", courses);
////                request.getRequestDispatcher(VIEW_PATH).forward(request, response);
////                return;
////            }
////            //filterPrice is not blank and categories is blanak
////            if (StringUtils.isNotBlank(filterPrice) && StringUtils.isBlank(categories)) {
////                if (filterPrice.equals("hightolow")) {
////                    courses = courseService.getAllCourses();
////                    Collections.sort(courses, new Comparator<Course>() {
////                        public int compare(Course a, Course b) {
////                            return (int) (b.getPrice() - a.getPrice());
////                        }
////                    });
////                    System.out.println("filterPrice is not blank and categories is blank");
////                    System.out.println(courses);
////                    request.setAttribute("courses", courses);
////                    dispatcher.forward(request, response);
////                    return;
////                }
////                if (filterPrice.equals("lowtohigh")) {
////                    courses = courseService.getAllCourses();
////                    Collections.sort(courses, new Comparator<Course>() {
////                        public int compare(Course a, Course b) {
////                            return (int) (a.getPrice() - b.getPrice());
////                        }
////                    });
////                    System.out.println("filterPrice is not blank and categories is blank");
////                    System.out.println(courses);
////                    request.setAttribute("courses", courses);
////                    dispatcher.forward(request, response);
////                    return;
////                }
////            }
////            //filterPrice is blank, categories is not blank
////            if (StringUtils.isBlank(filterPrice) && StringUtils.isNotBlank(categories)) {
////                courses = courseService.getCourseByCategory(categories);
////                System.out.println("filterPrice is blank and categories is not blank");
////                System.out.println(courses);
////                request.setAttribute("courses", courses);
////                dispatcher.forward(request, response);
////                return;
////            }
////            //Both filterPrice and categories are not blank
////            if (StringUtils.isNotBlank(filterPrice) && StringUtils.isNotBlank(categories)) {
////                if (filterPrice.equals("hightolow")) {
////                    courses = courseService.getCourseByCategory(categories);
////                    System.out.println("Both filterPrice and categories are not blank");
////                    System.out.println(courses);
////                    Collections.sort(courses, new Comparator<Course>() {
////                        public int compare(Course a, Course b) {
////                            return (int) (b.getPrice() - a.getPrice());
////                        }
////                    });
////                    System.out.println(courses);
////                    request.setAttribute("courses", courses);
////                    dispatcher.forward(request, response);
////                    return;
////                }
////                if (filterPrice.equals("lowtohigh")) {
////                    System.out.println("Both filterPrice and categories are not blank");
////                    courses = courseService.getCourseByCategory(categories);
////                    System.out.println(courses);
////                    Collections.sort(courses, new Comparator<Course>() {
////                        public int compare(Course a, Course b) {
////                            return (int) (a.getPrice() - b.getPrice());
////                        }
////                    });
////                    System.out.println(courses);
////                    request.setAttribute("courses", courses);
////                    dispatcher.forward(request, response);
////                    return;
////                }
////            }
////        }
////
////        //coursekeyword is not blank 
////        if (StringUtils.isNotBlank(courseKeyword)) {
////            //filterPrice and categories is blank
////            if (StringUtils.isBlank(filterPrice) && StringUtils.isBlank(categories)) {
////                System.out.println("Not Blank coursekeyword: ");
////                courses = courseService.getCourseByKeyword(courseKeyword);
////                System.out.println(courses);
////                request.setAttribute("courses", courses);
////                request.getRequestDispatcher(VIEW_PATH).forward(request, response);
////                return;
////            }
////            //filterPrice is not blank and categories is blank 
////            if (StringUtils.isNotBlank(filterPrice) && StringUtils.isBlank(categories)) {
////                
////                if (filterPrice.equals("hightolow")) {
////                    
////                    courses = courseService.getCourseByKeyword(courseKeyword);
////                    Collections.sort(courses, new Comparator<Course>() {
////                        public int compare(Course a, Course b) {
////                            return (int) (b.getPrice() - a.getPrice());
////                        }
////                    });
////                    request.setAttribute("courses", courses);
////                    dispatcher.forward(request, response);
////                    return;
////                }
////                if (filterPrice.equals("lowtohigh")) {
////
////                    if (StringUtils.isNotBlank(courseKeyword)) {
////                        courses = courseService.getCourseByKeyword(courseKeyword);
////                        Collections.sort(courses, new Comparator<Course>() {
////                            public int compare(Course a, Course b) {
////                                return (int) (a.getPrice() - b.getPrice());
////                            }
////                        });
////                        System.out.println(courses);
////                        request.setAttribute("courses", courses);
////                        dispatcher.forward(request, response);
////                        return;
////                    }
////                }
////            }
////                //filterPrice is blank and categories is not blank
////                if (StringUtils.isBlank(filterPrice) && StringUtils.isNotBlank(categories)) {
////                    List<Course> courses1 = new ArrayList<>();
////                    courses = courseService.getCourseByKeyword(courseKeyword);
////                    Category category = categoryService.getCategoryByName(categories);
////
////                    for (Course c : courses) {
////                        if (c.getCategoryId().equals(category.getId())) {
////                            courses1.add(c);
////                        }
////                    }
////                    System.out.println(courses1);
////                    request.setAttribute("courses", courses1);
////                    dispatcher.forward(request, response);
////                    return;
////                }
////                //filterPrice and categories are not blank
////                if (StringUtils.isNotBlank(filterPrice) && StringUtils.isNotBlank(categories)) {
////                    List<Course> courses1 = new ArrayList<>();
////                    if (filterPrice.equals("hightolow")) {
////                        courses = courseService.getCourseByKeyword(courseKeyword);
////                        Category category = categoryService.getCategoryByName(categories);
////
////                        for (Course c : courses) {
////                            if (c.getCategoryId().equals(category.getId())) {
////                                courses1.add(c);
////                            }
////                        }
////                        Collections.sort(courses1, new Comparator<Course>() {
////                            public int compare(Course a, Course b) {
////                                return (int) (b.getPrice() - a.getPrice());
////                            }
////                        });
////                        System.out.println(courses1);
////                        request.setAttribute("courses", courses1);
////                        dispatcher.forward(request, response);
////                        return;
////                    }
////                    if (filterPrice.equals("lowtohigh")) {
////                        courses = courseService.getCourseByKeyword(courseKeyword);
////                        Category category = categoryService.getCategoryByName(categories);
////
////                        for (Course c : courses) {
////                            if (c.getCategoryId().equals(category.getId())) {
////                                courses1.add(c);
////                            }
////                        }
////                        
////                        Collections.sort(courses1, new Comparator<Course>() {
////                            public int compare(Course a, Course b) {
////                                return (int) (a.getPrice() - b.getPrice());
////                            }
////                        });
////
////                        System.out.println(courses1);
////                        request.setAttribute("courses", courses1);
////                        dispatcher.forward(request, response);
////                        return;
////                    }
////                }
////            }
//        }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
