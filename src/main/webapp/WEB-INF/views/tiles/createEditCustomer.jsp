<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create / Edit Customer</title>
</head>
<body>

<div class="generic-div">
<sf:form method="POST" modelAttribute="customerImpl">
	<fieldset>

				<p class="page-title">Create Customer</p>
				
				<table class="dataview">
					<tr>
						<td><label for="user_firstname">First Name</label></td>
						<td>
						<sf:errors element="div" path="firstName" cssClass="feedbackPanelERROR" delimiter=", <br/>"/>
						<sf:input id="user_first_name" path="firstName" size="25" maxlength="25"/>
						</td>
						
					</tr>
					<tr>
						<td><label for="user_surname">Surname</label></td>
						<td>
						<sf:errors element="div" path="surname" cssClass="feedbackPanelERROR"/>
						<sf:input id="user_surname" path="surname" size="25" maxlength="25" />
						</td>
					</tr>
					<tr>
						<td><label for="user_email">E-mail Address</label></td>
						<td>
						<sf:errors element="div" path="email" cssClass="feedbackPanelERROR"/>
						<sf:input id="user_email"  path="email" size="25" maxlength="25" />
						</td>
					</tr>
					<tr>
						<td><label for="user_country">Country Code</label></td>
						<td>
						<sf:errors element="div" path="countryCode" cssClass="feedbackPanelERROR"/>
						<sf:input id="user_country"  path="countryCode" size="25" maxlength="25" />
						</td>
					</tr>
					
					<tr>
						<td><label for="user_password">Password </label><small>---Be tricky!</small></td>
						
						<td><sf:input id="user_password"  path="password" size="25" maxlength="25" /></td>
					</tr>
					
					<tr>
						<td><input type="submit" value="Save Details"/></td>
					</tr>

				</table>

			</fieldset>

</sf:form>

</div>

</body>
</html>