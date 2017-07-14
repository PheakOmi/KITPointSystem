<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kanban Board</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">
<style>
.project-list__wrapper {
	position: relative;
	width: 100%;
	overflow: hidden;
}

.project-list__wrapper::after, .project-list__wrapper::before,
	.project-list__wrapper>.inner::after, .project-list__wrapper>.inner::before,
	.project-list__wrapper .projects::after, .project-list__wrapper .projects::before,
	.project .project-tasks__wrapper::after, .project .project-tasks__wrapper::before
	{
	display: table;
	position: relative;
	content: " ";
}

.project-list__wrapper>.inner::after, .project-list__wrapper::after,
	.project-list__wrapper .projects::after, .project .project-tasks__wrapper::after
	{
	clear: both;
}

.project-list__wrapper>.inner {
	position: relative;
	width: 100%;
}

.project-list__wrapper .projects {
	position: relative;
	width: auto;
	padding: 25px 0;
	overflow-x: auto;
	display: flex;
}

.project-list__wrapper .projects .project.card {
	position: relative;
	min-width: 220px;
	float: left;
	margin-right: 15px;
	display: inline-block;
	background: transparent;
	box-shadow: 1px 0px 1px rgba(0, 0, 0, 0.1);
	border: 1px solid #ddd;
	border-radius: 3px;
}

.project.card .card-heading {
	width: 100%;
	padding: 10px;
	border-bottom: 1px solid #ddd;
	margin-bottom: 15px;
	background: #f5f5f5;
}

.project.card .card-heading h3 {
	color: #4a4a4a;
	font-weight: 600;
	font-size: 1.8rem;
}

.project.card .card-body {
	width: 100%;
	padding: 10px 10px 20px 10px;
	background: transparent;
}

.project .project-tasks__wrapper {
	position: relative;
	width: 100%;
}

.project-tasks__wrapper .project-task {
	width: 100%;
	float: left;
	margin: 5px 0;
	padding: 10px;
	background: #f1f1f1;
	border-radius: 3px;
	box-shadow: 1px 0px 1px rgba(0, 0, 0, 0.2);
}
</style>

</head>

<body onload="load();">
	<script type="text/javascript">
		load = function() {
			$.ajax({
				url : 'ProjectNTask',
				method : 'POST',
				success : function(response) {
					task = response.task;
					project = response.project;
					console.log(response);
					
					for (i=0;i<project.length;i++)
						{
						var projectcard =
							   '<div class="project card">' +
							    '<div class="card-heading">' +
							     '<h3>'+project[i].project_name+'</h3>' +
							    '</div>' +
							    '<div class="card-body">'+
							     '<div class="project-tasks__wrapper"'+'id='+project[i].id+'>'+
							     '</div>'+
								    '</div>'+
								   '</div>';
								   $(".projects").append(projectcard);
							for (j=0;j<task.length;j++)
								{
								
								if(task[j].project_id==project[i].id)	
									{var taskcard= 
										'<a href="updateTaskDetail?id='+task[j].id+'">'+'<div class="project-task">'+ 
									       '<p>'+task[j].name+'</p>'+
									      '</div>'+"</a>";
									$(".project-tasks__wrapper").append(taskcard);
								}
								}
							     
						
						}
				},
				error : function(err) {
					console.log("Error");
					console.log(JSON.stringify(err));
				}

			});
		}
	</script>
	<div class="project-list__wrapper">
		<div class="inner">
			<div class="projects">
				
				
				
					
			</div>
		</div>
	</div>

</body>
</html>




