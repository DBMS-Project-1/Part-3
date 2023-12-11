<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	<a href="login.jsp"target ="_self" > logout</a><br><br> 
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>

	
	<form action = "Big clients">
		<input type = "submit" value = "Big clients"/>
	</form>
	
	<form action = "Easy clients">
		<input type = "submit" value = "Easy clients"/>
	</form>
	
	<form action = "One tree quotes">
		<input type = "submit" value = "One tree quotes"/>
	</form>
	
	<form action = "Prospective clients">
		<input type = "submit" value = "Prospective clients"/>
	</form>
	
	<form action = "Highest tree">
		<input type = "submit" value = "Highest tree"/>
	</form>
	
	<form action = "Overdue bills">
		<input type = "submit" value = "Overdue bills"/>
	</form>
	
	<form action = "Bad clients">
		<input type = "submit" value = "Bad clients"/>
	</form>
	
	<form action = "Good clients">
		<input type = "submit" value = "Good clients"/>
	</form>
	
	<form action = "Statistics">
		<input type = "submit" value = "Statistics"/>
	</form>

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Password</th>
                <th>Credit Card Numbers</th>

            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.creditCardNum}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>