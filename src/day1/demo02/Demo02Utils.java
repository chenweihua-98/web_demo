package day1.demo02;

import day1.demo02.jdbcutils.DemoJdbcUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * 登录方法使用PreparedStatement对象来解决SQL注入问题,且效率更高
 * 参数使用？作为占位符
 */
public class Demo02Utils {

    public static void main(String[] args) {

        //键盘录入
        System.out.println("请输入用户名");
        Scanner sc1 = new Scanner(System.in);
        String username = sc1.next();
        System.out.println("请输入密码：");
        Scanner sc2 = new Scanner(System.in);
        String password = sc2.next();


        boolean flag = Demo02Utils.login(username, password);
        if (flag) {
            System.out.println("登录成功");
        } else {
            System.out.println("用户名或者密码错误");
        }
    }

    //登录方法
    public static boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            //连接数据库判断是否登录成功

            //1.获取连接
            conn = DemoJdbcUtils.getConnection();
            //定义sql
            String sql = "select * from user where username = ? and password = ?";
            //获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            //执行查询
            rs = pstmt.executeQuery();

            //判断 如果有下一行则返回true
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DemoJdbcUtils.close(rs, pstmt, conn);
        }


        return false;

    }


}
