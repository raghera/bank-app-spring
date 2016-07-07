<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Http 403 Access Denied</title>
</head>
<body>

<p class="page-title">HTTP 403 - Access denied</p>

<div class="generic-div">

<!-- Customised Access Denied page with an appropriate message -->

	<h3 class="feedbackPanelERROR">
		<c:if test="${not empty message}">
			<c:out value="${message}"/>
		</c:if>
	</h3>

</div>

</body>
</html>