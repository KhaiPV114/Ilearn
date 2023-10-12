package com.onlinelearning.Services.Impl;

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
        return cartDAO.getCartByUserIdAndCourseId(userId, courseId);
    }

    private void validateCart(Integer userId, Integer courseId) throws Exception {
        if (cartDAO.getCartByUserIdAndCourseId(userId, courseId) != null) {
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
        if (cartInCookie.contains(newCartItem)) {
            throw new Exception("This course already in your cart");
        } else {
            cartInCookie.add(newCartItem);
            addCartToCookie(response, cartInCookie);
        }
    }

    //Return list of cart item in cookie, empty if not found
    @Override
    public List<CartItem> getCartFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<CartItem> cartInCookie = new ArrayList<>();
        String[] courseIds = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CookieName)) {
                    courseIds = cookie.getValue().split(SeperateCharacter);

                }
            }
        }
        if (courseIds != null) {
            for (String course : courseIds) {
                try {
                    cartInCookie.add(CartItem.builder()
                            .courseId(Integer.parseInt(course))
                            .build());
                } catch (NumberFormatException e) {
                }
            }
        }
        return cartInCookie;
    }

    @Override
    public void addCartToCookie(HttpServletResponse response, List<CartItem> cart) {
        String dataStoreInCookie = "";
        for (CartItem cartItem : cart) {    //1-2-3-4-5
            dataStoreInCookie += cartItem.getCourseId().toString() + SeperateCharacter;
        }
        if (!dataStoreInCookie.isEmpty()) {
            dataStoreInCookie = dataStoreInCookie.substring(0, dataStoreInCookie.length() - 1);
        }
        Cookie cartCookie = new Cookie(CookieName, dataStoreInCookie);
        cartCookie.setPath("/");    //For all pages in website
        cartCookie.setMaxAge(7 * 24 * 60 * 60); //7 days
        response.addCookie(cartCookie);
    }

    @Override
    public void removeCartFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CookieName)) {
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
            if (cart.isEmpty()) {
                cart = getCartFromCookie(request);
                for (CartItem cartItem : cart) {
                    try {
                        cartItem.setUserId(user.getId());
                        cartItem = createCartItem(cartItem);
                    } catch (Exception ex) {
                    }
                }
            }
            removeCartFromCookie(request, response);
        }

        if (!cart.isEmpty()) {
            for (CartItem cartItem : cart) {
                coursesInCart.add(courseService.getCourseById(cartItem.getCourseId()));
            }
        }

        session.setAttribute("coursesInCart", coursesInCart);
    }

    @Override
    public boolean deleteCartOfUserId(Integer userId) {
        return cartDAO.deleteCartOfUserId(userId);
    }

}
