<body>
<script type="text/javascript">
ip="a";
var db_name;
var admin_name;
var admin_password;
var buttonLabel = "Create";

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







setIp = function ()
{
console.log(ip);
swal.withForm({
title: 'SMS Server Information',
text: 'Any text that you consider useful for the form',
showCancelButton: true,
confirmButtonColor: '#8CD4F5',
confirmButtonText: buttonLabel,
closeOnConfirm: false,
formFields: [
    { id: 'ip', placeholder:'IP',value:ip},
    { id: 'db_name', placeholder:'Database Name',value:db_name },
    { id: 'admin_name', placeholder:'Admin Name',value:admin_name },
    { id: 'admin_password', placeholder:'Admin Password',value:admin_password }
]
}, 
function(isConfirm) {
	
	if (isConfirm) {
console.log(this.swalForm.ip);
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
				swal("Oops!", "Batch name already existed", "error")
				
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

confirm =  function(id)
{
swal({
    title: "Do you want to update batch and student information?",
    text: "All information related to batch and student will be updated",
    type: "warning",
    showCancelButton: true,
    confirmButtonColor: "#30A9DE",
    confirmButtonText: "Update	",
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
    				alert("Both are updated");
    			else if(response.status=="888")
    				alert("Batch updated");
    			else
    				alert("No updation");
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
				//console.log(response);
				if(response.data!=null)
					{
					info = response.data; 
					ip = info.ip;
					console.log("In"+ip);
					db_name = info.db_name;
					admin_name = info.admin_name;
					admin_password  = info.admin_password;
					buttonLabel = "Update";
					}				
				}				
	});	
}
console.log(ip);
load();
setIp();

</script>
</body>
       