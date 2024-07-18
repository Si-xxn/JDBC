package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.www.dto.MemberDTO;

public class MemberDAO {
	// 회원 DB -> C(회원가입), R(로그인), U(회원정보수정), D(회원정보탈퇴)

	public MemberDAO() {
		// 기본생성자
	}

	public MemberDAO(Connection connection) {
		// 커스텀 생성자
	}

	public void register(Connection connection, String mid, String mpw) { // 회원가입 처리
		try {
			String sql = "insert into member(mno, mid, mpw, mdate) values(b_seq.nextval, ?, ?, sysdate)";
			PreparedStatement prst = connection.prepareStatement(sql);
			prst.setString(1, mid);
			prst.setString(2, mpw);
			int result = prst.executeUpdate();

			if (result > 0) {
				System.out.println("회원가입 완료, 로그인 후 이용해주세요.");
			} else {
				System.out.println("회원가입 실패, 빈 칸 없이 모두 입력해주세요.");
			}
			prst.close();
		} catch (SQLException e) {
			System.out.println("관리자 : SQL 문을 확인하세요");
			System.out.println("회원 : 정보가 모두 입력되었는지 확인하세요");
		}

	}

	public MemberDTO login(Connection connection, MemberDTO loginMemberDTO) { // 로그인 처리
		// connection -> main 에서 넘어온 JDBC 1, 2 단계
		// loginMemberDTO -> 로그인 시 키보드로 입력받은 ID, PW 값이 들어있다.
		// DB 에 있는 로그인 값을 찾아옴
		MemberDTO loginDTO = new MemberDTO(); // 리턴용 빈 객체
		try {
			String sql = "select mno, mid, mpw, mdate from member where mid = ? and mpw = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginMemberDTO.getMid());
			// Service 에서 받은 ID 첫번째 ? 적용
			preparedStatement.setString(2, loginMemberDTO.getMpw());
			// Service 에서 받은 PW 두번째 ? 적용

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println("로그인 성공 - 메인메뉴에서 게시판으로 이동하실 수 있습니다.");
				loginDTO = loginMemberDTO;
			} else {
				System.out.println("ID 또는 PW 를 확인해주세요.");
			}

//			ResultSet resultSet = preparedStatement.executeQuery();
//			// 위에서 만든 쿼리문 실행하고 결과를 ResultSet 표로 받는다.
//
//			while (resultSet.next()) {
//
//				loginDTO.setMno(resultSet.getInt("mno"));
//				loginDTO.setMid(resultSet.getString("mid"));
//				loginDTO.setMpw(resultSet.getString("mpw"));
//				loginDTO.setMdate(resultSet.getDate("mdate"));
//				// ResultSet 표에 있는 정보를 MemberDTO 객체에 넣음
//			}
//			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("찾는 ID 와 PW가 없습니다. ");
			System.out.println("관리자 : SQL 문을 확인하세요");
			System.out.println("회원 : ID 와 PW 확인하세요");
		}

		return loginDTO; // 로그인 완성 객체
	}// login 메서드

	public void select(Connection connection, String mid, String mpw) {

		try {
			String sql = "select * from member where mid = ? and mpw = ?";
			PreparedStatement prst = connection.prepareStatement(sql);
			System.out.println(mid + "," + mpw);
			prst.setString(1, mid);
			prst.setString(2, mpw);

			ResultSet rs = prst.executeQuery();

			while (rs.next()) {
				System.out.print("회원 아이디 : " + rs.getString("mid"));
				System.out.println("  |  회원 패스워드 : " + rs.getString("mpw"));
			}
			rs.close();
			prst.close();
		} catch (SQLException e) {
			System.out.println("찾는 정보가 없습니다. ");
			System.out.println("관리자 : SQL 문을 확인하세요");
			System.out.println("회원 : ID, Pw를 확인하세요");
		}
	}

	public MemberDTO update1(Connection connection, String newId, String mid, MemberDTO loginMember) { // 회원정보 수정

		try {
			String sql = "update member set mid = ? where mid = ?";

			PreparedStatement prst = connection.prepareStatement(sql);
			prst.setString(1, newId);
			prst.setString(2, mid);

			int result = prst.executeUpdate();

			if (result > 0) {
				System.out.println(" 정보 변경이 완료되었습니다. ");
				loginMember.setMid(newId);
			} else {
				System.out.println("회원정보가 맞지 않습니다.");
			}
			prst.close();
		} catch (SQLException e) {
			System.out.println("찾는 정보가 없습니다. ");
			System.out.println("관리자 : SQL 문을 확인하세요");
			System.out.println("회원 : ID, Pw를 확인하세요");
		}
		return loginMember;

	}

	public MemberDTO update2(Connection connection, String newMpw, String mid, MemberDTO loginMember) { // 회원정보 수정

		try {
			String sql = "update member set mpw = ?, where mid = ? ";

			PreparedStatement prst = connection.prepareStatement(sql);
			prst.setString(1, newMpw);
			prst.setString(2, mid);

			int result = prst.executeUpdate();

			if (result > 0) {
				System.out.println(" 정보 변경이 완료되었습니다. ");
				loginMember.setMpw(newMpw);
			} else {
				System.out.println("회원정보가 맞지 않습니다.");
			}
			prst.close();
		} catch (SQLException e) {
			System.out.println("찾는 정보가 없습니다. ");
			System.out.println("관리자 : SQL 문을 확인하세요");
			System.out.println("회원 : ID, Pw를 확인하세요");
		}
		return loginMember;

	}

	public void delete(Connection connection, String mid, String mpw, MemberDTO loginMember) { // 회원 탈퇴(삭제)

		try {
			String sql = "delete from member  where mid = ? and mpw = ?";
			PreparedStatement prst = connection.prepareStatement(sql);
			prst.setString(1, mid);
			prst.setString(2, mpw);
			int result = prst.executeUpdate();

			if (result > 0) {
				System.out.println("탈퇴가 완료되었습니다.");
				MemberDTO logout = new MemberDTO();
				loginMember = logout;
//				loginMember = null;
				System.exit(result);
			} else {
				System.out.println("정보가 일치하지 않습니다.");
			}
			prst.close();
		} catch (SQLException e) {
			System.out.println("찾는 정보가 없습니다. ");
			System.out.println("관리자 : SQL 문을 확인하세요");
			System.out.println("회원 : ID, Pw를 확인하세요");
		}

	}

}
