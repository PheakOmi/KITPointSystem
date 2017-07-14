
 <div class="row" id="margin-body">
                 <form class="form-horizontal" id="myForm">
                    <div class="col-lg-10">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">1 KIT Point</label>
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
						data:{value:$('#value1').val()},
						success: function(response){
							swal("Good job!", "You clicked the button!", "success")
						}				
					});
				}
		});
	});		
  </script>
	