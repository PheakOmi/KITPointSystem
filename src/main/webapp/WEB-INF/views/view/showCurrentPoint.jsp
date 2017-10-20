<body onload="load()">
<div class="row " id="margin-body">
<div id="tablewrapper" >
<table id="customers" style="width:50%; width:50%;margin: 0 AUTO;">
  <tr>
    <th>No</th>
    <th>Project</th>
    <th>Point</th>
  </tr>
</table>
</div>
</div>
<a onclick="goBack()" href="#" class="btn btn-info btn-md center" style="margin:1cm 5cm 3cm 8.3cm;width:110px;background-color:#4CAF50;">
          <span class="glyphicon glyphicon-chevron-left"></span> Go Back
        </a>
<script type="text/javascript">
load = function()
{
  var data = ${message};
  var sum=0;
  for(i=0;i<data.length;i++)
  {
    sum += parseFloat(data[i].kit_point);
    var row = "<tr class='record'><td>"+(i+1)+"</td>"+
    "<td class='project' id='"+data[i].project_id+"'>"+data[i].name+"</td>"+
    "<td><input type='text' readonly class='cc' maxlength='3' style='border: none;border-color: transparent;' id='idd' value="+data[i].kit_point+"></td></tr>";
    $("#customers").append(row);
  }
  var r = "<tr><td colspan='2' style='background-color: #ccc';><center>Total</center></td><td>"+sum+" Point"+"(s)"+"</td></tr>";
  $("#customers").append(r);
}

function goBack() {
    window.history.back();
}
</script>
</body>
