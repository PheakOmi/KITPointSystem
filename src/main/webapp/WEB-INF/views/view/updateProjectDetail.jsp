<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<body onload="load();">
<script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'userNProjectCategoryList?id=${id}',
			type:'GET',
			success: function(response){
				console.log(response);
				category = response.category;
				user = response.user;
				student = response.student;
				member = response.member;
				currentproject = response.currentproject;
				d=currentproject;
				ee=member;
				skillset = response.skillset;
				currentskill = response.currentskill;
				
				function formatDate(date) {
				    var d = new Date(date),
				        month = '' + (d.getMonth() + 1),
				        day = '' + d.getDate(),
				        year = d.getFullYear();

				    if (month.length < 2) month = '0' + month;
				    if (day.length < 2) day = '0' + day;

				    return [month, day, year].join('/');
				};
				if (currentproject.start_date==null)
	            	$("#startdate").val("");
				else
					currentproject.start_date=formatDate(currentproject.start_date);
				if (currentproject.end_date==null)
	            	$("#enddate").val("");
				else
					currentproject.end_date=formatDate(currentproject.end_date);
				if (currentproject.deadline==null)
	            	$("#deadline").val("");
				else
					currentproject.deadline=formatDate(currentproject.deadline);
	           
				
				$('#member').select2({
					  data: student
					});
				$('#member').val(member).trigger('change');
				$('#skillset').select2({
					  data: skillset
					});
				$('#skillset').val(currentskill).trigger('change');
				
				
				for(i=0; i<category.length; i++)					
					$("#projectcategory").append("<option value="+category[i].id+">"+category[i].name+" </option>");
				for(i=0; i<user.length; i++)
					$("#projectcoordinator").append("<option value="+user[i].id+">"+user[i].name+" </option>");
				for(i=0; i<student.length; i++){
					$("#teamleader").append("<option value="+student[i].id+">"+student[i].name+" </option>");
					//$("#member").append("<option value="+student[i].id+">"+student[i].name+" </option>");
					 
				}
					
					if(currentproject.status=="Completed Project")
					$("#upoint").removeAttr('style');
					$("#status").val(currentproject.status);
					$("#project_name").val(currentproject.project_name);
					$("#projectcode").val(currentproject.project_code);
					$("#projectcategory").val(currentproject.project_type);
					$("#projectcoordinator").val(currentproject.project_co);
					$("#teamleader").val(currentproject.project_leader);
					$("#planninghour").val(currentproject.initially_planned);
					$("#budget").val(currentproject.budget);
					$("#kitpoint").val(currentproject.kit_point);
					$("#deadline").val(currentproject.deadline);
					$("#startdate").val(currentproject.start_date);
					$("#enddate").val(currentproject.end_date);
					$('#member').children("option[value=" + $('#teamleader').val() + "]").remove();
				if(currentproject.status=="Completed Project")
					{
					$("#teamleader").prop('disabled', true);
					$("#planninghour").prop('disabled', true);
					$("#member").prop('disabled', true);
					$("#status").prop('disabled', true);
					}
			},
			
		error: function(err){
			swal("Oops!", "Connection timed out!", "error")
			console.log(JSON.stringify(err));
			}
			
		});
		
	}

	$(document).ready(function(){
		document.querySelector("#planninghour").addEventListener("keypress", function (evt) {
	        if (evt.which != 8 && evt.which != 0 && evt.which < 48 || evt.which > 57)
	        {
	            evt.preventDefault();
	        }
	    });
		$('#teamleader').on('change', function() {
			$('#member').children("option[value=" + this.value + "]").remove();	
			})
		$("[name=date]").keydown(function (event) {
		    event.preventDefault();
		});

		$('li#projectStlye').addClass('active');  	 
;
    	var date_input=$('input[name="date"]');
        var options={
          format: 'mm/dd/yyyy',
          todayHighlight: true,
          autoclose: true,
        };
        date_input.datepicker(options);
  	 
    	$("#myForm").on("submit",function(e){
    		e.preventDefault();
    		var member = $('#member').val(); 
    		var skillset = $('#skillset').val(); 
    		member.push($('#teamleader').val());
            var projectname = $("#project_name").val().trim();
    		var projectcode = $("#projectcode").val().trim();
    		var planninghour = $("#planninghour").val().trim();
    		//var skillset = $("#skillset").val().trim();
    		var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
    		var formats = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]+/;
    		if(d.status=="Completed Project")
    			{
    				var mm  = member.sort();
    				for (var i=0;i<mm.length;i++)
    					mm[i]=parseInt(mm[i]);
    				if($("#teamleader").val()!=d.project_leader)
    					{
    					swal("Project already completed!", "You cannot change project leader", "error")
    					return
    					}
    				if(JSON.stringify(ee.sort())!=JSON.stringify(mm))
    					{
    					console.log(JSON.stringify(ee.sort()))
    					console.log(JSON.stringify(mm))
    					swal("Project already completed!", "You cannot change project member", "error")
    					return
    					}
    				if($("#status").val()!=d.status)
    					{
    					swal("Project already completed!", "You cannot change project status", "error")
    					return
    					}
    				if($("#planninghour").val()!=d.initially_planned)
    					{
    					swal("Project already completed!", "You cannot change planning hour", "error")
    					return
    					}
    				
    			}
    		if((projectname==''))
			{
			swal("Oops!", "Project name cannot be empty", "error")
			return
			}
		if((projectcode==''))
		{
		swal("Oops!", "Project code cannot be empty", "error")
		return
		}
		if((planninghour==''))
		{
		swal("Oops!", "Planning hour cannot be empty", "error")
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
            id = ${id};
            var deadline = Date.parse($("#deadline").val());
            var startdate = Date.parse($("#startdate").val());
            var enddate = Date.parse($("#enddate").val());
            if(startdate>enddate)
        		swal("Oops!", "Your End Date is before Start Date", "error")
        	else if(startdate>deadline)
               	swal("Oops!", "Your Deadline is before Start Date", "error")
           	else{
	            $.ajax({
	    		url:'updateProject',
	    		type:'GET',
	    		data:{		
	    				id:id,
    					status:$("#status").val(),
    					project_name:$("#project_name").val(),
    					project_code:$("#projectcode").val(),
    					project_type:$("#projectcategory").val(),
    					project_co:$("#projectcoordinator").val(),
    					project_leader:$("#teamleader").val(),
    					initially_planned:$("#planninghour").val(),
    					budget:$("#budget").val(),
    					skill:skillset,
    					member:member,
    					kit_point:$("#kitpoint").val(),
    					deadline:$("#deadline").val(),
    					start_date:$("#startdate").val(),
    					end_date:$("#enddate").val()
    				<%--stage:val,--%>},
    		traditional: true,			
    		success: function(response){
    			if(response.status=="200")
				{
				setTimeout(function() {
			        swal({
			            title: "Done!",
			            text: "You have updated it successfully!",
			            type: "success"
			        }, function() {
			            window.location = "project";
			        });
			    }, 10);
				
				}
    				//var obj = jQuery.parseJSON(response);
    				    
    				else 
    					{
    					swal("Oops!",response.message, "error")
    					
    					}
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
	redirect = function(e, url){
		e.preventDefault();
		location.href=url;
	}
	redirect2 = function(e, url){
		e.preventDefault();
		var id = ${id};
		location.href="updatePointMember?id="+id;
	}
	redirect3 = function(e, url){
		e.preventDefault();
		var id = ${id};
		location.href="additional_hour?id="+id;
	}
	
	goTO = function(){
		$('#bsubmit').trigger('click');
	}
	
	
	
	
</script>	
			<form id="myForm">
 			<div class="row">
                 <div class="form-horizontal">
                    <div class="col-sm-6">

                            <div class="form-group">
                                <label class="col-sm-4 control-label">Project Name</label>
                                <div class="col-sm-8">
                                	<input class="form-control" id="project_name" maxlength="30" type="text" required>
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
                                <label class="col-sm-4 control-label">Project Member</label>
	                            <div class="col-sm-8">    
	                                <select class="js-example-basic-multiple form-control" id="member" multiple="multiple" required>
										  
									</select>
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
                                <label class="col-sm-4 control-label">Planning Hours</label>
                                <div class="col-sm-8">
                                	<input class="form-control" maxlength="4" id="planninghour" type="text" required>
                                </div>
                        </div>
                            <div style="margin:1% 0cm 10% 68%;"	>
                            <a onclick="redirect3(event,'additional_hour')" href="#" class="btn btn-success btn-xs">
          <span class="glyphicon glyphicon-new-window" ></span> View Students' additional hour</a>
                           
                            </div>     
                  		  </div>
                  		    
                         <div class="col-sm-6">
                            <div class="form-group ">
                                <label class="col-sm-4 control-label">Project code</label>
                                <div class="col-sm-8">
                                	<input class="form-control" id="projectcode" maxlength="15">
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
                                	<input class="form-control" id="kitpoint" maxlength="3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Budget</label>
                                <div class="col-sm-8">	
                                	<input class="form-control" id="budget" maxlength="7" type="text" required>
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
          <span class="glyphicon glyphicon-plus" ></span> Create a skill set</a>
                           
                            </div>
                            <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_N_ADMIN')">
                          	 <div style="text-align: center; margin-top: 50px;">	
                          	   
			                   <button type="submit" class="btn btn-default">Update</button>
			                   <button onclick="redirect(event,'project')" class="btn btn-default preventDefault" >Cancel</button>
			                   <button onclick="redirect2(event,'updatePointMember')" class="btn btn-success" style="display:none;margin-left:1cm;" id="upoint">Update Point For Members <span class="glyphicon glyphicon-chevron-right"></span></button>
	                    </div>
	                    </sec:authorize>
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