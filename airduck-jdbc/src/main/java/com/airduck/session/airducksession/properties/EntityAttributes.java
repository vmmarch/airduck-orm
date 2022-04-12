package com.airduck.session.airducksession.properties;

import com.airduck.annotation.Comment;
import com.airduck.annotation.OverridePrimaryKey;
import com.airduck.annotation.Table;
import com.airduck.commons.Lists;
import com.airduck.commons.ReflectionUtils;
import com.airduck.exception.IllegalException;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author airduck-vincent
 * Create time 2022/4/11
 */
@Data
public class EntityAttributes {

    /**
     * 表名
     */
    private String name;

    /**
     * 表备注
     */
    private String comment;

    /**
     * 字段列表
     */
    private List<ColumnAttributes> columnAttributes;

    /**
     * 解析实体属性
     */
    public EntityAttributes(Class<?> _class) {
        columnAttributes = Lists.newLinkedList();
        analyze(_class);
    }

    /**
     * 解析实体属性
     */
    private void analyze(Class<?> _class) {
        if (!_class.isAnnotationPresent(Table.class))
            throw new IllegalException("实体类不合法，未添加@Table注解。{}", _class.getName());

        // 获取表名
        Table table = _class.getAnnotation(Table.class);
        this.name = table.name();

        // 获取表备注
        if (_class.isAnnotationPresent(Comment.class)) {
            this.comment = _class.getAnnotation(Comment.class).value();
        }

        boolean isOverride = _class.isAnnotationPresent(OverridePrimaryKey.class);
        List<Field> fields = ReflectionUtils.searchFields(_class);
        for (Field field : fields) {
            ColumnAttributes columnAttributes = ColumnAttributes.create(this.name, isOverride, field);
            if (columnAttributes != null)
                this.columnAttributes.add(columnAttributes);
        }

    }

}
