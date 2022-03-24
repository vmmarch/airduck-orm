package com.brouck.horizon;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brouck.horizon.model.NextVal;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lts
 * Create time 2022/3/23
 */
public class SqlSessionQueryTest {

    @Test
    public void sqlSessionQuery() throws Exception {
        var sqlSession = new AbstractSqlSession(new Configuration(createDataSource()));
        sqlSession.openSqlSession(false);

        var nextValue = sqlSession.queryForObject("select * from next_val where id = ?", NextVal.class, 3213);
        System.out.println(JSONObject.toJSONString(nextValue));

        sqlSession.closeSqlSession();
    }

    @Test
    public void sqlSessionUpdate() throws Exception {
        var sqlSession = new AbstractSqlSession(new Configuration(createDataSource()));
        sqlSession.openSqlSession(true);

        int ret = sqlSession.update("insert into nextval(id, username, nickname) values(?, ?, ?) ",
                3213, "brouck", "不肉克");
        System.out.println("Update result: " + ret);

        sqlSession.closeSqlSession();
    }

    @Test
    public void sqlSessionBatch() throws Exception {
        var sqlSession = new AbstractSqlSession(new Configuration(createDataSource()));
        sqlSession.openSqlSession(false);

        int[] ret = sqlSession.updateBatch("insert into next_val(id, username, nickname) values(?, ?, ?); ",
                List.of(new Object[]{1, "brouck-horizon-0", "地平线-1"},
                        new Object[]{2, "brouck-horizon-1", "地平线-2"},
                        new Object[]{"3v", "brouck-horizon-2", "地平线-3"},
                        new Object[]{"4v", "brouck-horizon-3", "地平线-4"}));

        System.out.println("Update result: " + JSON.toJSONString(ret));

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
