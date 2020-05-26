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
			<c:when test="${not empty workerUp}">       
			<div class="example_content2 ">
				<h3>Worker</h3>
				<form action="${pageContext.request.contextPath}/workers" method="post">
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
						<tr>
							<td>${workerUp.workerid}</td>
							<td><input type="text" name="UpdateWorkerSurName" value=${workerUp.workerSurname}></input> </td>
							<td><input type="text" name="UpdateWorkerFirstName" value=${workerUp.workeFirstname}></input> </td>
							<td><input type="text" name="UpdateWorkerPatronomic" value=${workerUp.workerPatronomic}></input> </td>
							<td><
								<div class="seldemo">
									<select name="UpdateWorkerProfession">
									<c:choose>
									<c:when test="${workerUp.profession=='STEWARDESS'}">
										<option value="STEWARDESS" selected="selected">Стюардесса</option>
									</c:when>
									<c:otherwise>
										<option value="STEWARDESS">Стюардесса</option>
									</c:otherwise>
									</c:choose>
									<c:choose>
									<c:when test="${workerUp.profession=='NAVIGATOR'}">
										<option value="NAVIGATOR" selected="selected">Штурман</option>
									</c:when>
									<c:otherwise>
										<option value="NAVIGATOR" >Штурман</option>
									</c:otherwise>
									</c:choose>
									<c:choose>
									<c:when test="${workerUp.profession=='PILOT'}">
										<option value="PILOT" selected="selected">Пилот</option>
									</c:when>
									<c:otherwise>
										<option value="PILOT" >Пилот</option>
									</c:otherwise>
									</c:choose>
									<c:choose>
									<c:when test="${workerUp.profession=='RADIOMAN'}">
										<option value="RADIOMAN" selected="selected">Радист</option>
									</c:when>
									<c:otherwise>
										<option value="RADIOMAN" >Радист</option>
									</c:otherwise>
									</c:choose>
									</select >
								</div>
							</td>
							<td><button type="submit" name="UpdateWorker" value=${workerUp.workerid}>Update</button></td>
						</tr>
					</table>
				</form>
				<button class="open-button" onclick="openForm()">Добавить</button>
			</div>
			</c:when>
			<c:otherwise>
			<div class="example_content2 ">
				<h3>Worker</h3>
				<form action="${pageContext.request.contextPath}/workers" method="post">
					<table style="with: 50%" border = "1">
						<tr>
							<th>Surname</th>
							<th>Firstname</th>
							<th>Patronomic</th>
							<th>Profession</th>
							<th></th>
						</tr>
						<tr>
							<td><input type="text" name="NewWorkerSurName" value=""></input> </td>
							<td><input type="text" name="NewWorkerFirstName" value=""></input> </td>
							<td><input type="text" name="NewWorkerPatronomic" value=""></input> </td>
							<td>
								<div class="seldemo">
									<select name="NewWorkerProfession">
										<option value="STEWARDESS" selected="selected"> Стюардесса </option>
										<option value="NAVIGATOR" >Штурман</option>
									    <option value="PILOT" >Пилот</option>
										<option value="RADIOMAN" >Радист</option>
									</select >
								</div>
							</td>
							<td><button type="submit" name="NewWorker" >Add</button></td>
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