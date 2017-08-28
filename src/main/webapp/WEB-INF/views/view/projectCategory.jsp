<body>
   <!-- Page Heading -->
                <div class="row">
                        <h3 class="page-header">
                            Project Category 
                        </h3>
                </div>
   <!-- /.row -->
	<div class="row" id="margin-body">
		<form id="myForm">
			<div class="form-horizontal">
				<div class="col-sm-10">

					<div class="form-group">
						<label class="col-sm-2 control-label">Category Name</label>
						<div class="col-sm-10">
							<input class="form-control" id="projectCategory" type="text" maxlength="60" name="name"  required>
						</div>
					</div>
					<div class="col-sm-offset-2 col-sm-10">
						<button id="btnSubmit" type="submit" class="btn btn-default">Create</button>
						<button type="reset" class="btn btn-default">Cancel</button>
					</div>
				</div>

			</div>
		</form>
	</div>
<script type="text/javascript">
$(document).ready(function(){

		$("#projectCategory").keyup(function () {
	        console.log("Hello World");
	        if (this.value != this.value.replace(/[^a-zA-Z0-9\ ]/g, '')) {
		         this.value = this.value.replace(/[^a-zA-Z0-9\ ]/g, '');
	      }
		});
	$('li#settingStlye').addClass('active');
	$("#myForm").on("submit",function(e){
		e.preventDefault();
			$.ajax({
				url:'projectCategoryCreate',
				type:'POST',
				data:{name:$("#projectCategory").val()},
				success: function(response){
					if(response.status=="200")
						{
						$('#projectCategory').val('');
						swal("Done!", "You have created successfully!", "success")
						}
					//var obj = jQuery.parseJSON(response);
					    
					else 
						{
						swal("Oops!", "Category name already existed", "error")
						
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