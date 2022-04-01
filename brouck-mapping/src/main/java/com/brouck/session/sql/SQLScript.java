package com.brouck.session.sql;

import com.brouck.commons.Lists;
import com.brouck.session.metadata.ColumnMetaData;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * sql生成的对象
 *
 * @author brouck
 * Create time 2022/4/1
 */
@Data
public class SQLScript {

    /**
     * sql语句
     */
    private String sql;

    /**
     * 参数
     */
    private Object[] params;

    /**
     * 批处理参数
     */
    private List<Object[]> batchParams;

    private LinkedList<ColumnMetaData> columns = Lists.newLinkedList();

    public void addColumn(ColumnMetaData column) {
        columns.add(column);
    }

}
