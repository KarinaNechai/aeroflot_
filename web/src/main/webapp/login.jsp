<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Аэрофлот</title>
        <style>
            <%@include file='style.css' %>
        </style>
    </head>
    <body>
		<div id="container" style="width:100%">
			<div id="header" style="background-color:#A3FFC2;">
				<%@include file="navigationTop.jsp" %>
			</div>
			<div id="menu" style="background-color:#f1f1f1;height:calc(100vh - 40px);width:200px;float:left;">
				<%@include file="navigationLeftEmpty.jsp" %>
			</div>
			<div id="content" style="background-color: #fff;height:200px;width:1000px;float:left;">
			<form action="${pageContext.request.contextPath}/Login" method="post">
				<table style="with: 50%">
					<tr>
						<td>Логин</td>
						<td><input id="login" type="text" name="login" /></td>
					</tr>
					<tr>
						<td>Пароль</td>
						<td><input id="password" input type="password" name="password" /></td>
					</tr>
				</table>
				<input type="submit" value="Login" /></form>
				<a href="${pageContext.request.contextPath}/registration.jsp">registration</a>
			</form>
            </div>
            <div id="footer" class="footer">
		Copyright © 2020 www.aeroflot.by
      </div>
       </div>
 </body>
</html>