package com.strongit.ah.training.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
/**
 * 部门信息实体类	                                                                                                 
 * @equals			
 * @author			xhf
 * @createDate		2015年4月30日 上午11:49:30
 * @version			v1.0
 */
@Entity
@Table(name = "department", catalog = "chnmedicine", uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
public class Department implements java.io.Serializable {

	/** @since Ver 1.1 */
	private static final long serialVersionUID = 7900919991982673692L;
	
	/** 主键ID  */ 
	private String id;
	
	/** 父级部门Id */
	private String parentId;
	
	/** 部门编码 */
	private String code;
	
	/** 部门名称 */
	private String name;
	
	/** 状态 */
	private String status;
	
	/** 备注 */
	private String remark;
	
	/** 用于演示日历控件使用，无实际意义 */
	private Date establishTime;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 创建人 */
	private String createUser;
	
	/** 修改时间 */
	private Date updateTime;
	
	/** 修改人 */
	private String updateUser;
	
	/** 部门员工 */
	private Set<User> users = new HashSet<User>(0);

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** minimal constructor */
	public Department(String id, String code, String name, String status,
			Date createTime, String createUser) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.status = status;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	/** full constructor */
	public Department(String id, String parentId, String code,
			String name, String status, String remark,
			Date createTime, String createUser, Date updateTime,
			String updateUser,  Set<User> users) {
		this.id = id;
		this.parentId = parentId;
		this.code = code;
		this.name = name;
		this.status = status;
		this.remark = remark;
		this.createTime = createTime;
		this.createUser = createUser;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.users = users;
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "P_ID")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "CODE", unique = true, nullable = false, length = 30)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CREATE_TIME", nullable = false, length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_USER", nullable = false, length = 30)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "UPDATE_TIME", length = 10)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_USER", length = 30)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Date getEstablishTime() {
		return establishTime;
	}

	public void setEstablishTime(Date establishTime) {
		this.establishTime = establishTime;
	}




}