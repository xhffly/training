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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Source entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "source", catalog = "chnmedicine", uniqueConstraints = @UniqueConstraint(columnNames = "TAG"))
public class Source implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID:TODO
	 * @since Ver 1.1
	 */
	private static final long serialVersionUID = -3942675145485660353L;
	private String id;
	private Source source;
	private String name;
	private String tag;
	private String url;
	private String sort;
	private String remark;
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	private String createUser;
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date updateTime;
	private String updateUser;
//	@JsonIgnore
	private Set<Source> sources = new HashSet<Source>(0);
	@JsonIgnore
	private Set<RoleSource> roleSources = new HashSet<RoleSource>(0);

	// Constructors

	/** default constructor */
	public Source() {
	}

	/** minimal constructor */
	public Source(String id, Date createTime, String createUser) {
		this.id = id;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	/** full constructor */
	public Source(String id, Source source, String name, String tag,
			String url, String sort, String remark, Date createTime,
			String createUser, Date updateTime, String updateUser,
			Set<Source> sources, Set<RoleSource> roleSources) {
		this.id = id;
		this.source = source;
		this.name = name;
		this.tag = tag;
		this.url = url;
		this.sort = sort;
		this.remark = remark;
		this.createTime = createTime;
		this.createUser = createUser;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.sources = sources;
		this.roleSources = roleSources;
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
	@JoinColumn(name = "P_ID")
	public Source getSource() {
		return this.source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TAG", unique = true, length = 50)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "URL", length = 50)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "SORT", length = 10)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "source")
	public Set<Source> getSources() {
		return this.sources;
	}

	public void setSources(Set<Source> sources) {
		this.sources = sources;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "source")
	public Set<RoleSource> getRoleSources() {
		return this.roleSources;
	}

	public void setRoleSources(Set<RoleSource> roleSources) {
		this.roleSources = roleSources;
	}

}