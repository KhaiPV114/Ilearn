package com.onlinelearning.Services.Impl;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.onlinelearning.DAL.CartDAO;
import com.onlinelearning.DAL.Impl.CartDAOImpl;
import com.onlinelearning.Models.CartItem;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.CourseService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO = new CartDAOImpl();
    
    private final CourseService courseService = new CourseServiceImpl();

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
        return cartDAO.getCartByUserIdAndCourseId(userId, courseId);
    }

    private void validateCart(Integer userId, Integer courseId) throws Exception {
        if (cartDAO.getCartByUserIdAndCourseId(userId, courseId) != null) {
            throw new Exception("Cart: (" + userId + ", " + courseId + ") is already exist!");
        }
    }

    @Override
    public CartItem createCartItem(Integer userId, Integer courseId) throws Exception {
        validateCart(userId, courseId);
        return cartDAO.createCart(userId, courseId);
    }

    @Override
    public CartItem deleteCartItem(CartItem cartItem) throws Exception {
        try {
            validateCart(cartItem.getUserId(), cartItem.getCourseId());
        } catch (Exception cartItemIsExistException) {
            return cartDAO.deleteCart(cartItem);
        }
        throw new Exception("Cart: " + cartItem + "doesn't exist");
    }

    @Override
    public List<CartItem> getCartFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Gson gson = new Gson();
        List<CartItem> cart = new ArrayList<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    String cartInJson = cookie.getValue();
                    cart = gson.fromJson(cartInJson, new TypeToken<List<CartItem>>() {
                    }.getType());
                    break;
                }
            }
        }
        return cart;
    }

    @Override
    public void addCartToCookie(HttpServletResponse response, List<CartItem> cart) {
        Gson gson = new Gson();
        String cartJson = gson.toJson(cart);
        Cookie cartCookie = new Cookie("cart", cartJson);
        cartCookie.setPath("/");    //For all pages in website
        cartCookie.setMaxAge(7 * 24 * 60 * 60); //7 days
        response.addCookie(cartCookie);
    }

    @Override
    public void removeCartFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cart")) {
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                break;
            }
        }
    }

    @Override
    public void updateCartInSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        List<CartItem> cart;
        List<Course> coursesInCart = new ArrayList<>();

        if (user == null) {
            cart = getCartFromCookie(request);
        } else {
            cart = getCartByUserId(user.getId());
            //If in database, user haven't have cart's info
            if (cart.isEmpty()) {
                //Get cart from cookie, and add to database
                cart = getCartFromCookie(request);
                for (CartItem cartItem : cart) {
                    try {
                        cartItem = createCartItem(user.getId(), cartItem.getCourseId());
                    } catch (Exception ex) {
                    }
                }
            }
            removeCartFromCookie(request, response);
        }

        //Get list course in cart to display
        if (!cart.isEmpty()) {
            for (CartItem cartItem : cart) {
                coursesInCart.add(courseService.getCourseById(cartItem.getCourseId()));
            }
        }

        session.setAttribute("coursesInCart", coursesInCart);
    }

}
