package day1.demo02;

import day1.demo02.jdbcutils.DemoJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务操作
 */
public class DemoJdbc {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            //获取连接
            conn = DemoJdbcUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);

            //定义sql
            String sql1 = "update user set password = password - ? where id = ?";
            String sql2 = "update user set password = password + ? where id = ?";
            //获取执行sql对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            //设置参数
            pstmt1.setDouble(1, 500);
            pstmt1.setInt(2, 3);

            pstmt2.setDouble(1, 500);
            pstmt2.setInt(2, 4);

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

            //提交事务
            conn.commit();

        } catch (Exception e) {
            //事务回滚
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DemoJdbcUtils.close(pstmt1, conn);
            DemoJdbcUtils.close(pstmt1, null);
        }


    }


}
