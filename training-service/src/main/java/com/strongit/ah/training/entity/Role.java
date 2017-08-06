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
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role", catalog = "chnmedicine", uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
public class Role implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID:
	 * @since Ver 1.1
	 */
	private static final long serialVersionUID = -354505848925647615L;
	private String id;
	private String name;
	private String code;
	private String remark;
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	private String createUser;
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date updateTime;
	private String updateUser;
//	@JsonIgnore
	private Set<RoleSource> roleSources = new HashSet<RoleSource>(0);
//	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String id, Date createTime, String createUser) {
		this.id = id;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	/** full constructor */
	public Role(String id, String name, String code, String remark,
			Date createTime, String createUser, Date updateTime,
			String updateUser, Set<RoleSource> roleSources,
			Set<UserRole> userRoles) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.remark = remark;
		this.createTime = createTime;
		this.createUser = createUser;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.roleSources = roleSources;
		this.userRoles = userRoles;
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

	@Column(name = "NAME", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", unique = true, length = 30)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<RoleSource> getRoleSources() {
		return this.roleSources;
	}

	public void setRoleSources(Set<RoleSource> roleSources) {
		this.roleSources = roleSources;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}