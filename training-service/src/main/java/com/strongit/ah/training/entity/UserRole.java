package com.strongit.ah.training.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * UserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_role", catalog = "chnmedicine")
public class UserRole implements java.io.Serializable {

	// Fields

	/**
	 * @since Ver 1.1
	 */
	private static final long serialVersionUID = -7624684524810349448L;
	private String id;
	private Role role;
	private User user;
	private String remark;
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;

	// Constructors

	/** default constructor */
	public UserRole() {
	}

	/** minimal constructor */
	public UserRole(String id, Date createTime, String createUser) {
		this.id = id;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	/** full constructor */
	public UserRole(String id, Role role, User user, String remark,
			Date createTime, String createUser, Date updateTime,
			String updateUser) {
		this.id = id;
		this.role = role;
		this.user = user;
		this.remark = remark;
		this.createTime = createTime;
		this.createUser = createUser;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UESR_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

}