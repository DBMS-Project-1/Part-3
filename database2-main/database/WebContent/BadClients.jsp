<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bad Clients</title>
</head>
<body>
    <div align="center">  
        <h1>Bad Clients</h1>
    </div>  
    
    <div align="center">
        <h1>Bad Clients List</h1>
        
        <table border="1" cellpadding="6">
            <tr>
                <th>Client ID</th>
            </tr>
            <c:forEach var="clientid" items="${badClients}">
                <tr style="text-align:center">
                    <td><c:out value="${clientid}" /></td>
                </tr>
            </c:forEach>
        </table>

    </div>
</body>
</html>
