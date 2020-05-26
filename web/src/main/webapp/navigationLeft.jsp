<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.sidenav {
  height: 100%;
  width: 160px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  padding-top: 20px;
}

.sidenav a {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
}

.sidenav a:hover {
  color: #f1f1f1;
}
</style>
<form action="${pageContext.request.contextPath}/navigation" method="post">
       					<ul id="navbarV">
       						<li><a href="<%=request.getContextPath()%>/flights">Flights</a></li>
       <!--						<li><a href="<%=request.getContextPath()%>/crew">Crew</a></li> -->
       						<li><a href="<%=request.getContextPath()%>/workers">Workers</a></li>
       						<li><a href="<%=request.getContextPath()%>/planes">Plane</a></li>
       						<li><a href="<%=request.getContextPath()%>/airport">Airport</a></li>
       					</ul>
       </form>