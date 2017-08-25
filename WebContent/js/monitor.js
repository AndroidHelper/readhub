$(function(){
	$.ajax({
		url : "listMonitorItems",
		type : "get",
		success : function(data) {
			$("#monitor").html("");
			var html = "";
			html += "<tr><td>news saver:  </td><td>"+data.ns+"</td>";
			html += "<tr><td>news spider:  </td><td>"+data.nsd+"</td>";
			html += "<tr><td>proxy spider:  </td><td>"+data.psd+"</td>";
			$("#monitor").append(html);
		}
	});
});