<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<body onload="load();">
<script type="text/javascript">
	load = function(){
		var id = ${id};
		$.ajax({
			url:'ProjectNUser',
			type:'GET',
			data: {id: id},
			success: function(response){
				console.log(response)
				member = response.member;
				currenttask =response.currenttask;
				project = response.project;
				function formatDate(date) {
				    var d = new Date(date),
				        month = '' + (d.getMonth() + 1),
				        day = '' + d.getDate(),
				        year = d.getFullYear();

				    if (month.length < 2) month = '0' + month;
				    if (day.length < 2) day = '0' + day;

				    return [month, day, year].join('/');
				};
				if (currenttask.start_date==null)
	            	$("#startdate").val("");
				else
					currenttask.start_date=formatDate(currenttask.start_date);
				if (currenttask.end_date==null)
	            	$("#enddate").val("");
				else
					currenttask.end_date=formatDate(currenttask.end_date);
				if (currenttask.deadline==null)
	            	$("#deadline").val("");
				else
					currenttask.deadline=formatDate(currenttask.deadline);
	           
				for(i=0; i<member.length; i++)
					$("#user").append("<option value="+member[i].user_id+">"+member[i].user_name+" </option>");
				$("#project").append("<option value="+project.id+">"+project.project_name+" </option>");
				$("#project").attr("disabled", "disabled");
				$("#name").val(currenttask.name);
				$("#user").val(currenttask.assigned_to);
				$("#description").val(currenttask.description);
				$("#status").val(currenttask.status);
				$("#time").val(currenttask.time_spend);
				$("#deadline").val(currenttask.deadline);
				$("#startdate").val(currenttask.start_date);
				$("#enddate").val(currenttask.end_date);
			},
		error: function(err){
			
			console.log(JSON.stringify(err));
			}
			
		});
		
	}
	
	$(document).ready(function(){
		$("[name=date]").keydown(function (event) {
		    event.preventDefault();
		});
		$('li#taskStlye').addClass('active');
    	$("#myForm").on("submit",function(e){    
    		e.preventDefault();
    		var name = $("#name").val().trim();
			var time = $("#time").val().trim();
			var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
			var formats = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]+/;
			if((name=='')||(time==''))
				{
				swal("Oops!", "The input cannot be empty", "error")
				return
				}
			if(format.test(name))
				{
				swal("Oops!", "You cannot input special characters", "error")  
				return
				}
			if(format.test(time))
			{
			swal("Oops!", "You can only input number", "error")  
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
    		url:'updateTask',
    		type:'GET',
    		data:{		id:id,
    					project_id:$("#project").val(),
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
				setTimeout(function() {
			        swal({
			            title: "Done!",
			            text: "You have updated it successfully!",
			            type: "success"
			        }, function() {
			            window.location = "task";
			        });
			    }, 10);
				
				}
    				//var obj = jQuery.parseJSON(response);
    				    
				else 
 					swal("Oops!", response.message, "error")    
				
				},
    		error: function(err){
    				console.log(JSON.stringify(err));
    				
    				}
    		
    			});			
            }
    	});
    });	
</script>		
<form role="form" id="myForm">
<div class="wrapper">
 <div class="row">     
                    <div class="col-lg-6">

                            <div class="form-group">
                                <label>Task Name</label>
                                <input class="form-control" id="name" type="text" required>
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
                                <input class="form-control" id="time" maxlength="4" type="text" required>
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
                   
             
	                                        
        </div>            

</div>
<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_N_ADMIN')">
<button type="submit" class="btn btn-default">Update</button>
<button onclick="location.href = 'task';" class="btn btn-default">Cancel</button>      
   </sec:authorize>
</form>
</body>