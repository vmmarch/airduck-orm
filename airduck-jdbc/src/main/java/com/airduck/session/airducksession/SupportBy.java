package com.airduck.session.airducksession;

import com.airduck.exception.IllegalException;
import com.airduck.exception.NotSupportException;

import java.util.Date;

/**
 * 支持的数据库
 *
 * @author airduck-vincent
 * Create time 2022/4/11
 */
@SuppressWarnings("ALL")
public enum SupportBy {

    MYSQL,
    ORACLE,
    SQL_SERVER,
    ;

    public String typeMap(Class<?> type) {
        switch (this) {
            case MYSQL: return typeMapByForMySQL(type);
        }

        throw new NotSupportException("暂不支持的数据库：{}", this.name());
    }

    /**
     * MySQL字段和Java的映射
     * @param type
     * @return
     */
    private String typeMapByForMySQL(Class<?> type) {
        if (type == String.class)
            return "varchar";
        if (type == Integer.class)
            return "int";
        if (type == Long.class)
            return "bigint";
        if (type == Double.class)
            return "double";
        if (type == Float.class)
            return "float";
        if (type == Date.class)
            return "datetime";

        throw new IllegalException("无效或不支持的字段类型，{}", type.getName());
    }

}
