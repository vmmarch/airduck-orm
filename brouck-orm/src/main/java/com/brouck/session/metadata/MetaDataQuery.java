package com.brouck.session.metadata;

import java.util.List;

/**
 * @author brouck
 * Create time 2022/3/29
 */
public interface MetaDataQuery {

    /**
     * 查询当前数据库名
     */
    String database();

    /**
     * 当前库的所有表名
     */
    List<String> tables();

    /**
     * 查询表的所有字段数据
     */
    List<ColumnMetaData> columns(String table);

}
