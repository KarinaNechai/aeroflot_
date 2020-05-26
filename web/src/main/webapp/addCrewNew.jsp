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
					<c:when test="${not empty flightWorkersAdd}">
						<div class="example_content2 ">
							<h3>Crew of flight N "${flightWorkersAdd.getFlightId()}"</h3>
							<form action="${pageContext.request.contextPath}/crew" method="post">
								<table style="with: 50%" border = "1">
									<tr>							
										<td>Pilot </td>
										<td>
											<input list="Pilots" name="Pilot" />
												<datalist id="Pilots">
													<c:forEach items="${listPilot}" var="worker">
														<option value=${worker.workerid}> ${worker.getFullName()} </option>
													</c:forEach>
												</datalist>
										</td>
									</tr>						
									<tr>							
										<td>Radioman </td>
										<td>
											<input list="Radiomans" name="Radioman" />
												<datalist id="Radiomans">
													<c:forEach items="${listRadioman}" var="worker">
														<option value=${worker.workerid}> ${worker.getFullName()} </option>
													</c:forEach>
												</datalist>											
										</td>
									</tr>
									<tr>							
										<td>Navigator </td>
										<td>
										<input list="Navigators" name="Navigator" />
												<datalist id="Navigators">
													<c:forEach items="${listNavigator}" var="worker">
														<option value=${worker.workerid}> ${worker.getFullName()} </option>
													</c:forEach>
												</datalist>											
										</td>
										</td>
									</tr>
									<tr>							
										<td>Stewardess</td>
										<td>
										<input list="Stewardesses" name="Stewardess" />
												<datalist id="Stewardesses">
													<c:forEach items="${listStewardess}" var="worker">
														<option value=${worker.workerid}> ${worker.getFullName()} </option>
													</c:forEach>
												</datalist>											
										</td>
									</tr>
									<tr>
										<td><button type="submit" name="Create" value="${flightWorkersAdd.getFlightId()}"> Create</button></td>
									</tr>									
								</table>
							</form>
						</div>			
			</c:when>
			<c:when test="${not empty flightWorkersView}">
			
			<div class="example_content2 ">
				<h3>Crew of flight N "${flightWorkersView.getFlightId()}"</h3>
				<form action="${pageContext.request.contextPath}/crew" method="post">
					<table style="with: 50%" border = "1">
						<tr>
							<c:forEach items="${listPilot}" var="worker">
								<td>Pilot </td>											
								<td> ${worker.getFullName()} </td>
							</c:forEach>							
						</tr>
						<tr>
							<c:forEach items="${listNavigator}" var="worker">
								<td>Navigator</td>
								<td> ${worker.getFullName()} </td>									
							</c:forEach>
						</tr>
						<tr>
						<	c:forEach items="${listRadioman}" var="worker">
								<td>Radioman</td>
								<td> ${worker.getFullName()} </td>									
							</c:forEach>
						</tr>
						<tr>
							<c:forEach items="${listStewardess}" var="worker">
								<td>Stwerdess </td>
								<td> ${worker.getFullName()} </td>									
							</c:forEach>
						</tr>
						<tr>
							<td><button type="submit" name="Continue" value=2> Continue </button></td>
						</tr>
					</table>
				</form>
			</div>
			</c:when>
			</c:choose>
            </div>
            <div id="footer" class="footer">
       Copyright © 2020 www.aeroflot.by
      </div>
       </div>
 </body>
</html>