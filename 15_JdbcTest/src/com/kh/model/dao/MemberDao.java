package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.kh.model.vo.Member;

public class MemberDao {
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private final String ID = "C##KH";
	private final String PASSWORD = "KH";
	/**
	 * 사용자가 입력한 정보를 데이터베이스에 추가하는 메소드(회원정보추가)
	 * @param m 사용자 정보가 있는 Member 객체
	 * @return insert 쿼리문을 수행하고 처리된 row 수
	 * kdj create 2025.09.23
	 */
	public int insertMember(Member m) {
			Connection conn = null;
			Statement stmt = null;
			int result = 0; // 한사이클 돌고 return 을 하기에 위쪽에 정의
			
			try {
				//1. 오라클 드라이버를 로드
				Class.forName("DRIVER");
				
				//2. 오라클 접속요청
				conn = DriverManager.getConnection( 
						"URL", "ID", "PASSWORD");
				
				//3. 트랜잭션 제어 사용자 설정
				conn.setAutoCommit(false);
				
				//4. 쿼리문 작성(insert, update, delete)
				String sql = 
				"INSERT INTO MEMBER "
						+ " VALUES (SEQ_USERNO.NEXTVAL, '" 
						+ m.getUserId() + "', '"
						+ m.getUserPw() + "', '"
						+ m.getUserName() + "', '"
						+ m.getGender() + "', '"
						+ m.getAge() + "', '"
						+ m.getEmail() + "', '"
						+ m.getAddress() + "', '"
						+ m.getPhone() + "', '"
						+ m.getHobby() + "', SYSDATE)";
				//5. statement 생성
				stmt = conn.createStatement();
				
				//6. statement 통해서 쿼리문 실행
				result = stmt.executeUpdate(sql);
				
				//7. commit, rollback
				if(result > 0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					//8. 자원반납
					if(stmt != null)stmt.close();
					if(conn != null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return result;
	}
	/**
	 * 사용자 요청한 회원정보 리스트를 가져오는 메소드(회원정보 전체추출)
	 * @return 회원정보리스트를 리턴
	 */
	public ArrayList<Member> selectList(){
			Connection conn = null;
			Statement stmt = null;
			ResultSet rset = null;
			// ResultSet -> 반복문 -> Member -> list.add(Member)
			ArrayList<Member> list = new ArrayList<Member>();
			
			try {
				//1. 오라클 드라이버를 로드
				Class.forName("DRIVER");
				
				//2. 오라클 접속요청
				conn = DriverManager.getConnection( 
						"URL", "ID", "PASSWORD");
				
				// 3. 트랜잭션 설정
				conn.setAutoCommit(false);
				
				// 4. statement 생성
				stmt = conn.createStatement();
				
				// 5. select 쿼리문
				String sql = "SELECT * FROM MEMBER ORDER BY USERNO";
				
				// 6. 쿼리문 실행
				rset = stmt.executeQuery(sql);
				
				// 7. ResultSet 각 레코드객체를 추출해서 Member 객체에 담고,
				//		ArrayList list.add(member)
				while(rset.next()) {
					char gender = rset.getString("GENDER") == null ? ' ' : 
										rset.getString("GENDER").charAt(0);
					 Member m = new Member(
							 rset.getInt("USERNO"),
							 rset.getString("USERID"),
							 rset.getString("USERPW"),
							 rset.getString("USERNAME"), 
							 gender,
							 rset.getInt("AGE"),
							 rset.getString("EMAIL"),
							 rset.getString("ADDRESS"),
							 rset.getString("PHONE"),
							 rset.getString("HOBBY"),
							 rset.getDate("ENROLLDATE")
							 );
					 list.add(m);
				} // End Of While
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				try {
					rset.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
	}
	public Member selectById(Member m) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			//1. 오라클 드라이버를 로드
			Class.forName("DRIVER");
			
			//2. 오라클 접속요청
			System.out.println("<<오라클 드라이버 등록 완료(insert)");
			conn = DriverManager.getConnection( 
					"URL","ID", "PASSWORD");
			
			// 3. 트랜잭션 설정
			conn.setAutoCommit(false);
			
			// 4. statement 생성
			stmt = conn.createStatement();
			
			// 5. select 쿼리문
			String sql = "SELECT * FROM MEMBER WHERE USERID = '" 
			+ m.getUserId() + "'";
			
			// 6. 쿼리문 실행
			rset = stmt.executeQuery(sql);
			
			// 7. ResultSet 각 레코드객체를 추출해서 Member 객체에 담고,
			//		ArrayList list.add(member)
			if(rset.next()) {
				char gender = rset.getString("GENDER") == null ? ' ' : 
									rset.getString("GENDER").charAt(0);
				 member = new Member(
						 rset.getInt("USERNO"),
						 rset.getString("USERID"),
						 rset.getString("USERPW"),
						 rset.getString("USERNAME"), 
						 gender,
						 rset.getInt("AGE"),
						 rset.getString("EMAIL"),
						 rset.getString("ADDRESS"),
						 rset.getString("PHONE"),
						 rset.getString("HOBBY"),
						 rset.getDate("ENROLLDATE")
						 );
			} // End Of If
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			// 8. 객체 반납
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}
	public int updateById(Member m) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0; // 한사이클 돌고 return 을 하기에 위쪽에 정의
		
		try {
			//1. 오라클 드라이버를 로드
			Class.forName("DRIVER");
			
			//2. 오라클 접속요청
			conn = DriverManager.getConnection( 
					"URL", "ID", "PASSWORD");
			
			//3. 트랜잭션 제어 사용자 설정
			conn.setAutoCommit(false);
			
			//4. 쿼리문 작성(insert, update, delete)
			String sql = "UPDATE MEMBER SET "
					+ "USERPW = '"+ m.getUserPw() 
					+"', USERNAME = '"+ m.getUserName() 
					+"', ADDRESS = '"+ m.getAddress() 
					+"', PHONE = '"+ m.getPhone() 
					+"', HOBBY = '"+ m.getHobby() 
					+"' "
					+ "WHERE USERID = '"+ m.getUserId() + "'";
			
			//5. statement 생성
			stmt = conn.createStatement();
			
			//6. statement 통해서 쿼리문 실행
			result = stmt.executeUpdate(sql);
			
			//7. commit, rollback
			if(result > 0) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//8. 자원반납
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return result;
	}
	public int deleteById(Member m) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0; // 한사이클 돌고 return 을 하기에 위쪽에 정의
		
		try {
			//1. 오라클 드라이버를 로드
			Class.forName("DRIVER");
			
			//2. 오라클 접속요청
			conn = DriverManager.getConnection( 
					"URL", "ID", "PASSWORD");
			
			//3. 트랜잭션 제어 사용자 설정
			conn.setAutoCommit(false);
			
			//4. 쿼리문 작성(insert, update, delete)
			String sql = "DELETE FROM MEMBER WHERE USERID = '"
							+ m.getUserId() +"'";
			
			//5. statement 생성
			stmt = conn.createStatement();
			
			//6. statement 통해서 쿼리문 실행
			result = stmt.executeUpdate(sql);
			
			//7. commit, rollback
			if(result > 0) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//8. 자원반납
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return result;
	}
}

