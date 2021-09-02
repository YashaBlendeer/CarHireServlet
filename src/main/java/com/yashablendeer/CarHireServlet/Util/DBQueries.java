package com.yashablendeer.CarHireServlet.Util;

public class DBQueries {
//    public static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
//    public static final String FIND_USER_ROLE_BY_ID = "SELECT * FROM user_role WHERE user_id = ?";

//    EXTRACT USER QUERIES
    public static final String FIND_USER_BY_ID = "select users.user_id, users.active, users.email, users.last_name, " +
            "users.name, users.password, users.user_name, r.role " +
            "from users " +
            "inner join user_role ur on users.user_id = ur.user_id " +
            "inner join roles r on ur.role_id = r.role_id " +
            "where users.user_id = ?";

    public static final String FIND_ALL_USERS = "select users.user_id, users.active, users.email, users.last_name, " +
            "users.name, users.password, users.user_name, r.role " +
            "from users " +
            "inner join user_role ur on users.user_id = ur.user_id " +
            "inner join roles r on ur.role_id = r.role_id";
    public static final String FIND_USER_BY_USERNAME = "select users.user_id, users.active, users.email, users.last_name, " +
            "users.name, users.password, users.user_name, r.role " +
            "from users " +
            "inner join user_role ur on users.user_id = ur.user_id " +
            "inner join roles r on ur.role_id = r.role_id " +
            "where users.user_name = ?";

//    EXTRACT CAR QUERIES
    public static final String FIND_CAR_BY_ID = "select * from cars where car_id = ?";
    public static final String FIND_CARS_BY_MARK = "select * from cars where mark = ?";
    public static final String FIND_CARS_BY_QUALITY = "select * from cars where quality = ?";
    public static final String DELETE_CAR_BY_ID = "delete from cars where car_id = ?";
    public static final String FIND_ALL_CARS = "select * from cars";


//    EXTRACT ORDER QUERIES
    public static final String FIND_ORDER_BY_ID = "select * from orders where order_id = ?";
    public static final String FIND_ALL_ORDERS = "select * from orders";
    public static final String FIND_ORDERS_BY_CAR_ID = "select * from orders where car_id = ?";
    public static final String DELETE_ORDER_BY_ID = "delete from orders where order_id = ?";


//    EXTRACT REPAIR QUERIES
    public static final String FIND_REPAIR_BY_ID = "select * from repairs where id = ?";
    public static final String FIND_ALL_REPAIRS = "select * from repairs";
    public static final String FIND_REPAIR_BY_ORDER_ID = "select * from repairs where order_id = ?";

}
