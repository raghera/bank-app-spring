<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<div class="generic-div">
	<h3>Welcome.  Please log into your account:</h3><br/>
	
	<%-- <%Enumeration e =  request.getAttributeNames();
	while(e.hasMoreElements()) {
		System.out.println(e.nextElement().toString());
	}	
	%>
	 --%>
	<%-- <s:url var="authUrl" 
          value="/static/j_spring_security_check" /><!--<co id="co_securityCheckPath"/>--> --%>
	
<div class="generic-div">
 	<c:if test="${not empty loginError and 'fail' eq loginError}" >
		<p class="feedbackPanelERROR">${loginErrorMessage} Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
		
	</c:if>
	<c:if test="${not empty logoutMessage}" >
		<p class="feedbackPanelERROR">${logoutMessage} </p>
	</c:if>
</div>

<s:url var="authUrl" value="static/j_spring_security_check" ></s:url>
<form method="post" class="signin" action="${authUrl}">

	<label class="generic-label" for="email" >E-mail</label>
	<input id="email" name="j_username" type="text">
	<label class="generic-label" for="password">Password</label>
	<input id="password" name="j_password" type="password">
	<input type="submit" value="Login" name="commit">

</form>
<script type="text/javascript">
	document.getElementById('email').focus();
</script>
	
</div>
	<div><p>Not registered?  Register as a new Customer <a href="customer/edit/?new">here</a></p></div>
	
		<%-- HomeController sets up this response data by returning a model with the key "newCustomer" 
		which is what you reference here.
		--%>
	<%-- ID: <c:out value="${newCustomer.customerId}"></c:out><br/>
	Type: <c:out value="${newCustomer.customerType}"></c:out><br/>
	FirstName: <c:out value="${newCustomer.firstName}"></c:out><br/>
	Surname: <c:out value="${newCustomer.surname}"></c:out><br/>
	Email: <c:out value="${newCustomer.email}"></c:out><br/>
	countryCode: <c:out value="${newCustomer.countryCode}"></c:out><br/>
	 --%>
	
</body>
</html>