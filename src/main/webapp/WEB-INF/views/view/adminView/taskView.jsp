<body onload="load();">
 <script type="text/javascript">
  load = function() {
	 var slickjs = $("<script>");
  	$(slickjs).attr('src', '/KIT_Point_Management_System/resources/Bootstrap/js/slider/slick.min.js');
  	$(slickjs).appendTo('body');
   $.ajax({
      url : 'ProjectNTaskAdmin',
      method : 'GET',
      success : function(response) {	
    	
       task = response.task;	
       project = response.project;
       function formatDate(date) {
		    var d = new Date(date),
		        month = '' + (d.getMonth() + 1),
		        day = '' + d.getDate(),
		        year = d.getFullYear();

		    if (month.length < 2) month = '0' + month;
		    if (day.length < 2) day = '0' + day;
	
		    return [month, day, year].join('/');
		};
       console.log(response);
       for (i = 0; i < project.length; i++) {
        var projectcard = '<div class="project card" project-title="'+project[i].project_name+'">'
          + '<div class="card-heading">'
          + '<h3>'
          + project[i].project_name
          + '</h3>'
          + '</div>'
          + '<div class="card-body">'
          + '<div class="project-tasks__wrapper"'+'id='+project[i].id+'>'
          + '</div>' + '</div>' + '</div>';
        $(".projects").append(projectcard);
       }
       
       $('.projects').slick({
    	   infinite: true,
    	   slidesToShow: 5,	
    	   slidesToScroll: 3,    	   
    	   arrows:false,
    	   infinite: false,
    	   initialSlide: 0	
       });
       //$('.project').width(300)
       
  for (i = 0; i < task.length; i++) {
	  	if (task[i].start_date==null)
		  task[i].start_date="";
		else
			task[i].start_date=formatDate(task[i].start_date);
		if (task[i].deadline==null)
			task[i].deadline="";
		else
			task[i].deadline=formatDate(task[i].deadline);
		var StatusColor;
		if(task[i].status=="In Progress")
        	StatusColor = '#a5dff9'; 
        else if(task[i].status=="Completed")
        	StatusColor = '#65eea6';
        else if(task[i].status=="Postponed")
        	StatusColor = '#aaaabb';
        else if(task[i].status=="Delayed")
        	StatusColor = '#ec6e57';
	  var taskcard = '<div class="thumbnail" style="background-color:'
	  					+StatusColor+
	  					';"><div class="caption"><div class="col-lg-12"><h4 style="text-align:center;"><b>'
	  					+task[i].name+
	  					'</b></h4><p></p><i>Start Date:</i><div class="pull-right" style="font-size: 1.3rem;">'
	  					+task[i].start_date+
	  					'</div><br><i>Deadline:</i><div class="pull-right" style="font-size: 1.3rem;">'
	  					+task[i].deadline+
	  					'</div></div><button'+' onclick=\'location.href ='+'"updateTaskDetail?id='
	  					+task[i].id +'";'+
	  					'\'class="btn btn-primary btn-xs btn-update btn-add-card ">Update</button>'+
	  					'<button onclick="func('+task[i].id+');"'+
	  					'\' class="btn btn-danger btn-xs btn-update btn-add-card pull-right">Delete</button></div></div>';
      
        
        $("#" + task[i].project_id).append(taskcard);
       }
  
      },
      error : function(err) {
       console.log("Error");
       console.log(JSON.stringify(err));
      }
     });
  }
  func =  function(id)
	{
	swal({
	    title: "Do you want to delete this task?",
	    type: "warning",
	    showCancelButton: true,
	    confirmButtonColor: "#E71D36",
	    confirmButtonText: "Delete",
	    cancelButtonText: "Cancel",
	    closeOnConfirm: false,
	    closeOnCancel: true
	  },
	    function (isConfirm) {
	      if (isConfirm) {
	    	  $.ajax({
	    	     url:'deleteTaskDetail',
	    	     type:'GET',
	    	     data:{id:id},
	    	     traditional: true,
	    	     success: function(response){
	    	      if(response.status=="200")
	    	      {
	    	      setTimeout(function() {
	    	             swal({
	    	                 title: "Done!",
	    	                 text: "You have deleted it successfully!",
	    	                 type: "success"
	    	             }, function() {
	    	                 window.location = "taskAdminView";
	    	             });
	    	         }, 10);

	    	      }

	    	          else 
	    	           {
	    	           swal("Oops!","It is not deleted", "error")

	    	           } 
	    	     },
	    				error: function(err){
	    					
	    					console.log(JSON.stringify(err));
	    					}
	    	       });
	      } 
	    });
	    }
  
  $(document).ready(function() {
   $('li#taskStlye').addClass('active');
   $("#txtbox").keyup(function(){
	   $(".project").each(function(){
 	         
	          $(this).show();
	         
	        });
	     var searchValue = $("#txtbox").val().toLowerCase();
	     	if(searchValue!=null&&searchValue!="")
	     		{
	     		 $(".project").each(function(){
	    	         var title = $(this).attr('project-title'); 
	    	         title = title.toLowerCase();
	    	         var check = title.search(searchValue);
	    	         if(check==-1)
	    	         {
	    	          $(this).hide();
	    	         }
	    	        });
	     		}
	     	else{
	       
	        $(".project").each(function(){
	   	         
	   	          $(this).show();
	   	         
	   	        });
	     	}
	    });
   
  });
 </script>
 	
 <!-- Page Heading -->
 <div class="row">
  <h3 class="page-header">
   Task <a href="taskDetailAdminView" class="btn btn-default pull-right"
    style="margin-right: 40px; margin-bottom: 10px;">Create</a>
  </h3>
 </div>
 <table style="width:75%;">
 <tr>
 </tr>
 <tr>
 <td><i class="fa fa-square" style="font-size:20px;color:#a5dff9"><span style="font-size:20px;color:#a5dff9">&nbsp&nbsp&nbsp&nbsp&nbspIn Progress</span> </i></td>
 <td><i class="fa fa-square" style="font-size:20px;color:#65eea6"><span style="font-size:20px;color:#65eea6">&nbsp&nbsp&nbsp&nbsp&nbspCompleted</span> </i></td>
 <td><i class="fa fa-square" style="font-size:20px;color:#aaaabb"><span style="font-size:20px;color:#aaaabb">&nbsp&nbsp&nbsp&nbsp&nbspPostponed</span> </i></td>
 <td><i class="fa fa-square" style="font-size:20px;color:#ec6e57"><span style="font-size:20px;color:#ec6e57">&nbsp&nbsp&nbsp&nbsp&nbspDelayed</span> </i></td>
 </tr>
 </table><br>
 <div class="col-lg-4 pull-right">
            <div class="form-group">
                  <div class="input-group">
                    <input type="text" class="form-control"
                           placeholder="Type to search for any project..." id="txtbox"/>
                    <span class="input-group-addon">
                        <i class="fa fa-search"></i>
                    </span>
                </div>
            </div>
        </div>
 <div class="project-list__wrapper">
  <div class="inner">
   <div class="projects"></div>
  </div>
 </div>














    