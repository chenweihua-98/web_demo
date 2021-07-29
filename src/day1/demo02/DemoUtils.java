package day1.demo02;

import day1.demo02.jdbcutils.DemoJdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 演示工具类
 * 通过键盘录入用户名以及密码，判断用户是否登录成功
 */
public class DemoUtils {

    public static void main(String[] args) {

            //键盘录入
        System.out.println("请输入用户名");
        Scanner sc1 = new Scanner(System.in);
        String username = sc1.next();
        System.out.println("请输入密码：");
        Scanner sc2 = new Scanner(System.in);
        String password = sc2.next();


        boolean flag = DemoUtils.login(username,password);
        if (flag){
            System.out.println("登录成功");
        }else {
            System.out.println("用户名或者密码错误");
        }
    }

    //登录方法
    public  static boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //连接数据库判断是否登录成功

            //1.获取连接
             conn = DemoJdbcUtils.getConnection();
            //定义sql
            String sql = "select * from user where username = '"+username+"'and password = '"+password+"'";
            //获取执行sql的对象
             stmt = conn.createStatement();

            //执行查询
             rs = stmt.executeQuery(sql);

            //判断 如果有下一行则返回true
            return  rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DemoJdbcUtils.close(rs,stmt,conn);
        }


        return false;

    }


}
