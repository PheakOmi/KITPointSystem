<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>



<body onload="load();">
		
		<table id="table" border=1>
			<tr> <th> Name </th> <th> Salary </th> <th> Edit </th> <th> Delete </th> </tr>	
		</table>
			
	
 <script type="text/javascript">
	
	load = function(){	
		$.ajax({
			url:'list',
			type:'POST',
			success: function(response){
					data = response.data;
					//$('.tr').remove();
					for(i=0; i<response.data.length; i++){					
						$("#table").append("<tr class='tr'> <td> "+response.data[i].user_name+" </td> <td> "+response.data[i].user_salary+" </td> <td> <a href='#' onclick= edit("+i+");> Edit </a>  </td> </td> <td> <a href='#' onclick='delete_("+response.data[i].user_id+");'> Delete </a>  </td> </tr>");
					}			
			}				
		});
		
	}
		
</script>
	
</body>
</html>