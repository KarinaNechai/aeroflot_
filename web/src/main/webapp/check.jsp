<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <title>Balance</title>
    <style type="text/css">
      .example_main_div div {
        border: 1px solid black;
        margin: 1px;
        padding: 1px;
      }
      .example_content2 {
        min-height: calc(100vh - 70px);
 	    width: 67.00%;
		float: right;
      }
      .example_content {
        min-height: calc(100vh - 70px);
 	    width: 33.00%;
		float: left;
      }
	.content {
  	min-height: calc(100vh - 70px);
	}
	.footer {
 	height: 70px;
	background-color: green;
}
    </style>
  </head>
	<body>
		<div class="example_content">
			<h3>Введите данные нового чека</h3>
			<form action="${pageContext.request.contextPath}/check" method="post">
				<table style="with: 50%" border = "1">
				<tr>
					<td>
						<input type="radio" name="rdo" value="REVENUE">Приход<br>
					</td>
					<td>
						<input type="radio" name="rdo" value="EXPENCES" checked>Расход<br>
					</td>
				</tr>
				<tr>
					<td>
						Дата операции:
					</td>
					<td>
					    <c:set var = "now" value = "<% = new java.util.Date()%>" />
						<input 'fmt:formatDate type="date" value="${now}" pattern="dd.MM.yyyy" required id="date" name="date"'/>
					</td>
				</tr>
				<tr>
					<td>Сумма:</td>
					<td><input type="number" required name="sum" value="0" /></td>
				</tr>
				<tr>
					<td>Комментарий:</td>
					<td><input type="text" name="text" value="0"/></td>
				</tr>
				<tr>
					<td><button type="submit" name="AddCheck" >Добавить</button></td></td>
				</tr>
			</table>
			</form>
		</div>
		<div class="example_content2 ">
		<h3>Дневник доходов и расходов</h3>
		<form action="${pageContext.request.contextPath}/check" method="post">
				<table style="with: 50%" border = "1">
				<tr>
					<td>№</th>
					<td>Дата</th>
					<td>Тип</th>
					<td>Сумма</th>
					<td>Комментарий</th>
				</tr>   
	<c:forEach items="${listCheck}" var="check">
				<tr>
					<td> ${check.number}</td>
                    <td><fmt:formatDate  value="${check.date}" pattern="dd.MM.yyyy"/></td>
                    <td><c:choose>
                            <c:when test="${check.typeOfCheck=='REVENUE'}">
                                Приход
                                <br />
                            </c:when>
                            <c:otherwise>
                               Расход
                                <br />
                            </c:otherwise>
                        </c:choose>
                    </td>
					<td> ${check.sum}</td>
					<td> ${check.text}</td>
<td><button type="submit" name="Delete" value=${check.number}>Delete</button></td>
</tr>
</c:forEach>
</table>
<table border = "1">
	<tr>
		<td style="color: green"> Итого (приход)</td>
		<td style="color: green"><c:out value="${totalRevenue}"/> </td>
	</tr>
	<tr>
		<td style="color: green"> Итого (расход)</td>
		<td style="color: green"><c:out value="${totalExpences}"/> </td>
	</tr>
	
</table>

			</form>
		</div>
		<div >
<table border = "1">

</table>
</div>
<div class="footer"
<footer>
<form action="${pageContext.request.contextPath}/check" method="post">
<button type="submit" name="Logout" >Logout</button>
</form>
</footer>
</div>
 </body>
</html>