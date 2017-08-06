package com.strongit.ah.training.action.menu;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.strongit.ah.training.action.base.BaseActionSupport;
import com.strongit.ah.training.entity.TUser;
import com.strongit.ah.training.service.UserService;
@Controller
@Scope("prototype")
@ParentPackage("default")
public class MenuAction extends BaseActionSupport<TUser>{
	
	/**
	 * serialVersionUID:TODO
	 * @since Ver 1.1
	 */
	private static final long serialVersionUID = 4122426611991598688L;
	
	
	@Autowired
	private UserService userService;
	
	public String list() throws Exception{
//		List<TUser> list = userService.list();
//		System.out.println("user-list:"+list.size());
		return "list";
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String layout() throws Exception {
		return "layout";
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}

}
