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
	for(i=0;i<data.length;i++)
	{
		var row = "<tr class='record'><td>"+(i+1)+"</td>"+
		"<td>"+data[i].user_name+"</td>";
		$("#customers").append(row);
	}
}

function goBack() {
    window.history.back();
}
$(document).ready(function() {
    $('li#settingStlye').addClass('active');
});
</script>
</body>
