package com.brouck.horizon;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lts
 * Create time 2022/3/23
 */
public class SqlSessionTest {

    @Test
    public void sqlSessionTest() throws Exception {
        AbstractSqlSession sqlSession = new AbstractSqlSession(new Configuration(createDataSource()));
        sqlSession.openSqlSession();

        Integer nextValue = sqlSession.queryForObject("select * from next_val", Integer.class);
        System.out.println(nextValue);

        sqlSession.closeSqlSession();
    }

    public static DataSource createDataSource() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, "com.mysql.cj.jdbc.Driver");
        map.put(DruidDataSourceFactory.PROP_URL, "jdbc:mysql://localhost:3306/nanolimit?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        map.put(DruidDataSourceFactory.PROP_USERNAME, "root");
        map.put(DruidDataSourceFactory.PROP_PASSWORD, "root");
        return DruidDataSourceFactory.createDataSource(map);
    }

}
