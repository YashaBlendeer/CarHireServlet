package com.yashablendeer.CarHireServlet.filter;


import com.yashablendeer.CarHireServlet.model.Role;
import com.yashablendeer.CarHireServlet.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class PathsFilter implements Filter {

    private Pattern unauthorizedPattern = Pattern.compile("(/)|(/login\\b.*)|(/registration\\b.*)");
    private Pattern publicPattern = Pattern.compile("(/)|(/error)|(/login\\b.*)|(/registration\\b.*)|(/navbar\\b.*)");
    private Pattern userPattern = Pattern.compile("(/home\\b.*)|(/carOrder\\b.*)|(/carPage\\b.*)|(/allOrders\\b.*)|" +
            "(/navbar\\b.*)|(/error)|(/logout\\b.*)");
    private Pattern adminPattern = Pattern.compile("(/home\\b.*)|(/carPage\\b.*)|(/carAdd\\b.*)|(/carUpdate\\b.*)|" +
            "(/allUsers\\b.*)|(/navbar\\b.*)|(/error)|(/logout\\b.*)");
    private Pattern managerPattern = Pattern.compile("(/home\\b.*)|(/carPage\\b.*)|(/allOrders\\b.*)|(/navbar\\b" +
            ".*)|(/orderReject\\b.*)|(/orderRepair\\b.*)|(/error)|(/logout\\b.*)");
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        User user = (User) session.getAttribute("user");
        System.out.println("user: " + user);
        System.out.println("path: " + path);

        System.out.println("isLogged:" + isLogged(user));

        if(!isLogged(user) && publicPattern.matcher(path).matches()){
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("inside first if");
            return;
        }

        if(isLogged(user) && isActive(user) && isAccessible(user, path) ){
            System.out.println("inside 2 if");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else if(isLogged(user) && isActive(user) && !isAccessible(user, path)) {
            System.out.println("inside 3 if");
            response.sendRedirect("/home");
            return;
        }

        if(!isLogged(user) && !publicPattern.matcher(path).matches()){
            System.out.println("inside 4 if");
            response.sendRedirect("/login");
            return;
        }

        if(!isActive(user)){
            request.setAttribute("userBanned", "You are banned");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/error");

//            request.getRequestDispatcher("WEB-INF/error.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAccessible (User user, String path) {
        boolean isAccessible = false;

        Role role = user.getRole();
        switch (role) {
            case USER:
                isAccessible = userPattern.matcher(path).matches();
                break;
            case ADMIN:
                isAccessible = adminPattern.matcher(path).matches();
                break;
            case MANAGER:
                isAccessible = managerPattern.matcher(path).matches();
                break;
        }
        return isAccessible;
    }

    private boolean isLogged(User user) {
        return user != null;
    }

    private boolean isActive(User user) {
        return user.getActive();
    }
}
