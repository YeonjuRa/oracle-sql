package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Emp;

public class EmpDAO {
	//case문으로 job출력
	public static ArrayList<HashMap<String, String>>  selectJobCaseList()throws Exception{
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		Connection conn = DBHelper.getConnection();
		
		
		String sql = "select ename,"
				+ "	 job,"
				+ "	 case"
				+ "	 when job = 'PRESIDENT' then '빨강'"
				+ "	 when job = 'MANAGER' then '주황'"
				+ "	 when job = 'ANALYST' then '노랑'"
				+ "	 when job = 'CLERK' then '초록'"
				+ "	 else '파랑' end color"
				+ "	from emp"
				+ "	order by (case"
				+ "	 when color = '빨강' then 1"
				+ "	 when color = '주황' then 2"
				+ "	 when color = '노랑' then 3"
				+ "	 when color = '초록' then 4"
				+ "	 else 5 end) asc";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, String> m = new HashMap<>();
			m.put("ename", rs.getString("ename"));
			m.put("job", rs.getString("job"));
			m.put("color", rs.getString("color"));
			
			list.add(m);
		}
		return list;
	}
	
	//deptno목록 뒤에 부서별 인원 같이 조회하는 메서드
	public static ArrayList<HashMap<String,Integer>> selectRnumAndDeptnoList() throws Exception{
		 
		ArrayList<HashMap<String,Integer>> list = new  ArrayList<>();
		 Connection con = DBHelper.getConnection();
		 String sql = "select count(*)cnt, deptno deptNo"
		 		+ " from emp"
		 		+ " where deptno is not null"
		 		+ " group by deptno"
		 		+ " order by deptno asc";
		 PreparedStatement stmt = con.prepareStatement(sql);
		 ResultSet rs = stmt.executeQuery();
		 while(rs.next()) {
			 HashMap<String,Integer> m = new HashMap<>();
			 m.put("deptNo",rs.getInt("deptNo"));
			 m.put("cnt",rs.getInt("cnt"));
			 
			 
			 
			 list.add(m);
			 
			 
		 }
		 
		 
		 con.close();
		 
		 return list;
		
	}
	//DEPTNO 목록을 출력하는 메서드
	public static ArrayList<Integer> selectDeptNoList() throws Exception{
		 
		 ArrayList<Integer> list = new  ArrayList<Integer>();
		 Connection con = DBHelper.getConnection();
		 String sql = "SELECT DISTINCT deptno deptNo FROM emp WHERE DEPTNO IS NOT NULL ORDER BY deptno asc";
		 PreparedStatement stmt = con.prepareStatement(sql);
		 ResultSet rs = stmt.executeQuery();
		 while(rs.next()) {
			 Integer i = rs.getInt("deptNo"); //Auto Boxing int -> integer, integer-> int auto Unboxing
			 //래퍼타입과 기본타입간의 
			 list.add(i);
			 
			 
		 }
		 
		 
		 con.close();
		 
		 return list;
		
	}
	// 조인으로 Map을 사용하는 겨우
	public static ArrayList<HashMap<String, Object>> selectEmpAndDeptList()
													throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " emp.empno empNo, emp.ename ename, emp.deptno deptNo,"
				+ " dept.dname dname"
				+ " FROM emp INNER JOIN dept"
				+ " ON emp.deptno = dept.deptno";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("empNo", rs.getInt("empNo"));
			m.put("ename", rs.getString("ename"));
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("dname", rs.getString("dname"));
			list.add(m);
		}
		return list;
	}
	
	// VO 사용
	public static ArrayList<Emp> selectEmpList() throws Exception {
		ArrayList<Emp> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " empno empNo, ename, sal"
				+ " FROM emp";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			e.setEmpNo(rs.getInt("empNo"));
			e.setEname(rs.getString("ename"));
			e.setSal(rs.getDouble("sal"));
			list.add(e);
		}
		
		return list;
	}
	//q004WhereIn.jsp
	//숫자 여러개를 받을수도 있으니 매개변수는 arraylist로 설정
	public static ArrayList<Emp> selectEmpListByGrade(ArrayList<Integer> ckList)throws Exception{
		ArrayList<Emp> list = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		String sql = "SELECT ename,grade FROM emp WHERE grade IN ";
		PreparedStatement stmt = null;
		if(ckList.size()== 1) {
			sql = sql + "(?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
		}else if(ckList.size()== 2) {
			sql = sql + "(?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
		}else if(ckList.size()== 3) {
			sql = sql + "(?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
		}else if(ckList.size()== 4) {
			sql = sql + "(?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
			stmt.setInt(4, ckList.get(3));
		}else if(ckList.size()== 5){
			sql = sql + "(?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
			stmt.setInt(4, ckList.get(3));
			stmt.setInt(5, ckList.get(4));
		}
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			e.setEname(rs.getString("ename"));
			e.setGrade(rs.getInt("grade"));
			list.add(e);
		}
		con.close();
		return list;
	}
	
	//q005OrderBy.jsp
	public static ArrayList<Emp> selectEmpListSort(String col,String sort) throws Exception{
		//매개값 디버깅 
		System.out.println(col + "< -- col from EmpDAO.selectEmpListSort");
		System.out.println(sort + "< -- sort from EmpDAO.selectEmpListSort ");
		ArrayList<Emp> list = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		//매개 값에 따라 쿼리 문자열이 분기되어서 차이가 나는 경우 -> 동적쿼리
		//경우의 수
		//없다 
		//order by ename asc
		//order by ename desc
		//empno asc
		//empno desc
		
		//디버깅후 다음 코드 구현하기
		String sql = "SELECT empno,ename FROM emp";
		//col 과 sort값이 null 값이 아니라면 order by col sort 추가
		if(col != null && sort != null) {
			sql = sql + " ORDER BY " +col+" " + sort;
		}
		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println(stmt);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			e.setEmpNo(rs.getInt("empno"));
			e.setEname(rs.getString("ename"));
			list.add(e);
		}
		//쿼리문이 실행됏었다면 바뀐 리스트 사이즈 디버깅
		System.out.println(list.size());
		
		con.close();
		return list;
	}
		// q006GroupBy.jsp
		public static ArrayList<HashMap<String, Integer>> selectEmpSalStats() 
									throws Exception {
			ArrayList<HashMap<String, Integer>> list 
								= new ArrayList<>();
			Connection conn = DBHelper.getConnection();
			String sql = "SELECT"
					+ " grade"
					+ ", COUNT(*) count"
					+ ", SUM(sal) sum"
					+ ", AVG(sal) avg"
					+ ", MAX(sal) max"
					+ ", MIN(sal) min"
					+ " FROM emp"
					+ " GROUP BY grade"
					+ " ORDER BY grade ASC";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Integer> m = new HashMap<>();
				m.put("grade", rs.getInt("grade"));
				m.put("count", rs.getInt("count"));
				m.put("sum", rs.getInt("sum"));
				m.put("avg", rs.getInt("avg"));
				m.put("max", rs.getInt("max"));
				m.put("min", rs.getInt("min"));
				list.add(m);
			}
			
			conn.close();
			return list;
		}
	}

