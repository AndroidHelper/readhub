$(function() {
	$.ajax({
		url : "listMonitorItems",
		type : "get",
		success : function(data) {
			$("#monitor").html("");
			var html = "";
			html += "<tr><td>news spider:</td><td>" + data.nsd + "</td></tr>";
			html += "<tr><td>ip locater:</td><td>" + data.ip + "</td></tr>";			
			html += "<tr><td>news spider queue:</td><td>" + data.nsq + "</td></tr>";
			html += "<tr><td>news spider pool:</td><td>" + data.nsp + "</td></tr>";
			$("#monitor").append(html);			
			
			$("#monitor").append("<tr><td>Proxy statis(Last 24 Hours):<td></tr>");
			
			$.each(data.psList, function(i, p) {
				$("#monitor").append("<tr><td>Proxy Site:"+p.siteName+"</td><td>Total:"+p.totalCount+"</td><td>Valid:"+p.validCount+"</td><td>Valid Rate:"+p.validRate*100+"%</td></tr>");
			});
			
			$.each(data.visitor, function(i, v) {
				$("#monitor").append("<tr><td>visitors today:</td><td>ip: " + v.ip + " addr: " + v.addr + " count: " + v.count + "</td></tr>");
			});
			
		}
	});
});