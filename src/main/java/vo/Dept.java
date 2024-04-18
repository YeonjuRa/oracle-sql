package vo;
//VO value Object > DTO(Data Transfer Object)>Domain ->컬럼 속성 값의 범위 (숫자만 가질수있다, 날짜만 가질 수 있다 등등) -> 왼쪽으로 범위가 커진다.

import java.util.HashMap;

public class Dept {
	public int deptNo;
	public String dname;
	public String loc;
	public HashMap<String,Object> onoff;
}
