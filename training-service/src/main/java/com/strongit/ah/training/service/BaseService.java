package com.strongit.ah.training.service;

import java.io.Serializable;

/**
 * 基础service接口                                                                                               
 * @equals			
 * @author			xhf
 * @createDate		2015年3月31日 上午11:12:19
 * @version			v1.0
 */
public interface BaseService {
	
	/**
	 * 根据主键ID获得数据
	 * @author			xhf
	 * @createDate		2015年3月31日上午11:12:50
	 * @arithMetic                                                                           
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public <T> T get(Class<T> clazz, Serializable id) throws Exception;
	
	/**
	 * 保存
	 * @author			xhf
	 * @createDate		2015年3月31日上午11:14:10
	 * @arithMetic                                                                           
	 * @param entity
	 * @throws Exception
	 */
	public <T> void save(T entity) throws Exception;
	
	
	/**
	 * 保存
	 * @author			xhf
	 * @createDate		2015年3月31日上午11:14:10
	 * @arithMetic                                                                           
	 * @param entity
	 * @throws Exception
	 */
	public <T> void saveOrUpdate(T entity) throws Exception;
	/**
	 * 删除
	 * @author			xhf
	 * @createDate		2015年3月31日上午11:19:13
	 * @arithMetic                                                                           
	 * @param entity
	 * @throws Exception
	 */
	public <T> void delete(T entity) throws Exception;
	
}
