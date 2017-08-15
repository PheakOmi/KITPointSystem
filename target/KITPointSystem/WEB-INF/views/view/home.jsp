 <script type="text/javascript">
	load = function(){	
		$.ajax({
			url:'getProjectSatge',
			type:'POST',
			success: function(response){
					console.log(response);
					project_stage = response.listProjectStage;
					for(i=0; i<project_stage.length; i++){					
						$("#project_stage").append(" <li><a href="+project_stage[i].id+">"+Project_stage[i].name+" </a> </li>");
					}
			}				
		});
		
	}
</script>
<body onload="load">
	    <div class="navbar navbar-default">
                    <div class="container">
                        <div class="navbar-collapse collapse">
                            <ul class="nav navbar-nav" id="project_stage">
                                <li class="active"><a href="#">ALL</a>
                                </li>
                               
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Project name</h3>
                            </div>
                            <div class="panel-body">
                  	          <label>Tasks</label> 
                  	          <div class="progress">
                  				<div class="progress-bar" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"><span class="sr-only">60% Complete</span>
                    				</div>
                				</div>
            		      <div class="row">
                                 <!--Replace with image of member-->
                                    <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                        </div>
                                        <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                    </div>
                                    <div class="col-xs-8 text-right">
                                        <div class="huge">16</div>
                                        <div>KIT point</div>
                                    </div>
                                </div>
                            </div>
                             <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Project Name</h3>
                            </div>
                            <div class="panel-body">
                                <label>Tasks</label> 
                  	          <div class="progress">
                  				<div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"><span class="sr-only">60% Complete</span>
                    				</div>
                				</div>
                
                                   <div class="row">
                                 <!--Replace with image of member-->
                                    <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                        </div>
                                        <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                    </div>
                                    <div class="col-xs-8 text-right">
                                        <div class="huge">9</div>
                                        <div>KIT point</div>
                                    </div>
                                </div>
                            </div>
                          <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                      
                    </div>
                    <!-- /.col-sm-4 -->
                    <div class="col-sm-4">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <h3 class="panel-title">Project Name</h3>
                            </div>
                            <div class="panel-body">
                               <label>Tasks</label> 
                  	          <div class="progress">
                  				<div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 90%;"><span class="sr-only">60% Complete</span>
                    				</div>
                				</div>
                
                                  <div class="row">
                                 <!--Replace with image of member-->
                                    <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                        </div>
                                        <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                    </div>
                                    <div class="col-xs-8 text-right">
                                        <div class="huge">16</div>
                                        <div>KIT point</div>
                                    </div>
                                </div>
                            </div>
                              <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">Project Name</h3>
                            </div>
                            <div class="panel-body">
                                <label>Tasks</label> 
                  	          <div class="progress">
                  				<div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width: 18%;"><span class="sr-only">60% Complete</span>
                    				</div>
                				</div>
                
                                   <div class="row">
                                 <!--Replace with image of member-->
                                    <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                        </div>
                                        <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                    </div>
                                    <div class="col-xs-8 text-right">
                                        <div class="huge">17</div>
                                        <div>KIT point</div>
                                    </div>
                                </div>
                            </div>
                                 <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <!-- /.col-sm-4 -->
                    <div class="col-sm-4">
                        <div class="panel panel-warning">
                            <div class="panel-heading">
                                <h3 class="panel-title">Project Name</h3>
                            </div>
                            <div class="panel-body">
                                <label>Tasks</label> 
                  	          <div class="progress">
                  				<div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%;"><span class="sr-only">60% Complete</span>
                    				</div>
                				</div>
                
                                   <div class="row">
                                 <!--Replace with image of member-->
                                    <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                        </div>
                                        <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                    </div>
                                    <div class="col-xs-8 text-right">
                                        <div class="huge">12</div>
                                        <div>KIT point</div>
                                    </div>
                                </div>
                            </div>
                              <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <h3 class="panel-title">Project Name</h3>
                            </div>
                            <div class="panel-body">
                                <label>Tasks</label> 
                  	          <div class="progress">
                  				<div class="progress-bar" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100" style="width: 10%;"><span class="sr-only">60% Complete</span>
                    				</div>
                				</div>
                
                                   <div class="row">
                                 <!--Replace with image of member-->
                                    <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                        </div>
                                        <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                    </div>
                                    <div class="col-xs-8 text-right">
                                        <div class="huge">14</div>
                                        <div>KIT point</div>
                                    </div>
                                </div>
                            </div>
                                 <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <!-- /.col-sm-4 -->
                    <div class="col-sm-4">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <h3 class="panel-title">Project Name</h3>
                            </div>
                            <div class="panel-body">
                                <label>Tasks</label> 
                  	          <div class="progress">
                  				<div class="progress-bar" role="progressbar" aria-valuenow="28" aria-valuemin="0" aria-valuemax="100" style="width: 80%;"><span class="sr-only">100% Complete</span>
                    				</div>
                				</div>
                
                                  <div class="row">
                                 <!--Replace with image of member-->
                                    <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                        </div>
                                        <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                    </div>
                                    <div class="col-xs-8 text-right">
                                        <div class="huge">10</div>
                                        <div>KIT point</div>
                                    </div>
                                </div>
                            </div>
                                 <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <!-- /.col-sm-4 -->
                    <div class="col-sm-4">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <h3 class="panel-title">Project Name</h3>
                            </div>
                            <div class="panel-body">
                                <label>Tasks</label> 
                  	          <div class="progress">
                  				<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"><span class="sr-only">60% Complete</span>
                    				</div>
                				</div>
                
                                 <div class="row">
                                 <!--Replace with image of member-->
                                    <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                        </div>
                                        <div class="col-xs-2">
                                        <i class="fa fa-user fa-3x"></i>
                                    </div>
                                    <div class="col-xs-8 text-right">
                                        <div class="huge">16</div>
                                        <div>KIT point</div>
                                    </div>
                                </div>
                            </div>
                               <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                          
                    </div>
                    <!-- /.col-sm-4 -->
                
                </div>
                