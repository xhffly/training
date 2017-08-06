package com.strongit.ah.training.action.sys.user;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.strongit.ah.training.action.base.BaseActionSupport;
import com.strongit.ah.training.common.Page;
import com.strongit.ah.training.common.TrainingJsonConfig;
import com.strongit.ah.training.entity.User;
import com.strongit.ah.training.service.UserService;
@Controller
@Scope("prototype")
@ParentPackage("default")
public class UserAction extends BaseActionSupport<User>{
	
	private static final long serialVersionUID = 4122426611991598688L;
	 
	
	@Autowired
	private UserService userService;
	
	/** 用户信息 */
	private User user;
	
	/** 批量操作ids */
	private String dataIds;
	
	
	@Override
	public String list() throws Exception {
		return "list";
	}
	
	public String getList() throws Exception {
		
		Page<User> page = new Page<>(this.getRows());
		page.setPageNo(this.getPage());
		
		List<User> list = userService.find(page,user);
		
		int total = page.getTotalCount();
//		JSONObject json = new JSONObject();
//		json.put("total", total);
//		json.put("rows", JSONArray.fromObject(list).toString());
		JSONObject json = new JSONObject();
		json.put("total", total);
		JSONArray data = JSONArray.fromObject(list, new TrainingJsonConfig("userRoles"));
		json.put("rows", data.toString());
		rendText(json.toString());
		return null;
	}
	
	@Override
	public String save() throws Exception {
		
		try {
			
			userService.save(user);
			rendText("success");
			
		} catch (Exception e) {
			e.printStackTrace();
			rendText("faild");
		}
		return null;
	}

	@Override
	public String delete() throws Exception {
		
		try {
			if (!StringUtils.isBlank(dataIds)) {
				userService.deleteALL(dataIds.split(","));
				rendText("success");
			}
		} catch (Exception e) {
			
			rendText("faild");
		}
		return null;
	}

	@Override
	public String input() throws Exception {
		return "input";
	}

	@Override
	protected void prepareModel() throws Exception {
		 try {
			if(user != null && StringUtils.isNotBlank(user.getId()) ){
				 user = userService.get(User.class, user.getId());
				 user.setUpdateTime(new Date());
				 user.setUpdateUser("xhf");
			 } else {
				 user = new User();
				 user.setCreateTime(new Date());
				 user.setCreateUser("xhf");
				 user.setPassword("123456");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDataIds() {
		return dataIds;
	}

	public void setDataIds(String dataIds) {
		this.dataIds = dataIds;
	}


}
