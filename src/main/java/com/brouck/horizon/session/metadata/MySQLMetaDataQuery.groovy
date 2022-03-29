package com.brouck.horizon.session.metadata


import com.brouck.horizon.session.HorizonSession
import lombok.Data

/**
 * 针对MySQL的元数据查询接口
 *
 * @author lts
 * Create time 2022/3/29
 */
class MySQLMetaDataQuery implements MetaDataQuery {

    private HorizonSession horizonSession

    private String _database

    MySQLMetaDataQuery(HorizonSession horizonSession) {
        this.horizonSession = horizonSession
    }

    @Data
    static class Database {
        String name
    }

    @Override
    String database() {
        if (this._database == null) {
            var database = horizonSession.objectQuery("select database() `name`", Database.class)
            this._database = database.name
        }

        return this._database
    }

    @Override
    List<ColumnMetaData> columns(String table) {
        return horizonSession.listQuery(
                """
                        select * from information_schema.columns 
                        where table_schema='${database()}' and table_name='${table}';
                """, ColumnMetaData.class)
    }

}
