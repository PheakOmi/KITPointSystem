<body onload="load()">
<script type="text/javascript">
setIp = function (s,p)
{
var ip="";
var db_name="";
var admin_name="";
var admin_password="";
if(!isEmpty(p))
	{
	ip=p.data.ip;
	db_name=p.data.db_name;
	admin_name=p.data.admin_name;
	admin_password=p.data.admin_password;
	}
swal.withForm({
title: 'SMS Server Information',
text: 'Any text that you consider useful for the form',
showCancelButton: true,
confirmButtonColor: '#8CD4F5',
confirmButtonText: s,
closeOnConfirm: false,
formFields: [
    { id: 'ip', placeholder:'IP',value:ip},
    { id: 'db_name', placeholder:'Database Name',value:db_name },
    { id: 'admin_name', placeholder:'Admin Name',value:admin_name},
    { id: 'admin_password', placeholder:'Admin Password',value: admin_password}
]
}, 
function(isConfirm) {
	
	if (isConfirm) {
$.ajax({
	url:'serverInfoSubmit',
	type:'GET',
	data:{
			ip:this.swalForm.ip,
			db_name :this.swalForm.db_name,
			admin_name :this.swalForm.admin_name,
			admin_password :this.swalForm.admin_password},
	success: function(response)
		{
			if(response.status=="200")
			{
				swal("Done!",response.message,"success")
				confirm();
			}
			else if(response.status=="201")
			{
				swal("Done!",response.message,"success")
				confirm();
			}
			else if(response.status=="999")
				swal("Oops!", response.message, "error")
			else if(response.status=="1000")
				swal("Oops!", response.message, "error")
				
		},
	error: function(err){
			console.log(JSON.stringify(err));
			 } 
	
		});	
}
	else 
		 location.href = 'setting';
})

}



confirm =  function()
{
swal({
    title: "Do you want to update batch and student information?",
    text: "All information related to batch and student will be updated",
    type: "warning",
    showCancelButton: true,
    confirmButtonColor: "#30A9DE",
    confirmButtonText: "Update",
    cancelButtonText: "Cancel",
    closeOnConfirm: false,
    closeOnCancel: false
  },
    function (isConfirm) {
      if (isConfirm) {
    	  $.ajax({
    			url:'updateBatchNStudent',
    			type:'GET',
    			success: function(response){
    			message = response.message;
    			if(response.status=="999")
				{
				setTimeout(function() {
			        swal({
			            title: "Done!",
			            text: response.message,
			            type: "success"
			        }, function() {
			            window.location = "batch";
			        });
			    }, 10);
				}
    			else if(response.status=="888")
				{
				setTimeout(function() {
			        swal({
			            title: "Done!",
			            text: response.message,
			            type: "success"
			        }, function() {
			            window.location = "batch";
			        });
			    }, 10);
				}
    			else
				{
				setTimeout(function() {
			        swal({
			            title: "Oops!",
			            text: response.message,
			            type: "error"
			        }, function() {
			            window.location = "batch";
			        });
			    }, 10);
				}
    					},
				error: function(err){
					console.log(JSON.stringify(err));
					 } 
    							
    		});	
    	  
      } 
      else location.href = 'setting';
    });
    }


function load(){
	//ip = '';
		$.ajax({
		url:'getSmsServerInfo',
		type:'GET',
		success: function(response){
				var s;
				//console.log(response);
				if(response.status=="999")
					{
					s = "Update";
					p = response;
					}
				else{
					s = "Create";
				}
				confirm();
				setIp(s,p);
				}				
	});	
}


function isEmpty(obj) {
    for(var key in obj) {
        if(obj.hasOwnProperty(key))
            return false;
    }
    return true;
}	


function doesConnectionExist() {
    var xhr = new XMLHttpRequest();
    var file = "http://kit.edu.kh/admission.php";
    var randomNum = Math.round(Math.random() * 10000);
 
    xhr.open('HEAD', file + "?rand=" + randomNum, true);
    xhr.send();
     
    xhr.addEventListener("readystatechange", processRequest, false);
 
    function processRequest(e) {
      if (xhr.readyState == 4) {
        if (xhr.status >= 200 && xhr.status < 304) {
          alert("connection exists!");
        } else {
          alert("connection doesn't exist!");
        }
      }
    }
}
$(document).ready(function(){
	
});

</script>
</body>
       