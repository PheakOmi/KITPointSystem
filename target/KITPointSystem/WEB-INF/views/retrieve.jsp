<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
     <link rel="stylesheet" type="text/css" href="CSS/Login.css">  
    </head>
    <h2>${headerMsg}</h2>
    <body>  
        <form action="result.html" method="post">
        
        <label for="txtID">ID:</label>
        <input type="text" name="id" /> <br/>
        
         
         <input type="submit" value="Retrieve" />
        </form>       
    </body>
</html>