<script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'getKitPoint',
			type:'POST',
			success: function(response){
				kitpoint=response.kitPoint;
				$("#point").val(kitpoint[0].id);
				$("#value1").val(kitpoint[0].value);

			}
			
		});
		
	}
</script>		

 
 <body onload="load();">
 <div class="row" id="margin-body">
                 <form class="form-horizontal" id="myForm">
                    <div class="col-lg-10">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" id="point">1 KIT Point</label>
                                <div class="col-sm-10">
                               		 <input type="text" class="form-control" id="value1" name="value" required>
                                </div>
                        </div>
                         <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Save</button>
                            <button type="reset" class="btn btn-default">Cancel</button>
						</div>      
                      </div>
                           
                     </form>
                    </div>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#myForm").on('submit',function(e){
			e.preventDefault();
			 if($("#myForm").validate())
				{
				 $.ajax({
						url:'submit1',
						type:'POST',
						data:{id:$('#point').val(),value:$('#value1').val()},
						success: function(response){

							swal(response.message, "success")
						}				
					});
				}
		});
	});	
	
	
  </script>
	