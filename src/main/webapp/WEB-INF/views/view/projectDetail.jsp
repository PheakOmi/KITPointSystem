<body onload="load()">
<script type="text/javascript">
	var student;
	load = function(){	
		$.ajax({
			url:'userNProjectCategoryList?id=0',
			type:'GET',
			success: function(response){
				console.log(response);
				category = response.category;
				user = response.user;
				student = response.student;
				
				for(i=0; i<category.length; i++)					
					$("#projectcategory").append("<option value="+category[i].id+">"+category[i].name+" </option>");
				for(i=0; i<user.length; i++)
					$("#projectcoordinator").append("<option value="+user[i].id+">"+user[i].name+" </option>");
				for(i=0; i<student.length; i++)
					$("#teamleader").append("<option value="+student[i].id+">"+student[i].name+" </option>");
		$('#member').select2({data: student});					
			
			},
		error: function(err){
			swal("Oops!", "Connection timed out!", "error")
			console.log(JSON.stringify(err));
			}
			
		});
		
	}
	
	$(document).ready(function(){
		
		$('#teamleader').on('change', function() {
			$('#member').select2({data: student});	
			$('#member').children("option[value=" + this.value + "]").remove();	
			})
		$("[name=date]").keydown(function (event) {
		    event.preventDefault();
		});

		
		$('li#projectStlye').addClass('active');
    	$("#myForm").on('submit',function(e){
    		
    		var member = $('#member').val(); 
    		member.push($('#teamleader').val());
    		
    		e.preventDefault();
    		var projectname = $("#project_name").val().trim();
    		var projectcode = $("#projectcode").val().trim();
    		var planninghour = $("#planninghour").val().trim();
    		//var skillset = $("#skillset").val().trim();
    		var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
    		var formats = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]+/;
    		if((projectname=='') || (projectcode=='')||(planninghour==''))
    			{
    			swal("Oops!", "The input cannot be empty", "error")
    			return
    			}
    		if((format.test(projectname)) || (format.test(projectcode)))
    			{
    			swal("Oops!", "You cannot input special characters", "error")  
    			return
    			}
    		if((format.test(projectname)) || (format.test(projectcode)))
			{
			swal("Oops!", "You cannot input special characters", "error")  
			return
			}
    		if(formats.test(planninghour))
    			{
    			swal("Oops!", "You can only input number", "error")  
    			return
    			}
    		var deadline = Date.parse($("#deadline").val());
            var startdate = Date.parse($("#startdate").val());
            var enddate = Date.parse($("#enddate").val());
            if(startdate>enddate)
        		swal("Oops!", "Your End Date is before Start Date", "error")
        	else if(startdate>deadline)
               	swal("Oops!", "Your Deadline is before Start Date", "error")
    		else{
        	$.ajax({
        		url:'saveProject',
        		type:'GET',
        		data:{		status:$("#status").val(),
        					project_name:$("#project_name").val(),
        					project_code:$("#projectcode").val(),
        					project_type:$("#projectcategory").val(),
        					project_co:$("#projectcoordinator").val(),
        					project_leader:$("#teamleader").val(),
        					initially_planned:$("#planninghour").val(),
        					budget:0,
        					kit_point:$("#kitpoint").val(),
        					deadline:$("#deadline").val(),
        					start_date:$("#startdate").val(),
        					end_date:$("#enddate").val(),
        					member:member,},
        		traditional: true,			
        		success: function(response){
        				if(response.status=="200")
        					{
        					setTimeout(function() {
        				        swal({
        				            title: "Done!",
        				            text: "You have created it successfully!",
        				            type: "success"
        				        }, function() {
        				            window.location = "project";
        				        });
        				    }, 10);
        					
        					}
        				//var obj = jQuery.parseJSON(response);
        				else if(response.status=="555")
        					swal("Oops!",response.message, "error")
        				else if(response.status=="888")
        					swal("Oops!",response.message, "error")
        				else 
         					swal("Oops!", "Project Name already existed!", "error")    
        				
        				},
        		error: function(err){
        				console.log(JSON.stringify(err));
        				
        				}
        		
        			});	
    		}
    	});		
});	
    
</script>
<form id="myForm">
 			<div class="row">
                 <div class="form-horizontal">
                    <div class="col-sm-6">	

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Project Name</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" maxlength="30" id="project_name" required>
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
	                                <select class="form-control" id="teamleader" required >
	                                    <option></option>
	                                </select>
	                            </div>
                            </div>
                            
                                                          
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Project Member</label>
	                            <div class="col-sm-8">    
	                                <select class="js-example-basic-multiple form-control" id="member" multiple="multiple" required>
										  
									</select>
	                            </div>
                            </div>    
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Planning Hours</label>
                                <div class="col-sm-8">
                                	<input type="text" maxlength="4" class="form-control" id="planninghour" required>
                                </div>
                        </div>
                         <div class="form-group">
                                <label class="col-sm-4 control-label">Status</label>
	                            <div class="col-sm-8">    
	                                <select class="form-control" id="status">
	                                   <option value="Approved Project">Approved Project</option>
                                    	<option value="To approve Project">To approve Project</option>
                                    	<option value="Pending Project">Pending Project</option>
                                    	<option value="Completed Project">Completed Project</option> 
	                                </select>
	                            </div>
                            </div>       
                              <div class="form-group">
                                <button type="submit" id="btnSubmit" class="btn btn-default">Create</button>
			                   <button onclick="location.href = 'project';" class="btn btn-default">Cancel</button>
                            </div> 
                  		  </div>
                  		    
                         <div class="col-sm-6">
                            <div class="form-group ">
                                <label class="col-sm-4 control-label">Project code</label>
                                <div class="col-sm-8">
                                	<input type="text" class="form-control" maxlength="15" id="projectcode" required>
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
                                <label class="col-sm-4 control-label">KIT point</label>
                                <div class="col-sm-8">	
                                	<input class="form-control" id="kitpoint"  disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Budget</label>
                                <div class="col-sm-8">	
                                	<input type="text" class="form-control" maxlength="7" id="budget" disabled>
                                </div>
                            </div>
                            
                          	
                  		  </div>
                  	
	                    </div>
                    </div>
     </form>            


                    </body>                   
			