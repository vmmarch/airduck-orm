package com.brouck.session.metadata


import com.brouck.session.BrouckSession
import lombok.Data

/**
 * 针对MySQL的元数据查询接口
 *
 * @author brouck
 * Create time 2022/3/29
 */
class MySQLMetaDataQuery implements MetaDataQuery {

    private BrouckSession brouckSession

    private String _database

    MySQLMetaDataQuery(BrouckSession brouckSession) {
        this.brouckSession = brouckSession
    }

    @Data
    static class Database {
        String name
    }

    @Override
    String database() {
        if (this._database == null) {
            var database = brouckSession.objectQuery("select database() `name`", Database.class)
            this._database = database.name
        }

        return this._database
    }

    @Override
    List<String> tables() {
        return brouckSession.listQuery("""
            select table_name as `name` from information_schema.tables where table_schema='${database()}'
        """, String.class)
    }

    @Override
    List<ColumnMetaData> columns(String table) {
        return brouckSession.listQuery(
                """
                    SELECT
                      COLUMN_NAME,
                      if(IS_NULLABLE = 'YES', 'true', 'false') as IS_NULLABLE,
                      DATA_TYPE,
                      CHARACTER_MAXIMUM_LENGTH,
                      COLUMN_KEY 
                    FROM
                      information_schema.COLUMNS 
                    WHERE
                      table_schema = '${database()}' 
                      AND table_name = '${table}';
                """, ColumnMetaData.class)
    }

}
