package com.maxlong.sqlBudiler;

import java.util.Collection;

/**
 * Created on 2020/4/22.
 *
 * @author MaXiaolong
 */
public interface BaseFilter {

    QueryBuilder like(String field, Object value);

    QueryBuilder rLike(String field, Object value);

    QueryBuilder lLike(String field, Object value);

    QueryBuilder notlike(String field, Object value);

    QueryBuilder in(String field, Collection value);

    QueryBuilder in(String field, Object[] value);

    QueryBuilder notIn(String field, Object[] value);

    QueryBuilder notIn(String field, Collection value);

    QueryBuilder isNotNull(String field);

    QueryBuilder isNull(String field);

    QueryBuilder eq(String field, Object value);

    QueryBuilder neq(String field, Object value);

    QueryBuilder gt(String field, Object value);

    QueryBuilder gte(String field, Object value);

    QueryBuilder lt(String field, Object value);

    QueryBuilder lte(String field, Object value);

    QueryBuilder condition(String condition);
}
