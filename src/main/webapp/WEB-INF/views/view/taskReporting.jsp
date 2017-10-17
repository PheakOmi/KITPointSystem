<body onload="load()">
<script type="text/javascript">
load = function(){
	$('#mybtn').trigger('click');
}
</script>
<button style="display: none;"class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="mybtn">
    Login modal</button>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
    aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="location.href = 'reporting';">
                   <span class="glyphicon glyphicon-remove"></span></button>
                <h4 class="modal-title" id="myModalLabel">Task Lists Report</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#Login" data-toggle="tab">Which projects?</a></li>
                            <li><a href="#Registration" data-toggle="tab">Format</a></li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane active" id="Login">
                                <form role="form" class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        Category</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="category">
                                        	<option>-</option>
                                        	<option>b</option>
                                        	<option>c</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        Coordinator</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="coordinator">
                                        	<option>a</option>
                                        	<option>b</option>
                                        	<option>c</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        Team Leader</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="teamleader">
                                        	<option>a</option>
                                        	<option>b</option>
                                        	<option>c</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        Status</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="status">
                                        <option value="Approved Project">Approved Project</option>
                                    	<option value="To approve Project">To approve Project</option>
                                    	<option value="Pending Project">Pending Project</option>
                                    	<option value="Completed Project">Completed Project</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        From</label>
                                    <div class="col-sm-4 pull-right">
                                        <input class="form-control" id="from"  name="date" placeholder="MM/DD/YYY" type="text" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1" class="col-sm-2 control-label">
                                        To</label>
                                    <div class="col-sm-4 pull-right">
                                        <input class="form-control" id="to"  name="date" placeholder="MM/DD/YYY" type="text" required/>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                    </div>
                                    <div class="col-sm-10">
                                        
                                    </div>
                                </div>
                                </form>
                            </div>
                            <div class="tab-pane" id="Registration">
                                <form role="form" class="form-horizontal">
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        Report Format</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="status">
                                        	<option>In PDF Format</option>
                                        	<option>In Excel Format</option>
                                        </select>
                                    </div>
                                </div>
                               
                                </form>
                            </div>
                        </div>
                       
                    </div>
                    
                </div>
                <div class="modal-header">
                <button class="btn btn-default btn-md" onclick="location.href = 'reporting';">Cancel</button>
                <button type="submit" class="btn btn-primary btn-md" style="background-color:#51a351;border-color:#51a351;">
                                            Generate Report</button>
                
            </div>
            </div>
        </div>
    </div>
</div>
</body>
