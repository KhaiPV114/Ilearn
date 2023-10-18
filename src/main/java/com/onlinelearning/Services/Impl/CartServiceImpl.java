package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.CartDAO;
import com.onlinelearning.DAL.Impl.CartDAOImpl;
import com.onlinelearning.Models.CartItem;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.CourseService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO = new CartDAOImpl();
    private final CourseService CourseService = new CourseServiceImpl();
    private final AuthService AuthService = new AuthServiceImpl();
    private final String CookieName = "CART";
    private final String SeperateCharacter = "-";

    @Override
    public List<CartItem> getCartByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return cartDAO.getCartByUserId(userId);
    }

    @Override
    public CartItem getCartByUserIdAndCourseId(Integer userId, Integer courseId) {
        if (userId == null || courseId == null) {
            return null;
        }
        return cartDAO.getCartItem(userId, courseId);
    }

    private void validateCart(Integer userId, Integer courseId) throws Exception {
        if (cartDAO.getCartItem(userId, courseId) != null) {
            throw new Exception("This course already in your cart!");
        }
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) throws Exception {
        validateCart(cartItem.getUserId(), cartItem.getCourseId());
        CartItem createdCartItem = cartDAO.createCartItem(cartItem);
        if (createdCartItem == null) {
            throw new Exception("Failed to add this course to your cart!");
        }
        return createdCartItem;
    }

    @Override
    public CartItem deleteCartItem(CartItem cartItem) throws Exception {
        try {
            validateCart(cartItem.getUserId(), cartItem.getCourseId());
        } catch (Exception cartItemIsExistException) {
            return cartDAO.deleteCartItem(cartItem);
        }
        throw new Exception("Cart: " + cartItem + "doesn't exist");
    }

    @Override
    public void addNewCartItemToCookie(CartItem newCartItem, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CartItem> cartInCookie = getCartFromCookie(request);
        if (newCartItem != null) {
            if (cartInCookie.contains(newCartItem)) {
                throw new Exception("This course already in your cart");
            } else {
                cartInCookie.add(newCartItem);
                addCartToCookie(request, response, cartInCookie);
            }
        } else {
            throw new Exception("Invalid cart item");
        }
    }

    @Override
    public void removeCartItemFromCookie(CartItem cartItem, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CartItem> cartInCookie = getCartFromCookie(request);
        if (cartInCookie.isEmpty()) {
            throw new Exception("Cart in cookie is empty");
        }
        if (cartInCookie.contains(cartItem)) {
            cartInCookie.remove(cartItem);
            addCartToCookie(request, response, cartInCookie);
        } else {
            throw new Exception("Cart item is not exist");
        }
    }

    @Override
    public void removeCartFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CookieName)) {
//                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return;
            }
        }
    }

    @Override
    public List<CartItem> getCartFromCookie(HttpServletRequest request) {
        List<CartItem> cartInCookie = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        List<Course> courses = new ArrayList<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CookieName)) {
                    String[] coursesId = cookie.getValue().split(SeperateCharacter);
                    for (String courseId : coursesId) {
                        try {
                            courses.add(CourseService.validateCourse(Integer.parseInt(courseId)));
                        } catch (Exception ex) {
                            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }

        if (!courses.isEmpty()) {
            for (Course course : courses) {
                cartInCookie.add(CartItem.builder().courseId(course.getId()).build());
            }
        }
        return cartInCookie;
    }

    @Override
    public void addCartToCookie(HttpServletRequest request, HttpServletResponse response, List<CartItem> cart) {
        if (!cart.isEmpty()) {
            String dataStoreInCookie = "";
            for (CartItem cartItem : cart) {
                if (cartItem.getCourseId() != null) {
                    dataStoreInCookie += cartItem.getCourseId().toString() + SeperateCharacter;
                }
            }
            if (!dataStoreInCookie.isEmpty()) {
                dataStoreInCookie = dataStoreInCookie.substring(0, dataStoreInCookie.length() - 1);
            }
            Cookie cartCookie = new Cookie(CookieName, dataStoreInCookie);
            cartCookie.setPath("/");    //For all pages in website
            cartCookie.setMaxAge(7 * 24 * 60 * 60); //7 days
            response.addCookie(cartCookie);
        } else {
            removeCartFromCookie(request, response);
        }
    }

    @Override
    public List<Course> getCourseInCart(HttpServletRequest request, HttpServletResponse response) {
       
        User user = AuthService.getUser(request);
        List<CartItem> cart;
        List<Course> coursesInCart = new ArrayList<>();

        if (user == null) {
            cart = getCartFromCookie(request);
        } else {
            cart = getCartByUserId(user.getId());
            if (cart.isEmpty()) {
                if (!(cart = getCartFromCookie(request)).isEmpty()) {
                    for (CartItem cartItem : cart) {
                        try {
                            cartItem.setUserId(user.getId());
                            if (!CourseService.isEnrolled(user.getId(), cartItem.getCourseId())) {
                                cartItem = createCartItem(cartItem);
                            }
                        } catch (Exception ex) {
                        }
                    }
                }
            }
            removeCartFromCookie(request, response);
        }

        if (!cart.isEmpty()) {
            for (CartItem cartItem : cart) {
                try {
                    coursesInCart.add(CourseService.validateCourse(cartItem.getCourseId()));
                } catch (Exception ex) {
                    Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
//        request.getSession(true).setAttribute("coursesInCart", coursesInCart);
        return coursesInCart;
    }

    @Override
    public boolean deleteCartOfUserId(Integer userId) {
        return cartDAO.deleteCartOfUserId(userId);
    }

}
