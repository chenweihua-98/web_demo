package day1.demo02.jdbcutils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 * 简化书写  抽取一个方法获取连接对象 抽取一个方法释放资源
 *
 */
public class DemoJdbcUtils {

    /**
     * 不想传递参数，又保证工具类的通用性，使用配置文件 jdbc.properties
     * @return
     * 文件的读取，只需要读取一次即可，所以可以使用静态代码块实现
     */
    private static String url;//静态代码块要使用静态变量
    private static String user;
    private static String password;
    private static String driver;


    static {

        try {
            //读取资源文件
            //创建properties集合类
            Properties pro = new Properties();

            //获取src路径下的文件的方式 ClassLoader类加载器
            var classLoader = DemoJdbcUtils.class.getClassLoader();
            var resource = classLoader.getResource("jdbc.properties");
            String path = resource.getPath();


            //加载文件
            pro.load(new FileReader(path));

            //获取数据，赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");

            //注册驱动

            Class.forName(driver);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    //适用于查询命令
    public static  void close(ResultSet rs, Statement stmt, Connection conn){

        if (rs != null) {
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

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }

    public static  void close( Statement stmt, Connection conn){


        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }


}
