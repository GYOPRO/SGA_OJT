package com.sga.sol.vc.vo;

public class MemberVo {

	String user_id,user_password;
	int fail_count;
	String allow_ip,access_ip;
	String dek,kek;
	String salt, key;
	
	String lock_dtm,last_login_dtm;
	public MemberVo() {}
	//sd
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public int getFail_count() {
		return fail_count;
	}
	public void setFail_count(int fail_count) {
		this.fail_count = fail_count;
	}
	public String getAllow_ip() {
		return allow_ip;
	}
	public void setAllow_ip(String allow_ip) {
		this.allow_ip = allow_ip;
	}
	public String getAccess_ip() {
		return access_ip;
	}
	public void setAccess_ip(String access_ip) {
		this.access_ip = access_ip;
	}
	public String getLock_dtm() {
		return lock_dtm;
	}
	public void setLock_dtm(String lock_dtm) {
		this.lock_dtm = lock_dtm;
	}
	public String getLast_login_dtm() {
		return last_login_dtm;
	}
	public void setLast_login_dtm(String last_login_dtm) {
		this.last_login_dtm = last_login_dtm;
	}

	public String getDek() {
		return dek;
	}

	public void setDek(String dek) {
		this.dek = dek;
	}

	public String getKek() {
		return kek;
	}

	public void setKek(String kek) {
		this.kek = kek;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	
}
