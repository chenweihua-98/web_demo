package day1.demo01;

import java.sql.*;

/**
 * ResultSet 结果集对象，封装查询结果
 */

public class Demo03Jdbc {

    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.定义sql
            String sql = "select * from account";
            //获取collection对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "chenweihua");
            //4.获取执行sql的对象 statement
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.处理结果
            //6.1让游标向下移动一行
            while(rs.next()) {
                //6.2获取数据
                int id = rs.getInt(1);//代表列的编号,从1开始
                String name = rs.getNString("name");//代表列名称
                Double balance = rs.getDouble(3);
                System.out.println(id + "-------" + name + "--------" + balance);
            }
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn!=null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}
