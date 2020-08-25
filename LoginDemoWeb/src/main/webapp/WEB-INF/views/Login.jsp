<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login</title>
	</head>
	<body>	
        <f:form id="loginForm" method="POST" modelAttribute="loginBean">
        	<table cellspacing="0">
				<tr>
					<th>Bucik Login</th>
				</tr>
				<tr>
				</tr>
				<tr>
            		<td><f:label path="username">Enter your user-name:</f:label></td>
            		<td><f:input path="username" />&nbsp;<f:errors path="username" cssClass="error"/></td>
            	</tr>
            	<tr>
            		<td><f:label path="password">Please enter your password:</f:label></td>
            		<td><f:password path="password" />&nbsp;<f:errors path="password" cssClass="error"/></td>
            	</tr>
            	<tr>
            		<td><input type="submit" value="Submit"/>&nbsp;&nbsp;<a href="user/new">New User</a><td>
            	</tr>
				<tr>
					<th><c:if test="${not empty msg}"><c:out value="${msg}"/></c:if></th>
				</tr>
            </table>
        </f:form>
    </body>
</html>

