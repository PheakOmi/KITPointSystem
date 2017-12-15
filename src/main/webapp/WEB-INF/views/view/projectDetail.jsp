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
				skillset = response.skillset;
				
				for(i=0; i<category.length; i++)					
					$("#projectcategory").append("<option value="+category[i].id+">"+category[i].name+" </option>");
				for(i=0; i<user.length; i++)
					$("#projectcoordinator").append("<option value="+user[i].id+">"+user[i].name+" </option>");
				for(i=0; i<student.length; i++)
					$("#teamleader").append("<option value="+student[i].id+">"+student[i].name+" </option>");
		$('#member').select2({data: student});
		$('#skillset').select2({data: skillset});
		
			
			},
		error: function(err){
			swal("Oops!", "Connection timed out!", "error")
			console.log(JSON.stringify(err));
			}
			
		});
		
	}
	
	$(document).ready(function(){
		$('input[type=radio][name=auto]').change(function() {
	        if (this.value == 'no') {
	        	$("#kitpoint").prop('disabled', false);
	        	
	            
	        }
	        else {
	        	$("#kitpoint").prop('disabled', true);
	        	
	        }


	    });
		document.querySelector("#planninghour").addEventListener("keypress", function (evt) {
	        if (evt.which != 8 && evt.which != 0 && evt.which < 48 || evt.which > 57)
	        {
	            evt.preventDefault();
	        }
	    });
		document.querySelector("#kitpoint").addEventListener("keypress", function (evt) {
	        if (evt.which != 8 && evt.which != 0 && evt.which < 48 || evt.which > 57)
	        {
	            evt.preventDefault();
	        }
	    });
		document.querySelector("#budget").addEventListener("keypress", function (evt) {
	        if (evt.which != 8 && evt.which != 0 && evt.which < 48 || evt.which > 57)
	        {
	            evt.preventDefault();
	        }
	    });
		
		$('#teamleader').on('change', function() {
			$('#member').select2({data: student});	
			$('#member').children("option[value=" + this.value + "]").remove();	
			})
		$("[name=date]").keydown(function (event) {
		    event.preventDefault();
		});

		
		$('li#projectStlye').addClass('active');
    	$("#myForm").on('submit',function(e){
    		e.preventDefault();
    		var member = $('#member').val(); 
    		member.push($('#teamleader').val());
    		var skillset = $("#skillset").val();
    		var point = $("#kitpoint").val();
    		if(point==""||point==null)
    			point=0;
    		var budget = $("#budget").val();
    		if(budget==""||budget==null)
    			budget=0;
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
    			swal("Oops!", "You cannot input special characters for name and code", "error")  
    			return
    			}
    		if((format.test(projectname)) || (format.test(projectcode)))
			{
			swal("Oops!", "You cannot input special characters for name and code", "error")  
			return
			}
    		if(getlength(planninghour)>4)
    		{
    			swal("Oops!", "Planning Hour Cannot Be More Than 4 Digits", "error")  
    			return
    		}
    		if(getlength(point)>3)
    		{
    			swal("Oops!", "Point Cannot Be More Than 3 Digits", "error")  
    			return
    		}
    		if(getlength(budget)>7)
    		{
    			swal("Oops!", "Budget Cannot Be More Than 7 Digits", "error")  
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
    		var auto;
    		var check = $("input[name='auto']:checked").val();
    		if (check=="yes")
    			auto=true;
    		else 
    			auto=false;
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
        					auto:auto,
        					skill:skillset,
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
    	
    	
    	$("#myForm2").on("submit",function(e){
    		e.preventDefault();
    		var a = $("#sname").val().trim();
    		var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
    		if(a=='')
    			{
    			swal("Oops!", "The input cannot be empty", "error")
    			return
    			}
    		if(format.test(a))
    			{
    			swal("Oops!", "You cannot input special characters", "error")  
    			return
    			}
    			$.ajax({
    				url:'skillsetCreate',
    				type:'GET',
    				data:{name:$("#sname").val()},
    				success: function(response){
    					if(response.status=="200")
    						{
    						
    						$.ajax({
										url:'getSkillset',
										type:'GET',
										success: function(response){
											$('#skillset').select2({data: response.skill});
											$("#sname").val("");
										}				
									});
    						
    						
    						$('#closing').trigger('click');
    						}
    					//var obj = jQuery.parseJSON(response);
    					    
    					else 
    						{
    						swal("Oops!", "Skill Set already existed", "error")
    						
    						}
    						
    				},
    				error: function(err){
    					console.log(JSON.stringify(err));
    				}
    			});			
    	});
    	
});	
	goTO = function(){
		$('#bsubmit').trigger('click');
	}
	
	function getlength(number) {
	    return number.toString().length;
	}
    
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
                                 <div class="form-group">
                                <label class="col-sm-4 control-label">Auto Point Calculation?	</label>
	                            <div class="col-sm-8">
	                            
	                            <div  style="margin: 0cm 2cm 0cm 0.5cm;">
	                   					 <label class="radio-inline">
									      <input type="radio" name="auto" value="yes" checked>Yes
									    </label>
									    <label class="radio-inline">
									      <input type="radio" name="auto" value="no">No	
									    </label>
    
	                            </div>
	                            </div>
                            </div>		
                            
                              
                             <div class="form-group">
                                <label class="col-sm-4 control-label">KIT point</label>
                                <div class="col-sm-8">	
                                	<input class="form-control" id="kitpoint"  disabled maxlength="3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Budget</label>
                                <div class="col-sm-8">	
                                	<input type="text" class="form-control" maxlength="7" id="budget">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Skill Set	</label>
	                            <div class="col-sm-8">    
	                                <select class="js-example-basic-multiple form-control" id="skillset" multiple="multiple">
									
									</select>
	                            </div>
                            </div>    
                            <div style="margin:1% 0cm 10% 80%;"	>
                            <a href="#" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">
          <span class="glyphicon glyphicon-new-window" ></span> Create a skill set</a>
                           
                            </div>
                            <div id ="el" style="display: inline-block;margin:-7% 10% 0% 40%;">
                                <button type="submit" id="btnSubmit" class="btn btn-default">Create</button>
			                   <button onclick="location.href = 'project';" class="btn btn-default">Cancel</button>
                            </div> 
                          	
                  		  </div>
                  	
	                    </div>
                    </div>
     </form>      
     
           
 
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Create a skill set</h4>
        </div>
        <div class="modal-body">
     <form class="form-group" id="myForm2">
    <div>
      <label class="col-sm-4" for="email">Skill Set Name:</label>
      <div class="col-sm-7">
      <input type="text" class="form-control" id="sname" maxlength="60" placeholder="Enter Name" required>
      <button id="bsubmit" type="submit" class="btn btn-default" style="display:none;">Create</button>
      </div>
    </div>
  </form>
  <br>
        </div>
        <div class="modal-footer">
          <button onClick="goTO()" class="btn btn-default">Create</button>
          <button type="button" id="closing" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>		 
                    </body>                   
			