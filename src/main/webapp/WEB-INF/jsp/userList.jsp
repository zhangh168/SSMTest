<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	function addUser(){
		var username = $("#username").val();
		var password = $("#password").val();
		
		if(username == '' || password == ''){
			alert("参数不能为空");
			return;
		}
		
		$.ajax({
			type:"post",
			url:getWebRootPath()+'/user/addUser.do',
			data:{'username':username,'password':password},
			success:function(data){
				var json = eval("(" + data + ")");// 将数据转换成json类型
				if(json.stateCode==1){
					alert("新增成功");
					window.location.href=getWebRootPath()+"/user/getUserList.do"
				}else if(json.stateCode==2){
					alert("参数错误");
				}else{
					alert("新增失败");
				}
			},error:function(){
				alert("因为网络原因，加载失败，请稍后重试");
			}
		});
	}
	
	function getWebRootPath() {
	    var webroot=document.location.href;
	    webroot=webroot.substring(webroot.indexOf('//')+2,webroot.length);
	    webroot=webroot.substring(webroot.indexOf('/')+1,webroot.length);
	    webroot=webroot.substring(0,webroot.indexOf('/'));
	    var rootpath="/"+webroot;
	    return rootpath;
	}
</script>
</head>
<body>
	<h2>列表</h2>
	<table border="1" width="80%">
		<tr>
			<td>id</td>
			<td>用户名</td>
			<td>密码</td>
		</tr>
		<c:forEach items="${userList }" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.username }</td>
				<td>${user.password }</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<hr />
	<br />
	<h2>新增用户</h2>
	<form action="${pageContext.request.contextPath}/user/addFormUser.do" method="post">
		<table border="1" width="80%">
			<tr>
				<td>用户名</td>
				<td><input id="username" name="username"/></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input id="password" name="password"/></td>
			</tr>
			<tr>
				<td >
					<a href="javascript:void();" onclick="addUser();">异步新增</a>
				</td>
				<td >
					<input type="submit" value="form表单新增提交">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>