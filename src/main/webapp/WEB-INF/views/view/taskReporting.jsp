<style type="text/css">
</style>
<body onload="load()" style="overflow:scroll;">
<script type="text/javascript">
var projectdata;
	
	
load = function(){	
	$('#mybtn').trigger('click');
	$.ajax({
		url:'ProjectNUser',
		type:'GET',
		success: function(response){
			p=response;
			student = response.student;
			project = response.project;
			for(i=0; i<project.length; i++)					
				$("#project").append("<option value="+project[i].id+">"+project[i].project_name+" </option>");
			for(i=0; i<student.length; i++)
				$("#assigned_to").append("<option value="+student[i].id+">"+student[i].name+" </option>");
		},
	error: function(err){
		
		console.log(JSON.stringify(err));
		}
		
	});
	
}


	
$(document).ready(function(){
	$('#project').on('change', function() {
		console.log("Changed");
		var projectid = this.value;
		if(projectid==0)
			{
			response=p.student;
			$('#assigned_to')
		    .find('option')
		    .remove()
		    .end();
			for(i=0; i<response.length; i++)
				$("#assigned_to").append("<option value="+response[i].id+">"+response[i].name+" </option>");
			}
		else{
		$.ajax({
			url:'studentInTask',
			type:'GET',
			data: {id: projectid},
			success: function(response){
			console.log(response);
			student=response.student;
			$('#assigned_to')
		    .find('option')
		    .remove()
		    .end();

			for(i=0; i<student.length; i++)
				$("#assigned_to").append("<option value="+student[i].user_id+">"+student[i].user_name+" </option>");
			
			},
			error: function (err)
			{
				console.log(JSON.stringify(err));
			}
		});	  
		}
		})
		
		
	$("[name=date]").keydown(function (event) {
	    event.preventDefault();
	});
	$("#submitBtn").click(function(e){
		e.preventDefault();
		var t = $("#to").val();
		var f = $("#from").val();
        if(t==""||f=="")
        	{
        		swal("Oops!", "Both From and End has to be filled out", "error")
        		return
        	}
		else
		{
			f = Date.parse(f);
	        t = Date.parse(t);
			if(f>t)
	    		{
					swal("Oops!", "Your End Date is before Start Date", "error")
					return
				}
			else
			{
    	$.ajax({
    		url:'taskReportSubmit',
    		type:'GET',
    		data:{		status:$("#status").val(),
    					project_id:$("#project").val(),
    					assigned_to:$("#assigned_to").val(),
    					start_date:$("#from").val(),
    					end_date:$("#to").val(),},
    		traditional: true,			
    		success: function(response){
    				console.log(response);
    				if(response.status=="200")
    					 showTable(response.data);
    				else 
						swal("Oops!", "There is an error while querying data", "error")
					
 			},
    		error: function(err){
    				console.log(JSON.stringify(err));
    				
    				}
    		
    			});	
		}
		}
	});		
});
showTable = function(tasks){
	student = p.student;
	project = p.project;
	console.log(p);
	for (i=0;i<tasks.length;i++)
	{
		for(j=0;j<project.length;j++)
			{
			if(tasks[i].project_id==project[j].id)
				{
				tasks[i].project_id=project[j].project_name;
					break;
				}
			}

		for(l=0;l<student.length;l++)
		{
			if(tasks[i].assigned_to==student[l].id)
				{
				tasks[i].assigned_to = student[l].name;
					break;
				}
		}
	}
	$("#myModal").hide();
	for (i=0;i<tasks.length;i++)
		{
		if (tasks[i].start_date==null)
			tasks[i].start_date="";
		else
			tasks[i].start_date=formatDate(tasks[i].start_date);
		if (tasks[i].end_date==null)
			tasks[i].end_date="";
		else
			tasks[i].end_date=formatDate(tasks[i].end_date);
		if (tasks[i].deadline==null)
			tasks[i].deadline="";
		else
			tasks[i].deadline=formatDate(tasks[i].deadline);
		var row = "<tr><td>"+(i+1)+"</td>"+
						"<td>"+tasks[i].name+"</td>"+
						"<td>"+tasks[i].project_id+"</td>"+
						"<td>"+tasks[i].assigned_to+"</td>"+
						"<td class='ttime'>"+tasks[i].time_spend+"</td>"+
						"<td>"+tasks[i].start_date+"</td>"+
						"<td>"+tasks[i].end_date+"</td>"+
						"<td>"+tasks[i].deadline+"</td>"+
						"<td>"+tasks[i].status+"</td></tr>";
		$("#customers").append(row);
		}
	$(".ttime").hide();
	swal("Succeed!", tasks.length+" task(s) were found", "success")
	$("#customers").removeAttr('style');
	$("#btnGenerate").removeAttr('style');
}
generateReport = function()
{
	if($("#format").val()=='excel')
		{
			var data_type = 'data:application/vnd.ms-excel';
		    var table_div = document.getElementById('tablewrapper');
		    var table_html = table_div.outerHTML.replace(/ /g, '%20');
		
		    var a = document.createElement('a');
		    a.href = data_type + ', ' + table_html;
		    a.download = 'Tasks_Report' + Math.floor((Math.random() * 9999999) + 1000000) + '.xls';
		    a.click();
		}
	else
		$('#btn').trigger('click');
}


function HTMLtoPDF(){
	var pdf = new jsPDF('p', 'pt', 'letter');
	source = $('#tablewrapper')[0];
	specialElementHandlers = {
		'#bypassme': function(element, renderer){
			return true
		}
	}
	margins = {
	    top: 50,
	    left: 20,
	    right: 20,
	    width: 700
	  };
	pdf.fromHTML(
	  	source // HTML string or DOM elem ref.
	  	, margins.left // x coord
	  	, margins.top // y coord
	  	, {
	  		'width': margins.width // max width of content on PDF
	  		, 'elementHandlers': specialElementHandlers
	  	},
	  	function (dispose) {
	  	  // dispose: object with X, Y of the last line add to the PDF
	  	  //          this allow the insertion of new lines after html
	        pdf.save('html2pdf.pdf');
	      }
	  )		
	}
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [month, day, year].join('/');
};
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
                <h4 class="modal-title" id="myModalLabel">Tasks List Report</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#Login" data-toggle="tab">Which task?</a></li>
                            <li><a href="#Registration" data-toggle="tab">Format</a></li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane active" id="Login">
                                <form role="form" class="form-horizontal" id="myForm">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        Project</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="project">
                                        	<option value="0"></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        Assigned To</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="assigned_to">
                                        	<option value="0"></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        Status</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="status">
                                        <option></option>
                                        <option value="In Progress">In Progress</option>
	                                    <option value="Completed">Completed</option>
	                                    <option value="Delayed">Delayed</option>
	                                    <option value="Postponed">Postponed</option>
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
                                        <select class="form-control" id="format">
                                          	<option value="excel">In Excel Format</option>
                                        	<option value="pdf">In PDF Format</option>
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
                <button type="submit" class="btn btn-primary btn-md" id ="submitBtn" style="background-color:#51a351;border-color:#51a351;">
                                            View before generating</button>
                
            </div>
            </div>
        </div>
    </div>
    
   
<div class="row " id="margin-body">
<div id="tablewrapper">
<table id="customers" style="display:none;">
  <tr>
    <th>No</th>
    <th>Name</th>
    <th>Project</th>
    <th>Assigned To</th>
    <th class="ttime">Time Spent</th>
    <th>Start</th>
    <th>End</th>
    <th>Deadline</th>
    <th>Status</th>
  </tr>
</table>
</div>
<div id="editor"></div>
<br>
<br>
       <button onclick="generateReport()" type="button" class="btn btn-success pull-right" id="btnGenerate" style="display:none;">Generate Report</button> 
<button class="create_pdf" myprint="#tablewrapper" id="btn" style="display:none;">Generate PDF</button>
</div>
</body>
