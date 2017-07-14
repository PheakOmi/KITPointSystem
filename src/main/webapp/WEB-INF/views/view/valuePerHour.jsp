
<body onload="load();">
<form id="myForm">
 <div class="row">
                 <div class="form-horizontal">
                 <div class="col-sm-12">
                
                  <div class="form-group">
                                <label class="col-sm-1 control-label">Batch</label>
                               <div class="col-sm-11">
                                <select class="form-control" name="batch_name" id="batch_name">
                                </select>
                                </div>
                            </div>
                            </div>
                    <div class="col-sm-6">
    
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Semester 1</label>
                                <div class="col-sm-10">
                                	<input type="text" class="form-control" id="semester1" required>
                                	</div>
                        </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Semester 2 </label>
                                <div class="col-sm-10">
                                	<input type="text" class="form-control" id="semester2" required>
                                	</div>
                        </div> 
                           <div class="form-group">
                                <label class="col-sm-2 control-label">Semester 3</label>
                                <div class="col-sm-10">
                                	<input type="text" class="form-control" id="semester3" required>
                                </div>
                        </div>
  							  <div class="form-group">
                                <label class="col-sm-2 control-label">Semester 4</label>
                                <div class="col-sm-10">
                                	<input type="text" class="form-control" id="semester4" required>
                                </div>
                        </div>
                  		  </div>
                            <div class="col-sm-6">
    
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Semester 5 </label>
                                <div class="col-sm-10">
                                	<input type="text" class="form-control" id="semester5" required>
                                </div>
                        </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Semester 6 </label>
                                <div class="col-sm-10">
                                	<input type="text" class="form-control" id="semester6" required>
                                </div>
                        </div> 
                           <div class="form-group">
                                <label  class="col-sm-2 control-label">Semester 7</label>
                                <div class="col-sm-10" >
                                	<input type="text" class="form-control" id="semester7" required>
                                </div>
                        </div>
  							  <div class="form-group">
                                <label class="col-sm-2 control-label">Semester 8</label>
                                <div class="col-sm-10">
                                	<input type="text" class="form-control" id="semester8" required>
                                </div>
                        </div>
                  		  </div>
                  		 
                    <div class="col-sm-6">
                           	<button type="submit" class="btn btn-default">Save</button>
                            <button type="reset" class="btn btn-default">Cancel</button>
                    	</div>
	                    </div>
                    </div>
        </form>        
                    	
 <script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'getBatchList',
			type:'POST',
			success: function(response){
					console.log(response);
					data = response.batch;
					//$('.tr').remove();
					for(i=0; i<response.batch.length; i++){					
						$("#batch_name").append("<option value='"+response.batch[i].id+"'>"+response.batch[i].name+" </option>");
					}
			}				
		});
		
	}
	$(document).ready(function(){
		$("#myForm").on('submit',function(e){
			e.preventDefault();
			 if($("#myForm").validate())
				{
				 $.ajax({
						url:'getHour',
						type:"POST",
						data:{batch_name:$('#batch_name').val(),
							value_1:$('#semester1').val(), 
							value_2:$('#semester2').val(),
							value_3:$('#semester3').val(),
							value_4:$('#semester4').val(),
							value_5:$('#semester5').val(),
							value_6:$('#semester6').val(),
							value_7:$('#semester7').val(),
							value_8:$('#semester8').val()},
						success: function(response){
								alert(response.message);
						}				
					});		
				}
			
		});
	});	

		
</script>
</body>
