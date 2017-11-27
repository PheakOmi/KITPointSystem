<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">

$(document).ready(function() {
    $('li#projectStlye').addClass('active');
    
    $("#txtbox").keyup(function(){
    	var searchValue = $("#txtbox").val().toLowerCase();
    	console.log("sdsd");
 	   $("#project .col-sm-4").each(function(){
 		  var thisParent = this;
 		  if(searchValue!=null&&searchValue!="")
	   		{
 			 var title = $(this).find(".panel-title").attr('project-title');
	   		 title = title.toLowerCase();
	  	     if(!title.includes(searchValue))
	  	       {
	  	         $(thisParent).hide();
	  	         
	  	       }else{
	  	    	 $(thisParent).show();
	  	       }
	  	       
	   		}
	   		else{
	     
	      		$("#project .col-sm-4").each(function(){
	      			$(this).show();
	 	      	});
	   		}
 	   });
 	    
	});
});
		formatDate =function (date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	
	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
	
	    return [month, day, year].join('-');
	};
	func =  function(id)
	{
	swal({
	    title: "Do you want to delete this project?",
	    text: "All information related to this project will be deleted!",
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
	    	     url:'deleteProjectDetail',
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
	    	                 window.location = "project";
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

	load = function(){	
		$("li#id1").addClass("active");
		$.ajax({
			url:'getProjectforAdmin',
			type:'GET',
			success: function(response){
					
					project=response.project;
					for(i=0; i<project.length; i++){
						
						if (project[i].kit_point==""||project[i].kit_point==null)
							project[i].kit_point="0.00";
						if (project[i].start_date==null||project[i].start_date=="")
							project[i].start_date="";
						else
							project[i].start_date=formatDate(project[i].start_date);
						if (project[i].deadline==null||project[i].deadline=="")
							project[i].deadline="";
						else
							project[i].deadline=formatDate(project[i].deadline);
						
						var panel, approved_button="";
						 switch(project[i].status){
							case "To approve Project":
								panel="panel-default";
								/* approved_button="<button type='submit' class='btn btn-warning' onclick='approveProject("+project[i].id+");'>Approve</button>";
								 */
								break;
							case "Approved Project":
								panel="panel-primary";
								break;
							case "Completed Project":
								panel="panel-success";
								break;
							case "Pending Project":
								panel="panel-danger";
								break;
							default:
								panel="panel-default";
								break;
						
						 }
						var projectDiv =
						"<div class='col-sm-4' data-project-status='"+project[i].status+
						"'>"+
						"<div class='panel "+panel+"'>"+
							"<div class='panel-heading'>"+
								"<h3 class='panel-title' project-title='"+project[i].project_name+"'>"+ project[i].project_name+"</h3>"+
                            "</div>"+
                            "<div class='panel-body'>"+
                               
                
                               "<div class='row'>"+
                                    "<div class='col-xs-6'>"+
                                    "<label>Start Date :</label>"+project[i].start_date+
                                    "</div>"+
                                    "<div class='col-xs-6'>"+
                                    "<label>Deadline :</label>"+project[i].deadline+
                                    "</div>"+
                               "</div>"+
                               "<div class='row'>"+
                               "<div class='col-xs-6'>"+approved_button+
                               "</div>"+
                               "<div class='col-xs-6 text-right'>"+ 
                               "<div class='huge'>"+project[i].kit_point+"</div>"+ 
                                   "<div>KIT point</div>"+ 
                                   "</div>"+
                          "</div>"+
                            "</div>"+
                              
                                "<div class='panel-footer'>"+
                                "<a href='updateProjectDetailAdminView?id="+project[i].id+"'>"+
                                    "<span class='pull-left'>View Details</span>"+                                    
                                    "</a>"+
                                    '<div>'+

                                    "</div>"+
                                    "<div class='clearfix'></div>"+
                                   
                                "</div>"+
                            
                        "</div>"+
                        "</div>";
						
						$("#project").append(projectDiv);

					}
			}				
		});	
	}

	 approveProject=function(id){
		var project_id=id;
		$.ajax({
			url:'aprroveTheProject',
			type:'GET',
			data:{id:project_id},
			success: function(response){
				if(response.status=="200")
					{
					location.reload();
					}
				//var obj = jQuery.parseJSON(response);
				    
				else 
					{
					swal("Oops!", "There is an error while updating!", "error")
					
					}
				},
		error: function(err){
				console.log(JSON.stringify(err));

				
			}
			
		})
		
		
	}
	 
	 showProjectOverDeadline=function(){
			$("li#id5").removeClass("active");
			$("#id1").removeClass("active");
			$("#id2").removeClass("active");
			$("#id3").removeClass("active");
			$("#id4").removeClass("active");
			$("#over").addClass("active");
			$("div #project").html('');
			$.ajax({
				url:'getProjectforAdmin',
				type:'GET',
				success: function(response){
						
						project=response.project;
						var today = new Date();
						var dd = today.getDate();
						var mm = today.getMonth()+1; //January is 0!
						var yyyy = today.getFullYear();

						if(dd<10) {
						    dd = '0'+dd
						} 

						if(mm<10) {
						    mm = '0'+mm
						} 

						today = mm + '/' + dd + '/' + yyyy;
						var todaydate = Date.parse(today);
						for(i=0; i<project.length; i++){
							
							
							if (project[i].kit_point==""||project[i].kit_point==null)
								project[i].kit_point="0.00";
							if (project[i].start_date==null||project[i].start_date=="")
								project[i].start_date="";
							else
								project[i].start_date=formatDate(project[i].start_date);
							if (project[i].deadline==null||project[i].deadline=="")
								project[i].deadline="";
							else
								project[i].deadline=formatDate(project[i].deadline);
					        var deadlinedate = Date.parse(project[i].deadline);
					        if(todaydate>deadlinedate && project[i].status!="Completed Project" )
					    {
					        	
					        var panel, approved_button="";
							 switch(project[i].status){
								case "To approve Project":
									panel="panel-default";
									approved_button="<button type='submit' class='btn btn-warning' onclick='approveProject("+project[i].id+");'>Approve</button>";
									
									break;
								case "Approved Project":
									panel="panel-primary";
									break;
								case "Completed Project":
									panel="panel-success";
									break;
								case "Pending Project":
									panel="panel-danger";
									break;
								default:
									panel="panel-default";
									break;
							
							 }
							var projectDiv =
							"<div class='col-sm-4' data-project-status='"+project[i].status+
							"'>"+
							"<div class='panel "+panel+"'>"+
								"<div class='panel-heading'>"+
									"<h3 class='panel-title' project-title='"+project[i].project_name+"'>"+ project[i].project_name+"</h3>"+
	                            "</div>"+
	                            "<div class='panel-body'>"+
	                               
	                
	                               "<div class='row'>"+
	                                    "<div class='col-xs-6' style='color:lightred;'>"+
	                                    "<label style='color:#CC3333;'>Number of days crossed :</label>"+daydiff(deadlinedate,todaydate)+
	                                    " day(s)</div>"+
	                                    "<div class='col-xs-6'>"+
	                                    "<label>Deadline :</label>"+project[i].deadline+
	                                    "</div>"+
	                               "</div>"+
	                               "<div class='row'>"+
	                               "<div class='col-xs-6'>"+approved_button+
	                               "</div>"+
	                               "<div class='col-xs-6 text-right'>"+ 
	                               "<div class='huge'>"+project[i].kit_point+"</div>"+ 
	                                   "<div>KIT point</div>"+ 
	                                   "</div>"+
	                          "</div>"+
	                            "</div>"+
	                              
	                                "<div class='panel-footer'>"+
	                                "<a href='updateProjectDetail?id="+project[i].id+"'>"+
	                                    "<span class='pull-left'>View Details</span>"+                                    
	                                    "</a>"+
	                                    '<div>'+
	                                    "<a href='javascript:func("+project[i].id+")'>"+
	                                    '<span class="pull-right deleteProject">Delete</span>'+ 
	                                    "</a>"+
	                                    "</div>"+
	                                    "<div class='clearfix'></div>"+
	                                   
	                                "</div>"+
	                            
	                        "</div>"+
	                        "</div>";
							
							$("#project").append(projectDiv);

						}
				}
				}				
			});	
		
		
	}
	 
	 
	 function daydiff(first, second) {
		    return Math.round((second-first)/(1000*60*60*24));
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
			$("#over").removeClass("active");
			project_status="Completed Project";
		}
		else if (statusData=="Approved Project")
		{
			$("li#id2").addClass("active");
			$("#id1").removeClass("active");
			$("#id3").removeClass("active");
			$("#id4").removeClass("active");
			$("#id5").removeClass("active");
			$("#over").removeClass("active");
			
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
			$("#over").removeClass("active");
			project_status="To approve Project";
		}
		else if (statusData=="Pending Project")
		{
			$("li#id4").addClass("active");
			$("#id1").removeClass("active");
			$("#id2").removeClass("active");
			$("#id3").removeClass("active");
			$("#id5").removeClass("active");
			$("#over").removeClass("active");
			project_status="Pending Project";
		}	else 
		{
			$("#id2").removeClass("active");
			$("#id3").removeClass("active");
			$("#id4").removeClass("active");
			$("#id5").removeClass("active");
			$("#over").removeClass("active");
			$("div #project").html('');
			load();
			return;
		}
		$.ajax({
			url:'getProjectBasedOnStatusAdmin',
			type:'GET',
			data:{status:project_status},
			success: function(response){
				
				project=response.project;
				$("div #project").html('');
				for(i=0; i<project.length; i++){
					if (project[i].kit_point==""||project[i].kit_point==null)
						project[i].kit_point="0.00";
					if (project[i].start_date==null||project[i].start_date=="")
						project[i].start_date="";
					else
						project[i].start_date=formatDate(project[i].start_date);
					if (project[i].deadline==null||project[i].deadline=="")
						project[i].deadline="";
					else
						project[i].deadline=formatDate(project[i].deadline);
					
					var panel ,approved_button="";
					 switch(project[i].status){
						case "To approve Project":
							panel="panel-default";
							/* approved_button="<button class='btn btn-warning' onclick='approveProject("+project[i].id+")'>Approve</button>"
						 */	break;
						case "Approved Project":
							panel="panel-primary";
							break;
						case "Completed Project":
							panel="panel-success";
							break;
						case "Pending Project":
							panel="panel-danger";
							break;
						default:
							panel="panel-default";
							break;
					
					 }
					var projectDiv =
						"<div id='myform' class='col-sm-4' data-project-status='"+project[i].status+
						"'>"+
						"<div class='panel "+panel+"'>"+
							"<div class='panel-heading'>"+
								"<h3 class='panel-title' project-title='"+project[i].project_name+"'>"+project[i].project_name+"</h3>"+
                            "</div>"+
                            "<div class='panel-body'>"+
  /*                           "<label>Progress</label>"+
                  	           "<div class='progress'>"+
                  			   "<div class='progress-bar' role='progressbar' aria-valuenow='40' aria-valuemin='0' aria-valuemax='100' style='width: 90%;'><span class='sr-only'>60% Complete</span>"+
                    		   "</div>"+
                			   "</div>"+	*/
                
                               "<div class='row'>"+
                                    "<div class='col-xs-6'>"+
                                    "<label>Start Date :</label>"+project[i].start_date+
                                    "</div>"+
                                    "<div class='col-xs-6'>"+
                                    "<label>Deadline :</label>"+project[i].deadline+
                                    "</div>"+
                               "</div>"+
                               "<div class='row'>"+
                               "<div class='col-xs-6'>"+approved_button+
                               "</div>"+
                               "<div class='col-xs-6 text-right'>"+ 
                               "<div class='huge'>"+project[i].kit_point+"</div>"+ 
                                   "<div>KIT point</div>"+ 
                                   "</div>"+
                          "</div>"+
                            "</div>"+
                              
                                "<div class='panel-footer'>"+
                                "<a href='updateProjectDetailAdminView?id="+project[i].id+"'>"+
                                    "<span class='pull-left'>View Details</span>"+ 
                                    
                                    "</a>"+
                                    '<div>'+
                                    "</div>"+
                                    
                                    "<div class='clearfix'></div>"+
                                "</div>"+
                           
                        "</div>"+
                        "</div>";
					$("#project").append(projectDiv);
					}
				}
			});
		
	}
	function relocate_create()
	{
	     location.href = "projectDetail";
	}
	
</script>
<body onload="load();">
                    <div class="container">
                       
                            <ul class="nav nav-tabs" >
                            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
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
                                <li id="over" onclick='showProjectOverDeadline()'><a>Behind-Deadline Project</a>
                                </li>
                            	</ul>
                            	</div>
					          
					           <div class="row">
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
        </div><br><br><br><br><br>
                <div id="project">
                </div>
         </div>
            
        

                
                
                
               
                


