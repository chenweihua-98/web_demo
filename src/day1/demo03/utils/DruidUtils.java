package day1.demo03.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import day1.demo02.jdbcutils.DemoJdbcUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid连接池的工具类
 */
public class DruidUtils {

    //1.定义成员变量
    private static DataSource ds;

    static {

        try {
            //1.加载配置文件
            Properties pro = new Properties();
            pro.load(DemoJdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //获取连接
    public  static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //释放资源
    public  static  void  close(Statement stmt,Connection conn){
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public  static  void  close(ResultSet rs, Statement stmt, Connection conn){
        if (rs != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static  DataSource getDataSource(){
        return ds;
    }




}
