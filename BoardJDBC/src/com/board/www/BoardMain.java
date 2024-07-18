package com.board.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.board.www.dao.BoardDAO;
import com.board.www.dto.MemberDTO;
import com.board.www.service.BoardService;
import com.board.www.service.MemberService;

public class BoardMain {
	// 필드
	public static Scanner sc = new Scanner(System.in);
//	public static BoardDAO boardDAO = new BoardDAO(); // JDBC 담당
	public static Connection connection = null;
	public static MemberDTO loginMember = null; // 로그인 후 객체 
	// 생성자
	public BoardMain() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버명
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.111.103:1521:orcl",
					"boardtest", "boardtest"); // 2단계 - URL, ID, PW
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버명 또는 ojdbc6.jar 를 확인해주세요.");
			// 1단계 예외처리
//			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("URL, ID, PW 또는 쿼리문이 잘못되었습니다.");
			// 2단계 예외처리
			System.exit(0); // 프로세스 강제 종료
		} 
	}// BoardMain 생성자 종료
	
	// 메서드
	public static void main(String[] args) {
		// JDBC 를 활용하여 게시판 구현
		// 기본적인 설정 : Scanner, JDBC 연동, 주메뉴
		
		BoardMain boardMain = new BoardMain(); // 생성자 호출 -> 1, 2 단계 실행
		
		System.out.println("------- MBC 아카데미 대나무숲 -------");
		boolean run = true;
		while(run) {
			System.out.println("1.회원 \t2.게시판 \t3.종료");
			System.out.print(">> ");
			int select = sc.nextInt();
			
			switch(select) {
			case 1: 
				System.out.println("------ 회원용 서비스 ------");
				MemberService memberService = new MemberService();
				loginMember = memberService.memberMenu(sc, loginMember, connection);
				// 회원 서비스에서 나올 때 로그인 정보 유지
				break;
			case 2: 
				System.out.println("------ 게시판 이동 ------");
				BoardService boardService = new BoardService();
				boardService.boardMenu(sc, connection, loginMember);
				break;
			case 3: 
				System.out.println("------ 종  료 ------");
				run = false;
				break;	
			default : 
				System.out.println("잘못된 메뉴 입력입니다. 다시 입력해주세요.");
			}// switch 종료
		}// while 종료

	}

}
