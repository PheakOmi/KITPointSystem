
<script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'getProject',
			type:'POST',
			success: function(response){
					console.log(response);
					project=response.project;

					for(i=0; i<response.project.length; i++){
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
                                    "<div class='col-xs-2'>"+
                                    "</div>"+
                                    "<div class='col-xs-8 text-right'>"+ 
                                    "<div class='huge'>"+response.project[i].kit_point+"</div>"+ 
                                        "<div>KIT point</div>"+ 
                                        "</div>"+
                               "</div>"+
                            "</div>"+
                              "<a href='#'>"+
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

	
	$(document).ready(function(){
	    $(".approve_project").click(function(){
	    	$( "data-project-status" ).filter( "." )
	    	    ;
	    });
	    $(".btn2").click(function(){
	        $("p").show();
	    });
	});

	showProjectBasedStatus=function (status){
		var x = document.getElementsByTagName("DIV")[0].getAttribute("data-project-status"); 
		if (status=='all'){
			
		}
		else if (status!=x)
		{
			div.style.display = "none";
		}
		
	}

</script>
<body onload="load();">
	    <div class="navbar navbar-default">
                    <div class="container" data-project-status="">
                        <div class="navbar-collapse collapse">
                            <ul class="nav navbar-nav" id="project-stage" onclick="showProjectBasedStatus(this.value)">
                                <li class="active" value="all"><a>ALL</a>
                                </li>
                                <li class="approve_project" value ="Approved Project"><a>Approved Project</a>
                                </li>
                                <li value ="To approve Project"><a>To approve Project</a>
                                </li>
                                <li value ="Pending Project"><a>Pending Project</a>
                                </li>
                                <li value ="Completed Project"><a>Completed Project</a>
=======
                                <li value ="Approved Project"><a href="#about">Approved Project</a>
                                </li>
                                <li value ="To approve Project"><a href="#contact">To approve Project</a>
                                </li>
                                <li value ="Pending Project"><a href="#contact">Pending Project</a>
                                </li>
                                <li value ="Completed Project"><a href="#contact">Completed Project</a>
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

                
                
                
               
                


