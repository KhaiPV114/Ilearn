package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.CartDAO;
import com.onlinelearning.DAL.Impl.CartDAOImpl;
import com.onlinelearning.Models.CartItem;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Utils.Encryption;
import com.onlinelearning.Utils.Impl.AESEncryptionImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CartServiceImpl implements CartService {

    private final Encryption AES = new AESEncryptionImpl();
    private final CartDAO CartDAO = new CartDAOImpl();
    private final CourseService CourseService = new CourseServiceImpl();
    private final AuthService AuthService = new AuthServiceImpl();
    private final String CartCookieName = "CART-STORE";
    private final String SeperateCharacter = "-";

    @Override
    public List<CartItem> getCartByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return CartDAO.getCartByUserId(userId);
    }

    @Override
    public CartItem getCartByUserIdAndCourseId(Integer userId, Integer courseId) {
        if (userId == null || courseId == null) {
            return null;
        }
        return CartDAO.getCartItem(userId, courseId);
    }

    private void validateCart(Integer userId, Integer courseId) throws Exception {
        if (CartDAO.getCartItem(userId, courseId) != null) {
            throw new Exception("This course is exist in your cart");
        }
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) throws Exception {
        validateCart(cartItem.getUserId(), cartItem.getCourseId());
        CartItem createdCartItem = CartDAO.createCartItem(cartItem);
        if (createdCartItem == null) {
            throw new Exception("Failed to add this course to your cart");
        }
        return createdCartItem;
    }

    @Override
    public CartItem deleteCartItem(CartItem cartItem) throws Exception {
        try {
            validateCart(cartItem.getUserId(), cartItem.getCourseId());
            throw new Exception("This course is not exist in your cart");
        } catch (Exception cartItemIsExistException) {
            return CartDAO.deleteCartItem(cartItem);
        }
    }

    @Override
    public void addNewCartItemToCookie(CartItem newCartItem, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CartItem> cartInCookie = new ArrayList<>();
        try {
            cartInCookie = getCartFromCookie(request);
        } catch (Exception e) {
        }

        if (newCartItem != null) {
            if (cartInCookie.contains(newCartItem)) {
                throw new Exception("This course is exist in your cart");
            } else {
                cartInCookie.add(newCartItem);
                addCartToCookie(request, response, cartInCookie);
            }
        } else {
            throw new Exception("Invalid cart item to add");
        }
    }

    @Override
    public void removeCartItemFromCookie(CartItem cartItem, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CartItem> cartInCookie = new ArrayList<>();
        try {
            cartInCookie = getCartFromCookie(request);
        } catch (Exception e) {
        }
        if (cartItem != null) {
            if (cartInCookie.contains(cartItem)) {
                cartInCookie.remove(cartItem);
                addCartToCookie(request, response, cartInCookie);
            } else {
                throw new Exception("This course is not exist in your cart");
            }
        } else {
            throw new Exception("Invalid cart item to remove");
        }
    }

    //Return a list of validated cart item from cookie (Guest, Learner)
    private List<CartItem> getCartFromCookie(HttpServletRequest request) throws Exception {
        //Get cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CartCookieName)) {

                    //Decrypt data from cookie
                    String decrypted = AES.decrypt(cookie.getValue());
                    if (decrypted == null) {
                        throw new Exception("Cart in cookie is invalid format");
                    }

                    //Spilit data encrypted to array of course id
                    HashSet<Integer> courseIds = new HashSet<>();
                    for (String data : decrypted.split(SeperateCharacter)) {
                        try {
                            courseIds.add(Integer.parseInt(data));
                        } catch (NumberFormatException e) {
                        }
                    }

                    //Validate course (skip if invalid)
                    //If is learner, check if they have enroll course
                    List<CartItem> cartInCookie = new ArrayList<>();
                    User user = AuthService.getUser(request);
                    if (user == null) {
                        for (Integer courseId : courseIds) {
                            try {
                                if (CourseService.validateCourse(courseId) != null) {
                                    cartInCookie.add(CartItem.builder().courseId(courseId).build());
                                }
                            } catch (Exception e) {
                            }
                        }
                    } else {
                        for (Integer courseId : courseIds) {
                            try {
                                if (CourseService.validateCourse(courseId) != null
                                        && !CourseService.isEnrolled(user.getId(), courseId)) {
                                    cartInCookie.add(CartItem.builder().courseId(courseId).build());
                                }
                            } catch (Exception e) {
                            }
                        }
                    }

                    if (cartInCookie.isEmpty()) {
                        throw new Exception("Cart in cookie is empty");
                    }

                    return cartInCookie;
                }
            }
        }
        throw new Exception("Cannot found cookie store cart");
    }

    //Add cart to cookie, if cart empty, remove cookie
    private void addCartToCookie(HttpServletRequest request, HttpServletResponse response, List<CartItem> cart) {
        if (!cart.isEmpty()) {

            //Convert cart to string data
            String dataCookie = "";
            for (CartItem cartItem : cart) {
                if (cartItem.getCourseId() != null) {
                    dataCookie += cartItem.getCourseId().toString() + SeperateCharacter;
                }
            }

            //Encrypt data
            if (!dataCookie.isEmpty()) {
                dataCookie = AES.encrypt(dataCookie.substring(0, dataCookie.length() - 1));
            }

            //Add cookie
            Cookie cartCookie = new Cookie(CartCookieName, dataCookie);
            cartCookie.setPath("/");    //For all pages in website
            cartCookie.setMaxAge(7 * 24 * 60 * 60); //7 days
            response.addCookie(cartCookie);
        } else {
            removeCartFromCookie(request, response);
        }
    }

    private void removeCartFromCookie(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Remove cookie cart");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CartCookieName)) {
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return;
            }
        }
    }

    @Override
    public List<Course> getCourseInCart(HttpServletRequest request, HttpServletResponse response) {
        User user = AuthService.getUser(request);
        List<CartItem> cart = new ArrayList<>();
        List<Course> coursesInCart = new ArrayList<>();
        
        if (user == null) {
            System.out.println("Get Cookie Cart");
            try {
                cart = getCartFromCookie(request);
            } catch (Exception ex) {
            }
        } else {
            System.out.println("Get User Cart");
            cart = getCartByUserId(user.getId());
            if (cart.isEmpty()) {
                try {
                    cart = getCartFromCookie(request);
                    for (CartItem cartItem : cart) {
                        cartItem.setUserId(user.getId());
                        cartItem = createCartItem(cartItem);
                    }
                } catch (Exception e) {
                }
            }
            removeCartFromCookie(request, response);
        }

        if (!cart.isEmpty()) {
            for (CartItem cartItem : cart) {
                try {
                    coursesInCart.add(CourseService.validateCourse(cartItem.getCourseId()));
                } catch (Exception ex) {
                }
            }
        }
        return coursesInCart;
    }

    @Override
    public boolean deleteCartOfUserId(Integer userId) {
        return CartDAO.deleteCartOfUserId(userId);
    }

}
