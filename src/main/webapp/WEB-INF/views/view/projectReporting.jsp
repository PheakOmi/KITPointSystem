<body onload="load()" style="overflow:scroll;">
<script type="text/javascript">
load = function(){
	$('#mybtn').trigger('click');
	$.ajax({
	      url : 'userNProjectCategoryList',
	      method : 'GET',
	      success : function(response) {
	    	  	p=response;	
				category = response.category;
				user = response.user;
				student = response.student;
				for(i=0; i<category.length; i++)					
					$("#category").append("<option value="+category[i].id+">"+category[i].name+" </option>");
				for(i=0; i<user.length; i++)
					$("#coordinator").append("<option value="+user[i].id+">"+user[i].name+" </option>");
				for(i=0; i<student.length; i++)
					$("#teamleader").append("<option value="+student[i].id+">"+student[i].name+" </option>");
				},
		error :function(err){
			console.log(JSON.stringify(err));
		}
	      });
}
$(document).ready(function(){
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
    		url:'projectReportSubmit',
    		type:'GET',
    		data:{		status:$("#status").val(),
    					project_type:$("#category").val(),
    					project_co:$("#coordinator").val(),
    					project_leader:$("#teamleader").val(),
    					start_date:$("#from").val(),
    					end_date:$("#to").val(),},
    		traditional: true,			
    		success: function(response){
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
showTable = function(projects){
	category = p.category;
	user = p.user;
	student = p.student;
	console.log(p);
	for (i=0;i<projects.length;i++)
	{
		for(j=0;j<category.length;j++)
			{
			if(projects[i].project_type==category[j].id)
				{
					projects[i].project_type = category[j].name;
					break;
				}
			}
		for(k=0;k<user.length;k++)
		{
			if(projects[i].project_co==user[k].id)
				{
					projects[i].project_co = user[k].name;
					break;
				}
		}
		for(l=0;l<student.length;l++)
		{
			if(projects[i].project_leader==student[l].id)
				{
					projects[i].project_leader = student[l].name;
					break;
				}
		}
	}
	$("#myModal").hide();
	for (i=0;i<projects.length;i++)
		{
		if (projects[i].kit_point==""||projects[i].kit_point==null)
			projects[i].kit_point="0.00";
		if (projects[i].start_date==null)
			projects[i].start_date="";
		else
			projects[i].start_date=formatDate(projects[i].start_date);
		if (projects[i].end_date==null)
			projects[i].end_date="";
		else
			projects[i].end_date=formatDate(projects[i].end_date);
		if (projects[i].deadline==null)
			projects[i].deadline="";
		else
			projects[i].deadline=formatDate(projects[i].deadline);
		var row = "<tr><td>"+(i+1)+"</td>"+
						"<td>"+projects[i].project_name+"</td>"+
						"<td class='pcode'>"+projects[i].project_code+"</td>"+
						"<td>"+projects[i].project_co+"</td>"+
						"<td>"+projects[i].project_leader+"</td>"+
						"<td class='ptype'>"+projects[i].project_type+"</td>"+
						"<td>"+projects[i].start_date+"</td>"+
						"<td>"+projects[i].end_date+"</td>"+
						"<td>"+projects[i].deadline+"</td>"+
						"<td class='pbudget'>"+projects[i].budget+"</td>"+
						"<td class='ppoint'>"+projects[i].kit_point+"</td>"+
						"<td>"+projects[i].status+"</td></tr>";
		$("#customers").append(row);
		}
	$(".pcode").hide();
	$(".ptype").hide();
	$(".pbudget").hide();
	$(".ppoint").hide();
	swal("Succeed!", projects.length+" project(s) were found", "success")
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
		    a.download = 'Projects_Report' + Math.floor((Math.random() * 9999999) + 1000000) + '.xls';
		    a.click();
		}
}


function HTMLtoPDF(){
	var doc = new jsPDF();
	var specialElementHandlers = {
	    '#editor': function (element, renderer) {
	        return true;
	    }
	};

	  
	    doc.fromHTML($('#tablewrapper').html(), 15, 15, {
	        'width': 170,
	            'elementHandlers': specialElementHandlers
	    });
	    doc.save('sample-file.pdf');
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
                <h4 class="modal-title" id="myModalLabel">Projects List Report</h4>
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
                                <form role="form" class="form-horizontal" id="myForm">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        Category</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="category" required>
                                        	<option value="0"></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        Coordinator</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="coordinator" required>
                                        	<option value="0"></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        Team Leader</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="teamleader" required>
                                        	<option value="0"></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        Status</label>
                                    <div class="col-sm-4 pull-right">
                                        <select class="form-control" id="status"required>
                                        <option></option>
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
    <th class ="pcode">Code</th>
    <th>Coordinator</th>
    <th>Leader</th>
    <th class="ptype">Category</th>
    <th>Start</th>
    <th>End</th>
    <th>Deadline</th>
    <th class="pbudget">Budget</th>
    <th class="ppoint">Point</th>
    <th>Status</th>
  </tr>
</table>
</div>
<div id="editor"></div>
<br>
<br>
       <button onclick="generateReport()" type="button" class="btn btn-success pull-right" id="btnGenerate" style="display:none;">Generate Report</button> 
       <button class="create_pdf" onclick="createPDFClick();" myprint="#tablewrapper" id="btn">Generate PDF</button>
</div>
</body>
