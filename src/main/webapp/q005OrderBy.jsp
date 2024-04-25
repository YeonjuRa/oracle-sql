<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%@ page import="java.util.*" %>
<!-- controller layer -->
<!-- mvc 아키텍쳐 -> model view controller -->
<%
	
	//어떤 컬럼 으로 정렬
	String col = request.getParameter("col");
	System.out.println(col+ "<- q005OrderBy .jsp param col");
	//오름 /내림
	String sort = request.getParameter("sort");
	System.out.println(sort+ "<- q005OrderBy .jsp param sort");
	//DAO 호출 하기 -> model 반환
	//-> 모델 => DAO
	ArrayList<Emp> list = EmpDAO.selectEmpListSort(col,sort);
	System.out.println(list.size()+"<-- list size");
	
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- view layer -> 출력  -->
	<table border="1">
		<tr>
			<th>emp No
			<a href="./q005OrderBy.jsp?col=empno&sort=asc">오름</a>
			<a href="./q005OrderBy.jsp?col=empno&sort=desc">내림</a>
			</th>
			<th>ename
			<a href="./q005OrderBy.jsp?col=ename&sort=asc">오름</a>
			<a href="./q005OrderBy.jsp?col=ename&sort=desc">내림</a>
			</th>
		</tr>
		<%
			for(Emp e: list){
		%>	
			<tr>
				<td><%=e.getEmpNo() %></td>
				<td><%=e.getEname() %></td>
			</tr>
		
		
		
		<% 	
			}
		
		%>
		
		
		
		
	</table>
</body>
</html>