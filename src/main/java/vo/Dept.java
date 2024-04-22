package vo;
//VO value Object > DTO(Data Transfer Object)>Domain ->컬럼 속성 값의 범위 (숫자만 가질수있다, 날짜만 가질 수 있다 등등) -> 왼쪽으로 범위가 커진다.

import java.util.HashMap;

public class Dept {
	private int deptNo;
	private String dname;
	private String loc;
	private HashMap<String,Object> onoff;
	
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public HashMap<String, Object> getOnoff() {
		return onoff;
	}
	public void setOnoff(HashMap<String, Object> onoff) {
		this.onoff = onoff;
	}
}
