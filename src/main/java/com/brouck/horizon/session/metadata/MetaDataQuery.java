package com.brouck.horizon.session.metadata;

import com.brouck.horizon.generator.table.InColumn;

import java.util.List;

/**
 * @author lts
 * Create time 2022/3/29
 */
public interface MetaDataQuery {

    /**
     * 查询当前数据库名
     */
    String database();

    /**
     * 查询表的所有字段数据
     */
    List<InColumn> columns(String table);

}
