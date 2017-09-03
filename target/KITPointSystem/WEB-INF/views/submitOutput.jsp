<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<center>
		<h2>${headerMsg}</h2>
		
		<table>
		
		<tr>
		  <td>ID: ${msg.id}</td>
		</tr>
		
		<tr>
		  <td>Name: ${msg.name}</td>
		</tr>
		 
		<tr>
		  <td>Salary: ${msg.salary}</td>
		</tr>
					
		
       </table>
		
	</center>
	
</body>
</html>