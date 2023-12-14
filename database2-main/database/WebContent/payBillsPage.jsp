<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Request a Quote</title>
</head>
<body>
    <div align = "center">    
        <h1>List all Replies</h1>

        <a href="login.jsp"target ="_self" > logout</a><br><br> 
    </div>  
    
    <div align="center">
        <h1>My Bills</h1>
        <table border="1" cellpadding="5">
            <caption><h2>List of Bills</h2></caption>
            <tr>
                <th>Bill ID</th>
                <th>Quote ID</th>
                <th>Amount Due</th>
                <th>Amount Paid</th>
                <th>Payment Date</th>
                <th>Bill Generated Date</th>
            </tr>
            <c:forEach var="bill" items="${listBill}">
                <tr style="text-align:center">
                    <td><c:out value="${bill.id}" /></td>
                    <td><c:out value="${bill.quoteId}" /></td>
                    <td><c:out value="${bill.amountDue}" /></td>
                    <td><c:out value="${bill.amountPaid}" /></td>
                    <td><c:out value="${bill.paymentDate}" /></td>
                    <td><c:out value="${bill.billGeneratedDate}" /></td>
                </tr>
            </c:forEach>
        </table>
        
        <h1>Pay a bill</h1>
        <form action="payBill" method="post">
            <label for="bill">Bill ID:</label>
            <input type="number" id="billId" name="id" required><br><br>
            <label for="bill">Amount to Pay:</label>
            <input type="number" id="billPay" name="amountPaid" required><br><br>
            <label for="bill">Payment Date (yyyy-mm-dd):</label>
            <input type="datetime" id="paymentDate" name="date" required><br><br>
            
            <input type="submit" value="Submit Payment">
        </form>
        
    </div>
</body>
</html>
