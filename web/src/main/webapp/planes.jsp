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
			<div class="example_content2 ">
				<h3>Planes</h3>
				<form action="${pageContext.request.contextPath}/planes" method="post">
					<table style="with: 50%" border = "1">
						<tr>
							<th>№</th>
							<th>Plane Name</th>
							<th>Capacity</th>
							<th>Range</th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach items="${listPlanes}" var="plane">
						<tr>
							<td>${plane.planeId}</td>
							<td>${plane.planeName} </td>
							<td>${plane.capacity} </td>
							<td>${plane.range} </td>
 							<td><button type="submit" name="Delete" value=${plane.planeId}>Delete</button></td>
							<td><button type="submit" name="Update" value=${plane.planeId}>Update</button></td>
						</tr>
						</c:forEach>
						<tr>
							<td><button type="submit" name="Add">Add</button></td>
						</tr>
						<tr>
                            <ul class="pagination">
                                <c:if test="${currentPage != 1}">
                        	        <li><a href="<%=request.getContextPath()%>/planes?page=${currentPage - 1}">Previous</a></li>
                                </c:if>
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li><a class="active" href="<%=request.getContextPath()%>/planes?page=${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                        	                <li><a href="<%=request.getContextPath()%>/planes?page=${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${currentPage lt noOfPages}">
                        	        <li><a href="<%=request.getContextPath()%>/planes?page=${currentPage +1}">Next</a></li>
                                </c:if>
                             </u1>
                        </tr>
					</table>
	            </form>
		    </div>
            <div id="footer" class="footer">
       Copyright © 2020 www.aeroflot.by
      </div>
       </div>
 </body>
</html>