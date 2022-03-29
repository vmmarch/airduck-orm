package com.brouck.horizon.session;

import com.brouck.horizon.annotation.Table;
import com.brouck.horizon.exception.IllegalTableClassException;
import com.brouck.horizon.exception.SearchNotFoundException;
import com.brouck.horizon.session.metadata.TableMetaData;

import java.util.HashMap;
import java.util.Map;

/**
 * horizon sql session封装类，这个类一定是被管理在容器中的。必须要持久化在
 * 内存中。
 *
 * @author lts
 * Create time 2022/3/25
 */
public class HorizonSession {

    /**
     * SQLSession对象
     */
    private final SqlSession _sqlSession;

    /**
     * 表信息元数据
     */
    private final Map<String, TableMetaData> tableMetaDataMap = new HashMap<>();

    /**
     * 创建HorizonSqlSession
     */
    public HorizonSession(SqlSession sqlSession) {
        this._sqlSession = sqlSession;
    }

    /**
     * 打开事务
     */
    public void openTransaction() {
        _sqlSession.openSqlSession(true);
    }

    /**
     * 关闭事务
     */
    public void closeTransaction() {
        _sqlSession.closeSqlSession();
    }

    /**
     * 手动添加表元数据
     * @param entityClass 实体类
     */
    public void addTableMetaData(Class<?> entityClass) {
        TableMetaData tableMetaData = new TableMetaData(entityClass);
        tableMetaDataMap.put(tableMetaData.getTableName(), tableMetaData);
    }

    /**
     * 创建查询对象
     */
    public <T> Query<T> createQuery(Class<T> _class) {
        Table table = _class.getAnnotation(Table.class);
        if (table == null)
            throw new IllegalTableClassException("创建查询失败，实体类必须存在@Table注解。");

        // 查询表元数据
        TableMetaData tableMetaData = tableMetaDataMap.get(table.name());
        if (tableMetaData == null)
            throw new SearchNotFoundException("未找到当前表的元数据信息，当前表名：{}", table.name());

        return new Query<>(_sqlSession, tableMetaData);
    }

}
