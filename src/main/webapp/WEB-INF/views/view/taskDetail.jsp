<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<spring:url value="/resources/Bootstrap/css/sweetalert.css" var="alertStyle"/>
      <link rel="stylesheet" href="${alertStyle}">
<spring:url value="/resources/Bootstrap/js/sweetalert.min.js" var="alertJS"/>
      <script src="${alertJS}"></script>
<spring:url value="/resources/Bootstrap/js/date/jquery.js" var="dateJS"/>
<spring:url value="/resources/Bootstrap/js/date/jquery-ui.js" var="dateJS2"/>
<script src="${dateJS}"></script>
<script src="${dateJS2}"></script>
<spring:url value="/resources/Bootstrap/css/date/jquery-ui.css" var="dateStyle"/>
<link rel="stylesheet" href="${dateStyle}">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="load();">
<script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'ProjectNUser',
			type:'POST',
			success: function(response){
				console.log(response);
				project = response.project;
				user = response.user;
				for(i=0; i<project.length; i++)					
					$("#project").append("<option value="+project[i].id+">"+project[i].project_name+" </option>");
				for(i=0; i<user.length; i++){
					if(user[i].user_type=="t")
					$("#user").append("<option value="+user[i].id+">"+user[i].name+" </option>");
				}
			},
		error: function(err){
			console.log("KKKKKKK");
			console.log(JSON.stringify(err));
			}
			
		});
		
	}
</script>		

<div class="wrapper">
 <div class="row">
                 <form role="form">
                    <div class="col-lg-6">

                            <div class="form-group">
                                <label>Task Name</label>
                                <input class="form-control" id="name">
                        	</div>
                            <div class="form-group">
                                <label>Project: </label>
                                <select class="form-control" id="project">
                                    
                                </select>
                        	</div>
                            <div class="form-group">
                                <label>Assign to:</label>
                                <select class="form-control" id="user">
                                    
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Planning Hour</label>
                                <input class="form-control" id="time">
                        	</div>
    						<div class="form-group">
                                <label>Description</label>
                                <input class="form-control" id="description">
                        	</div>
                                 
                            
                           
                        </div>
                           
                         <div class="col-lg-6">
                           
                            <div class="form-group col-lg-6">
                                <label class="control-label" for="date">Start Date</label>
                                  <input class="form-control" id="startdate" name="date" placeholder="MM/DD/YYY" type="text"/>
                            </div>
    					 <div class="form-group col-lg-6">
                                <label class="control-label" for="date">End Date</label>
                              <input class="form-control" id="enddate" name="date" placeholder="MM/DD/YYY" type="text"/>
                            </div>
                                 <div class="form-group col-lg-6">
                                <label class="control-label" for="date">Deadline</label>
                                <input class="form-control" id="deadline" name="date" placeholder="MM/DD/YYY" type="text"/>
                        </div>
                                 <div class="form-group col-lg-6">
                                <label>Status</label>
                                <select class="form-control" id="status">
                                    <option value="In Progress">In Progress</option>
                                    <option value="Completed">Completed</option>
                                    <option value="Delayed">Delayed</option>
                                    <option value="Postponed">Postponed</option>
                                    
                                </select>
                            </div>                      
                  		  </div>
                   
                    	
	                    </form>
	                    
        </div>            

</div>
<button id="btnSubmit" class="btn btn-default">Save Changes</button>
<script>
                    $(document).ready(function(){
                    	var date_input=$('input[name="date"]');
                        var options={
                          format: 'mm/dd/yyyy',
                          todayHighlight: true,
                          autoclose: true,
                        };
                        date_input.datepicker(options);
                    	$("#btnSubmit").click(function(){                         
                            $.ajax({
                    		url:'saveTask',
                    		type:'POST',
                    		data:{		project_id:$("#project").val(),
                    					name:$("#name").val(),
                    					assigned_to:$("#user").val(),
                    					description:$("#description").val(),
                    					status:$("#status").val(),
                    					time_spend:parseInt($("#time").val()),
                    					deadline:$("#deadline").val(),
                    					start_date:$("#startdate").val(),
                    					end_date:$("#enddate").val(),},
                    		traditional: true,			
                    		success: function(response){
                    				if(response.status=="200")
                    					{
                    					swal("Good job!", "You clicked the button!", "success")
                    					}
                    				//var obj = jQuery.parseJSON(response);
                    				    
                    				else 
                    					{
                    					swal("Oops!", "It is not saved!", "error")
                    					
                    					}
                    				},
                    		error: function(err){
                    				console.log(JSON.stringify(err));
                    				console.log("Hello");
                    				}
                    		
                    			});			
                    	
                    	});
                    });	
					</script>
</body>
</html>