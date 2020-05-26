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
					<h3>Users</h3>
						<form action="${pageContext.request.contextPath}/Admin" method="post">
							<table border = "1">
								<tr>
									<th>Фамилия</th>
										<th>Имя</th>
										<th>Email</th>
										<th>Телефон</th>
										<th></th>
									</tr>                           
									<c:forEach items="${users}" var="user">
									<tr>
										<td> ${user.firstName}</td>
										<td> ${user.lastName}</td>
										<td> ${user.email}</td>
										<td> ${user.phone}</td>
										<td><button type="submit" name="Delete" value=${user.userId}>Delete</button></td>
									</tr>
									</c:forEach>
									<tr>
                                        <ul class="pagination">
                                            <c:if test="${currentPage != 1}">
                                    	        <li><a href="<%=request.getContextPath()%>/admin?page=${currentPage - 1}">Previous</a></li>
                                            </c:if>
                                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <li><a class="active" href="<%=request.getContextPath()%>/admin?page=${i}">${i}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                    	                <li><a href="<%=request.getContextPath()%>/admin?page=${i}">${i}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <c:if test="${currentPage lt noOfPages}">
                                    	        <li><a href="<%=request.getContextPath()%>/admin?page=${currentPage +1}">Next</a></li>
                                            </c:if>
                                        </ul>
                                    </tr>
							</table>
						</form>
				</div>
            <div id="footer" class="footer">
       Copyright © 2020 www.aeroflot.by
      </div>
	  </div>
       </div>
 </body>
</html>