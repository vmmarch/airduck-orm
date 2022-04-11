package com.airduck.annotation;

/**
 * @author airduck-vincent
 * Create time 2022/4/11
 */
public enum IdGenerator {

    /**
     * 自增
     */
    INCREMENT,

    /**
     * 使用UUID算法生成
     */
    UUID,

    /**
     * 根据序列生成
     */
    SEQUENCE

}
