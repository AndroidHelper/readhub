$(function() {
	$.ajax({
		url : "listMonitorItems",
		type : "get",
		success : function(data) {
			$("#monitor").html("");
			var html = "";
			html += "<tr><td>news saver:</td><td>" + data.ns + "</td></tr>";
			html += "<tr><td>news spider:</td><td>" + data.nsd + "</td></tr>";
			html += "<tr><td>proxy spider:</td><td>" + data.psd + "</td></tr>";
			html += "<tr><td>ip locater:</td><td>" + data.ip + "</td></tr>";			
			html += "<tr><td>news spider queue:</td><td>" + data.nsq + "</td></tr>";
			html += "<tr><td>news spider pool:</td><td>" + data.nsp + "</td></tr>";
			html += "<tr><td>proxy spider pool:</td><td>" + data.psp + "</td></tr>";
			html += "<tr><td>last saved news count:</td><td>" + data.lsnc + "</td></tr>";
			
			$("#monitor").append(html);
			$.each(data.visitor, function(i, v) {
				$("#monitor").append("<tr><td>visitors today:</td><td>ip: " + v.ip + " addr: " + v.addr + " count: " + v.count + "</td></tr>");
			});
		}
	});
});