$(function ()
		{
	
		var ID=localStorage.pageID;
		
		 $.ajax(
				  {
					  type:"GET",
					  url:"../news/getByID",
					  data:{"id": ID},
					  
					  async: false,
					  success:function(data)
					  {
						  $(".content-list").empty();
						  var html=' ';
						  var newsName=data.newsName;
						  var newsWriter=data.publishName;
						  var newsData=data.publishDate;
						  var newsContent=data.content;
						 html+='<h2>'+newsName+'</h2>'+'<span>'+newsWriter+'</span>&nbsp&nbsp&nbsp<span>'+newsData+'</span>'
						 +'<p>'+newsContent+'</p>';
						  $(".content-list").html(html);
						 
						  
					  }
				  });
		
			
		})