<body onload="load()">
<div class="row " id="margin-body">
<div id="tablewrapper" >
<table id="customers" style="width:50%; width:50%;margin: 0 AUTO;">
  <tr>
    <th>No</th>
    <th>Name</th>
    <th>Point</th>
  </tr>
  
</table>
</div>
</div>
<a href="view_update_point" class="btn btn-info btn-md center" style="margin:1cm 5cm 3cm 8cm;width:110px;background-color:#4CAF50;">
          <span class="glyphicon glyphicon-chevron-left"></span> Go Back
        </a>
<script type="text/javascript">
load = function()
{
	var batch_id = ${message};
	$.ajax({
	      url : 'getAllPoint',
	      method : 'GET',
	      data : {id:batch_id},
	      success : function(response) {
	    	  data = response.data;
	    	  console.log(data)
	    	  for (i=0;i<data.length;i++)
	    	  {
		    	  var row = "<tr class='hoverable' data-url='updateAllPoint?id="+data[i].id+"' ><td>"+(i+1)+"</td>"+
					"<td>"+data[i].name+"</td>"+
					"<td>"+data[i].text+"</td></tr>";
		    	  $("#customers").append(row);
				}
	    	  
	    	  $(".hoverable").on('click', function() {
	    	    	location.href=$(this).attr('data-url');
	    		});
				},
		error :function(err){
			console.log(JSON.stringify(err));
		}
	      });
	}
function goBack() {
    window.history.back();
}
$(document).ready(function() {
    $('li#settingStlye').addClass('active');
    
});
</script>
</body>
