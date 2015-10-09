package net.gslab.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity           //指明这是数据库实体
@Table(name="t_admin")  //对应数据库的表t_member
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)            //设置cache缓存


public class Admin extends BaseDomain{

	@Id   //指定为主键
	private int adminId;         //主键，同时也是登陆账户
	private String adminName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date   birthDate;
	private String password;
	private String password_captcha;  //密码 验证码，重置密码时会用到这个字段
	
	private String email;
	private String email_active;              //email状态，是否激活，"0"代表未激活，"1"代表激活,初始化是是null
	private String email_captcha;             //email验证码，系统发送激活邮件后，会同步这个标记
	private String gender;
	private String QQ;
	private String address;
	private String imgUrl;
	private String mobilePhone;
	private String selfEvaluation;

	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail_active() {
		return email_active;
	}
	public void setEmail_active(String email_active) {
		this.email_active = email_active;
	}
	public String getEmail_captcha() {
		return email_captcha;
	}
	public void setEmail_captcha(String email_captcha) {
		this.email_captcha = email_captcha;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getSelfEvaluation() {
		return selfEvaluation;
	}
	public void setSelfEvaluation(String selfEvaluation) {
		this.selfEvaluation = selfEvaluation;
	}
	public String getPassword_captcha() {
		return password_captcha;
	}
	public void setPassword_captcha(String password_captcha) {
		this.password_captcha = password_captcha;
	}
	
	
}
