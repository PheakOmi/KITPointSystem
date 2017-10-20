<body>
   <!-- Page Heading -->
                <div class="row">
                        <h3 class="page-header">
                            Create Batch 
                        </h3>
                </div>
   <!-- /.row -->
<div class="row" id="margin-body">
    <form id="myForm">
				 <div class="form-horizontal">
				 <div class="col-sm-8">
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Name</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id ="name1" name="name" required>
						    </div>
						  </div>
                          
                          <div class="form-group">
                                <label class="col-sm-2 control-label">Start Date</label>
                              	<div class="col-sm-10">
                              		<input class="form-control" id="startdate"  name="date" placeholder="MM/DD/YYY" type="text" required/>
                              	</div>
                            </div>  
                          <div class="form-group">
                                <label class="col-sm-2 control-label">End Date</label>
                              	<div class="col-sm-10">
                              		<input class="form-control" id="enddate"  name="date" placeholder="MM/DD/YYY" type="text" required/>
                              	</div>
                            </div>  
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" id="btnSubmit" class="btn btn-default">Create</button>
						      <button onclick="location.href = 'setting';" class="btn btn-default">Cancel</button>
						    </div>
						  </div>
						  </div>
						</div>
						</form>
                    </div>	
<script type="text/javascript">
$(document).ready(function(){
	$("[name=date]").keydown(function (event) {
	    event.preventDefault();
	});
	
	$('li#settingStlye').addClass('active');
	$("#myForm").on('submit',function(e){
		e.preventDefault();
		var a = $("#name1").val().trim();
		var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
		if(a=='')
			{
			swal("Oops!", "The input cannot be empty", "error")
			return
			}
		if(format.test(a))
			{
			swal("Oops!", "You cannot input special characters", "error")  
			return
			}
        var startdate = Date.parse($("#startdate").val());
        var enddate = Date.parse($("#enddate").val());
        if(startdate>enddate)
    		swal("Oops!", "Your End Date is before Start Date", "error")
		else{
			 $.ajax({
				url:'batchSubmit',
				type:'GET',
				data:{
						name:$("#name1").val().trim(),
						start_date:$("#startdate").val(),
						end_date:$("#enddate").val()},
				success: function(response)
					{
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
						//var obj = jQuery.parseJSON(response);
						    
						else 
							{
								$('#name1').val('');
								swal("Oops!", "Batch name already existed", "error")
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
</body>
          
       </html>
      
       