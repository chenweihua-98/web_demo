package day1.demo04;

import com.alibaba.druid.util.JdbcUtils;
import day1.demo03.utils.DruidUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * jdbcTemplate 入门
 */
public class DemoTemplate {

    public static void main(String[] args) {
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
        //调用方法
        String sql = "update user set password = 5000 where id = ?";
        int count = template.update(sql,3);
        System.out.println(count);

    }

}
;