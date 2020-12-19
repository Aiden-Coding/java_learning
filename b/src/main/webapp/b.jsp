<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>B网站访问</title>
</head>
<script type="text/javascript"
	src="http://www.itmayiedu.com/static/common/jquery-1.7.2.min.js?t=2017-07-27"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			type : "get",
			async : false,
			url : "http://b.b.com/b/ToFromServlet?userName=644064",
			dataType : "json",
			success : function(data) {
				alert("获取结果:"+data["userName"]);
			},
			error : function() {
				alert('fail');
			}
		});

	});
</script>

<body>
	<img alt="" src="http://a.a.com/a/imgs/log.png">
</body>
</html>