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
				<%@include file="navigationLeft.jsp" %>
			</div>
			<div id="content" style="background-color: #fff;height:200px;width:1000px;float:left;">
			<c:choose>
			<c:when test="${not empty airportUp}">       
			<div class="example_content2 ">
				<h3>Airport</h3>
				<form action="${pageContext.request.contextPath}/airport" method="post">
					<table style="with: 50%" border = "1">
						<tr>
							<td>№</td>
							<td>${airportUp.id}</td>
						</tr>
						<tr>
							<td>Name</td>
							<td><input type="text" name="UpdateAirportName" value=${airportUp.name}></input> </td>
						</tr>
						<tr>
							<td><button type="submit" name="UpdateAirport" value=${airportUp.id}>Update</button></td>
						</tr>
					</table>
				</form>
				<button class="open-button" onclick="openForm()">Добавить</button>
			</div>
			</c:when>
			<c:otherwise>
			<div class="example_content2 ">
				<h3>Добавить новый аэропорт</h3>
				<form action="${pageContext.request.contextPath}/airport" method="post">
					<table style="with: 50%" border = "1">
							<td>Name</td>
							<td><input type="text" name="NewAirportName" value="" requared /> </td>
					</table>
					<button type="submit" name="NewAirport" >Add</button>
				</form>
				<button class="open-button" onclick="openForm()">Добавить</button>
			</div>
    </c:otherwise>
</c:choose>
            </div>
            <div id="footer" class="footer">
       Copyright © 2020 www.aeroflot.by
      </div>
       </div>
 </body>
</html>