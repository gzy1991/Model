var page=1;
var last=0;	

$(function ()
{
	getTotPage();
    getNews(page);
    
   
  
})

//获取相应的页
function getNews(page)
{
	$.ajax(
			  {
				  type:"GET",
				  url:"../news/getPage_2",
				  data:{"pageIndex": page, "pageSize":10},
				  dataType:"json",
				  async: false,
				  success:function(data)
				  {
					  $("#newslist").empty();
					 
					  data=data.data;
					  var html=' ';
					  $.each(data,function(commentIndex,comment){
						  html+='<li id="'+comment['newsId']+'" class="newsList" style="width:650px">'+
	              '<a href="#" target="_parent" class="c56628"title="'+comment['newsName']+'">'+comment['newsName']+'</a>'
	             +'<span class="c56628_date">&nbsp;'+comment["publishDate"]+';</span></li>'+
	        '<hr id="duannumu2_0" style="display:none;height:1px;border:none;border-top:1px dashed #CCCCCC">';
					  });
					  var html1='<span>'+page+'/'+last+'</span>';
					  $("#newslist").html(html);
					  $("#foot .page").html(html1);
					  $(".footF").click(function()
					    		{
					    	getNews(1);
					    		});
					    $(".footP").click(function()
					    		{
					    	if(page==1) alert("已经是第一页！");
					    	     else 
					    	    	 {page=page-1;getNews(page);}
					    		});
					    $(".footN").click(function()
					    		{
					    	      if(page==last)
					    	    	  alert("已经是最后一页！");
					    	    	  else
					    	    		  {page=page+1;
					    	    		  getNews(page);}
					    	     
					    		});
					    $(".footL").click(function()
					    		{
					    	      page=last;
					    	      getNews(page);
					    		});
					    clickB();
				  }
				  
			  });
	
	}

//获取总页数
function getTotPage()
{
	$.ajax(
			  {
				  type:"GET",
				  url:"../news/getTotalcount",
				  async: false,
				  success:function(data)
				  {
					 if(data%10==0) last=(data-data%10)/10;
					 else last=(data-data%10)/10+1;
				  }
			      
			  });
	
	
	
}

function clickB()
{
	$(".B").click(function()
			{
		
		var p=$('input.topage').val();
		getNews(p);
			});
	      
	
	$('.c56628').click(function()
		  {
	  var idnews=$(this).parent().attr("id");
	//  alert("hahahah");
	  $.ajax(
			  {
				  type:"GET",
				  url:"../news/getByID",
				  data:{"id": idnews},
				  dataType:"json",
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
					 // alert("hehehe");
				  }
			  });
	  
		  });
	
	}