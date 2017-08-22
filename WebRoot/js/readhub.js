
$(function(){
	$.ajax({
		url : "news",
		type : "get",
		success : function(data) {
			$("#newstable").html("");
			$.each(data, function(i, news) {
				var html ="";
				html += "<tr><td><a href='"+news.url+"'>"+news.title+"</a></td></tr>";
				html += "<tr><td>"+news.brief+"</td></tr>";
				html += "<tr><td>来源：<a href='"+news.siteAddr+"'>"+news.siteName+"</a></td></tr>";
				$("#newstable").append(html);
			});
		}
	});
});