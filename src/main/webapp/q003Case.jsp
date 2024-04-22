<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%

	ArrayList<HashMap<String,String>> selectJobList = EmpDAO.selectJobCaseList();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
	<tr>
		<th>ename</th>
		<th>job</th>
		<th>color</th>
		</tr>
		
		<tr>
	
	<%
		for(HashMap<String,String> m :selectJobList){
	%>	
		<td><%=m.get("ename") %></td>
		<td><%=m.get("job") %></td>
		<td><%=m.get("color") %></td>
		</tr>
	<% 	
		}
	%>
	
	
	
	
	</table>
</body>
</html>