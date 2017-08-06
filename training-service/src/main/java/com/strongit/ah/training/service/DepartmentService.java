package com.strongit.ah.training.service;

import java.util.List;

import com.strongit.ah.training.common.Page;
import com.strongit.ah.training.entity.Department;

/**
 * 部门管理service                                                                                            
 * @equals			
 * @author			xhf
 * @createDate		2015年4月30日 上午11:57:37
 * @version			v1.0
 */
public interface DepartmentService extends BaseService {

	/**
	 * 不带分页的部门查询
	 * @author			xhf
	 * @createDate		2015年4月22日上午10:55:23
	 * @arithMetic                                                                           
	 * @return
	 * @throws Exception
	 */
	public List<Department> list() throws Exception;

	/**
	 * 批量删除部门
	 * @author			xhf
	 * @createDate		2015年4月22日上午10:55:40
	 * @arithMetic                                                                           
	 * @param ids
	 * @throws Exception
	 */
	public void deleteALL(String[] ids) throws Exception;

	
	/**
	 * 分页查询部门信息
	 * @author			xhf
	 * @createDate		2015年4月22日上午10:55:52
	 * @arithMetic                                                                           
	 * @param page
	 * @param deptCondition 查询条件
	 * @return 
	 * @throws Exception
	 */
	public List<Department> find(Page<Department> page,Department deptCondition) throws Exception;
	

}
