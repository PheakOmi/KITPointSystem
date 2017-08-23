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
						      <input type="text" class="form-control" name="name" id="name" required>
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
		                                    <input type="radio" id="user_type" name="optionsRadiosInline" value="Super Admin" checked>Super admin
		                                </label>
		                                <label class="radio-inline">
		                                    <input type="radio" id="user_type"  name="optionsRadiosInline" value="Admin">Admin
		                                </label>
		                                
		                           </div>
		                            
                            </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-default">Create</button>
						      <button type="reset" class="btn btn-default">Cancel</button>
						  
						    </div>
						  </div>
						  </div>
						</form>
                    </div>
                    
                    
                 	
 <script type="text/javascript">
			
			$(document).ready(function(){
				
				$("#name").keyup(function () {
					if (this.value != this.value.replace(/[^a-zA-Z0-9]/g, '')) {
				         this.value = this.value.replace(/[^a-zA-Z0-9]/g, '');
					});
				
				$("#email").keyup(function () {
			      if (this.value != this.value.replace(/[^a-zA-Z0-9\@.]/g, '')) {
			         this.value = this.value.replace(/[^a-zA-Z0-9\@.]/g, '');
			      }
				});
				$('li#settingStlye').addClass('active');
				$("#myForm").on('submit',function(e){
					e.preventDefault();
					 
						 $.ajax({
								url:'addUser',
								type:"POST",
								data:{name:$('#name').val(),
									email:$('#email').val(), 
									password:$('#password').val(),
									user_type:$('#user_type').val(),
									batch:$('#batch').val()},
								success: function(response){
									if(response.status=="200")
									{
									swal("Success!", "You have created successfully!", "success")
									}
									else{
										swal("Oops!", response.message, "error")
									}
								}				
							});		
						
				});
			});	
				
</script>