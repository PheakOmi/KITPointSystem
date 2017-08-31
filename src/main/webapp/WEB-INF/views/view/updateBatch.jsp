<body onload="load();">
<script type="text/javascript">
load = function(){	
	$.ajax({
		url:'semesterAndBatchList',
		type:'GET',
		success: function(response){
				console.log(response);
				semester = response.semester;
				batch = response.batch;
				for(i=0; i<response.semester.length; i++){					
					$("#semester").append("<option value="+response.semester[i].id+">"+response.semester[i].semester+" </option>");
					
				}
				for(i=0; i<response.batch.length; i++){					
					$("#batch").append("<option value="+response.batch[i].id+">"+response.batch[i].name+" </option>");
					
				}
				$("#batch").change(function(){
					var batchValue = document.getElementById("batch").value;
					for(i=0; i<response.batch.length; i++){
						  if(response.batch[i].id==batchValue)
							  {
							  batchValue = response.batch[i].semester_id;
							  break;
							  }
					  }
					$("#semester").val(batchValue);
					
				});
		}				
	});
}
$(document).ready(function(){
	$('li#settingStlye').addClass('active');
	$("#btnSubmit").click(function(){		 
	$.ajax({
		url:'updateBatch',
		type:'GET',
		data:{id:$("#batch").val(),semester_id:$("#semester").val()},
		success: function(response){
				if(response.status=="200")
					{
					$('#name1').val('');
					swal("Done!", "You have updated it successfully!", "success")
					}
				//var obj = jQuery.parseJSON(response);
				    
				else 
					{
					$('#name1').val('');
					swal("Oops!", "It is not updated!", "error")
					
					}
				},
		error: function(err){
				console.log(JSON.stringify(err));
				}
		
			});			
	
	});
});	
</script>
   <!-- Page Heading -->
                <div class="row">
                        <h3 class="page-header">
                            Update Batch 
                        </h3>
                </div>
   <!-- /.row -->
 <div class="row" id="margin-body">
    
				 <div class="form-horizontal">
				 <div class="col-sm-8">
						  <div class="form-group">
						    <label class="col-sm-2 control-label">Batch</label>
						    <div class="col-sm-10">
						      <select class="form-control" id="batch">
                                 </select>
						    </div>
						  </div>
                            
                          <div class="form-group">
                                <label class="col-sm-2 control-label">Semester</label>
                                <div class="col-sm-10">
                                <select class="form-control" id="semester">
                                 </select>
                                </div>
                            </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button id="btnSubmit" class="btn btn-default">Update</button>
						    
						  
						    </div>
						  </div>
						  </div>
						</div>
                    </div>	
          </body>
       