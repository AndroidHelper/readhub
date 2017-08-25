$(function(){
	$.ajax({
		url : "listMonitorItems",
		type : "get",
		success : function(data) {
			$("#monitor").html("");
			var html = "";
			html += "<tr><td>news saver:</td><td>"+data.ns+"</td>";
			html += "<tr><td>news spider:</td><td>"+data.nsd+"</td>";
			html += "<tr><td>proxy spider:</td><td>"+data.psd+"</td>";
			html += "<tr><td>news spider queue:</td><td>"+data.nsq+"</td>";
			html += "<tr><td>news spider pool:</td><td>"+data.nsp+"</td>";
			html += "<tr><td>proxy spider pool:</td><td>"+data.psp+"</td>";
			html += "<tr><td>last saved news count:</td><td>"+data.lsnc+"</td>";
			$("#monitor").append(html);
		}
	});
});