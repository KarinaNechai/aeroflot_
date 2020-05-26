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
			<div class="example_content2 ">
		<h3>Regisration</h3>
		<form action="${pageContext.request.contextPath}/registration" method="post">
			<table style="with: 50%">
				<tr>
					<td>Фамилия</td>
					<td><input type="text" name="firstName" required/></td>
				</tr>
				<tr>
					<td>Имя</td>
					<td><input type="text" name="lastName" /></td>
				</tr>
				<tr>
					<td>e-mail</td>
					<td><input type="text" name="email" required pattern="\S+@[a-z]+.[a-z]+" /></td>
				</tr>
				<tr>
					<td>Телефон</td>
					<td><input type="text" name="telephone" /></td>
				</tr>
				<tr>
					<td>Логин</td>
					<td><input type="text" name="login" required /></td>
				</tr>
				<tr>
					<td>Пароль</td>
					<td><input type="password" name="password" required /></td>
				</tr>
				<tr>
					<td><button type="submit" name="Save"> Зарегистрироваться</button></td>
					<td><button type="submit" name="Cancel" >Отмена</button></td>
				</tr>
			</table>
		</form>
						</div>
            </div>
            <div id="footer" class="footer">
       Copyright © 2020 www.aeroflot.by
      </div>
       </div>
 </body>
</