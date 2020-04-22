/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0
 * @Date:        2016年3月14日 上午11:48:37
 */
package com.maxlong.sqlBudiler;


import com.google.common.base.Strings;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;


public class QueryFilter implements  Serializable{
	private Example example = null;
	private Criteria criteria = null;

	private Integer page;// 要查找第几页
	private Integer pageSize;// 每页显示多少条

	private Integer draw;//dataTable的请求标记数

	private HttpServletRequest request;

	private boolean nosaas = true;

	private String saasId;


	/**
	 * <p>手动设置saasId   主要用在远程调用时</p>
	 * @return: String
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}


	private QueryFilter(){}

	/**
	 * 构造封装 Example 对象
	 *
	 * QueryFilter 默认Saas模式
	 * <p> TODO</p>
	 * @param:    @param entityClass
	 */
	public QueryFilter(Class<?> entityClass){
		//初始化example对象
		example = new Example(entityClass);
		//创建查询条件对象
		criteria = example.createCriteria();
	}

	/**
	 * dataOrderBy
	 * <p> TODO</p>
	 * @param:    @param request
	 * @param:    @param name
	 * @param:    @param value
	 * @return: void
	 * @throws:
	 */
	private void orderBy(HttpServletRequest request,String name ,String value){
		if(name.contains("order")==true){
			if(name.contains("dir")){
				String num = request.getParameter(name.replace("dir", "column"));
				String data = request.getParameter("columns["+num+"]"+"[data]");
				if(data!=null&&!"".equals(data)){
					example.setOrderByClause(data+" " + value);
				}
			}
		}
	}

	/**
	 * or
	 * <p> TODO</p>
	 * @param:    @param condition
	 * @param:    @param value
	 * @param:    @return
	 * @throws:
	 */
	public QueryFilter or(String condition,String value){
		Criteria newcriteria = example.createCriteria();
		example.or(addCriteria(condition, value,newcriteria));
		return this;
	}


	/**
	 * Remote调用时封装的QueryFilter
	 *
	 * 主要自动封装dataTable参数
	 * <p> TODO</p>
	 * @param:    @param entityClass
	 * @param:    @param request
	 */
	public QueryFilter(Class<?> entityClass,HashMap<String, String> requestMap){
		//初始化example对象
		this.example = new Example(entityClass);
		//创建查询条件对象
		this.criteria = example.createCriteria();

		if(requestMap!=null&&requestMap.size()>0){

			Set<Entry<String, String>> entrySet = requestMap.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();

			while (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				String name = next.getKey();
				String value = next.getValue();

				// StringUtils.isNotBlank(value)
				if (!Strings.isNullOrEmpty(value)) {

				//	addFilter(name, value);

					/**
					 * dataTable分页数据
					 */
					if (name.equalsIgnoreCase("start")) {
						Integer start=Integer.parseInt(value);
						String length = requestMap.get("length");
						this.setPage(start/Integer.parseInt(length)+1);
					}
					else if (name.equalsIgnoreCase("length")) {
						this.setPageSize(Integer.parseInt(value));
					}
					//dataTable 请求次数
					else if(name.equalsIgnoreCase("draw")){
						this.setDraw(Integer.parseInt(value));
					}
					//dataTable 自动like查询
					else if(name.contains("_like")||name.contains("_LIKE")){
						String[] strArr = name.split("_");
						try {
							criteria.andLike(strArr[0], "%"+value+"%");
						} catch (RuntimeException e) {
						}
					}
					//其它正常处理
					else if(name.contains("_")){
						try {
							addCriteria(name, value,criteria);
						} catch (RuntimeException e) {
						}
					}


					//dataTable 自动按列排序
				//	orderBy(request, name, value);


				}
			}

		}



	}


	/**
	 * 带request的QueryFilter
	 *
	 * 主要自动封装dataTable参数
	 * <p> TODO</p>
	 * @param:    @param entityClass
	 * @param:    @param request
	 */
	public QueryFilter(Class<?> entityClass,HttpServletRequest request){
		this.request = request;
		//初始化example对象
		this.example = new Example(entityClass);
		//创建查询条件对象
		this.criteria = example.createCriteria();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name).trim();

			// StringUtils.isNotBlank(value)
			if (!Strings.isNullOrEmpty(value)) {

			//	addFilter(name, value);

				/**
				 * dataTable分页数据
				 */
				if (name.equalsIgnoreCase("start")) {
					Integer start=Integer.parseInt(value);
					String length = request.getParameter("length");
					this.setPage(start/Integer.parseInt(length)+1);
				}
				else if (name.equalsIgnoreCase("length")) {
					this.setPageSize(Integer.parseInt(value));
				}
				//dataTable 请求次数
				else if(name.equalsIgnoreCase("draw")){
					this.setDraw(Integer.parseInt(value));
				}
				//dataTable 自动like查询
				else if(name.contains("_like")||name.contains("_LIKE")){

					String[] strArr = name.split("_");
					try {
						criteria.andLike(strArr[0], "%"+value+"%");
					} catch (RuntimeException e) {
					}
				}
				//其它正常处理
				else if(name.contains("_")){
					try {
						addCriteria(name, value,criteria);
					} catch (RuntimeException e) {
					}
				}


				//dataTable 自动按列排序
				orderBy(request, name, value);


			}
		}


	}

	private Criteria addCriteria(String condition, Criteria criteria){
		criteria.andCondition(condition);
		return criteria;
	}

	/**
	 * 添加条件
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param condition
	 * @param:    @param value
	 * @return: void
	 * @throws:
	 */
	private Criteria addCriteria(String condition, Object value,Criteria criteria){
		/*-------------------------------------notlike处理-------------------------------------------------*/
		if(condition.contains("_notlike")||condition.contains("_NOTLIKE")){
			String column = condition.replace("_notlike","").replace("_NOTLIKE","");
			criteria.andNotLike(column, value+"");
		}
		/*-------------------------------------like处理-------------------------------------------------*/
		else if(condition.contains("_like")||condition.contains("_LIKE")){
			String column = condition.replace("_like","").replace("_LIKE","");
			criteria.andLike(column, value+"");
		}
		/*-------------------------------------in处理-------------------------------------------------*/
		else if(condition.contains("_in")||condition.contains("_IN")){
			String[] split = condition.split("_");

			if(value instanceof String){
				String v = ((String) value).trim();
				String[] vArr = v.split(",");
				StringBuffer sb = new StringBuffer();
				for(int i = 0 ; i < vArr.length ; i++ ){
					sb.append("'"+vArr[i]+"'");
					if(i<vArr.length-1){
						sb.append(",");
					}
				}
				criteria.andCondition(" "+split[0]+" "+"in" +" ( " +sb.toString() + " )");
			}else{
				criteria.andIn(split[0], (List)value);
			}

		}/*-------------------------------------not in处理-------------------------------------------------*/
		else if(condition.contains("_notin")||condition.contains("_NOTIN")){
			String[] split = condition.split("_");

			if(value instanceof String){
				String v = ((String) value).trim();
				String[] vArr = v.split(",");
				StringBuffer sb = new StringBuffer();
				for(int i = 0 ; i < vArr.length ; i++ ){
					sb.append("'"+vArr[i]+"'");
					if(i<vArr.length-1){
						sb.append(",");
					}
				}
				criteria.andCondition(" "+split[0]+" "+"not in" +" ( " +sb.toString() + " )");
			}else{
				criteria.andNotIn(split[0], (List)value);
			}
		}/*-------------------------------------is null处理-------------------------------------------------*/
		else if(condition.contains("_isnull")||condition.contains("_ISNULL")){
			String[] split = condition.split("_");
			criteria.andIsNull(split[0]);
		}/*-------------------------------------is not null处理-------------------------------------------------*/
		else if(condition.contains("_isnotnull")||condition.contains("_ISNOTNULL")){
			String column = condition.replace("_isnotnull","").replace("_ISNOTNULL","");
			criteria.andIsNotNull(column);
		}
		/*-------------------------------------【_EQ】【_GT】【_LT】【_NEQ】处理-------------------------------------------------*/
		/**
		 *        【_EQ】              =
		 *        【_GT】              >
		 *        【_LT】             <
		 *        【_NEQ】          !=
		 *        【_GEQ】          >=
		 *        【_LEQ】          <=
		 */
		else if(condition.contains("_EQ")){
			String[] strArr = condition.split("_");
			criteria.andCondition(strArr[0]+"=", value);
		}else if(condition.contains("_GT")){
            String column = condition.replace("_GT","");
            criteria.andCondition(column + ">", value);
		}else if(condition.contains("_LT")){
			String[] strArr = condition.split("_");
			criteria.andCondition(strArr[0]+"<", value);
		}else if(condition.contains("_NEQ")){
			String[] strArr = condition.split("_");
			criteria.andCondition(strArr[0]+"!=", value);
		}else if(condition.contains("_GET")){
            String column = condition.replace("_GET","");
			criteria.andCondition(column + ">=", value);
		}else if(condition.contains("_LET")){
            String column = condition.replace("_LET","");
			criteria.andCondition(column + "<=", value);
		}
		/*-------------------------------------【=】【>】【<】【!=】【>=】【<=】处理-------------------------------------------------*/
		else{
			criteria.andCondition(condition, value);
		}


		return criteria;

	}




	/**
	 *
	 *	手写左边条件，右边用value值
	 *  --------------------------------------------------------
	 *  等于
	 * 	例：select * from app_user where id = 10
	 *  调用： addFilter("id=",10)
	 *
	 *  --------------------------------------------------------
	 *  大于
	 * 	例：select * from app_user where id > 10
	 *  调用： addFilter("id>",10)
	 *
	 *  --------------------------------------------------------
	 *  小于
	 * 	例：select * from app_user where id < 10
	 *  调用： addFilter("id<",10)
	 *
	 *  --------------------------------------------------------
	 *  不等于
	 * 	例：select * from app_user where id != 10
	 *  调用： addFilter("id!=",10)
	 *
	 *  --------------------------------------------------------
	 *  like
	 *  例：select * from app_user where username like '%小明%'
	 *  调用：addFilter("username_like",'%小明%');
	 *  调用：addFilter("username_like",'%小明');
	 *  调用：addFilter("username_like",'小明%');
	 *
	 *  --------------------------------------------------------
	 *  in  两种参数
	 *  	   一、   "1,2,3"  字符串
	 *  	   二    List<String>  list = new ArrayList<String>()      String  可  换Long,Integer等
	 *  	    list.add("1");
	 *  		list.add("2")
	 *  例：select * from app_user where id in ("1,2,3")
	 *  调用：addFilter("id_in","1,2,3");
	 *  调用:addFilter("in_in",list);
	 *
	 *  --------------------------------------------------------
	 *
	 * @param:    @return
	 * @return: QueryFilter
	 * @throws:
	 */
	public QueryFilter addFilter(String condition, Object value){
		addCriteria(condition,value,criteria);
		return this;
	}

	public QueryFilter addFilter(String condition){
		addCriteria(condition,criteria);
		return this;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getDraw() {
		return draw;
	}

	/**
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setDraw(Integer draw) {
		this.draw = draw;
	}


	/**
	 * orderby
	 * 		例：select * from app_user order by username asc
	 *
	 * 		调用：setOrderby("username asc");
	 *
	 * <p> TODO</p>
	 * @param:    @param orderby
	 * @return: void
	 * @throws:
	 */
	public void setOrderby(String orderby) {
		this.example.setOrderByClause(orderby);
	}


	/**
	 * <p> TODO</p>
	 * @return:     HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * <p> TODO</p>
	 * @return: HttpServletRequest
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Example
	 */
	public Example getExample() {
		return example;
	}

	/**
	 * 设置为nosaas
	 * <p> TODO</p>
	 * @param:    @return
	 * @return: QueryFilter
	 * @throws:
	 */
	public QueryFilter setNosaas() {
		this.nosaas = false;
		return this;
	}


}
