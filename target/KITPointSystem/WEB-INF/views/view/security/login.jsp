<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KIT Point</title>
  <!-- Login CSS -->
<spring:url value="/resources/Bootstrap/css/style.css" var="loginStyle"/>
      <link rel="stylesheet" href="${loginStyle}">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script> 
<script>
$(document).ready(function(){
	$("#myForm").validate();
});	
</script>
  </head>
<body onload='document.loginForm.username.focus();'>
<h1 align="center">${message}</h1>
  <div class="login-page">
  <div class="form">
  	<h1>KIT Point Management</h1>
  
    <form class="login-form" id="myForm" action="<c:url value='/login' />" method="post">
    	<c:if test="${not empty error}">
			<div class="error" style="color:red">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
     	<input type='text'placeholder="Username" name='username' required>
      <input type="password" placeholder="Password" name="password" required/>
      <input type="submit" class="a" value="Login">
	<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

    </form>
  </div>
</div>
</body>
</html>

	