<style type="text/css">
</style>
<body onload="load()" style="overflow:scroll;">
<script type="text/javascript">	

load = function(){	
	$('#mybtn').trigger('click');
	$.ajax({
		url:'skillSetList',
		type:'GET',
		success: function(response){
			data = response.data;
			$('#skillset').select2({data: data});
		},
	error: function(err){
		
		console.log(JSON.stringify(err));
		}
		
	});
	
}


	
$(document).ready(function(){
	$("#myForm2").on("submit",function(e){
		e.preventDefault();
		window.location.href = 'viewAllProjectBySkill?data='+$("#skillset").val();
				
	});
	
});	


goTO = function(){
	$('#bsubmit').trigger('click');
}
</script>
<button style="display: none;"class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="mybtn">
    Login modal</button>
    
    
    <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Select Any Skill Set</h4>
        </div>
        <div class="modal-body">
     <form class="form-group" id="myForm2">
    <div class="clearfix">
      <label class="col-sm-2" for="email">Skill Set:</label>
      <div class="col-sm-7">
		      <select style="width: 100%;" class="js-example-basic-multiple form-control" id="skillset" multiple="multiple" required>
											
			  </select>
      		<button id="bsubmit" type="submit" class="btn btn-default" style="display:none;">Submit</button>
      </div>
    </div>
  </form>	
        </div>
        <div class="modal-footer">
          <button onClick="goTO()" class="btn btn-default">Submit</button>
          <button type="button" id="closing" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>		
   
   
   
   
<div class="row " id="margin-body">
<div id="tablewrapper" >
<table id="customers" style="display:none;width:100%;margin: 0 AUTO;">
  <tr>
    <th>No</th>
    <th>Name</th>
  </tr>
  
</table>
</div>
 
</div>
</body>
