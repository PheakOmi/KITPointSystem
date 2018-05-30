<body onload="load()">
<div class="row " id="margin-body">
<div class="text-center" id="hh">
      <div>
    <h1 style="color: grey;font-family: inherit;font-size:25px;">Name: <span id="ssname"></span></h1>
    </div>
  </div>
 
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
<a onclick="parent.history.go(-1)" class="btn btn-info btn-md center" id="goBack1" style="margin:1cm 5cm 3cm 8.3cm;width:110px;background-color:#4CAF50;">
          <span class="glyphicon glyphicon-chevron-left"></span> Go Back
        </a>
<a onclick="parent.history.go(-2)" class="btn btn-info btn-md center" id="goBack2" style="margin:1cm 6cm 3cm 8cm;width:110px;background-color:#4CAF50;">
          <span class="glyphicon glyphicon-chevron-left"></span> Go Back
        </a>
<a onclick="edit()" id ="edit" href="#" class="btn btn-info btn-md center" style="margin:-3.85cm 45cm 15cm 5cm;color:white;width:80px;background-color:#ccc;border-color:#ccc">
          <span class="glyphicon glyphicon-edit"></span>Edit</a>
<a onclick="update()" id ="update" href="#" class="btn btn-info btn-md center" style="margin:-15.845cm 0cm 10cm 2cm;color:white;width:80px;background-color:#ccc;border-color:#ccc">
          <span class="glyphicon glyphicon-check"></span>Update</a>
<script type="text/javascript">
load = function()
{
	$("#update").hide();
	$("#goBack2").hide();
	var dd = ${message};
	var data = dd.data;
	data.sort(compare);
	$("#ssname").text(dd.name);
	if(isEmpty(data))
		$("#edit").hide();
	else
		$("#update").attr("user_id",data[0].user_id);
//	$("#update").attr("user_id",data.)
	var sum=0;
	for(i=0;i<data.length;i++)
	{
		sum += parseFloat(data[i].kit_point);
		var row = "<tr class='record'><td>"+(i+1)+"</td>"+
		"<td class='project' id='"+data[i].project_id+"'>"+data[i].name+"</td>"+
		"<td><input type='text' readonly class='cc' maxlength='6' style='border: none;border-color: transparent;' id='idd' value="+parseFloat(data[i].kit_point).toFixed(3)+"></td></tr>";
		$("#customers").append(row);
		
		$('.cc').keypress(function(evt){
			var charCode = (evt.which) ? evt.which : evt.keyCode;
	          if (charCode != 46 && charCode > 31 
	            && (charCode < 48 || charCode > 57))
	             return false;

	          return true;
	    });		
	}
	var r = "<tr><td colspan='2' style='background-color:	#ccc';><center>Total</center></td><td>"+sum.toFixed(3)+" Point"+"(s)"+"</td></tr>";
	$("#customers").append(r);
}

function goBack() {
    window.history.back();
}
function edit(){
	$( "input" ).removeAttr( "readonly" );
	$ (".cc").removeAttr("style");
	$("#edit").hide();
	$("#update").css('margin','-3.85cm 40cm 15cm 3cm');
	$("#update").show();
	$("#goBack2").show();
	$("#goBack1").hide();
}
function update(){
	var user_id = $("#update").attr("user_id");
	var kitPoint="";	
	var cou =0;
	var toVal=0;
	$('#customers .record').each(function() {
		cou++;
	    var project_id = $(this).find(".project").attr("id");    
	    var point = $(this).find(".cc").val();
	    if(point=="" || point==null)
	    	point=0;
	    if($.isNumeric(point))
			toVal++;  
		kitPoint +=project_id+","+point+"/";
	    });
	kitPoint = kitPoint.substring(0, kitPoint.length-1);
	console.log(cou)

	if(toVal==cou)
	
	{
		$.ajax({
		url:'updatePointSubmit',
		type:'GET',
		data:{		user_id:user_id,
					kit_point:kitPoint,},
		traditional: true,			
		success: function(response){
				if(response.status=="200")
					{setTimeout(function() {
				        swal({
				            title: "Done!",
				            text: "You have updated it successfully!",
				            type: "success"
				        }, function() {
				        	parent.history.go(-2)
				        });
				    }, 10);}
				else 
					swal("Oops!", response,message, "error")
					
			},
		error: function(err){
				console.log(JSON.stringify(err));
				
				}
		
			});	
		}
	else{
		swal("It is not number!", "Please put only number", "error")
	}
	
}
function isEmpty(obj) {
    for(var key in obj) {
        if(obj.hasOwnProperty(key))
            return false;
    }
    return true;
}	
$(document).ready(function() {
    $('li#settingStlye').addClass('active');
});

function compare(a, b) {
	  const genreA = a.name.toUpperCase();
	  const genreB = b.name.toUpperCase();
	  
	  let comparison = 0;
	  if (genreA > genreB) {
	    comparison = 1;
	  } else if (genreA < genreB) {
	    comparison = -1;
	  }
	  return comparison;
	}
</script>
</body>
