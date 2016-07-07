<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account Transactions View</title>
</head>
<body>

	<p class="page-title">Account Transactions</p>

	<!-- Only 1 Account being viewed here -->
	
	<table id="accDetails" class="dataview">
	<tr>
	<td><h3>Account Id: </h3> </td>
	<td><c:out value="${account.accountId}"></c:out></td>
	</tr>
	<tr>
	<td><h3>Balance: </h3> </td>
	<td><c:out value="${account.balance}"></c:out></td>
	</tr>
	<tr>
	<td><h3>Status: </h3> </td>
	<td><c:out value="${account.status}"></c:out></td>
	</tr>
	<tr>
	<td><h3>Type: </h3> </td>
	<td><c:out value="${account.accountType}"></c:out></td>
	</tr>
	<tr>
	<td><h3>Currency: </h3> </td>
	<td><c:out value="${account.currencyId}"></c:out></td>
	</tr>	
	</table>
	
	<table id="accDetails" class="dataview">
	<tr>
	<th>TransactionId</th><th>Balance</th><th>Status</th><th>Account Type</th><th>Currency</th>
	</tr>
	<c:if test="${empty transactionList}">
		<h2 style="padding: 10px;">There are currently no transactions relating to this account</h2>
	</c:if>
	<c:forEach items="${transactionList}" var="transaction">
	<tr align="center">
	<td><c:out value="${transaction.transactionId}"></c:out></td>
	<td><c:out value="${transaction.amount}"></c:out></td>
	<td><c:out value="${transaction.account.accountId}"></c:out></td>
	<td><c:out value="${transaction.timestamp}"></c:out></td>
	</tr>
	</c:forEach>
	</table>
	
	
	<br>
	<a href='<c:url value="/customer/edit/${email}" ></c:url>'>
		Return to Customer Details Page. 
	</a> 
	

</body>
</html>