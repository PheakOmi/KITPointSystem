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
					if(user[i].user_type=="User")
					$("#user").append("<option value="+user[i].id+">"+user[i].name+" </option>");
				}
			},
		error: function(err){
			console.log("KKKKKKK");
			console.log(JSON.stringify(err));
			}
			
		});
		
	}
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
             					swal("Success!", "You have created it successfully!", "success")
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
<form id="myForm">
<div class="wrapper">
 <div class="row">
                 
                    <div class="col-lg-6">

                            <div class="form-group">
                                <label>Task Name</label>
                                <input class="form-control" id="name" type="text" required>
                        	</div>
                            <div class="form-group">
                                <label>Project: </label>
                                <select class="form-control" id="project" type="text" required>
                                    
                                </select>
                        	</div>
                            <div class="form-group">
                                <label>Assign to:</label>
                                <select class="form-control" id="user">
                                    
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Planning Hour</label>
                                <input class="form-control" id="time" type="text" required>
                        	</div>
    						<div class="form-group">
                                <label>Description</label>
                                <input class="form-control" id="description" type="text" required>
                        	</div>
                        </div>
                           
                         <div class="col-lg-6">
                           
                            <div class="form-group col-lg-6">
                                <label class="control-label" for="date">Start Date</label>
                                  <input class="form-control" id="startdate" name="date" placeholder="MM/DD/YYY" type="text" required/>
                            </div>
    					 <div class="form-group col-lg-6">
                                <label class="control-label" for="date">End Date</label>
                              <input class="form-control" id="enddate" name="date" placeholder="MM/DD/YYY" type="text" required/>
                            </div>
                                 <div class="form-group col-lg-6">
                                <label class="control-label" for="date">Deadline</label>
                                <input class="form-control" id="deadline" name="date" placeholder="MM/DD/YYY" type="text" required/>
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
</form>
</body>
