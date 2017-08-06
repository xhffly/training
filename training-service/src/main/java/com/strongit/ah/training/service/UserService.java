package com.strongit.ah.training.service;

import java.util.List;

import com.strongit.ah.training.common.Page;
import com.strongit.ah.training.entity.User;

public interface UserService extends BaseService {
	
	/**
	 * 无分页的用户查询
	 * @author			xhf
	 * @createDate		2015年4月2日上午11:15:39
	 * @arithMetic                                                                           
	 * @return
	 * @throws Exception
	 */
	public List<User> list() throws Exception;

	/**
	 * 
	 * @author			xhf
	 * @createDate		2015年4月2日上午11:15:34
	 * @arithMetic                                                                           
	 * @param ids
	 * @throws Exception
	 */
	public void deleteALL(String[] ids) throws Exception;

	/**
	 * 用户信息分页查询
	 * @author			xhf
	 * @createDate		2015年4月2日上午11:15:29
	 * @arithMetic                                                                           
	 * @param page
	 * @param userCondition 
	 * @return
	 * @throws Exception
	 */
	public List<User> find(Page<User> page, User userCondition) throws Exception;
	

}
