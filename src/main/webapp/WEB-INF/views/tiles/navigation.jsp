<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="email"/>
<c:url var="custDetails" value="/customer/accounts/"></c:url>
<s:url var="logoutUrl" value="/static/j_spring_security_logout" ></s:url>
</sec:authorize>


<!-- Turn these to links depending on what page you are on -->
<ul class="menu-left">
  <li><h3><a href='<c:url value="/home"></c:url>'>	Home: </a> </h3>  </li>

<sec:authorize access="isAuthenticated()">
  <li><h3><a href='<c:out value="${custDetails}?email=${email}"></c:out>'> Customer Details: </a></h3></li> 
  <li><h3><a href='<c:url value="${logoutUrl}"/>'> Logout: </a> </h3></li>
</sec:authorize>
  
</ul>


	
	
	
	

</body>
</html>