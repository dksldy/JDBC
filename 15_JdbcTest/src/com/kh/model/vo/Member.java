package com.kh.model.vo;

import java.util.Date;

public class Member {
	// 멤버 변수
	
	private int userNo;        // USERNO    NUMBER
    private String userId;    // USERID    VARCHAR2(20 BYTE)
    private String userPw;    //USERPW    VARCHAR2(20 BYTE)
    private String userName;// USERNAME    VARCHAR2(20 BYTE)
    private char gender;    //GENDER    CHAR(1 BYTE)
    private int age;        //AGE    NUMBER
    private String email;    //EMAIL    VARCHAR2(30 BYTE)
    private String address;    // ADDRESS    VARCHAR2(100 BYTE)
    private String phone;    // PHONE    VARCHAR2(13 BYTE)
    private String hobby;    // HOBBY    VARCHAR2(50 BYTE)
    private Date enrollDate;// ENROLLDATE    DATE --> java.sql.Date
    
    // 생성자 (기본생성자(디폴트 생정자), 매변생성자)
    public Member() {
    	super();
    }
    
    // 매인생성자
	public Member(int userNo, String userId, String userPw, String userName, char gender, int age, String email,
			String address, String phone, String hobby, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}
	
 	// getter/ setter
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	// to string
	@Override
	public String toString() {
		return "Member [" + userNo + ", \t" + userId + ", \t" + 
	userPw + ", \t" + userName + ", \t" + gender + ", \t" + age + 
	", \t" + email + ", \t" + address + ", \t" + phone + ", \t" + 
						hobby + ", \t" + enrollDate + "]";
	}
}
