package com.brouck.horizon.session;

import com.brouck.horizon.commons.Lists;
import com.brouck.horizon.commons.StringUtils;
import com.brouck.horizon.generator.id.IdGeneratorForIncrement;
import com.brouck.horizon.session.metadata.TableMetaData;
import com.brouck.horizon.tools.HorizonUtils;
import lombok.Data;

import java.util.Map;

/**
 * 脚本构建
 *
 * @author brouck
 * Create time 2022/4/1
 */
@Data
class ScriptBuilder {

    /** 表名 */
    private String tableName;
    /** 字段列表 */
    private String columns;
    /** 字段值列表 */
    private Object[] values;
    /** 更新条件 */
    private String where;

    /**
     * 获取插入数据的SQL
     */
    public String getInsertSQL() {
        return "insert into `" + tableName + "`(" + columns + ") " +
                HorizonUtils.getParamValue(values.length);
    }

    /**
     * 构建一个sql脚本所需的基本数据
     */
    public static ScriptBuilder build(Object updaeObject, Map<String, TableMetaData> tableMetaDataMap) {
        String tableName = HorizonUtils.getTableName(updaeObject);
        TableMetaData tableMetaData = tableMetaDataMap.get(tableName);

        // 获取所有字段以及字段值
        var tableColumns = new StringBuilder();
        var args = Lists.newLinkedList();
        tableMetaData.getColumns().values().forEach(column -> {
            // 如果是主键并且id是自增长那么就不需要插入
            if (column.isPrimaryKey() && column.getGeneratedValue() == IdGeneratorForIncrement.class) {
                return;
            }

            args.add(column.getValue(updaeObject));
            tableColumns.append("`").append(column.getName()).append("`").append(",");
        });
        StringUtils.removeLastCharacter(tableColumns);

        // 设置对应的数据
        var updateDataBaseInfo = new ScriptBuilder();
        updateDataBaseInfo.setTableName(tableMetaData.getName());
        updateDataBaseInfo.setColumns(tableColumns.toString());
        updateDataBaseInfo.setValues(args.toArray());

        return updateDataBaseInfo;
    }

}
