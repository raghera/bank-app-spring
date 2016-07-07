<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CustomerAccountDetailsPage</title>
</head>
<body>

	<p class="page-title">Customer Details</p>

<%-- <%Enumeration e =  request.getAttributeNames();
	while(e.hasMoreElements()) {
		System.out.println(e.nextElement().toString());
	}	
	%> --%>
	<div class="generic-div">
		<c:out default="Please see your account details below." value="${greeting}"></c:out>
	</div>

	<table id="custDetails" class="dataview">
	<tr><th>Customer Information</th><th></th></tr>
	<tr>
	<td><h3>Customer Id: </h3> </td>
	<td><c:out value="${customerImpl.customerId}"></c:out></td>
	</tr>
	<tr>
	<td><h3>FirstName: </h3> </td>
	<td><c:out value="${customerImpl.firstName}"></c:out></td>
	</tr>
	<tr>
	<td><h3>Surname: </h3> </td>
	<td><c:out value="${customerImpl.surname}"></c:out></td>
	</tr>
	<tr>
	<td><h3>E-mail/Username: </h3> </td>
	<td><c:out value="${customerImpl.email}"></c:out></td>
	</tr>	
	</table>

	<p class="page-title">Accounts Details</p>

	<table id="accDetails" class="dataview">
	<tr>
	<th>AccountId</th><th>Balance</th><th>Status</th><th>Account Type</th><th>Currency</th>
	</tr>
	<c:forEach items="${customerImpl.accountsList}" var="account">
	<tr align="center">
	<td><a href='<c:url value="/customer/accounts/transactions/${account.accountId}" ></c:url>'><c:out value="${account.accountId}"></c:out> </a> </td>
	<td><c:out value="${account.balance}"></c:out></td>
	<td><c:out value="${account.status}"></c:out></td>
	<td><c:out value="${account.accountType}"></c:out></td>
	<td><c:out value="${account.currencyId}"></c:out></td>
	
	</tr>

	</c:forEach>
	</table>

</body>
</html>