<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Overdue Bills</title>
</head>
<body>
	<div align = "center">	
<h1>Overdue Bills</h1>

</div>	
	
	
    <div align="center">
        
        <h1>List of Overdue Bills</h1>
        <table border="1" cellpadding="6">
            <tr>
                <th>Bill ID</th>
            </tr>
            <c:forEach var="id" items="${overdueBills}">
                <tr style="text-align:center">
                    <td><c:out value="${id}" /></td>
                </tr>
            </c:forEach>
        </table>

    </div>
</body>
</html>