package com.brouck.horizon.session;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lts
 * Create time 2022/3/25
 */
public class GetSQLSession {


    public static SqlSession getSqlSession() {
        return new AbstractSqlSession(new Configuration(createDataSource()));
    }

    private static DataSource createDataSource() {
        Map<String, String> map = new HashMap<>();
        map.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, "com.mysql.cj.jdbc.Driver");
        map.put(DruidDataSourceFactory.PROP_URL, "jdbc:mysql://localhost:3306/nanolimit?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        map.put(DruidDataSourceFactory.PROP_USERNAME, "root");
        map.put(DruidDataSourceFactory.PROP_PASSWORD, "root");
        try {
            return DruidDataSourceFactory.createDataSource(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
