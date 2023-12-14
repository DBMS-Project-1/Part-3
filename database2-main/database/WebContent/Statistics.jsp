<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Statistics</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div align="center">	
        <h1>Statistics</h1>
        <table>
            <tr>
                <th>Client ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Total Trees Cut</th>
                <th>Total Due Amount</th>
                <th>Total Paid Amount</th>
                <th>Work Done Date</th>
            </tr>
            <c:forEach var="bill" items="${statisticsList}">
                <tr>
                    <td><c:out value="${bill.clientId}" /></td>
                    <td><c:out value="${bill.firstName}" /></td>
                    <td><c:out value="${bill.lastName}" /></td>
                    <td><c:out value="${bill.totalTreesCut}" /></td>
                    <td><c:out value="${bill.amountDue}" /></td>
                    <td><c:out value="${bill.amountPaid}" /></td>
                    <td><c:out value="${bill.billGeneratedDate}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
