$(function() {
	$.init();
        
        $(".header-guest div, .header-user div").hover(function(){
		$(this).addClass("icon-hover");
	},function(){
		$(this).removeClass("icon-hover");
	});
        
        $(".board-m, .sub-board-m").hover(function(){
		$(this).addClass("board-hover");
	},function(){
		$(this).removeClass("board-hover");
	});
        $("#bottom-item-close").live("click",function(){
		$(this).closest(".bottom-item").addClass("hidden");
	});
        $(".header-title").live("click",function(){
		location.reload();
	});
});