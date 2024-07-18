package com.board.www.service;

import java.sql.Connection;
import java.util.Scanner;

import com.board.www.dao.MemberDAO;
import com.board.www.dto.MemberDTO;

public class MemberService {
	// 회원에 대한 처리 -> C(회원가입), R(로그인), U(회원 정보 수정), D(회원 탈퇴)
	
	public static MemberDAO memberDAO = new MemberDAO(); // MemberDAO 클래스 불러올때 사용할 객체

	public MemberDTO memberMenu(Scanner sc, MemberDTO loginMember, Connection connection) { // while 부메뉴 반복 처리
		boolean memberRun = true;
		while (memberRun) {
			System.out.println("__________________________________");
			System.out.println("1. 회원가입   2. 로그인   3. 회원수정   4. 회원탈퇴   5. 이전");
			System.out.print(">> ");
			int memberSelect = sc.nextInt();

			switch (memberSelect) {
			case 1:
				join(sc, connection);
				break;
			case 2:
				loginMember = login(sc, loginMember, connection);
				break;
			case 3:
				modify(sc, loginMember, connection);
				break;
			case 4:
				delete(sc, connection, loginMember);
				break;
			case 5:
				System.out.println("메인메뉴로 돌아갑니다.");
				memberRun = false;
				break;
			}// switch

		} // while
		return loginMember;

	}// memberMenu

	public void join(Scanner sc, Connection connection) { // 회원가입 동작 메서드

		System.out.println("회원가입 메서드로 진입");
		System.out.print("ID : ");
		String joinId = sc.next();
		System.out.print("PW : ");
		String joinPw = sc.next();
		
		memberDAO.register(connection, joinId, joinPw);

	}// join

	public MemberDTO login(Scanner sc, MemberDTO loginMember, Connection connection) { // 회원 로그인 동작 메서드

		System.out.println("로그인 메서드로 진입");
		System.out.print("ID : ");
		String loginId = sc.next();
		System.out.print("PW : ");
		String loginPw = sc.next();
		MemberDTO loginMemberDTO = new MemberDTO(loginId, loginPw);
		// 키보드로 입력받은 값을 객체로 생성
		MemberDAO memberDAO = new MemberDAO();
		loginMember = memberDAO.login(connection, loginMemberDTO);
		return loginMember;
		//return memberDAO.login(connection, loginMemberDTO);
		//DB 에서 넘어온 객체를 리턴
	}// login

	public void modify(Scanner sc, MemberDTO loginMember, Connection connection) { // 회원 정보 수정
		if(loginMember != null) {
			boolean run = true;
			System.out.println("본인 확인을 위해 ID 와 PW를 입력해주세요.");
			System.out.print("ID >> ");
			String id1 = sc.next();
			System.out.print("PW >> ");
			String pw1 = sc.next();
			memberDAO.select(connection, id1, pw1);
			while (run) {
				System.out.println("회원 정보 수정 메서드로 진입");
				System.out.println("____________________________________");
				System.out.println("1.ID 변경   2.PW 변경   3.이전");
				int select = sc.nextInt();
				switch (select) {
				case 1:
					System.out.print("변경 ID >> ");
					String newId = sc.next();
					loginMember = memberDAO.update1(connection, newId, id1, loginMember);
					break;
				case 2:
					System.out.print("변경 PW >> ");
					String newPw = sc.next();
					loginMember = memberDAO.update2(connection, newPw, id1, loginMember);
					break;
				case 3:
					run = false;
					break;
				}// switch
			}// while
		}// modify
		else {
			System.out.println("로그인 먼저 진행해주세요");
		}
		

	}// modify

	public void delete(Scanner sc, Connection connection, MemberDTO loginMember) {

		System.out.println("회원 탈퇴 메서드로 진입");
		System.out.println("본인 확인을 위해 ID 와 PW를 입력해주세요.");
		System.out.print("ID >> ");
		String mid = sc.next();
		System.out.print("PW >> ");
		String mpw = sc.next();
		memberDAO.delete(connection, mid, mpw, loginMember);

	}// delete

}// class
