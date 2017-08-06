package com.strongit.ah.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strongit.ah.training.common.Page;
import com.strongit.ah.training.entity.User;
import com.strongit.ah.training.service.BaseServiceImpl;
import com.strongit.ah.training.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService  {

	@Override
	@Transactional(readOnly=true)
	public List<User> list() throws Exception {
		List<User> list = dao.findAll(User.class);
		return list;
	}

	@Override
	@Transactional
	public void deleteALL(String[] ids) throws Exception {
		String hql = "delete User where id in(:ids) ";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ids", ids);
		dao.batchExecute(hql, values);
	}

	@Override
	public List<User> find(Page<User> page, User userCondition) throws Exception {
		StringBuffer hql = new StringBuffer("from User where 1=1 ");
		Map<String, Object> values = new HashMap<String, Object>();
		if(userCondition != null ){
			if(StringUtils.isNotBlank(userCondition.getName())){
				hql.append(" and name like :name ");
				values.put("name", "%"+userCondition.getName().trim()+"%");
			}else if(StringUtils.isNotBlank(userCondition.getLoginName())){
				hql.append(" and loginName like :loginName ");
				values.put("loginName", "%"+userCondition.getLoginName().trim()+"%");
			}
		}
		hql.append(" order by createTime desc ");
		dao.find(page, hql.toString(),values);
		return page.getResult();
	}


	

}
