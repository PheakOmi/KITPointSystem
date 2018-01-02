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
			document.querySelector("#value1").addEventListener("keypress", function (evt) {
				var charCode = (evt.which) ? evt.which : evt.keyCode;
		        if (charCode != 46 && charCode > 31 
		          && (charCode < 48 || charCode > 57))
		           return false;

		        return true;
		    });
	
			
			$('li#settingStlye').addClass('active');
			$("#myForm").on('submit',function(e){
				e.preventDefault();
				if($('#value1').val()==null||$('#value1').val()=="")
					$('#value1').val(0);
				if(!$.isNumeric($('#value1').val()))
					{
					swal("It is not number!", "Please put only number", "error")
					return
					}
					
					 $.ajax({
							url:'submit1',
							type:'GET',
							data:{value:$('#value1').val()},
							success: function(response){

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
                               		 <input type="text" maxlength="3" class="form-control" id="value1" name="value" required>
                                </div>
                        </div>
                         <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Update</button>
                            <button onclick="location.href = 'setting';" class="btn btn-default">Cancel</button>
						</div>      
                      </div>
                    </div>
       </form>