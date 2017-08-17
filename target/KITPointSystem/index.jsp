<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Flat HTML5/CSS3 Login Form</title>
  
  
  <spring:url value="/resources/Bootstrap/css/style.css" var="loginStyle"/>
      <link rel="stylesheet" href="${loginStyle}">
 
</head>

<body>
<h1>${message}</h1>
  <div class="login-page">
  <div class="form">
    <form class="login-form" action="validate" method="post">
      <input type="text" placeholder="Email" name="email" />
      <input type="password" placeholder="Password" name="password" />
      <input type="submit" class="a" value="Login">
    </form>
  </div>
</div>
</body>
</html>
