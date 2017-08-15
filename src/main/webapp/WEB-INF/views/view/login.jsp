<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<body>
<h1 align="center">${message}</h1>
  <div class="login-page">
  <div class="form">
    <form class="login-form" id="myForm" action="validate" method="post">
      <input type="email" placeholder="Email" name="email" required/>
      <input type="password" placeholder="Password" name="password" />
      <input type="submit" class="a" value="Login">
    </form>
  </div>
</div>
</body>
</html>
