package com.yashablendeer.CarHireServlet.controller.command;

import com.yashablendeer.CarHireServlet.model.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandSecurity {
    public static void addUserSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
//        context.setAttribute("user", user.getLogin());
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getUserName());

        System.out.println("logged users in addSession: " + loggedUsers);

        context.setAttribute("loggedUsers", loggedUsers);
        session.setAttribute("user", user);
        session.setAttribute("userRole", user.getRole().name());

        System.out.println("user in addUserSession" + user);
    }

    public static void destroyUserSession(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");
        HttpSession session = request.getSession();
        System.out.println("loggedUsers in destroy before destroy" + loggedUsers);

        loggedUsers.remove(userName);
        session.removeAttribute("user");
        session.removeAttribute("userRole");

        System.out.println("loggedUsers in destroy after destroy" + loggedUsers);

        request.getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}
