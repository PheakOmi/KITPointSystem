<body onload="load()">
	<!-- Page Heading -->
	<div class="row">
		<h3 class="page-header">Value Per Hour</h3>
	</div>
	<!-- /.row -->

	<form id="myForm">
		<div class="row">
			<div class="form-horizontal">
				<div class="col-sm-12">

					<div class="form-group">
						<label class="col-sm-1 control-label">Batch</label>
						<div class="col-sm-11">
							<select class="form-control" maxlength="10" name="batch_name"
								id="batch_name" required>
								<option></option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-sm-6">

					<div class="form-group">
						<label class="col-sm-2 control-label">Semester 1</label>
						<div class="col-sm-10">
							<input type="text" maxlength="10"
								class="form-control valueperhour" id="semester1" required>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">Semester 2 </label>
						<div class="col-sm-10">
							<input type="text" maxlength="10"
								class="form-control valueperhour" id="semester2" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Semester 3</label>
						<div class="col-sm-10">
							<input type="text" maxlength="10"
								class="form-control valueperhour" id="semester3" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Semester 4</label>
						<div class="col-sm-10">
							<input type="text" maxlength="10"
								class="form-control valueperhour" id="semester4" required>
						</div>
					</div>
				</div>
				<div class="col-sm-6">

					<div class="form-group">
						<label class="col-sm-2 control-label">Semester 5 </label>
						<div class="col-sm-10">
							<input type="text" maxlength="10"
								class="form-control valueperhour" id="semester5" required>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">Semester 6 </label>
						<div class="col-sm-10">
							<input type="text" maxlength="10"
								class="form-control valueperhour" id="semester6" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Semester 7</label>
						<div class="col-sm-10">
							<input type="text" maxlength="10"
								class="form-control valueperhour" id="semester7" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Semester 8</label>
						<div class="col-sm-10">
							<input type="text" maxlength="10"
								class="form-control valueperhour" id="semester8" required>
						</div>
					</div>
				</div>

				<div class="col-sm-6">
					<button type="submit" class="btn btn-default" id="mybtn">Create</button>
					
					<button onclick="location.href = 'setting';"
						class="btn btn-default">Cancel</button>
				</div>
			</div>
		</div>
	</form>

	<script type="text/javascript">
	var status;
	load = function(){	
		$.ajax({
			url:'getBatchList',
			type:'GET',
			success: function(response){
					console.log(response);
					data = response.batch;
					for(i=0; i<response.batch.length; i++){					
						$("#batch_name").append("<option value='"+response.batch[i].id+"'>"+response.batch[i].name+" </option>");
					}
			}				
		});
		
	}
	$(document).ready(function(){
		
		 $('li#settingStlye').addClass('active');
		$("#myForm").on('submit',function(e){
			e.preventDefault();
		    
		    var semester1 = $('#semester1').val().trim();
		    var semester2 = $('#semester2').val().trim();
		    var semester3 = $('#semester3').val().trim();
		    var semester4 = $('#semester4').val().trim();
		    var semester5 = $('#semester5').val().trim();
		    var semester6 = $('#semester6').val().trim();
		    var semester7 = $('#semester7').val().trim();
		    var semester8 = $('#semester8').val().trim();
		    
			var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]+/;
			if((semester1=='')|| (semester2=='')|| (semester3=='')|| (semester4=='')|| (semester5=='')|| (semester6=='')|| (semester7=='')|| (semester8==''))
				{
				swal("Oops!", "The input cannot be empty", "error")
				return
				}
			if((format.test(semester1)) || (format.test(semester2)) || (format.test(semester3)) || (format.test(semester4)) || (format.test(semester5)) || (format.test(semester6)) || (format.test(semester7)) || (format.test(semester8)))
				{
				swal("Oops!", "You can only input number", "error")  
				return
				}	 
				 $.ajax({
						url:'getHour',
						type:"GET",
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
							if(response.status=="200")
							{
								setTimeout(function() {
	        				        swal({
	        				            title: "Done!",
	        				            text: response.message,
	        				            type: "success"
	        				        }, function() {
	        				            window.location = "setting";
	        				        });
	        				    }, 10);							
							}
							else 
								swal("Oops!", response.message, "error")
						}				
					});		
			});
	});	
	$('#batch_name').on('change', function() {
		var batch_id = this.value;
		$.ajax({
			url:'getAllValuePerHour',
			type:'GET',
			data: {id: batch_id},
			success: function(response){
			console.log(response);
			vph=response.vph;
			if (vph==""||vph==null)
				{
				$(".valueperhour").each(function(){
					$(this).val("");
				});
				status="Create";
				$("#mybtn").text(status);
				}
			else
				{
				status="Update";
				$("#mybtn").text(status);
				$(".valueperhour").each(function(){
					$(this).val("");
				});
				for (i=0; i<vph.length;i++)
					{
						switch(vph[i].semester_id) {
					    case 1:
					    	$('#semester1').val(vph[i].value);
					        break;
					    case 2:
					    	$('#semester2').val(vph[i].value);
					        break;
				      	case 3:
					    	$('#semester3').val(vph[i].value);
					        break;
				        case 4:
					    	$('#semester4').val(vph[i].value);
					        break;
				        case 5:
					    	$('#semester5').val(vph[i].value);
					        break;
				        case 6:
					    	$('#semester6').val(vph[i].value);
					        break;
						    
					    case 7:
					    	$('#semester7').val(vph[i].value);
					        break;
					    case 8:
					    	$('#semester8').val(vph[i].value);
					        break;
					    default:
					    	break;
					   					        
						}
				}
				}

			},
			error: function (err)
			{
				console.log(JSON.stringify(err));
			}
		});	  
		})
		
</script>
</body>
