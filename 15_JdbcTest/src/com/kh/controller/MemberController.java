package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

// view -> controller 로 요청하기
// controller 는 받은 요청을 MemberDao 부르기, 
// 			그 후 리턴값 받기, 받은 리턴값을 view 에 전달하기
// MemberDao 에서 작업한 결과값 리턴받기
// controller 에 리턴받는값 view 에 전달하기

public class MemberController {
	/**
	 * 사용자로부터 (View 클래스로부터) 회원가입 요청을 받는다.
	 * MemberDao 가입요청을 하고(매개변수 전달), 가입에 결과 리턴을 받는다.
	 * @param 매개변수 ... 값을 전부 제공을 받는다.
	 */
	   public void insertMember(String userId, String userPw, 
			   String userName, char gender, int Age, 
			   String email, String address, 
			   String phone, String hobby) {
		   
		   Member m = new Member();
		   m.setUserId(userId);
		   m.setUserPw(userPw);
		   m.setUserName(userName);
		   m.setGender(gender);
		   m.setAge(Age);
		   m.setEmail(email);
		   m.setAddress(address);
		   m.setPhone(phone);
		   m.setHobby(hobby);
		   
		   int result = new MemberDao().insertMember(m);
		   
		   if(result > 0) {
			   // 회원가입 성공된 화면출력 요청.
		   }else {
			   // 회원가입 실패한 화면출력 요청.
		   }
	   }
	   
	   public void selectList() {
		   ArrayList<Member> list = new MemberDao().selectList();
		   
		   if(list.isEmpty()) {
			   // 전체화면 리스트내용 없음을 출력요청
		   }else{
			   // 전체화면 리스트를 view 에 보여달라(list) 요청
		   }
	   }
	   
	   public void selectById(String userId) {
		   Member m = new Member();
		   m.setUserId(userId);
		   
		   Member member = new MemberDao().selectById(m);
		   
		   if(member == null) {
			   // 해당된 id 의 멤버정보가 없음을 출력요청
		   }else{
			   // 해당된 id 의 멤버정보를 view에 보내주고 화면에 출력요청
		   }
	   }
	   
	   public void updateById(String userId, String userPw, 
			   String userName, String address, 
			   String phone, String hobby) {
		   
		   Member m = new Member();
		   m.setUserId(userId);
		   m.setUserPw(userPw);
		   m.setUserName(userName);
		   m.setAddress(address);
		   m.setPhone(phone);
		   m.setHobby(hobby);
		   
		   int result = new MemberDao().updateById(m);
		   
		   if(result > 0) {
			   // 업데이트 성공 화면 출력 요청
		   }else {
			   // 업데이트 실패 화면 출력 요청
		   }
	   }
	   
	   public void deleteById(String userId) { 
		   
		   Member m = new Member();
		   m.setUserId(userId);
		   
		   int result = new MemberDao().deleteById(m);
		   
		   if(result > 0) {
			   // 삭제 성공 화면 출력 요청
		   }else {
			   // 삭제 실패 화면 출력 요청
		   }
	   }
	   
}
