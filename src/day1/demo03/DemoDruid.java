package day1.demo03;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Druid 演示
 */
public class DemoDruid {

    public static void main(String[] args) throws Exception {

        //加入配置文件，并加载配置文件
        Properties pro = new Properties();
        var is = DemoDruid.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);

        //获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);

        //获取连接
        var conn = ds.getConnection();
        System.out.println(conn);

    }


}
