package com.kh.view;

import java.util.Scanner;

public class MemberView {

	public void mainMenu() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("==== 회원관리 프로그램 ====");
			System.out.println("1. 회원추가 : ");
			System.out.println("2. 전체 회원 조회 : ");
			System.out.println("3. 회원 아이디로 검색 : ");
			System.out.println("4. 회원 정보 수정 : ");
			System.out.println("5. 회원 탈퇴 : ");
			System.out.println("6. 회원 이름 검색 : ");
			System.out.println("0. 프로그램 종료 : ");
			System.out.print("번호 입력 : ");
			int menu = Integer.parseInt(sc.nextLine());
		
			switch(menu) {
			case 1 : insertMember(); break;
			case 2 : selectList(); break;
			case 3 : selectById(); break;
			case 4 : updateMember(); break;
			case 5 : deleteMember(); break;
			case 6 : selectByName(); break;
			case 0 : System.out.println("프로그램 종료");
					return;
			default :
				System.out.println("잘못된 번호입니다.");
			}
	}
	}

	private void selectByName() {
		System.out.println("selectByName");
	}

	private void deleteMember() {
		System.out.println("deleteMember");
	}

	private void updateMember() {
		System.out.println("updateMember");
	}

	private void selectById() {
		System.out.println("selectById");
	}

	private void selectList() {
		System.out.println("selectList");
	}

	private void insertMember() {
		System.out.println("insertMember");
	}
}
