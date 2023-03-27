package com.sga.sol.vc.vo;

public class MemberVo {

	String userId,userPassword;
	int fail_count;
	String allow_ip,access_ip;
	String dek,kek;
	String salt, key;
	String lock_dtm,last_login_dtm;
	
	public MemberVo() {}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
	
	

	
}
