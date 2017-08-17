<body onload="load();">
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
						    <label class="col-sm-2 control-label">Batch</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id ="name1" name="name" required>
						    </div>
						  </div>
                          <div class="form-group">
                                <label class="col-sm-2 control-label">Semester</label>
                                <div class="col-sm-10">
                                <select class="form-control" id="semester" name="semester_id">
                                 </select>
                                </div>
                            </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" id="btnSubmit" class="btn btn-default">Create</button>
						      <button type="reset" class="btn btn-default">Cancel</button>
						    </div>
						  </div>
						  </div>
						</div>
						</form>
                    </div>	
<script type="text/javascript">
load = function()
	{	
		$.ajax({
			url:'semesterList',
			type:'POST',
			success: function(response)
				{
					console.log(response);
					data = response.data;
					//$('.tr').remove();
					for(i=0; i<response.data.length; i++)				
						$("#semester").append("<option value="+response.data[i].id+">"+response.data[i].semester+" </option>");
				}
			});
	}
$(document).ready(function(){
	
	$("#name1").keyup(function () {
      if (this.value != this.value.replace(/[^a-zA-Z0-9\.]/g, '')) {
         this.value = this.value.replace(/[^a-zA-Z0-9\.]/g, '');
      }
	});
	$('li#settingStlye').addClass('active');
	$("#myForm").on('submit',function(e){
		e.preventDefault();
		
			 $.ajax({
				url:'batchSubmit',
				type:'POST',
				data:{name:$("#name1").val(),semester_id:$("#semester").val()},
				success: function(response)
					{
						if(response.status=="200")
							{
								$('#name1').val('');
								swal("Success!", "You have created it successfully!", "success")
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
			 
		
	});
});	
</script>
</body>
          
       </html>
      
       