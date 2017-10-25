<body>
   <!-- Page Heading -->
                <div class="row">
                        <h3 class="page-header">
                            Create User
                        </h3>
                </div>
   <!-- /.row -->
 <div class="row" id="margin-body">
    
				 <form class="form-horizontal" id="myForm">
				 <div class="col-sm-2">

               	</div>
				 <div class="col-sm-8"> 
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Name</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" name="name" maxlength="30" id="name" required>
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Email</label>
						    <div class="col-sm-10">
						      <input type="email" class="form-control" name="email" id="email" required>
						    </div>
						  </div>
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Password</label>
						    <div class="col-sm-10">
						      <input type="password" class="form-control" name="password" id="password" required>
						    </div>
						  </div>
						   <div class="form-group">
						     <label class="col-sm-2 control-label">User Type</label>
								  <div class="col-sm-10">
		                                <label class="radio-inline">
		                                    <input type="radio" class="user_type" name="optionsRadiosInline" value="ROLE_ADMIN"  checked>Super admin
		                                </label>
		                                <label class="radio-inline">
		                                    <input type="radio" class="user_type"  name="optionsRadiosInline" value="ROLE_N_ADMIN">Admin
		                                </label>
		                                
		                           </div>
		                            
                            </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-default">Create</button>
						      <button onclick="location.href = 'setting';" class="btn btn-default">Cancel</button>
						  
						    </div>
						  </div>
						  </div>
						</form>
                    </div>                 	
 <script type="text/javascript">
			
			$(document).ready(function(){
				
				$('li#settingStlye').addClass('active');
				$("#myForm").on('submit',function(e){
					e.preventDefault();
					var name = $("#name").val().trim();
					var email = $("#email").val().trim();
					var password = $("#password").val().trim();
					var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
					var formatemail = /[!#$%^&*()+\-=\[\]{};':"\\|,<>\/?]+/;
					if((name=='') || (email=='') || (password==''))
						{
						swal("Oops!", "The input cannot be empty", "error")
						return
						}
	
					if(format.test(name))
						{
						console.log("JJ");
						swal("Oops!", "You cannot input special characters", "error")  
						return
						}
					if( !validateEmail(email))
					{
					swal("Oops!", "You cannot input special character", "error")  
					return
					}		 	
						 $.ajax({
								url:'addUser',
								type:"GET",
								data:{name:$('#name').val().trim(),
									email:$('#email').val().trim(), 
									password:$('#password').val().trim(),
									user_type:$("input[name='optionsRadiosInline']:checked").val(),
									batch:$('#batch').val()},
								success: function(response){
									if(response.status=="200")
									{
										setTimeout(function() {
			        				        swal({
			        				            title: "Done!",
			        				            text: "You have created it successfully!",
			        				            type: "success"
			        				        }, function() {
			        				            window.location = "setting";
			        				        });
			        				    }, 10);
									}
									else{
										swal("Oops!", response.message, "error")
									}
								}				
							});		
						
				});
			});	
			function validateEmail($email) {
				  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
				  return emailReg.test( $email );
				}			
</script>