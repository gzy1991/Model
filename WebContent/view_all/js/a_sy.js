
$(function()
{
	$(".navigationBar ul").hide();
	$(".navigationBar").hover(function()
	{
      $(this).addClass("navigationBar1").children().slideDown();
      $(this).find("a").css("color","#FFFFFF");
	},function()
	{
		$(this).find("a").css("color","#000000");
		$(this).children("ul").hide();
		$(this).removeClass("navigationBar1");
	})
})//导航栏

$(function()
{
	
	$(".ab").hover(function()
	{
      $(this).addClass("ab2");
	},function()
	{
		
		$(this).removeClass("ab2");
	})
})//侧导航栏鼠标划过效果

var num=1;
function lunbo()
	{
      if(num>3)
      	num=1;
    
      $(".pictures").fadeOut().attr("src","images/pic"+num+".jpg").fadeIn();
      $(".pictureDiv ul li").eq(num-1).removeClass("on");
      $(".pictureDiv ul li").eq(num%3).addClass("on");
      num++;
	}

$(function()
{
	setInterval("lunbo()",3000);

})



$(function ()
{
	$(".navigationBar2 li").mouseover(function ()
	{
		$(this).addClass("navigationBar3");
	}).mouseout(function ()
	{
		$(this).removeClass("navigationBar3");
	})
})


