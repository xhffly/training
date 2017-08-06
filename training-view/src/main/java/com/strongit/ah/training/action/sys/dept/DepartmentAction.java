package com.strongit.ah.training.action.sys.dept;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.strongit.ah.training.entity.Department;
import com.strongit.ah.training.service.DepartmentService;

@Controller
@Scope("prototype")
@ParentPackage("default")
public class DepartmentAction extends BaseActionSupport<Department>{
	
	private static final long serialVersionUID = 4122426611991598688L;
	 
	
	@Autowired
	private DepartmentService departmentService;
	
	/** 用户信息 */
	private Department department;
	
	/** 批量操作ids */
	private String dataIds;
	
	
	private List<Department> departmentList;
	
	@Override
	public String list() throws Exception {
		departmentList = departmentService.list();
		return "list";
	}
	
	public String getList() throws Exception {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Page<Department> page = new Page<>(this.getRows());
		page.setPageNo(this.getPage());
		
		List<Department> list = departmentService.find(page,department);
		
		int total = page.getTotalCount();
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", JSONArray.fromObject(list).toString());
		rendText(json.toString());
		return null;
	}
	
	
	

	@Override
	public String save() throws Exception {
		
		try {
			if(StringUtils.isBlank(department.getParentId())){
				department.setParentId("0");
			}
			departmentService.save(department);
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
				departmentService.deleteALL(dataIds.split(","));
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
	
	/**
	 * 获得easyui的部门树，json结构数据
	 * @author			xhf
	 * @createDate		2015年4月27日上午10:34:18
	 * @arithMetic                                                                           
	 * @throws Exception
	 */
	public void deptTree() throws Exception{
		JSONArray array = new JSONArray();
		//组织树状结构
		List<Department> deptList = departmentService.list();
		for (Department department : deptList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", department.getId());
			jsonObject.put("text", department.getName());
//			List<Department> deptList = department.find(sql.toString());
//			if(deptList.isEmpty()){
//				jsonObject.put("state", "open");
//			}else{
//				jsonObject.put("state", "closed");
//			}
			array.add(jsonObject);
		}
		
		rendText(array.toString());
	}
	

	@Override
	protected void prepareModel() throws Exception {
		 try {
			if(department != null && StringUtils.isNotBlank(department.getId()) ){
				 department = departmentService.get(Department.class, department.getId());
				 department.setUpdateTime(new Date());
				 department.setUpdateUser("xhf");
			 } else {
				 department = new Department();
				 department.setCreateTime(new Date());
				 department.setCreateUser("xhf");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String getDataIds() {
		return dataIds;
	}

	public void setDataIds(String dataIds) {
		this.dataIds = dataIds;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}


}
