<div class="container">	
	<div class="row block-center">
        <div class="col-sm-4 col-sm-offset-4" id="form-box">
            <form id="myForm">
                <div id="ff" class="panel panel-info" >
                    <div class="panel-heading" id="panel-heading">
                        <h4 class="titre">KIT Point System Reset Password</h4>
                    </div>
                    <div class="panel-body">
          
                            <div class="form-group has-info">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                    <label for="email" class="sr-only"></label>
                                    <input type="password" class="form-control" id="newpw" name="password" placeholder="New Password" required/><br/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group has-info">
                                    <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                    <label for="password" class="sr-only"></label>
                                    <input type="password" class="form-control" id="cfpw" name="password" placeholder="Confirm Password" required/><br/>
                                </div>
                            </div>
               
                            <span id="clear"></span>
                            <button type="submit" class="btn btn-info btn-lg btn-block" id="rs" >Reset</button><br/>
                            <div class="alert alert-danger" style="display:none;" id="msg">
    Your password reset token is invalid or has expired!		
  </div>
                     
                    </div>
                    
                </div>
            </div>
        </form>    
	</div>
</div>

<script type="text/javascript">
	var data = ${model};
	if(data.status=="incorrect")
		{
			console.log("Came")	
			$("#ff").removeClass("panel-info");
			$("#ff").addClass("panel-danger");
			$("#rs").hide();
			$("#msg").show();
			$("#newpw").prop('disabled', true);
			$("#cfpw").prop('disabled', true);
			
		}
	else{
		 $('#rs').attr("value", data.id);
	}
	$(document).ready(function(){
		$("#myForm").on('submit',function(e){
			e.preventDefault();
			if($("#newpw").val()!=$("#cfpw").val())
				{
				swal("Oops!", "Passwords are not matched", "error")
				}
			else{				
				 $.ajax({
					url:'resetPwSubmit',
					type:'GET',
					data:{
							new_pw:$("#newpw").val(),
							confirm_pw:$("#cfpw").val(),
							id:$("#rs").val()},
					success: function(response)
						{
							if(response.status=="200")
								{
								setTimeout(function() {
	        				        swal({
	        				            title: "Done!",
	        				            text: "You have reset it successfully!",
	        				            type: "success"
	        				        }, function() {
	        				            window.location = "login";
	        				        });
	        				    }, 10);
								}
							//var obj = jQuery.parseJSON(response);
							    
							else 
								{
									swal("Oops!", "Reset is not done!", "error")
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