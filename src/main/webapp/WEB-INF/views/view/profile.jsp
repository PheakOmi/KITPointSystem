<script>
$(document).ready(function(){
	$("#myForm").on('submit',function(e){
		e.preventDefault();
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
<body>
<div class="row" id="margin-body">
                 <form class="form-horizontal" id="myForm">
                    <div class="col-sm-10">

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
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
                       
                            <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Save</button>
                            <button onclick="location.href = 'setting';" class="btn btn-default">Cancel</button>
						</div>
						</div>
                       
						
                        
                     </form>
                    </div>
         </body>
               