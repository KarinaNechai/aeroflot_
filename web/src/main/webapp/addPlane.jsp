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
			<c:when test="${not empty planeUp}">       
			<div class="example_content2 ">
				<h3>Plane</h3>
				<form action="${pageContext.request.contextPath}/planes" method="post">
					<table style="with: 50%" border = "1">
						<tr>
							<th>№</th>
							<th>Plane Name</th>
							<th>Capacity</th>
							<th>Range</th>
							<th></th>
						</tr>
						<tr>
							<td>${planeUp.planeId}</td>
							<td><input type="text" name="UpdatePlaneName" value=${planeUp.planeName}></input> </td>
							<td><input type="text" name="UpdateCapasity" value=${planeUp.capacity}></input> </td>
							<td><input type="text" name="UpdateRange" value=${planeUp.range}></input> </td>
							<td><button type="submit" name="UpdatePlane" value=${planeUp.planeId}>Update</button></td>
						</tr>
					</table>
				</form>
				<button class="open-button" onclick="openForm()">Добавить</button>
			</div>
			</c:when>
			<c:otherwise>
			<div class="example_content2 ">
				<h3>Plane</h3>
				<form action="${pageContext.request.contextPath}/planes" method="post">
					<table style="with: 50%" border = "1">
						<tr>
							<th>Plane Name</th>
							<th>Capacity</th>
							<th>Range</th>
							<th></th>
							<th></th>
						</tr>
						<tr>
							<td><input type="text" name="NewPlaneName" value=""></input> </td>
							<td><input type="text" name="NewCapasity" value=""></input> </td>
							<td><input type="text" name="NewRange" value=""></input> </td>
							<td><button type="submit" name="NewPlane" >Add</button></td>
						</tr>
					</table>
				</form>
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