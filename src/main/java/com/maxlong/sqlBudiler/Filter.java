package com.maxlong.sqlBudiler;


import com.google.common.collect.Lists;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;

/**
 * Created on 2020/4/22.
 *
 * @author MaXiaolong
 */
public class Filter implements BaseFilter {

    protected QueryBuilder queryBuilder;

    protected Example example;

    protected Filter superFilter;

    protected Example.Criteria criteria;

    private Filter(){}

    protected Filter(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
        this.example = queryBuilder.filter.example;
        this.criteria = example.createCriteria();
    }

    protected Filter(QueryBuilder queryBuilder, Class<?> entityClass) {
        this.queryBuilder = queryBuilder;
        example = new Example(entityClass);
        criteria = example.createCriteria();
    }

    @Override
    public QueryBuilder like(String field, Object value) {
        this.criteria.andLike(field, "%" + value + "%");
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder rLike(String field, Object value) {
        this.criteria.andLike(field, value + "%");
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder lLike(String field, Object value) {
        this.criteria.andLike(field, "%" + value);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder notlike(String field, Object value) {
        this.criteria.andNotLike(field, value + "");
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder in(String field, Collection value) {
        this.criteria.andIn(field, value);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder in(String field, Object[] value) {
        this.criteria.andIn(field, Lists.newArrayList(value));
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder notIn(String field, Object[] value) {
        this.criteria.andNotIn(field, Lists.newArrayList(value));
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder notIn(String field, Collection value) {
        this.criteria.andNotIn(field, value);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder isNotNull(String field) {
        this.criteria.andIsNotNull(field);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder isNull(String field) {
        this.criteria.andIsNull(field);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder eq(String field, Object value) {
        this.criteria.andEqualTo(field, value);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder neq(String field, Object value) {
        this.criteria.andNotEqualTo(field, value);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder gt(String field, Object value) {
        this.criteria.andGreaterThan(field, value);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder gte(String field, Object value) {
        this.criteria.andGreaterThanOrEqualTo(field, value);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder lt(String field, Object value) {
        this.criteria.andLessThan(field, value);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder lte(String field, Object value) {
        this.criteria.andLessThanOrEqualTo(field, value);
        return this.queryBuilder;
    }

    @Override
    public QueryBuilder condition(String condition) {
        this.criteria.andCondition(condition);
        return this.queryBuilder;
    }
}
