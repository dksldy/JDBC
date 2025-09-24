package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

public class MemberView {
	private Scanner sc = new Scanner(System.in);
	private MemberController controller = new MemberController();

	public void mainMenu() {
		while (true) {
			System.out.println("==== 회원관리 프로그램 ====");
			System.out.println("1. 회원추가 : ");
			System.out.println("2. 전체 회원 조회 : ");
			System.out.println("3. 회원 아이디로 검색 : ");
			System.out.println("4. 회원 정보 수정 : ");
			System.out.println("5. 회원 탈퇴 : ");
//			System.out.println("6. 회원 이름 검색 : ");
			System.out.println("0. 프로그램 종료 : ");
			System.out.print("번호 입력 : ");
			int menu = Integer.parseInt(sc.nextLine());

			switch (menu) {
			case 1:
				insertMember();
				break;
			case 2:
				selectList();
				break;
			case 3:
				selectById();
				break;
			case 4:
				updateMember();
				break;
			case 5:
				deleteMember();
				break;
			case 6:
				selectByName();
				break;
			case 0:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("잘못된 번호입니다.");
			}
		}
	}

	/**
	 * 1번화면 ~ 6번화면 설정
	 */
	// 1. 회원 추가
	private void insertMember() {
		System.out.println("==== 회원 추가 ====");
		
		System.out.print("userId 입력 : ");
		String userId = sc.nextLine();
		
		System.out.print("userPw 입력 : ");
		String userPw = sc.nextLine();
		
		System.out.print("userName 입력 : ");
		String userName = sc.nextLine();
		
		System.out.print("gender(M/F) 입력 : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);
		// toUpperCase => 대소문자 구분없이 대문자로 변환 (성별 입력 꿀팁임)
		
		System.out.print("Age 입력 : ");
		int Age = Integer.parseInt(sc.nextLine());
		// 최대한 에러날일 없게 Integer로 써버리기~
		
		System.out.print("email 입력 : ");
		String email = sc.nextLine();
		
		System.out.print("address 입력 : ");
		String address = sc.nextLine();
		
		System.out.print("phone(ex.010-1234-1234) 입력 : ");
		String phone = sc.nextLine();
		
		System.out.print("hobby 입력 : ");
		String hobby = sc.nextLine();

		// 회원가입 버튼 클릭 동일
		controller.insertMember(userId, userPw, userName, gender, 
								Age, email, address, phone, hobby);
	}

	// 2. 전체회원 조회
	private void selectList() {
		System.out.println("==== 전체 회원 =====");
		// 전체 회원 조회버튼
		controller.selectList();
	}

	// 3. 회원 아이디로 검색
	private void selectById() {
		System.out.println("==== 회원 조회 ====");
		System.out.print("userId 입력 :");
		String userId = sc.nextLine();
		// 조회할 회원 아이디를 입력 -> 조회요청 버튼
		controller.selectById(userId);
	}

	// 4. 회원정보 수정
	private void updateMember() {
		System.out.println("==== 회원정보 수정 =====");
		
		System.out.print("userId 입력 : ");
		String userId = sc.nextLine();
		
		System.out.print("userPw 입력 : ");
		String userPw = sc.nextLine();
		
		System.out.print("userName 입력 : ");
		String userName = sc.nextLine();
		
		System.out.print("address 입력 : ");
		String address = sc.nextLine();
		
		System.out.print("phone(ex.010-1234-1234) 입력 : ");
		String phone = sc.nextLine();
		
		System.out.print("hobby 입력 : ");
		String hobby = sc.nextLine();
		
		// 수정요청하는 userId와 수정내용을 입력하고, 수정해달라는 버튼
		controller.updateById(userId, userPw, userName, 
											address, phone, hobby);
	}

	// 5. 회원정보 삭제
	private void deleteMember() {
		System.out.println("==== 회원정보 삭제 ====");
		
		System.out.print("userId 입력 : ");
		String userId = sc.nextLine();
		
		// 회원정보 삭제 버튼
		controller.deleteById(userId);
	}

	// 6. 회원 이름으로 조회
	private void selectByName() {
		System.out.println("==== 회원 조회 ====");
		System.out.println("userName 입력 : ");
	
		System.out.print("userName 입력 : ");
		String userName = sc.nextLine();
		
		// selectByName 컨트롤러 메소드 만들어야함
		//controller.seletByName(userName);
	}

	/**
	 * 서버에서 화면을 요청하면 응답화면
	 * @param message
	 */
	// 성공메세지 출력화면
	public void displaySuccess(String message) {
		System.out.println("서비스 요청 성공 : " + message);
	};

	// 실패메세지 출력화면
	public void displayFailed(String message) {
		System.out.println("서비스 요청 실패 : " + message);
	};

	// 조회결과가 없을때 출력화면
	public void displayNoData(String message) {
		System.out.println("데이터 요청 결과 : " + message);
	};

	// 전체회원 출력화면
	public void displayMemberList(ArrayList<Member> list) {
		
		// 향상된 for 문
		for(Member m : list) {
			System.out.println(m.toString());
		}
		
		// 일반 for 문
//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i));
//		}
	};

	// 한명 회원 출력화면
	public void displayMember(Member m) {
		System.out.println("조회된 회원 : " + m.toString());
	};

}










