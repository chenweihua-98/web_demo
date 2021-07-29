package day1.demo01;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoJdbc {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.导入驱动jar包
        //2.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取数据库连接对象
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "chenweihua");
        //4.定义sql语句
        String sql = "update account set balance = 500 where id = 1";
        //5.获取执行sql的对象Statement
        var stat = conn.createStatement();
        //6.执行sql
        int count = stat.executeUpdate(sql);
        //7.处理结果
        System.out.println(count);
        //8.释放资源
        stat.close();
        conn.close();
    }



}
