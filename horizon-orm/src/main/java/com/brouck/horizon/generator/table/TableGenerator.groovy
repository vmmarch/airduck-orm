package com.brouck.horizon.generator.table

import com.brouck.horizon.session.HorizonSession
import com.brouck.horizon.session.metadata.TableMetaData

/**
 * 表生成器
 *
 * @author lts
 * Create time 2022/3/29
 */
class TableGenerator {

    /**
     * 生成一张表
     *
     * @param horizonSession sqlSession
     * @param metadata 表元数据
     */
    def generate(HorizonSession sqlSession, TableMetaData metadata) {
        sqlSession.openTransaction()

        sqlSession.closeTransaction()
    }

}
