package com.saeyan.dto;


public class MemberVO {
	private String name;
	private String userid;
	private String pwd;
	private String email;
	private String phone;
	private int admin;
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", userid=" + userid + ", pwd=" + pwd + ", email=" + email + ", phone="
				+ phone + ", admin=" + admin + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	
	
}
