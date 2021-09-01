package com.yashablendeer.CarHireServlet.dao;

import com.yashablendeer.CarHireServlet.Util.BCrypt;
import com.yashablendeer.CarHireServlet.Util.DBUtil;
import com.yashablendeer.CarHireServlet.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoOld {
    private Connection conn;
    public UserDaoOld() {

        conn= DBUtil.getConnection();//connect to database
    }

    public boolean checkLogin(String login, String password)
    {
        boolean canLogin=false;
        boolean truePass=false;

        //Search database for user name and password and return true if found
        try
        {
            PreparedStatement ps = conn
                    .prepareStatement("select * from users where user_name=?");
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                truePass = BCrypt.checkpw(password, rs.getString("password"));
            }
            canLogin = rs.first() && truePass; //true if found userName and pass else false
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return canLogin;
    }

    public User userSession(String userName)
    {
        User user = new User(); //create new user object
        try
        {
            PreparedStatement ps = conn
                    .prepareStatement("select * from users where user_name=?");//search database for email
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                user.setId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setActive(rs.getBoolean("active"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
