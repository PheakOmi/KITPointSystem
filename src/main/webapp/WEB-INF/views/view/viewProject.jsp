
<script type="text/javascript">
	formatDate =function (date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	
	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
	
	    return [month, day, year].join('-');
	};
	load = function(){	
		$("li#id1").addClass("active");
		$.ajax({
			url:'getProject',
			type:'POST',
			success: function(response){
					console.log(response);
					project=response.project;
					
					for(i=0; i<response.project.length; i++){
						project[i].deadline=formatDate(project[i].deadline);
						project[i].start_date=formatDate(project[i].start_date);
						
						var projectDiv =
						"<div class='col-sm-4' data-project-status='"+response.project[i].status+
						"'>"+
						"<div class='panel panel-success'>"+
							"<div class='panel-heading'>"+
								"<h3 class='panel-title'>"+ response.project[i].project_name+"</h3>"+
                            "</div>"+
                            "<div class='panel-body'>"+
                               "<label>Progress</label>"+
                  	           "<div class='progress'>"+
                  			   "<div class='progress-bar' role='progressbar' aria-valuenow='40' aria-valuemin='0' aria-valuemax='100' style='width: 90%;'><span class='sr-only'>60% Complete</span>"+
                    		   "</div>"+
                			   "</div>"+
                
                               "<div class='row'>"+
                                    "<div class='col-xs-6'>"+
                                    "<label>Start Date :</label>"+response.project[i].start_date+
                                    "</div>"+
                                    "<div class='col-xs-6'>"+
                                    "<label>Deadline :</label>"+response.project[i].deadline+
                                    "</div>"+
                               "</div>"+
                               "<div class='row'>"+
                               "<div class='col-xs-6'>"+
                               "</div>"+
                               "<div class='col-xs-6 text-right'>"+ 
                               "<div class='huge'>"+response.project[i].kit_point+"</div>"+ 
                                   "<div>KIT point</div>"+ 
                                   "</div>"+
                          "</div>"+
                            "</div>"+
                              "<a href='updateProjectDetail?id="+ response.project[i].id+"'>"+
                                "<div class='panel-footer'>"+
                                    "<span class='pull-left'>View Details</span>"+
                                    "<span class='pull-right'><i class='fa fa-arrow-circle-right'></i></span>"+
                                    "<div class='clearfix'></div>"+
                                "</div>"+
                            "</a>"+
                        "</div>"+
                        "</div>";
						
						$("#project").append(projectDiv);

					}
			}				
		});	
	}

	

	showProjectBasedStatus=function(statusData){
		var project_status;
		if (statusData=="Completed Project")
		{
			$("li#id5").addClass("active");
			$("#id1").removeClass("active");
			$("#id2").removeClass("active");
			$("#id3").removeClass("active");
			$("#id4").removeClass("active");
			project_status="Completed Project";
		}
		else if (statusData=="Approved Project")
		{
			$("li#id2").addClass("active");
			$("#id1").removeClass("active");
			$("#id3").removeClass("active");
			$("#id4").removeClass("active");
			$("#id5").removeClass("active");
			
			project_status="Approved Project";
		}
		else if (statusData=="To approve Project")
		{
			$("li#id3").addClass("active");			
			$("#id2").removeClass("active");
			$("#id1").removeClass("active");
			$("#id2").removeClass("active");
			$("#id4").removeClass("active");
			$("#id5").removeClass("active");
			project_status="To approve Project";
		}
		else if (statusData=="Pending Project")
		{
			$("li#id4").addClass("active");
			$("#id1").removeClass("active");
			$("#id2").removeClass("active");
			$("#id3").removeClass("active");
			$("#id5").removeClass("active");
			project_status="Pending Project";
		}	else 
		{
			$("#id2").removeClass("active");
			$("#id3").removeClass("active");
			$("#id4").removeClass("active");
			$("#id5").removeClass("active");
			$("div #project").html('');
			load();
			return;
		}
		$.ajax({
			url:'getProjectBasedOnStatus',
			type:'POST',
			data:{status:project_status},
			success: function(response){
				console.log(response);
				project=response.project;
				$("div #project").html('');
				for(i=0; i<response.project.length; i++){
					project[i].deadline=formatDate(project[i].deadline);
					project[i].start_date=formatDate(project[i].start_date);
					
					var projectDiv =
						"<div class='col-sm-4' data-project-status='"+response.project[i].status+
						"'>"+
						"<div class='panel panel-success'>"+
							"<div class='panel-heading'>"+
								"<h3 class='panel-title'>"+ response.project[i].project_name+"</h3>"+
                            "</div>"+
                            "<div class='panel-body'>"+
                               "<label>Progress</label>"+
                  	           "<div class='progress'>"+
                  			   "<div class='progress-bar' role='progressbar' aria-valuenow='40' aria-valuemin='0' aria-valuemax='100' style='width: 90%;'><span class='sr-only'>60% Complete</span>"+
                    		   "</div>"+
                			   "</div>"+
                
                               "<div class='row'>"+
                                    "<div class='col-xs-6'>"+
                                    "<label>Start Date :</label>"+response.project[i].start_date+
                                    "</div>"+
                                    "<div class='col-xs-6'>"+
                                    "<label>Deadline :</label>"+response.project[i].deadline+
                                    "</div>"+
                               "</div>"+
                               "<div class='row'>"+
                               "<div class='col-xs-6'>"+
                               "</div>"+
                               "<div class='col-xs-6 text-right'>"+ 
                               "<div class='huge'>"+response.project[i].kit_point+"</div>"+ 
                                   "<div>KIT point</div>"+ 
                                   "</div>"+
                          "</div>"+
                            "</div>"+
                              "<a href='updateProjectDetail?id="+ response.project[i].id+"'>"+
                                "<div class='panel-footer'>"+
                                    "<span class='pull-left'>View Details</span>"+
                                    "<span class='pull-right'><i class='fa fa-arrow-circle-right'></i></span>"+
                                    "<div class='clearfix'></div>"+
                                "</div>"+
                            "</a>"+
                        "</div>"+
                        "</div>";
					$("#project").append(projectDiv);
					}
				}
			});
		
	}

</script>
<body onload="load();">
	    <div class="navbar navbar-default">
                    <div class="container" data-project-status="">
                        <div class="navbar-collapse collapse">
                            <ul class="nav navbar-nav" >
                                <li id="id1" onclick='showProjectBasedStatus("all");'><a>ALL</a>
                                </li>
                                <li id="id2" onclick='showProjectBasedStatus("Approved Project");'><a >Approved Project</a>
                                </li>
                                <li id="id3" onclick='showProjectBasedStatus("To approve Project");'><a>To approve Project</a>
                                </li>
                                <li id="id4" onclick='showProjectBasedStatus("Pending Project");'><a>Pending Project</a>
                                </li>
                                <li id="id5" onclick='showProjectBasedStatus("Completed Project");'><a>Completed Project</a>
                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
                <a href="projectDetail" class="btn btn-default">Create</a>
                <br><br>
                <div class="row">
                
                <div id="project">
                </div>
         </div>

                
                
                
               
                


