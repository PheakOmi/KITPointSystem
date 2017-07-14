 <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
 <spring:url value="/resources/Bootstrap/css/sweetalert.css" var="alertStyle"/>
      <link rel="stylesheet" href="${alertStyle}">
<spring:url value="/resources/Bootstrap/js/sweetalert.min.js" var="alertJS"/>
      <script src="${alertJS}"></script>
</head>



<body onload="load();">
<script type="text/javascript">
load = function(){	
	$.ajax({
		url:'semesterAndBatchList',
		type:'POST',
		success: function(response){
				console.log(response);
				semester = response.semester;
				batch = response.batch;
				for(i=0; i<response.semester.length; i++){					
					$("#semester").append("<option value="+response.semester[i].id+">"+response.semester[i].semester+" </option>");
					
				}
				for(i=0; i<response.batch.length; i++){					
					$("#batch").append("<option value="+response.batch[i].id+">"+response.batch[i].name+" </option>");
					
				}
				$("#batch").change(function(){
					var batchValue = document.getElementById("batch").value;
					for(i=0; i<response.batch.length; i++){
						  if(response.batch[i].id==batchValue)
							  {
							  batchValue = response.batch[i].semester_id;
							  }
					  }
					$("#semester").val(batchValue);
					
				
					
				    
				});

		}				
	});
}
$(document).ready(function(){
	$("#btnSubmit").click(function(){		 
	$.ajax({
		url:'updateBatch',
		type:'POST',
		data:{id:$("#batch").val(),semester_id:$("#semester").val()},
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
					swal("Oops!", "It is not updated!", "error")
					
					}
				},
		error: function(err){
				console.log(JSON.stringify(err));
				}
		
			});			
	
	});
});	
</script>
 <div class="row" id="margin-body">
    
				 <div class="form-horizontal">
				 <div class="col-sm-8">
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Batch</label>
						    <div class="col-sm-10">
						      <select class="form-control" id="batch">
                                 </select>
						    </div>
						  </div>
                            
                          <div class="form-group">
                                <label class="col-sm-2 control-label">Semester</label>
                                <div class="col-sm-10">
                                <select class="form-control" id="semester">
                                 </select>
                                </div>
                            </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button id="btnSubmit" class="btn btn-default">Update</button>
						      <button type="reset" class="btn btn-default">Cancel</button>
						  
						    </div>
						  </div>
						  </div>
						</div>
                    </div>	
          </body>
       </html>
       