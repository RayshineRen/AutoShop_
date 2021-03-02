package com.Ray.pojo;

import java.util.Date;

public class Role {

	private Integer id;   //id
	private String roleCode; //角色编码
	private String roleName; //角色名称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", roleCode='" + roleCode + '\'' +
				", roleName='" + roleName + '\'' +
				'}';
	}

	public Role() {
	}

	public Role(Integer id, String roleCode, String roleName) {
		this.id = id;
		this.roleCode = roleCode;
		this.roleName = roleName;
	}
}