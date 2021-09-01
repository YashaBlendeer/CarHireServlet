package com.yashablendeer.CarHireServlet.servlet;

import com.yashablendeer.CarHireServlet.dao.UserDaoOld;
import com.yashablendeer.CarHireServlet.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static String HOME= "/home.jsp";
    private UserDaoOld dao;
    public LoginServlet()
    {
        super();
        dao = new UserDaoOld();//create new data object to interact with database
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pwOut = response.getWriter();
        //get input from jsp
        String userName=request.getParameter("userName");
        String password =request.getParameter("password");

        if(dao.checkLogin(userName, password))
        {
            //create session and store variables
            User user= dao.userSession(userName);
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUserName());
            session.setAttribute("password", password);
            //load welcome page with session data
            RequestDispatcher view = request.getRequestDispatcher(HOME);
            view.forward(request, response);

        }
        //if input is not stored in database print error message and reload page
        else
        {
            pwOut.print("<p style=\"color:red\">Incorrect Username or Password!</p>");
            RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
            view.include(request, response);

        }

        pwOut.close();
    }

}
