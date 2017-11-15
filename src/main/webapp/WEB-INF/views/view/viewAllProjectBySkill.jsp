<body onload="load()">
<div class="row " id="margin-body">
<div id="tablewrapper" >
<table id="customers" style="display:none;width:50%;margin: 0 AUTO;">
  <tr>
    <th>No</th>
    <th>Name</th>
    <th>Skill Set
  </tr>
  
</table>
</div>
 
</div>
<a onclick="goBack()" class="btn btn-info btn-md center" id="goBack1" style="margin:1cm 5cm 3cm 8.3cm;width:110px;background-color:#4CAF50;">
          <span class="glyphicon glyphicon-chevron-left"></span> Go Back
        </a>
<script type="text/javascript">
load = function()
{
	$("#myModal").hide();
	  var data = ${message};
	  if(data==null || data=="")
		  swal("Oops!", "There is no project found", "error")
	  console.log(data)
	  for (i=0;i<data.length;i++)
	  {
	  var row = "<tr class='hoverable' data-url='viewAllMember?id="+data[i].id+"' ><td>"+(i+1)+"</td>"+
			"<td>"+data[i].project_name+"</td>"+
			"<td>"+data[i].skillset.substring(0, data[i].skillset.length-2)+"</td>";
			;
	  $("#customers").append(row);
		}
	  
	  $(".hoverable").on('click', function() {
	    	location.href=$(this).attr('data-url');
		});
	$("#customers").removeAttr('style');
	$("#customers").css('width','50%');
	$("#customers").css('margin','0 AUTO');
}
$(document).ready(function() {
    $('li#settingStlye').addClass('active');
});
function goBack() {
    window.history.back();
}
</script>
</body>
