<body onload="load()">
<div class="row " id="margin-body">
<div id="tablewrapper" >
<table id="customers" style="width:50%; width:50%;margin: 0 AUTO;">
  <tr>
    <th>No</th>
    <th>Name</th>
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
	var data = ${message};
	var members = data.data;
	for(i=0;i<members.length;i++)
	{
		var row = "<tr class='record'><td>"+(i+1)+"</td>"+
		"<td>"+members[i].user_name+"</td>";
		$("#customers").append(row);
	}
	var lrow = "<tr style='background-color:lightgray;text-align: center;'><td colspan='2'><b>Project Name: </b>"+data.name+"</td></tr>";
	$("#customers").append(lrow);
}

function goBack() {
    window.history.back();
}
$(document).ready(function() {
    $('li#settingStlye').addClass('active');
});
</script>
</body>
