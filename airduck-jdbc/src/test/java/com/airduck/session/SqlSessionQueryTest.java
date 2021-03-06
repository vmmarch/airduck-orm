package com.airduck.session;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.airduck.session.model.NextVal;
import com.airduck.session.sqlsession.AbstractSqlSession;
import com.airduck.session.sqlsession.Configuration;
import com.airduck.session.sqlsession.SqlSession;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author airduck-vincent
 * Create time 2022/3/23
 */
public class SqlSessionQueryTest {

    @Test
    public void sqlSessionQuery() throws Exception {
        var sqlSession = new AbstractSqlSession(new Configuration(createDataSource()));
        sqlSession.openSqlSession(false);

        var nextValue = sqlSession.objectQuery("select * from next_val where id = ?", NextVal.class, 3213);
        System.out.println(JSONObject.toJSONString(nextValue));

        sqlSession.closeSqlSession();
    }

    @Test
    public void sqlSessionUpdate() throws Exception {
        var sqlSession = new AbstractSqlSession(new Configuration(createDataSource()));
        sqlSession.openSqlSession(true);

        int ret = sqlSession.executeUpdate("insert into nextval(id, username, nickname) values(?, ?, ?) ",
                3213, "airduck", "δΈθε");
        System.out.println("Update result: " + ret);

        sqlSession.closeSqlSession();
    }

    @Test
    public void sqlSessionDelete() throws Exception {
        var sqlSession = new AbstractSqlSession(new Configuration(createDataSource()));
        sqlSession.openSqlSession(true);

        int ret = sqlSession.executeUpdate("delete from next_val where id = ?", 1);
        System.out.println("Update result: " + ret);

        sqlSession.closeSqlSession();
    }

    @Test
    public void sqlSessionBatch() throws Exception {
        var sqlSession = new AbstractSqlSession(new Configuration(createDataSource()));
        sqlSession.openSqlSession(true);

        int[] ret = sqlSession.executeUpdateBatch("insert into next_val(id, username, nickname) values(?, ?, ?); ",
                List.of(new Object[]{1, "airduck-airduck-0", "ε°εΉ³ηΊΏ-1"},
                        new Object[]{2, "airduck-airduck-1", "ε°εΉ³ηΊΏ-2"},
                        new Object[]{3, "airduck-airduck-2", "ε°εΉ³ηΊΏ-3"},
                        new Object[]{4, "airduck-airduck-3", "ε°εΉ³ηΊΏ-4"}));

        System.out.println("Update result: " + JSON.toJSONString(ret));

        sqlSession.closeSqlSession();
    }

    public  static SqlSession getSqlSession() {
        try {
            return new AbstractSqlSession(new Configuration(createDataSource()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
