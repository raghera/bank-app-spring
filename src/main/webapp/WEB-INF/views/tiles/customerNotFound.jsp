<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Not Found</title>
</head>
<body>

	<p class="page-title">Customer Not Found</p>

<div class="generic-div">

<!-- Customised Access Denied page with an appropriate message -->

	<h3 class="feedbackPanelERROR">
		<c:if test="${not empty message}">
			<c:out value="${message}"/>
		</c:if>
	</h3>


<c:url var="url" value="/customer/edit/?new"/>
<!-- <s:url var="url" value="static/j_spring_security_check" ></s:url> -->

	<a href="${url}"><button>Register</button></a>
</div>

</body>
</html>