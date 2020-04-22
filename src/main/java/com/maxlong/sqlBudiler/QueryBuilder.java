package com.maxlong.sqlBudiler;

import tk.mybatis.mapper.entity.Example;

/**
 * Created on 2020/4/22.
 *
 * @author MaXiaolong
 */
public class QueryBuilder {

    protected Filter filter;

    private QueryBuilder(){}

    public static QueryBuilder start(Class<?> entityClass){
        QueryBuilder builder = new QueryBuilder();
        builder.filter = new Filter(builder, entityClass);
        return builder;
    }

    public Filter and(){
        return this.filter;
    }

    public Filter or(){
        Filter filter = new Filter(this);
        filter.superFilter = this.filter;
        filter.example.or(filter.criteria);
        this.filter = filter;
        return filter;
    }

    public QueryBuilder endOr(){
        this.filter = this.filter.superFilter;
        return this;
    }

    public Example builer(){
        return this.filter.example;
    }
}
