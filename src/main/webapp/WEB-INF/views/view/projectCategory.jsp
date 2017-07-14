<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
<spring:url value="/resources/Bootstrap/css/sweetalert.css"
	var="alertStyle" />
<link rel="stylesheet" href="${alertStyle}">
<spring:url value="/resources/Bootstrap/js/sweetalert.min.js"
	var="alertJS" />
<script src="${alertJS}"></script>
</head>
<body>
	<div class="row" id="margin-body">
		<form id="myForm">
			<div class="form-horizontal">
				<div class="col-sm-10">

					<div class="form-group">
						<label class="col-sm-2 control-label">Category Name</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="name" id="name1"
								required>
						</div>
					</div>
					<div class="col-sm-offset-2 col-sm-10">
						<button id="btnSubmit" type="submit" class="btn btn-default">Create</button>
						<button type="reset" class="btn btn-default">Cancel</button>
					</div>
				</div>

			</div>
		</form>
	</div>
	<script type="text/javascript">
$(document).ready(function(){
	$("#myForm").on("submit",function(e){
		e.preventDefault();
		if($("#myForm").validate())
		{
			$.ajax({
				url:'projectCategoryCreate',
				type:'POST',
				data:{name:$("#name1").val()},
				success: function(response){
					if(response.status=="200")
						{
						$('#name1').val('');
						swal("Good job!", "You clicked the button!", "success")
						}
					//var obj = jQuery.parseJSON(response);
					    
					else 
						{
						$('#name1').val('');
						swal("Oops!", "Category name already existed", "error")
						
						}
						
				},
				error: function(err){
					console.log(JSON.stringify(err));
				}
			});
		}
				
	});
});    
                    </script>
</body>
</html>