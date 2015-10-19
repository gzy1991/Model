$(function ()
		{
	getNews();
	$(".newCM").click(function()
			{
		var ID=$(this).attr("id");
		localStorage.pageID=ID;
		 window.location.href="a_zxxw_content.html";
		
			})
		})

function getNews()
{
	
	$.ajax(
			  {
				  type:"GET",
				  url:"../news/get3Page",
				  dataType:"json",
				  async: false,
				  success:function(data)
				  {
					  $("#newC").empty();
					  var html=' ';
					  var date;
					  var title;
					  var content;
					  var d;
					  var y;
					  var newid;
					  
					  $.each(data,function(commentIndex,comment){
						  date=comment['publishDate'];
						  title=comment['newsName'];
						  content=comment['content'];
						  newid=comment["newsId"];
						  d=date[8]+date[9];
						  y=date[0]+date[1]+date[2]+date[3]+"."+date[5]+date[6];
						 
						  html+='<div class="news">'+
						  	'<div class="date">'+
						  		'<font class="d">'+d+'</font>'+
						  		'<font class="m">'+y+'</font>'+
						  	'</div>'+
						  	'<div class="newsContent">'+
						  		'<a class="newsTitle">'+title+'</a>'+
						  		'<p class="newsText">'+content+
						  		'<button class="newCM" id="'+newid+'">详细>></button></p></div></div>';});
					  $("#newC").html(html);
					   }
			 
			  });
	
					  
	
	
	
}