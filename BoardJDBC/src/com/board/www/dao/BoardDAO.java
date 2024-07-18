package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.www.dto.BoardDTO;
import com.board.www.dto.MemberDTO;

public class BoardDAO {
	// 데이터베이스 처리 (C, R, U, D)

	public void list(Connection connection) {

		try {
			String sql = "select bno, btitle, bcontent, bwriter, bDate from board order by bno asc";
			// board 테이블에 있는 데이터를 가져온다.

			PreparedStatement prst = connection.prepareStatement(sql);

			ResultSet rs = prst.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getInt("bno") + " | ");
				System.out.print(rs.getString("btitle") + "\t");
				System.out.print(rs.getString("bwriter") + "     ");
				System.out.println(rs.getDate("bDate"));

//				boardDTO.setBno(rs.getInt("bno"));
//				boardDTO.setBtitle(rs.getString("btitle"));
//				boardDTO.setBcontent(rs.getString("bcontent"));
//				boardDTO.setBwriter(rs.getString("bwriter"));
//				boardDTO.setbDate(rs.getDate("bDate"));

			} // while
				// 5단계
			rs.close();
			prst.close(); // finally 역할
		} catch (SQLException e) {
			System.out.println("BoardDAO.list() SQL 오류");
		}

	}

	public void write(Connection connection, BoardDTO newBoard, MemberDTO loginMember) {
		try {
//			System.out.println("?");
			String sql = "insert into board(bno, btitle, bcontent, bwriter, bDate) values (b_seq.nextval, ?, ?, ?, sysdate)";
//			System.out.println("?");
//			              insert into board(bno, btitle, bcontent, bwriter, bDate) values(b_seq.nextval, ?, ?, ?, sysdate);
			System.out.println(newBoard.getBtitle());
			System.out.println(newBoard.getBcontent());
			System.out.println(loginMember.getMid());
			PreparedStatement prst = connection.prepareStatement(sql);
//			System.out.println("?");
			prst.setString(1, newBoard.getBtitle());
			prst.setString(2, newBoard.getBcontent());
			prst.setString(3, loginMember.getMid());
//			System.out.println("?");

			int result = prst.executeUpdate();
			System.out.println(result);
			if(result > 0) {
				System.out.println("게시글이 등록되었습니다.");
			}else {
				System.out.println("실패");
			}

//			while (rs.next()) {
//				System.out.println("작성자 : " + rs.getString(loginMember.getMid()) + " | " + rs.getDate(5));
//				System.out.println("제목 : " + rs.getString("title"));
//				System.out.println("내용 : " + rs.getString("content"));
//			}
//			rs.close();
			prst.close();
		} catch (SQLException e) {
			System.out.println("BoardDAO.write() SQL 오류");
			System.out.println(e);
		}
	}// write

	public void select(Connection connection, int bno, MemberDTO loginMember) {
		try {
			String sql = "select*from board where bno = ?";

			PreparedStatement prst = connection.prepareStatement(sql);
			prst.setInt(1, bno);
			ResultSet rs = prst.executeQuery();

			while (rs.next()) {
				System.out.println("작성자 : " + loginMember.getMid() + "      | " + rs.getDate(5));
				System.out.println("제목 : " + rs.getString(2));
				System.out.println("내용 : " + rs.getString(3));
			}
			rs.close();
			prst.close();
		} catch (SQLException e) {
			System.out.println("BoardDAO.write() SQL 오류");
		}
	}

	public void myPost(Connection connection, String mid) {
		try {
			String sql = "select * from board where bwriter = ?";
			PreparedStatement prst = connection.prepareStatement(sql);
			prst.setString(1, mid);

			ResultSet rs = prst.executeQuery();

			while (rs.next()) {
				System.out.println("NO." + rs.getString(1) + " | " + rs.getString(2));
				System.out.println(rs.getString(3));
			}
			rs.close();
			prst.close();
		} catch (SQLException e) {
			System.out.println("BoardDAO.myPost() SQL 오류");
		}

	}// myPost

	public void update(Connection connection, String title, String content, int bno) {
		try {
			String sql = "update board set btitle=?, bcontent = ? where bno = ?";
			PreparedStatement prst = connection.prepareStatement(sql);
			prst.setString(1, title);
			prst.setString(2, content);
			prst.setInt(3, bno);
			int result = prst.executeUpdate();
			
			if(result > 0) {
				System.out.println("수정이 완료되었습니다.");
			}else {
				System.out.println("수정이 실패되었습니다.");
			}
//			ResultSet rs = prst.executeQuery();
//			System.out.println("?");
//			while (rs.next()) {
//				System.out.println("제목 : " + rs.getString("btitle"));
//				System.out.println("내용 : " + rs.getString("bcontent"));
//			}
//			rs.close();
			prst.close();
		} catch (SQLException e) {
			System.out.println("BoardDAO.update() SQL 오류");
		}
	}// update

	public void delete(Connection connection, MemberDTO loginMember, int bno) {
		try {
			String sql = "delete from board where bno = ?";
			PreparedStatement prst = connection.prepareStatement(sql);
			prst.setInt(1, bno);
			
			int result = prst.executeUpdate();
			
			if(result > 0) {
				System.out.println("게시물이 삭제 되었습니다.");
			} else {
				System.out.println("잘못된 입력입니다. 다시 확인해주세요.");
			}
		} catch (SQLException e) {
			System.out.println("BoardDAO.delete() SQL 오류");
		}
	}
}
