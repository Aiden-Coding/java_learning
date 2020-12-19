<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Form表单</title>
<script type="text/javascript">
	var flag = false;// 没有点击 true点击
	function isFlag() {
      
		if(!flag){
			flag=true;//标识已经点击
			return true;
		}
		return false;
   
	}
</script>
</head>

<body>
	<form action="${pageContext.request.contextPath}/DoFormServlet"
		method="post" onsubmit="return isFlag()">
	<input type="hidden" value="${sessionToken}" name="sessionToken">
		用户名：<input type="text" name="userName"> <input  on type="submit"
			value="提交"   id="submit">
	</form>
</body>
</html>
