<body onload="load();">
 <script type="text/javascript">
  load = function() {
   $
     .ajax({
      url : 'ProjectNTask',
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
        var projectcard = '<div class="project card"'+'id='+project[i].project_name+'>'
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
  for (i = 0; i < task.length; i++) {
	  if (task[i].start_date==null)
		  task[i].start_date="";
		else
			task[i].start_date=formatDate(task[i].start_date);
		if (task[i].deadline==null)
			task[i].deadline="";
		else
			task[i].deadline=formatDate(task[i].deadline);
	  var taskcard = '<div class="a"><a href ="updateTaskDetail?id='
          + task[i].id + '"><div class="thumbnail"><div class="caption"><div class="col-lg-12"><h4><b>'+task[i].name+'</b></h4><p></p><i><b>Start Date:</b></i><div class="pull-right">'+task[i].start_date+'</div><br><i><b>End Date:</b></i><div class="pull-right">'+task[i].deadline+'</div></div><button class="btn btn-primary btn-xs btn-update btn-add-card ">Update</button><button onclick="location.href'+'="project";'+'" class="btn btn-danger btn-xs btn-update btn-add-card pull-right">Delete</button></div></div></a></div>';
      
        if(task[i].status=="In Progress")
        	$(".thumbnail").css('background-color', '#a5dff9'); 
        else if(task[i].status=="Completed")
        	$(".thumbnail").css('background-color', '#56A902');
        else if(task[i].status=="Postponed")
        	$(".thumbnail").css('background-color', '#8b8687');
        $("#" + task[i].project_id).append(taskcard);
       }
      },
      error : function(err) {
       console.log("Error");
       console.log(JSON.stringify(err));
      }
     });
  }
  $(document).ready(function() {
   $('li#taskStlye').addClass('active');
  });
 </script>
 <!-- Page Heading -->
 <div class="row">
  <h3 class="page-header">
   Task <a href="taskDetail" class="btn btn-default pull-right"
    style="margin-right: 40px; margin-bottom: 10px;">Create</a>
  </h3>
 </div>
 <div class="project-list__wrapper">
  <div class="inner">
   <div class="projects"></div>
  </div>
 </div>
</body>














    