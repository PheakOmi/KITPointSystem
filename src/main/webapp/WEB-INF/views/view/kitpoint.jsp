<script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'getKitPoint',
			type:'GET',
			success: function(response){
				kitpoint=response.kitPoint;
				if(response.kitPoint[0]!=null){
				$("#point").val(kitpoint[0].id);
				$("#value1").val(kitpoint[0].value);}	

			}
			
		});
		}
		$(document).ready(function(){
			$("#value1").keyup(function () {
		    
		      if (this.value != this.value.replace(/[^0-9]/g, '')) {
		         this.value = this.value.replace(/[^0-9]/g, '');
		      }
			});
			
			$('li#settingStlye').addClass('active');
			$("#myForm").on('submit',function(e){
				e.preventDefault();
				 
					 $.ajax({
							url:'submit1',
							type:'GET',
							data:{value:$('#value1').val()},
							success: function(response){

								swal("Success!", "You have updated it successfully!", "success")
							}				
						});
					
			});
		});	
	
</script>		
 <body onload="load();">

 <!-- <img src onerror="load()">  --> 

   <!-- Page Heading -->
                <div class="row">
                        <h3 class="page-header">
                            KIT Point 
                        </h3>
                </div>
   <!-- /.row -->
   <form class="form-horizontal" id="myForm">
 <div class="row" id="margin-body">
                 
                    <div class="col-lg-10">
                            <div class="form-group ">
                                <label class="col-sm-2 control-label" id="point">1 KIT Point</label>
                                <div class="col-sm-10 input-group">
                                	<span class="input-group-addon">$</span>
                               		 <input type="text" maxlength="10" class="form-control" id="value1" name="value" required>
                                </div>
                        </div>
                         <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Update</button>
                            <button onclick="location.href = 'setting';" class="btn btn-default">Cancel</button>
						</div>      
                      </div>
                    </div>
       </form>
</script>