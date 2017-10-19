<body onload="load()">
<div class="row " id="margin-body">
	<div class="col-sm-12">
	<h2>Please select batch to view or update students' points</h2>
       	<div class="center">
       	<br><br><br><br><br><br>
       	
  <select name="sources" id="sources" class="custom-select sources" placeholder="Batch">
  		<option value="0"></option>
  </select>
  <button type="button" onclick="view()" class="btn btn-warning btn-lg" style="margin-right: 100px;margin-left: 100px">View or Update Points</button>
  </div>
</div>
<script type="text/javascript">
load = function(){	
	$.ajax({
		url:'getBatchList',
		type:'GET',
		success: function(response){
				console.log(response);
				data = response.batch;
				for(i=0; i<data.length; i++){					
					$("#sources").append("<option value='"+response.batch[i].id+"'>"+response.batch[i].name+" </option>");
				}
				$(".custom-select").each(function() {
					  var classes = $(this).attr("class"),
					      id      = $(this).attr("id"),
					      name    = $(this).attr("name");
					  var template =  '<div class="' + classes + '">';
					      template += '<span class="custom-select-trigger">' + $(this).attr("placeholder") + '</span>';
					      template += '<div class="custom-options">';
					      $(this).find("option").each(function() {
					        template += '<span class="custom-option ' + $(this).attr("class") + '" data-value="' + $(this).attr("value") + '">' + $(this).html() + '</span>';
					      });
					  template += '</div></div>';
					  
					  $(this).wrap('<div class="custom-select-wrapper"></div>');
					  $(this).hide();
					  $(this).after(template);
					});
					$(".custom-option:first-of-type").hover(function() {
					  $(this).parents(".custom-options").addClass("option-hover");
					}, function() {
					  $(this).parents(".custom-options").removeClass("option-hover");
					});
					$(".custom-select-trigger").on("click", function() {
					  $('html').one('click',function() {
					    $(".custom-select").removeClass("opened");
					  });
					  $(this).parents(".custom-select").toggleClass("opened");
					  event.stopPropagation();
					});
					$(".custom-option").on("click", function() {
					  $(this).parents(".custom-select-wrapper").find("select").val($(this).data("value"));
					  $(this).parents(".custom-options").find(".custom-option").removeClass("selection");
					  $(this).addClass("selection");
					  $(this).parents(".custom-select").removeClass("opened");
					  $(this).parents(".custom-select").find(".custom-select-trigger").text($(this).text());
					});
		}				
	});
	
}
view = function (){
	var id = $("#sources").val();
	if (id==0)
		{
			swal("Oops!", "Please choose any batch!", "error")
			return
		}
	else{
		location.href ="viewPoint?id="+id;
	}
}
update = function (){
	var id = $("#sources").val();
	if (id==0)
		{
			swal("Oops!", "Please choose any batch!", "error")
			return
		}
	else{
		location.href ="updatePoint?id="+id;
	}
}
$(document).ready(function() {
    $('li#settingStlye').addClass('active');
});
</script>
</body>
