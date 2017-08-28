<body onload="load();">
<script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'userNProjectCategoryList?id=${id}',
			type:'POST',
			success: function(response){
				console.log(response);
				category = response.category;
				user = response.user;
				student = response.student;
				currentproject = response.currentproject;
				function formatDate(date) {
				    var d = new Date(date),
				        month = '' + (d.getMonth() + 1),
				        day = '' + d.getDate(),
				        year = d.getFullYear();

				    if (month.length < 2) month = '0' + month;
				    if (day.length < 2) day = '0' + day;

				    return [month, day, year].join('/');
				};
				currentproject.start_date=formatDate(currentproject.start_date);
				currentproject.end_date=formatDate(currentproject.end_date);
				currentproject.deadline=formatDate(currentproject.deadline);
				
<%--				stage = response.stage;	--%>
				for(i=0; i<category.length; i++)					
					$("#projectcategory").append("<option value="+category[i].id+">"+category[i].name+" </option>");
<%--				for (i = 0; i < stage.length; i++) {
    			var checkBox = $('<input class="checkbox" type="checkbox" value="'+stage[i].id+'"><label for="checkbox">'+stage[i].stage_name+'</label><br />');
    			checkBox.appendTo('#stage');
    
}	--%>
for(i=0; i<user.length; i++)
	$("#projectcoordinator").append("<option value="+user[i].id+">"+user[i].name+" </option>");
for(i=0; i<student.length; i++){
				$("#teamleader").append("<option value="+student[i].id+">"+student[i].name+" </option>");
				$("#member").append("<option value="+student[i].id+">"+student[i].name+" </option>");}
				$("#status").val(currentproject.status);
				$("#project_name").val(currentproject.project_name);
				$("#projectcode").val(currentproject.project_code);
				$("#projectcategory").val(currentproject.project_type);
				$("#projectcoordinator").val(currentproject.project_co);
				$("#teamleader").val(currentproject.project_leader);
				$("#planninghour").val(currentproject.initially_planned);
				$("#budget").val(currentproject.budget);
				$("#skillset").val(currentproject.skillset);
				$("#kitpoint").val(currentproject.kit_point);
				$("#deadline").val(currentproject.deadline);
				$("#startdate").val(currentproject.start_date);
				$("#enddate").val(currentproject.end_date);
				
			},
		error: function(err){
			console.log("KKKKKKK");
			console.log(JSON.stringify(err));
			}
			
		});
		
	}

	$(document).ready(function(){
		$("[name=date]").keydown(function (event) {
		    event.preventDefault();
		});

		$('li#projectStlye').addClass('active');  	 
		$("#project_name").keyup(function () {
			if (this.value != this.value.replace(/[^a-zA-Z0-9\ ]/g, '')) {
		         this.value = this.value.replace(/[^a-zA-Z0-9\ ]/g, '');
		      }
			});
			$("#projectcode").keyup(function () {
				if (this.value != this.value.replace(/[^a-zA-Z0-9\ ]/g, '')) {
			         this.value = this.value.replace(/[^a-zA-Z0-9\ ]/g, '');
			      }
				});
			$("#skillset").keyup(function () {
				if (this.value != this.value.replace(/[^a-zA-Z0-9\ ]/g, '')) {
			         this.value = this.value.replace(/[^a-zA-Z0-9\ ]/g, '');
			      }
				});
			
			$("#planninghour").keyup(function () {
				if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
			         this.value = this.value.replace(/[^0-9\.]/g, '');
			      }
				});
			$("#budget").keyup(function () {
				if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
			         this.value = this.value.replace(/[^0-9\.]/g, '');
			      }
				});
		$('li#projectStlye').addClass('active');
    	var date_input=$('input[name="date"]');
        var options={
          format: 'mm/dd/yyyy',
          todayHighlight: true,
          autoclose: true,
        };
        date_input.datepicker(options);
  	 
    	$("#myForm").on("submit",function(e){		 
            e.preventDefault();
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
	    		type:'POST',
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
    				console.log("Hello");
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
                                <label class="col-sm-4 control-label">Planning Hours</label>
                                <div class="col-sm-8">
                                	<input class="form-control" maxlength="4" id="planninghour" type="number" required>
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
                                	<input class="form-control" id="projectcode" maxlength="15">
                                </div>
                        </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Start Date</label>
                                <div class="col-sm-8">
                                	<input class="form-control" id="startdate"  name="date" placeholder="MM/DD/YYY" type="text" required/>
                                </div>
                            </div>
    					 <div class="form-group">
                                <label class="col-sm-4 control-label">End Date</label>
                              	<div class="col-sm-8">
                              		<input class="form-control" id="enddate"  name="date" placeholder="MM/DD/YYY" type="text" required/>
                              	</div>
                            </div>
                                 <div class="form-group ">
                                <label class="col-sm-4 control-label">Deadline</label>
                                <div class="col-sm-8">
                                	<input class="form-control" name="date" id="deadline" placeholder="MM/DD/YYY" type="text" required/>
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
                                	<input class="form-control" id="skillset" type="text">
                                </div>
                            </div> 
                             <div class="form-group">
                                <label class="col-sm-4 control-label">KIT point</label>
                                <div class="col-sm-8">	
                                	<input class="form-control" id="kitpoint" maxlength="3" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Budget</label>
                                <div class="col-sm-8">	
                                	<input class="form-control" id="budget" maxlength="7" type="text" required>
                                </div>
                            </div>
                          	 <div class="ol-sm-offset-2 col-sm-10">	
			                   <button type="submit" class="btn btn-default">Update</button>
			                   <button type="reset" class="btn btn-default">Cancel</button>
	                    </div>
                  		  </div>
	                    </div>                   
                    </div>
                    </form>
                    </body>