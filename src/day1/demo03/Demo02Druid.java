package day1.demo03;

import day1.demo03.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * 使用Druid连接池工具类测试
 *
 */
public class Demo02Druid {

    public static void main(String[] args) {

        /**
         * 完后操作给user表增加一条记录
         *
         */

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //获取连接
            conn = DruidUtils.getConnection();
            //定义sql
            String sql = "insert into user values(null,?,?)";
            //获取pstmt对象
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,"wangwu");
            pstmt.setDouble(2,1500);
            //执行sql
            var count = pstmt.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            DruidUtils.close(pstmt,conn);
        }

    }


}


