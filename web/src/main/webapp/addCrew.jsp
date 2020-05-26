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
			<c:when test="${not empty CrewUp}">       
				<div class="example_content2 ">
					<h3>Crew</h3>
					<form action="${pageContext.request.contextPath}/crew" method="post">
						<table style="with: 50%" border = "1">
							<tr>
								<th>№</th>
								<th>Surname</th>
								<th>Firstname</th>
								<th>Patronomic</th>
								<th>Profession</th>
								<th></th>
								<th></th>
							</tr>
							<c:forEach items="${CrewUp.getListWorker()}" var="worker">
							<tr>
								<td>${worker.workerid}</td>
								<td>${worker.workerSurname}</td>
								<td>${worker.workeFirstname}</td>
								<td>${worker.workerPatronomic}</td>
								<td><c:choose>
									<c:when test="${worker.profession=='STEWARDESS'}">
										Стюардесса<br />
									</c:when>
									<c:when test="${worker.profession=='NAVIGATOR'}">
										Штурман<br />
									</c:when>
									<c:when test="${worker.profession=='PILOT'}">
										Пилот<br />
									</c:when>
									<c:when test="${worker.profession=='PILOT'}">
										Радист<br />
									</c:when>
									<c:otherwise>
										Уборщица<br />
									</c:otherwise>
									</c:choose>
								</td>
								<td><button type="submit" name="DeleteWorker" value=${workerUp.workerid}>Delete</button></td>
							</tr>
							<tr>
								<td><button type="submit" value=1 name="Add" >Add</button></td>
							</tr>
							</c:forEach>
						</table>
					</form>
				</div>
			</c:when>
			<c:otherwise>
			<div class="example_content2 ">
				<h3>Create crew for fligtt</h3>
				<form action="${pageContext.request.contextPath}/crew" method="post">
					<table style="with: 50%" border = "1">
						<tr>
							<td>Pilot N 1</th>
							<td><input type="text" name="Pilot1" value=""></input> </td>
						</tr>
						<tr>
							<td>Pilot N 2</th>
							<td><input type="text" name="Pilot2" value=""></input> </td>
						</tr>
						<tr>
							<td>Navigator</th>
							<td><input type="text" name="Navigator" value=""></input> </td>
						</tr>
						<tr>
							<td>Radioman</th>
							<td><input type="text" name="Radioman" value=""></input> </td>
						</tr>
						<tr>
							<td>Stwerdess N1</th>
							<td><input type="text" name="Stwerdess1" value=""></input> </td>
						</tr>
						<tr>
							<td>Stwerdess N2</th>
							<td><input type="text" name="Stwerdess2" value=""></input> </td>
						</tr>						
						<tr>
							<td><button type="submit" name="FormCrew">Form</button></td>
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