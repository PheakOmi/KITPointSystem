<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
     <link rel="stylesheet" type="text/css" href="CSS/Login.css">  
    </head>
    <body>  
        <form action="submit.html" method="post">
        
        <label for="txtID">ID:</label>
        <input type="text" name="id" /> <br/>
        
         <label for="txtName">Name:</label>
         <input type="text" name="name" /><br/>
         
         <label for="txtSalary">Salary:</label>
         <input type="text" name="salary" /><br/>
         
         <input type="submit" value="Submit" />
        </form>       
    </body>
</html>