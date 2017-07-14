<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head> 	
<title>Project</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
<spring:url value="/resources/Bootstrap/css/checkbox/build.css" var="checkboxStyle"/>
      <link rel="stylesheet" href="${checkboxStyle}">
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
</head>
<body onload="load();">
<script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'userNProjectCategoryList?id=0',
			type:'POST',
			success: function(response){
				console.log(response);
				category = response.category;
				user = response.user;
<%--				stage = response.stage;	--%>
				for(i=0; i<category.length; i++)					
					$("#projectcategory").append("<option value="+category[i].id+">"+category[i].name+" </option>");
<%--				for (i = 0; i < stage.length; i++) {
    			var checkBox = $('<input class="checkbox" type="checkbox" value="'+stage[i].id+'"><label for="checkbox">'+stage[i].stage_name+'</label><br />');
    			checkBox.appendTo('#stage');
    
}	--%>
				for(i=0; i<user.length; i++){
					if(user[i].user_type=="t")
					$("#projectcoordinator").append("<option value="+user[i].id+">"+user[i].name+" </option>");
					else if(user[i].user_type=="s")
					$("#teamleader").append("<option value="+user[i].id+">"+user[i].name+" </option>");
				}
			},
		error: function(err){
			console.log("KKKKKKK");
			console.log(JSON.stringify(err));
			}
			
		});
		
	}
</script>		
<form id="myForm">
 			<div class="row">
                 <div class="form-horizontal">
                    <div class="col-sm-6">

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Project Name</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="project_name" required>
                                </div>
                        </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Project Category</label>
	                            <div class="col-sm-8">
	                                <select class="form-control" id="projectcategory">
	           
	                                </select>
	                            </div>
                            </div>
    					 <div class="form-group">
                                <label class="col-sm-4 control-label">Project Coordinator</label>
	                            <div class="col-sm-8">    
	                                <select class="form-control" id="projectcoordinator">
	                                    
	                                </select>
	                            </div>
                            </div>
                            
                           <div class="form-group">
                                <label class="col-sm-4 control-label">Team Leader</label>
	                            <div class="col-sm-8">    
	                                <select class="form-control" id="teamleader">
	                                    
	                                </select>
	                            </div>
                            </div>                                 
                            
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Planning Hours</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="planninghour" required>
                                </div>
                        </div>
                           
                  		  </div>
                  		    
                         <div class="col-sm-6">
                            <div class="form-group ">
                                <label class="col-sm-4 control-label">Project code</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" id="projectcode" required>
                                </div>
                        </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Start Date</label>
                                <div class="col-sm-8">
                                	<input class="form-control" id="startdate"  name="date" placeholder="MM/DD/YYY" type="text"/>
                                </div>
                            </div>
    					 <div class="form-group">
                                <label class="col-sm-4 control-label">End Date</label>
                              	<div class="col-sm-8">
                              		<input class="form-control" id="enddate"  name="date" placeholder="MM/DD/YYY" type="text"/>
                              	</div>
                            </div>
                                 <div class="form-group ">
                                <label class="col-sm-4 control-label">Deadline</label>
                                <div class="col-sm-8">
                                	<input class="form-control" name="date" id="deadline" placeholder="MM/DD/YYY" type="text"/>
                                </div>
                        </div>
<%--                                  <div class="form-group">
                                <label class="col-sm-4 control-label">Project Stage</label>
	                            <div class="col-sm-8">
	                            
	                            <div id="stage" class="checkbox checkbox-primary">
	                   					
	                            </div>
	                            </div>
                            </div>		--%>
                            
                              <div class="form-group">
                                <label class="col-sm-4 control-label">Skill Set</label>
                                <div class="col-sm-8">
                                	<input class="form-control" id="skillset">
                                </div>
                            </div> 
                             <div class="form-group">
                                <label class="col-sm-4 control-label">KIT point</label>
                                <div class="col-sm-8">	
                                	<input class="form-control" id="kitpoint">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Budget</label>
                                <div class="col-sm-8">	
                                	<input type="text" class="form-control" id="budget" required>
                                </div>
                            </div>
                          	 <div class="ol-sm-offset-2 col-sm-10">	
			                   <button type="submit" id="btnSubmit" class="btn btn-default">Create</button>
			                   <button type="reset" class="btn btn-default">Cancel</button>
	                    </div>
                  		  </div>
                  	
	                    </div>
                    </div>
     </form>              
                    <script>
                    $(document).ready(function(){
                    	var date_input=$('input[name="date"]');
                        var options={
                          format: 'mm/dd/yyyy',
                          todayHighlight: true,
                          autoclose: true,
                        };
                        date_input.datepicker(options);
                    	$("#myForm").on('submit',function(e){
                    		e.preventDefault();
                        if($("#myForm").validate())
            			{
                        	$.ajax({
                        		url:'saveProject',
                        		type:'POST',
                        		data:{		project_name:$("#project_name").val(),
                        					project_code:$("#projectcode").val(),
                        					project_type:$("#projectcategory").val(),
                        					project_co:$("#projectcoordinator").val(),
                        					project_leader:$("#teamleader").val(),
                        					initially_planned:$("#planninghour").val(),
                        					budget:$("#budget").val(),
                        					skillset:$("#skillset").val(),
                        					kit_point:$("#kitpoint").val(),
                        					deadline:$("#deadline").val(),
                        					start_date:$("#startdate").val(),
                        					end_date:$("#enddate").val()
                        				<%--stage:val,--%>},
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
            			}
                    	});	
            	});
                    	<%-- $("#btnSubmit").click(function(){		 
                   		var val = [];
                            $('.checkbox:checked').each(function(i){
                              val[i] = $(this).val();
                            });		
                            console.log("Name is: "+$("#project_name").val());	
                            
                    				
                    	
                    	});
                    });	 --%>
					</script>
                    </body>
                    </html>
                    
			