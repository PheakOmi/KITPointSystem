<body onload="load();">
<script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'ProjectNUser',
			type:'GET',
			success: function(response){
				
				project = response.project;
				for(i=0; i<project.length; i++)					
					$("#project").append("<option value="+project[i].id+">"+project[i].project_name+" </option>");
				
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
		$("#myForm").on('submit',function(e){
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
			swal("Oops!", "Only Integer Accepted in Planning Hour", "error")  
			return
			}
			var deadline = Date.parse($("#deadline").val());
            var startdate = Date.parse($("#startdate").val());
            var enddate = Date.parse($("#enddate").val());
            if(startdate>enddate)
        		swal("Oops!", "Your End Date is before Start Date", "error")
        	else if(startdate>deadline)
               	swal("Oops!", "Your Deadline is before Start Date", "error")
			else {
					$.ajax({
             		url:'saveTask',
             		type:'GET',
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
    					setTimeout(function() {
    				        swal({
    				            title: "Done!",
    				            text: "You have created it successfully!",
    				            type: "success"
    				        }, function() {
    				            window.location = "task";
    				        });
    				    }, 10);
    					
    					}
    				//var obj = jQuery.parseJSON(response);
    				    
    				else 
    					{
    					swal("Oops!", response.message, "error")
    					
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
<form id="myForm">
<div class="wrapper">
 <div class="row">
                 
                    <div class="col-lg-6">

                            <div class="form-group">
                                <label>Task Name</label>
                                <input class="form-control" id="name" maxlength="30" type="text" required>
                        	</div>
                            <div class="form-group">
                                <label>Project: </label>
                                <select class="form-control" id="project" required>
                                    <option></option>
                                </select>
                        	</div>
                            <div class="form-group">
                                <label>Assign to:</label>
                                <select class="form-control" id="user">
                                   
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Planning Hour</label>
                                <input class="form-control" maxlength="4" id="time" type="text" required>
                        	</div>
    						<div class="form-group">
                                <label>Description</label>
                                <input class="form-control" id="description" type="text" required>
                        	</div>
                        </div>
                           
                         <div class="col-lg-6">
                           
                            <div class="form-group col-lg-6">
                                <label class="control-label" for="date">Start Date</label>
                                  <input class="form-control" id="startdate" name="date" placeholder="MM/DD/YYY" type="text" />
                            </div>
    					 <div class="form-group col-lg-6">
                                <label class="control-label" for="date">End Date</label>
                              <input class="form-control" id="enddate" name="date" placeholder="MM/DD/YYY" type="text" />
                            </div>
                                 <div class="form-group col-lg-6">
                                <label class="control-label" for="date">Deadline</label>
                                <input class="form-control" id="deadline" name="date" placeholder="MM/DD/YYY" type="text" />
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
<button type="submit" class="btn btn-default">Create</button>
<button onclick="location.href = 'task';" class="btn btn-default">Cancel</button>
</form>
<script type="text/javascript">
$('#project').on('change', function() {
	var projectid = this.value;
	$.ajax({
		url:'studentInTask',
		type:'GET',
		data: {id: projectid},
		success: function(response){
		console.log(response);
		student=response.student;
		$('#user')
	    .find('option')
	    .remove()
	    .end();

		for(i=0; i<student.length; i++)
			$("#user").append("<option value="+student[i].user_id+">"+student[i].user_name+" </option>");
		
		},
		error: function (err)
		{
			console.log(JSON.stringify(err));
		}
	});	  
	})
</script>
</body>
