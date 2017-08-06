package com.strongit.ah.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strongit.ah.training.common.Page;
import com.strongit.ah.training.entity.Department;
import com.strongit.ah.training.service.BaseServiceImpl;
import com.strongit.ah.training.service.DepartmentService;

/**
 * 部门管理service	                                                                                                 
 * @equals			
 * @author			xhf
 * @createDate		2015年4月24日 下午3:29:00
 * @version			v1.0
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl implements
		DepartmentService {

	@Transactional(readOnly=true)
	@Override
	public List<Department> list() throws Exception {
		List<Department> list = dao.findAll(Department.class);
		return list;
	}

	@Transactional
	@Override
	public void deleteALL(String[] ids) throws Exception {
		String hql = "delete Department where id in(:ids) ";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ids", ids);
		dao.batchExecute(hql, values);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Department> find(Page<Department> page,Department deptCondition) throws Exception {
		StringBuffer hql = new StringBuffer("from Department where 1=1 ");
		Map<String, Object> values = new HashMap<String, Object>();
		if(deptCondition != null ){
			if(StringUtils.isNotBlank(deptCondition.getName())){
				hql.append(" and name like :name ");
				values.put("name", "%"+deptCondition.getName().trim()+"%");
			}else if(StringUtils.isNotBlank(deptCondition.getCode())){
				hql.append(" and code like :code ");
				values.put("code", "%"+deptCondition.getCode().trim()+"%");
			}else if(StringUtils.isNotBlank(deptCondition.getParentId())){
				hql.append(" and parentId = :parentId ");
				values.put("parentId", deptCondition.getParentId().trim());
			}
		}
		hql.append(" order by createTime desc ");
		dao.find(page, hql.toString(),values);
		return page.getResult();
	}

}
