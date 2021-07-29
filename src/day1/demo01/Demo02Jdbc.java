package day1.demo01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**

account表 添加一条记录 insert语句

 */
public class Demo02Jdbc {


    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;

        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.定义sql
            String sql = "insert into account values(null,'王五',3000)";
            //获取collection对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "chenweihua");
            //4.获取执行sql的对象 statement
            stmt = conn.createStatement();
            //5.执行sql
            int count = stmt.executeUpdate(sql);
            //处理结果
            System.out.println(count);
            if (count > 0){
                System.out.println("添加成功");
            }else {
                System.out.println("添加失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
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
