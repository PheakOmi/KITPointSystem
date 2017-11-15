<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script>
$(document).ready(function(){
	
	$("#myForm").on('submit',function(e){
		e.preventDefault();

		var name = $("#name").val();
		console.log(name);
		var email = $("#email").val();
		var currentpassword = $("#currentpassword").val();
        var newpassword = $("#newpassword").val();
        var confirmpassword = $("#confirmpassword").val();
        
        if(newpassword!=confirmpassword)
    		swal("Oops!", "Your new password does not match", "error")
		else{
    	$.ajax({
    		url:'editProfile',
    		type:'GET',
    		data:{		
    				email:$("#email").val(),
    				user_type:name,
    				password:$("#currentpassword").val(),
    				name:$("#newpassword").val(),
    			 },			
    		success: function(response){
    				if(response.status=="999")
    					{
    					setTimeout(function() {
    				        swal({
    				            title: "Done!",
    				            text: response.message,
    				            type: "success"
    				        }, function() {
    				        	formSubmit();
    				        });
    				    }, 10);
    					
    					}
    				else if(response.status=="77")
     					swal("Oops!", response.message, "error")
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
<body>
<div class="row" id="margin-body">
                 <form class="form-horizontal" id="myForm">
                    <div class="col-sm-10">

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
                                <input type="hidden" id="name" value="${pageContext.request.userPrincipal.name}">
                                <input type="text" class="form-control" id="email" required>
                                </div>
                       		 </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Current Password</label>
                                <div class="col-sm-10">
                                <input type="password" class="form-control" id="currentpassword" required>
                                </div>
                       		 </div>
                       		 <div class="form-group">
                                <label class="col-sm-2 control-label">New Password</label>
                                <div class="col-sm-10">
                                <input type="password" class="form-control" id="newpassword" required>
                                </div>
                       		 </div>
                       		 <div class="form-group">
                                <label class="col-sm-2 control-label">Confirm Password</label>
                                <div  class="col-sm-10">
                                <input type="password" class="form-control" id="confirmpassword" required>
                                </div>
                       		 </div>
                       		 <div class="form-group">
                                <label class="col-sm-2 control-label"></label>
                                
                       		 </div>
                       
                            <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Save</button>
                            <sec:authorize access="hasRole('ROLE_USER')">
                            <button onclick="location.href = 'projectUserView';" class="btn btn-default">Cancel</button>
                            </sec:authorize>  
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <button onclick="location.href = 'project';" class="btn btn-default">Cancel</button>
                            </sec:authorize>  
                            <sec:authorize access="hasRole('ROLE_N_ADMIN')">
                            <button onclick="location.href = 'projectAdminView';" class="btn btn-default">Cancel</button>
                            </sec:authorize>  
						</div>
						</div>
                     
						
                        
                     </form>
                    </div>
         </body>
               