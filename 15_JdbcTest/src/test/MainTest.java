package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MainTest {
	/*
	자바 프로그램에서 오라클에 접속하여 쿼리문을 실행하고 결과값을 받으려고 한다.
	 1. 오라클 드라이버 로드 : Class.forName
	 						("oracle.jdbc.driver.OracleDriver");
	 						
	 2. 오라클 연결을 하면 : Connection(conn) 
	 			객체를 받음 : DriverManager.getConnection
	 						("오라클서버위치", "아이디", "패스워드")
	 								("url, id, pwd")
	 
	 3. 오라클에서 트랜처리를 수동처리 요청 : conn.setAutoCommit(false);
	 								
	 4.  Statement 생성 : Statement stmt = conn.createStatement();
	 
	 5. 디비에서 해야될 query문 생성 (select, insert, update, delete)
	 
	 6. 1) int count = stmt.executeUpdate(sql) : 
			(insert, update, delete 쿼리문 작성 디비실행) 
	 			결과값 실행된 row 수를 리턴
	 			
	    2) ResultSet rset = stmt.executeQuery(sql) :
	    						(select) 결과값 : 테이블구조 ResultSet
	    						
	 7. 1) 우리가 필요한 로직을 구현하면 된다.(count 결과값을 가지고 로직 실행.),
	 					(ResultSet -> Arraylist<Test>)
	 					
	 	2) 트랜잭션 처리 (commit, rollback)
	 	
	 8. 자원을 닫는다.(Connection, Statement, ResultSet)
	 */
	//1. Connection : 데이터베이스(Oracle) 연결정보를 가지고있는 클래스
	public static void main(String[] args) {
		
//		insertTest();
//		updateTest();
//		deleteTest();
		selectTest();
		System.out.println("The end");
	}

// 데이터 삽입(INSERT)
	private static void insertTest() {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("<<오라클 드라이버 등록 완료(insert)");
			
			conn = DriverManager.getConnection( 
					"jdbc:oracle:thin:@127.0.0.1:1521:xe",
					 "C##KH", "KH");
			System.out.println("<< 오라클 디비 접속 완료");
			
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String sql = 
				"INSERT INTO TEST (TNO, TNAME, TDATE) VALUES"
				+ " (4, '안중근', SYSDATE)";
			
			int count = stmt.executeUpdate(sql);
			
			if(count > 0) {
				conn.commit();
				System.out.println("insert 완료");
			}
			else {
				conn.rollback();
				System.out.println("insert 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		
			try {
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
}

// 수정(UPDATE)
	public static void updateTest() {
		
		// 1. Connection (객체(클래스)선언후 import 하기)
		Connection conn = null;
		// 2. Statement 객체참조변수 선언(java.sql import)
		Statement stmt = null;
		/*
		   1-1
		 - Class.forName("oracle.jdbc.driver.OracleDriver");
		 	oracle.jdbc.driver.OracleDriver 
		 	클래스를 forName(패키지명,클래스명) 찾아서 메모리 로드 시켜줘.
		 - 입력후 try/catch 불러오기
		 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("<< 오라클 드라이버 등록 완료");
			
		/*
		   1-2 드라이버 매니저를 통해 Connection 객체를 만들어 저장
		 - 입력후 catch 문 하나 더 만들기
		 - conn 에 드라이버매니저.getConnection(IP, ID, PWD) 연결하기
		 */
			conn = DriverManager.getConnection( 
					"jdbc:oracle:thin:@127.0.0.1:1521:xe",
					 "C##KH", "KH");
			System.out.println("<< 오라클 디비 접속 완료");
			
		// 1-3 자동커밋 설정 해제
			conn.setAutoCommit(false);
			
		// 2-1 Statement 생성
			stmt = conn.createStatement();
		
		// 3. update SQL 쿼리문 만들기
			String sql = 
				"UPDATE TEST SET TNAME = '무길동' WHERE TNO = 1";
		
		// 4. SQL 문 전송 및 결과 받기
		// insert / update / delete 실행 카운트수를 리턴한다.
		// select => ResultSet 객체를 리턴한다.(반복문을 통해 가져오면된다.)
			int count = stmt.executeUpdate(sql);
		
		// 5. 결과값을 가지고 조치를 취한다.
			if(count > 0) {
				conn.commit();
				System.out.println("update 완료");
			}
			else {
				conn.rollback();
				System.out.println("update 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		// 6. 객체자원 반납
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 삭제(DELETE)
	private static void deleteTest() {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("<<오라클 드라이버 등록 완료(insert)");
			
			conn = DriverManager.getConnection( 
					"jdbc:oracle:thin:@127.0.0.1:1521:xe",
					 "C##KH", "KH");
			System.out.println("<< 오라클 디비 접속 완료");
			
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String sql = "delete from TEST where TNAME = '이순신'";
			
			int count = stmt.executeUpdate(sql);
			
			if(count > 0) {
				conn.commit();
				System.out.println("delete 완료");
			}
			else {
				conn.rollback();
				System.out.println("delete 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		
			try {
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 정보 보기? (SELECT)
	private static void selectTest() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		// 1. 오라클 드라이버 로드
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection( 
				"jdbc:oracle:thin:@127.0.0.1:1521:xe","C##KH", "KH");
			
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM TEST ORDER BY TNO";
			
			rset = stmt.executeQuery(sql);
			
			// 테이블구조로 이루어져 있다. ex) List<test> 
			// 다음위치로 갈때 next()
			// 해당되는 레코드를 가져올땐 rset.get~("컬럼명") or rset.get~(컬럼인덱스)
			while(rset.next()) {
				int tno = rset.getInt("TNO");
				String tname = rset.getString("TNAME");
				Date tdate = rset.getDate("TDATE");
				
				System.out.println
				("TNO = " + tno + "\tTNAME = " + tname + 
											"\tTDATE = " + tdate);
			}
			
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
	}
}

// 계정 C##kh, 시퀀스 만들기, 멤버 테이블 만들기