<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Easy Clients</title>
</head>
<body>
    <div align="center">    
        <h1>Easy Clients</h1>
        <h2>List of Easy Clients</h2>
        <table border="1" cellpadding="6">
            <tr>
                <th>Client ID</th>
            </tr>
            <c:forEach var="client" items="${easyClients}">
                <tr style="text-align:center">
                    <td><c:out value="${client}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>  
</body>
</html>
