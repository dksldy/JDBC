package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberView;

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
			   new MemberView().displaySuccess(userName + 
					   						"님의 회원가입이 완료되었습니다.");
		   }else {
			   new MemberView().displayFailed("회원가입 실패");
		   }
	   }
	   
	   public void selectList() {
		   ArrayList<Member> list = new MemberDao().selectList();
		   
		   if(list.isEmpty()) {
			   new MemberView().displayNoData("회원전체정보가 없습니다.");
		   }else{
			   new MemberView().displayMemberList(list);
		   }
	   }
	   
	   public void selectById(String userId) {
		   Member m = new Member();
		   m.setUserId(userId);
		   
		   Member member = new MemberDao().selectById(m);
		   
		   if(member == null) {
			   new MemberView().displayFailed("해당 " + userId + 
					   						" 에 해당하는 정보가 없습니다.");
		   }else{
			   new MemberView().displaySuccess(userId + "님의 정보" + 
					   										member);
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
			   new MemberView().displaySuccess(userName + 
					   						"님의 정보수정이 완료되었습니다.");
		   }else {
			   new MemberView().displayFailed(userName + 
					   								"님의 정보수정 실패");
		   }
	   }
	   
	   public void deleteById(String userId) { 
		   
		   Member m = new Member();
		   m.setUserId(userId);
		   
		   int result = new MemberDao().deleteById(m);
		   
		   if(result > 0) {
			   new MemberView().displaySuccess(userId + 
					   						"님의 회원정보가 삭제되었습니다.");
		   }else {
			   new MemberView().displayFailed(userId + 
					   							"님의 회정정보 삭제 실패");
		   }
	   }
	   
}
