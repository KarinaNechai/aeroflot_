<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
    <meta charset="utf-8">
   <title>Аэрофлот</title>
    <style type="text/css">
      #navbar ul{
        display: none;
        background-color: #f90;
        position: absolute;
        top: 100%;
      }
      #navbar li:hover ul { display: block; }
      #navbar, #navbar ul{
        margin: 0;
        padding: 0;
        list-style-type: none;
      }
      #navbar {
        height: 30px;
        background-color: #666;
        padding-left: 25px;
        min-width: 470px;
      }
      #navbar li {
        float: left;
        position: relative;
        height: 100%;
      }
      #navbar li a {
        display: block;
        padding: 6px;
        width: 100px;
        color: #fff;
        text-decoration: none;
        text-align: center;
      }
	#navbarV ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 25%;
    background-color: #f1f1f1;
    position: fixed;
    height: 100%;
    overflow: auto;
}

#navbarV li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}

#navbarV li a.active {
    background-color: #4CAF50;
    color: white;
}

#navbarV li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
	.footer {
 	height: 40px;
	background-color:#A3FFC2;
	clear:both;
	text-align:center;

	.example_content {
        min-height: calc(100vh - px);
 	    width: 33.00%;
		float: left;
      }
	.example_content2 {
        min-height: calc(100vh - 40px);
 	    width: 6733.00%;
		float: right;
      }
}
 {box-sizing: border-box;}
 table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: #4CAF50;
    color: white;
}
/* Кнопка, используемая для открытия формы чата - закреплена в нижней части страницы */
.open-button {
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 23px;
  right: 28px;
  width: 280px;
}

/* Всплывающий чат - скрыт по умолчанию */
.airopor-popup {
  display: none;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}

/* Добавление стилей в контейнер формы */
.form-container {
  max-width: 300px;
  padding: 10px;
  background-color: white;
}

/* Полноразмерная текстовая область */
.form-container input {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
  resize: none;
  min-height: 100px;
}

/* Когда текстовая область получит фокус, сделайте что-нибудь */
.form-container textarea:focus {
  background-color: #ddd;
  outline: none;
}

/* Установите стиль для кнопки отправить/кнопка */
 .form-container .btn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}

/* Добавьте красный цвет фона к кнопке отмена */
.form-container .cancel {
  background-color: red;
}

/* Добавьте некоторые эффекты наведения на кнопки */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}
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