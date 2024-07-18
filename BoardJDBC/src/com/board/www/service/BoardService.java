package com.board.www.service;

import java.sql.Connection;
import java.util.Scanner;

import com.board.www.dao.BoardDAO;
import com.board.www.dto.BoardDTO;
import com.board.www.dto.MemberDTO;

public class BoardService {
	// Board 부메뉴 (C, R, U, D, List)
	public static BoardDAO board = new BoardDAO();

	public void list(Connection connection, Scanner sc, MemberDTO loginMember) { // 게시물 목록 보기
		BoardDAO boardDAO = new BoardDAO();
		System.out.println("__________________________________________");
		System.out.println();
		System.out.println("            대나무 숲 게시판 목록");
		System.out.println();
		System.out.println("__________________________________________");
		System.out.println("NO.    제목         작성자          작성일");

		boardDAO.list(connection);
	
		
	}// list 종료
	public void boardMenu(Scanner sc, Connection connection, MemberDTO loginMember) {
		if(loginMember != null) {
			list(connection, sc, loginMember);
			boolean run = true;
			while (run) {
				System.out.println("__________________________________________");
				System.out.println();
				System.out.println("1.글쓰기   2.상세보기   3.글수정   4.글삭제   5.이전");
				System.out.print(">> ");
				int select = sc.nextInt();
				switch (select) {
				case 1:
					System.out.println("---------- 글 작 성 ----------");
					write(sc, connection, loginMember);
					break;
				case 2:
					view(sc, connection, loginMember);
					break;
				case 3:
					System.out.println("---------- 글 수 정 ----------");
					update(sc, loginMember, connection);
					break;
				case 4:
					System.out.println("---------- 글 삭 제 ----------");
					delete(sc, loginMember, connection);
					break;
				case 5:
					System.out.println("---------- 이  전 ----------");
					run = false;
					break;
				default:
					System.out.println("올바른 메뉴를 입력해주세요.");
				}// switch
			}
		}else { 
			list(connection, sc, loginMember);
			System.out.println("더 자세한 정보는 로그인 또는 회원가입 후 이용해주세요.");
		}
		
	}// menu
	
	public void write(Scanner sc, Connection connection, MemberDTO loginMember) {
		BoardDTO newBoard = new BoardDTO();
		
		System.out.print("제목 : ");
		String title = sc.next();
		System.out.print("내용 : ");
		String content = sc.next();
		newBoard.setBtitle(title);
		newBoard.setBcontent(content);
		
		board.write(connection, newBoard, loginMember);
	}
	public void view(Scanner sc, Connection connection, MemberDTO loginMember) {
		list(connection, sc, loginMember);
		System.out.println();
		System.out.println("---------- 상 세 보 기 ----------");
		System.out.print("게시글 번호 입력 >> ");
		int no = sc.nextInt();
		
		board.select(connection, no, loginMember);
	}
	
	public void update(Scanner sc, MemberDTO loginMember, Connection connection) {
		board.myPost(connection, loginMember.getMid());
		System.out.print("수정 게시글 번호 >> ");
		int no = sc.nextInt();
		
		System.out.println("제목 : ");
		String modifyTitle = sc.next();
		System.out.println("내용 : ");
		String mocifyCon = sc.next();
		board.update(connection, modifyTitle, mocifyCon, no);
		
		
		
	}
	
	public void delete(Scanner sc, MemberDTO loginMember, Connection connection) {
		board.myPost(connection, loginMember.getMid());
		System.out.print("삭제 게시글 번호 >> ");
		int no = sc.nextInt();
		
		board.delete(connection, loginMember, no);
	}
	
}// class
