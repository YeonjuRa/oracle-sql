package dao;
import java.sql.*;
import java.util.Properties;
import java.io.FileReader;
public class DBHelper {
	public static Connection getConnection() throws Exception{
		//오라클 db접근 클래스 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("드라이버 로딩 성공");
		//로컬 테스트
		//로컬에서 설정파일 불러오기
		FileReader fr = new FileReader("c:\\dev\\auth\\oracledb.properties");
		Properties prop = new Properties();
		prop.load(fr);
		String dbUser = prop.getProperty("id");
		String dbPw = prop.getProperty("pw");
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
		System.out.println(dbUser);
		System.out.println(dbPw);
		//클라우드 테스트
		//jdbc:oracle:thin:@접속문자열TNS?TNS_ADMIN=전자지갑폴더위치
		Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPw);
		
		System.out.println(con);
		return con;
	}
	//getConnection 메서드 디버깅용 테스트 코드
	public static void main(String[] args) throws Exception{
		Connection con = DBHelper.getConnection();
		System.out.println(con);
	}
}
