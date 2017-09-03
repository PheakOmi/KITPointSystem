
<%@ page language="java" import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>
             
	ID:<input type="text" id="user_id1"/><br>							     
	<button onclick='delete_();'>Submit</button>    <!-- When Click, it will call the below function(submit = function()) -->	   	   	   
	  
			   
<script type="text/javascript">
			
	data = "";	
	delete_ = function(){		 
		 $.ajax({
			url:'delete',
			type:'POST',
			data:{user_id:$("#user_id1").val()},
			success: function(response){
					alert(response.message);
			}				
		});
}
		
 </script>
	
</body>
</html>