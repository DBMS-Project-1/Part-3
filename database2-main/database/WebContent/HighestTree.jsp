<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Highest Trees</title>
</head>
<body>
    <div align="center">    
        <h1>Highest Trees</h1>
        <h2>List of Highest Trees</h2>
        <table border="1" cellpadding="6">
            <tr>
                <th>Tree ID</th>
            </tr>
            <c:forEach var="tree" items="${highestTrees}">
                <tr style="text-align:center">
                    <td><c:out value="${tree}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>  
</body>
</html>
