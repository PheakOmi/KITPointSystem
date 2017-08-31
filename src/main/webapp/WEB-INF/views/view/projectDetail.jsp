<body onload="load();">
<script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'userNProjectCategoryList?id=0',
			type:'GET',
			success: function(response){
				console.log(response);
				category = response.category;
				user = response.user;
				student = response.student;
<%--				stage = response.stage;	--%>
				for(i=0; i<category.length; i++)					
					$("#projectcategory").append("<option value="+category[i].id+">"+category[i].name+" </option>");
<%--				for (i = 0; i < stage.length; i++) {
    			var checkBox = $('<input class="checkbox" type="checkbox" value="'+stage[i].id+'"><label for="checkbox">'+stage[i].stage_name+'</label><br />');
    			checkBox.appendTo('#stage');
    
}	--%>
for(i=0; i<user.length; i++)
	$("#projectcoordinator").append("<option value="+user[i].id+">"+user[i].name+" </option>");
for(i=0; i<student.length; i++)
	$("#teamleader").append("<option value="+student[i].id+">"+student[i].name+" </option>");
	//$("#member").append("<option value="+student[i].id+">"+student[i].name+" </option>");
$('#member').select2({
	  data: student
	});					
					
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
			if (this.value != this.value.replace(/[^0-9]/g, '')) {
		         this.value = this.value.replace(/[^0-9]/g, '');
		      }
			});
		$("#budget").keyup(function () {
			if (this.value != this.value.replace(/[^0-9]/g, '')) {
		         this.value = this.value.replace(/[^0-9]/g, '');
		      }
			});
		$('li#projectStlye').addClass('active');
    	$("#myForm").on('submit',function(e){
    		var member = $('#member').val(); 
    		member.push($('#teamleader').val());
    		console.log(member);
    		e.preventDefault();
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
        					budget:$("#budget").val(),
        					skillset:$("#skillset").val(),
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
        				    
        				else 
        					{
        					swal("Oops!", "Project Name already existed!", "error")
        					
        					}
        				},
        		error: function(err){
        				console.log(JSON.stringify(err));
        				console.log("Hello");
        				}
        		
        			});	
    		}
		
    	});	
/*                    	kitPointCalculate=function (hour){
    		  $.ajax({
    		   url:'getKitPoint',
    		   type:'POST',
    		   success: function(response){
    		    kitpoint=response.kitPoint;
    		    var value=kitpoint[0].value;
    		    var point=hour*value;
    		    $("#kitpoint").val(point);
    		   }
    		   
    		   
    		  });
    		  
    		 }	*/
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
			                   <button type="reset" class="btn btn-default">Cancel</button>
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
                                	<input class="form-control" id="skillset">
                                </div>
                            </div> 
                             <div class="form-group">
                                <label class="col-sm-4 control-label">KIT point</label>
                                <div class="col-sm-8">	
                                	<input class="form-control" id="kitpoint"  disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Budget</label>
                                <div class="col-sm-8">	
                                	<input type="text" class="form-control" maxlength="7" id="budget" required>
                                </div>
                            </div>
                            
                          	
                  		  </div>
                  	
	                    </div>
                    </div>
     </form>            

                    </body>                    
			