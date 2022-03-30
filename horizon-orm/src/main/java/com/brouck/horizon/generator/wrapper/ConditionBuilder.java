package com.brouck.horizon.generator.wrapper;

import java.util.*;

/**
 * @author lts
 * Create time 2022/3/25
 */
@SuppressWarnings("unchecked")
public class ConditionBuilder<Children extends ConditionBuilder<Children>> {

    /**
     * where sql语句
     */
    private final StringBuilder _where = new StringBuilder("where 1 = 1");

    /**
     * 构建好的SQL参数列表
     */
    private final List<Object> _parameters = new LinkedList<>();

    /**
     * 条件映射
     */
    private final Map<String, Map<String, Object>> _condition = new LinkedHashMap<>();

    {
        _condition.put("eq", new HashMap<>());
        _condition.put("ne", new HashMap<>());
        _condition.put("ge", new HashMap<>());
        _condition.put("gt", new HashMap<>());
        _condition.put("le", new HashMap<>());
        _condition.put("lt", new HashMap<>());
        _condition.put("like", new HashMap<>());
        _condition.put("leftLike", new HashMap<>());
        _condition.put("rightLike", new HashMap<>());
        _condition.put("in", new HashMap<>());
    }

    /**
     * 获取所有参数
     */
    protected List<Object> getParameters() {
        return _parameters;
    }

    /**
     * 等于
     *
     * @param name  字段名
     * @param value 查询值
     */
    public Children eq(String name, Object value) {
        return putCondition("eq", name, value);
    }

    /**
     * 不等于
     *
     * @param name  字段名
     * @param value 查询值
     */
    public Children ne(String name, Object value) {
        return putCondition("ne", name, value);
    }

    /**
     * 大于
     *
     * @param name  字段名
     * @param value 查询值
     */
    public Children gt(String name, Object value) {
        return putCondition("gt", name, value);
    }

    /**
     * 大于等于
     *
     * @param name  字段名
     * @param value 查询值
     */
    public Children ge(String name, Object value) {
        return putCondition("ge", name, value);
    }

    /**
     * 小于
     *
     * @param name  字段名
     * @param value 查询值
     */
    public Children lt(String name, Object value) {
        return putCondition("lt", name, value);
    }

    /**
     * 小于等于
     *
     * @param name  字段名
     * @param value 查询值
     */
    public Children le(String name, Object value) {
        return putCondition("le", name, value);
    }

    /**
     * 模糊查询（全模糊）
     *
     * @param name  字段名
     * @param value 查询值
     */
    public Children like(String name, Object value) {
        return putCondition("like", name, value);
    }

    /**
     * 模糊查询（左模糊）
     *
     * @param name  字段名
     * @param value 查询值
     */
    public Children leftLike(String name, Object value) {
        return putCondition("leftLike", name, value);
    }

    /**
     * 模糊查询（右模糊）
     *
     * @param name  字段名
     * @param value 查询值
     */
    public Children rightLike(String name, Object value) {
        return putCondition("rightLike", name, value);
    }

    public Children in(String name, Object... args) {
        return putCondition("in", name, args);
    }

    /**
     * 存放查询或者更新条件内容
     *
     * @param prefix 操作前缀
     * @param name   字段名
     * @param value  查询值
     */
    private Children putCondition(String prefix, String name, Object value) {
        _condition.get(prefix).put(name, value);
        return (Children) this;
    }

    /**
     * 构建Where sql语句
     */
    protected void buildWhere() {
        _condition.forEach((condition, attribute) -> {
            if (condition.equals("eq")) {
                attribute.forEach((key, val) -> {
                    _where.append(" and ").append(key).append(" = ? ");
                    _parameters.add(val);
                });
            }

            if (condition.equals("ne")) {
                attribute.forEach((key, val) -> {
                    _where.append(" and ").append(key).append(" != ? ");
                    _parameters.add(val);
                });
            }

            if (condition.equals("ge")) {
                attribute.forEach((key, val) -> {
                    _where.append(" and ").append(key).append(" >= ? ");
                    _parameters.add(val);
                });
            }

            if (condition.equals("gt")) {
                attribute.forEach((key, val) -> {
                    _where.append(" and ").append(key).append(" > ? ");
                    _parameters.add(val);
                });
            }

            if (condition.equals("le")) {
                attribute.forEach((key, val) -> {
                    _where.append(" and ").append(key).append(" <= ? ");
                    _parameters.add(val);
                });
            }

            if (condition.equals("lt")) {
                attribute.forEach((key, val) -> {
                    _where.append(" and ").append(key).append(" < ? ");
                    _parameters.add(val);
                });
            }

            if (condition.equals("like")) {
                attribute.forEach((key, val) -> {
                    _where.append(" and ").append(key).append(" like concat('%', ?").append(",'%') ");
                    _parameters.add(val);
                });
            }

            if (condition.equals("leftLike")) {
                attribute.forEach((key, val) -> {
                    _where.append(" and ").append(key).append(" like concat('%', ?").append(",'') ");
                    _parameters.add(val);
                });
            }

            if (condition.equals("rightLike")) {
                attribute.forEach((key, val) -> {
                    _where.append(" and ").append(key).append(" like concat('', ?").append(",'%') ");
                    _parameters.add(val);
                });
            }

            if (condition.equals("in")) {
                attribute.forEach((key, val) -> {
                    _where.append(" and ").append(key).append(" in ( ");

                    Object[] args = (Object[]) val;
                    for (Object arg : args) {
                        _where.append("?,");
                        _parameters.add(arg);
                    }

                    _where.deleteCharAt(_where.length() - 1);
                    _where.append(")");
                });
            }

        });
    }

    /**
     * @return 构建好的Where SQL
     */
    protected String getWhereSQL() {
        buildWhere();
        return _where.toString();
    }

}

