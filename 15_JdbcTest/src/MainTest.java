import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainTest {
	
	//자바 프로그램에서 오라클에 접속하여 쿼리문을 실행하고 결과값을 받으려고 한다.
	
	public static void main(String[] args) {
		//1. Connection : 데이터베이스(Oracle) 연결정보를 가지고있는 클래스
		
		updateTest();
		System.out.println("The end");
	}

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
				"UPDATE TEST SET TNAME = '저길동' WHERE TNO = 1";
		
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
}














